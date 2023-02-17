package com.example.clinic.service;

import com.example.clinic.model.Patient;
import com.example.clinic.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// service layer, which talks to data layer
// @Component or @Service are the same thing, the purpose is to let the patient contoller at API layer
//to know that this is a Bean. (or a service class)
@Service
public class PatientService {
    private final PatientRepository patientRepository;
    @Autowired
    public PatientService(PatientRepository patientRepository){
        this.patientRepository=patientRepository;
    }
    public List<Patient> getPatients(){
        return patientRepository.findAll();
    }
}
