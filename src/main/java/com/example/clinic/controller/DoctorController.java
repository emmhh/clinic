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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    @GetMapping("/dashboard/{did}")
    public Hashtable<String, String> getDashboard(@PathVariable(value="did") Long did){
        Hashtable<String, String> res = doctorService.getDashboardByDid(did);
        return res;
    }
    @GetMapping("/bar/{did}")
    public List<Integer> getBarChart(@PathVariable(value="did") Long did){
        List<Integer> res = doctorService.getBarChartByDid(did);
        return res;
    }
}
