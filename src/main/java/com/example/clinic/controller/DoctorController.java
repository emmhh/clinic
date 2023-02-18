package com.example.clinic.controller;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Reminder;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.service.DoctorService;
import com.example.clinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.print.Doc;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/doctor")
public class DoctorController {
    private final DoctorService doctorService;
    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService= doctorService;
    }
//    @GetMapping(value = "/{did}")
//    public List<Reminder> getData(@PathVariable Long did) {
//        return doctorService.getPatientsStatus(did);
//    }
}
