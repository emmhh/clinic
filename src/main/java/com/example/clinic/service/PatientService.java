package com.example.clinic.service;

import com.example.clinic.model.Patient;
import com.example.clinic.model.Reminder;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.geom.PathIterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// service layer, which talks to data layer
// @Component or @Service are the same thing, the purpose is to let the patient contoller at API layer
//to know that this is a Bean. (or a service class)
@Service
public class PatientService {
    private PatientRepository patientRepository;
    private final ReminderRepository reminderRepository;

    @Autowired
    public PatientService(PatientRepository patientRepository,
                          ReminderRepository reminderRepository){
        this.patientRepository=patientRepository;
        this.reminderRepository = reminderRepository;
    }
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
    public List<Reminder> getAllRemindersByPid(Long pid){
        Optional<Patient> pat = patientRepository.findById(pid);
        List<Reminder> res = reminderRepository.findAllByPatient(pat);
        return res;
    }
    public Optional<Reminder> updateReminder(Map<String, String> json){
        String ridStr = json.get("rid");
        System.out.print(ridStr);
        Long rid = Long.parseLong(ridStr);
        System.out.print("@@@@");
        System.out.print(rid);
        System.out.print("@@@@");
        Optional<Reminder> reminder = reminderRepository.findById(rid);
        reminder.ifPresentOrElse(
                reminder1 -> {reminder1.setCompleted(true);
                                Boolean outdated = reminder1.getOutdated();
                                if(!outdated){
                                    reminderRepository.save(reminder1);
                                    System.out.print("@@ Reminder Update Completed!");
                                }else{
                                    System.out.print("@@ Failed to update Reminder status because it's outdated!");
                                }
                                },
                () -> {System.out.print("Failed to update reminder! Reminder does not exist");});
        return reminder;
    }
}
