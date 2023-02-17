package com.example.clinic.patient;

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
