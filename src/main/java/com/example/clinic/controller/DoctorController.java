package com.example.clinic.controller;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Reminder;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.ReminderRepository;
import com.example.clinic.service.DoctorService;
import com.example.clinic.service.PatientService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="/doctor")
public class DoctorController {
    private DoctorService doctorService;
    private DoctorRepository doctorRepository;

    private ReminderRepository reminderRepository;

    @Autowired
    public DoctorController(DoctorService doctorService,
                            DoctorRepository doctorRepository) {
        this.doctorService= doctorService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("")
    public List<Doctor> getDoctors(){
        return doctorService.getDoctors();
    }
//    @GetMapping(value = "/{did}")
//    public ResponseEntity<List<Reminder>> getData(@PathVariable(value="did") Long did) {
//        List<Reminder> res = doctorService.getPatientsStatus(did);
//        return new ResponseEntity<>(res, HttpStatus.OK);
//    }
    @GetMapping(value = "/{did}")
    public List<Reminder> getData(@PathVariable(value="did") Long did) {
        List<Reminder> reminderList = doctorService.getPatientsStatus(did);
        return reminderList;
    }
    @GetMapping("/list")
    public List<Doctor> listUsers() {
        List<Doctor> listUsers = doctorService.listDoctors();
        return listUsers;
    }
//    TODO shoulve return the counts by patient. Not by doctor.
    @GetMapping("/dashboard/{did}")
    public Hashtable<String, List<Integer>> getDashboard(@PathVariable(value="did") Long did){
        Hashtable<String, List<Integer>> res = doctorService.getDashboardByDid(did);
        return res;
    }
//    TODO exception handling either from front-end by not allowing users to pass in pid that is not managed by did.
//  or from back-end to handle exceptions and return proper status back the the front end.
    @GetMapping("/bar/{did}/{pid}")
    public List<Integer> getBarChart(@PathVariable(value="did") Long did, @PathVariable(value="pid") Long pid){
        List<Integer> res = doctorService.getBarChartByDidPid(did, pid);
        return res;
    }
//    @PostMapping("/post/{did}")
//    public String postReminder(@PathVariable(value="did") Long did){
//
//    }
}
