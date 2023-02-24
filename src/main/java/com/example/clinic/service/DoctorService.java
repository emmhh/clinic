package com.example.clinic.service;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Patient;
import com.example.clinic.model.Reminder;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class DoctorService {
    private DoctorRepository doctorRepository;
    private PatientRepository patientRepository;
    private ReminderRepository reminderRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository,
                         ReminderRepository reminderRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.reminderRepository = reminderRepository;
    }
    public List<Reminder> getPatientsStatus(Long did){
        Optional<Doctor> doc = doctorRepository.findById(did);
        List<Reminder> reminderList = reminderRepository.findALLByDid(doc);
        return reminderList;
    }
    public List<Doctor> getDoctors(){
        return doctorRepository.findDoctors();
    }

    public List<Doctor> listDoctors(){
        List<Doctor> l = doctorRepository.findAll();
        return l;
    }
//    Provides meta-date for dashboard, outdated are also considered as unfinished.
//    {patient1: [0, 0, 0], patient2:[0, 0, 0]}
//    TODO change the data structure for sorted result.
    public Hashtable<String, List<Integer>> getDashboardByDid(Long did){
        int lowIndex = 0;
        int midIndex = 1;
        int highIndex = 2;
        Optional<Doctor> doc = doctorRepository.findById(did);
        List P1 = reminderRepository.findUnfinishedCountByPriorityAndDoc(doc, Integer.valueOf(1));
        List P2 = reminderRepository.findUnfinishedCountByPriorityAndDoc(doc, Integer.valueOf(2));
        List P3 = reminderRepository.findUnfinishedCountByPriorityAndDoc(doc, Integer.valueOf(3));
        System.out.print(P1);
        System.out.print(P2);
        System.out.print(P3);
        Hashtable<String, List<Integer>> res = new Hashtable<String, List<Integer>>();
//        increment the returned hashtable based on query result.
        List<List<Integer>> PList = Arrays.asList(P1, P2, P3);
        List<Integer> indexList = Arrays.asList(lowIndex, midIndex, highIndex);
//        for(int i=0;i<PList.size();i++){
//            for(int j=0;j<indexList.size();j++){
//                res = HTConstruct(res, PList.get(i), indexList.get(j));
//            }
//        }
        res = HTConstruct(res, P1, lowIndex);
        res = HTConstruct(res, P2, midIndex);
        res = HTConstruct(res, P3, highIndex);
        System.out.print(res);
        return res;
    }
    public Hashtable<String, List<Integer>> HTConstruct(Hashtable<String, List<Integer>> res, List P1, int lowIndex){
        for(int i=0;i<P1.size();i++){
//          data fetched from database : [name, count, name2, count2, ...]
            String returned = P1.get(i).toString();
            String[] patientInfo = returned.split(",");
            String patientName = patientInfo[0];
            System.out.print("#");
            System.out.print(patientInfo[0]);
            System.out.print("#");
            System.out.print(patientInfo[1]);
            System.out.print("#");

            Integer new_priority = Integer.parseInt(patientInfo[1]);
//          Add the values to the return value : {name1:[], name2:[], ...}
            List<Integer> priorityList = res.get(patientName);
            if (priorityList == null){
//                priorityList = new ArrayList<Integer>(3);
                priorityList = Arrays.asList(0, 0, 0);
//                res.put(patientName, priorityList);
            }
            Integer priority = priorityList.get(lowIndex);
            if(priority == null){
                priorityList.set(lowIndex, new_priority);
            } else {
                priorityList.set(lowIndex, priority + new_priority);
            }
            res.put(patientName, priorityList);
        }
        return res;
    }
//    Provides metadata for barchart.
//  TODO exception handling:
//    1. when doctor does not exist
//    2. when patient does not exit / patient  is not managed by the provided doctor.
    public List<Integer> getBarChartByDidPid(Long did, Long pid){
        Optional<Doctor> doc = doctorRepository.findById(did);
        Optional<Patient> pat = patientRepository.findById(pid);
        List<Reminder> reminderList = reminderRepository.findALLByDidPid(doc, pat);

        List<Integer> res = new ArrayList<>();
        for (int i=0;i<7;i++){
            res.add(i, 0);
        }
        for(int i=0;i<reminderList.size();i++){
            LocalDateTime today = LocalDateTime.now();
//            only count the outdated reminders
            if(reminderList.get(i).getOutdated() == false){
                continue;
            }
            for (int j=1; j<=7; j++) {
                Timestamp ref = Timestamp.valueOf(today.minusDays(j));
                Timestamp tmp = reminderList.get(i).getTimestamp();
//                System.out.print(reminderList.get(i).getTimestamp());
                int diff = tmp.compareTo(ref);
                if (diff > 0) {
                    int index = 7-j;
                    int val = res.get(index);
                    res.set(index, ++val);
                    break;
                }
            }
        }
        return res;
    }
//  {txt: , duration_hours: , priority: , did: , pid: }
    public Reminder saveReminder(Map<String, String> json){
        String text = json.get("txt");
        String durationStr = json.get("duration_hours");
        String priorityStr = json.get("priority");
        String didStr = json.get("did");
        String pidStr = json.get("pid");
        LocalDateTime nowLDT = LocalDateTime.now();
        Timestamp now = Timestamp.valueOf(nowLDT);
        Optional<Doctor> docFetch = doctorRepository.findById(Long.parseLong(didStr));
        Optional<Patient> patFetch = patientRepository.findById(Long.parseLong(pidStr));
        Reminder reminder = new Reminder();
        docFetch.ifPresentOrElse(
                (doc) -> {patFetch.ifPresentOrElse(
                        (pat) -> {reminder.setTxt(text);
                                    reminder.setDuration(Duration.ofHours(Integer.parseInt(priorityStr)));
                                    reminder.setPriority(Integer.parseInt(priorityStr));
                                    reminder.setDoctor(doc);
                                    reminder.setPatient(pat);
                                    reminder.setTimestamp(now);
                                    reminderRepository.save(reminder);
                                        System.out.println("new reminder saved to database!");},
                        () -> { System.out.println(
                            "Failed to save reminder because patient does not exist!"); });},
                () -> { System.out.println("Failed to save reminder because doctor does not exist!"); });
        return reminder;
    }
}
