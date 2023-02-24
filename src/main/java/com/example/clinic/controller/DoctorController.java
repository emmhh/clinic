package com.example.clinic.controller;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Reminder;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.ReminderRepository;
import com.example.clinic.service.DoctorService;
import com.example.clinic.service.PatientService;
import com.example.clinic.service.ReminderService;
import jdk.jfr.Frequency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;
import java.util.*;
import javax.print.Doc;

@RestController
@RequestMapping(path="/doctor")
public class DoctorController {
    private DoctorService doctorService;
    private DoctorRepository doctorRepository;
    private ReminderService reminderService;
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
    @PostMapping(path = "/create_reminder")
    public ResponseEntity<Reminder> createReminder(@RequestBody Map<String, String> json) {
        Reminder reminder = doctorService.saveReminder(json);
        return new ResponseEntity<>(reminder, HttpStatus.CREATED);
    }
}