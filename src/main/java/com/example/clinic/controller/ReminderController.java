package com.example.clinic.controller;

import com.example.clinic.model.Reminder;
import com.example.clinic.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.cdi.Eager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/reminder")
public class ReminderController {
    private ReminderService reminderService;
    @Autowired
    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }
    @GetMapping("/list")
    public List<Reminder> getAllReminders(){
        return reminderService.getAllReminders();
    }
}
