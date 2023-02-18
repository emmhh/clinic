package com.example.clinic.service;

import com.example.clinic.model.Patient;
import com.example.clinic.model.Reminder;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ReminderRepository reminderRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, PatientRepository patientRepository,
                         ReminderRepository reminderRepository){
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
        this.reminderRepository = reminderRepository;
    }
//    public List<Reminder> getPatientsStatus(Long did){
//        List<Reminder> reminderList = reminderRepository.findALlByDid(did);
//        return reminderList;
//    }
}
