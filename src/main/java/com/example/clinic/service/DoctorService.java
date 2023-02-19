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
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;

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
        List<Reminder> reminderList = reminderRepository.findALlByDid(doc);
        return reminderList;
    }
    public List<Doctor> getDoctors(){
        return doctorRepository.findDoctors();
    }

    public List<Doctor> listDoctors(){
        List<Doctor> l = doctorRepository.findAll();
        return l;
    }
//    Provides meta-date for dashboard
    public Hashtable<String, String> getDashboardByDid(Long did){
        Hashtable<String, String> res = new Hashtable<String, String>();
        Optional<Doctor> doc = doctorRepository.findById(did);
        List P1 = reminderRepository.findUnfinishedCountByPriorityAndDoc(doc, Integer.valueOf(1));
        List P2 = reminderRepository.findUnfinishedCountByPriorityAndDoc(doc, Integer.valueOf(2));
        List P3 = reminderRepository.findUnfinishedCountByPriorityAndDoc(doc, Integer.valueOf(3));
//        System.out.print(P1);
//        System.out.print(P2);
//        System.out.print(P3);
        res.put("low", String.valueOf(P1));
        res.put("mid", String.valueOf(P2));
        res.put("hi", String.valueOf(P3));
        return res;
    }
//    Provides metadata for barchart.
    public List<Integer> getBarChartByDid(Long did){
        Optional<Doctor> doc = doctorRepository.findById(did);
        List<Reminder> reminderList = reminderRepository.findALlByDid(doc);
        List<Integer> res = new ArrayList<>();
        for (int i=0;i<7;i++){
            res.add(i, 0);
        }
        for(int i=0;i<reminderList.size();i++){
            LocalDateTime today = LocalDateTime.now();
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
}
