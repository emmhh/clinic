package com.example.clinic.service;

import com.example.clinic.model.Reminder;
import com.example.clinic.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {
    private ReminderRepository reminderRepository;
    @Autowired
    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }
    public List<Reminder> getAllReminders(){
        return reminderRepository.findAll();
    }

}
