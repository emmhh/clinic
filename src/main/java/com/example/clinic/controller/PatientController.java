package com.example.clinic.controller;

import com.example.clinic.model.Patient;
import com.example.clinic.model.Reminder;
import com.example.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//API Layer -> talk to service layer, service layer talk to Data access layer.
// API Layer
// contain all the resources for our api
@RestController
@RequestMapping(path="/patient")
public class PatientController {
    private PatientService patientService;
    //    autowired annotation makes sure that patientService is instantiated for us.
//    (since we need to avoid instantiating these classes ourselves)
    @Autowired
    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // make this class serve as rest end points
    @GetMapping("/list")
    public List<Patient> getPatients(){
        return patientService.getPatients();
    }

    @GetMapping("/{pid}")
    public List<Reminder> getAllRemindersByPid(@PathVariable(value="pid") Long pid){
        return patientService.getAllRemindersByPid(pid);
    }

}

