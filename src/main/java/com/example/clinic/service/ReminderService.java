package com.example.clinic.service;

import com.example.clinic.model.Reminder;
import com.example.clinic.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.time.Period;

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

    public int updateAllOutdatedAttribute(){
        int res = 0;
        List<Reminder> allReminders = reminderRepository.findAll();
        LocalDateTime today = LocalDateTime.now();
        for(int i=0;i<allReminders.size();i++){
//          Fetch timestamp and duration stored in the reminder.
            Reminder reminder = allReminders.get(i);
            Boolean completed = reminder.getCompleted();
            Boolean outdated = reminder.getOutdated();
//            If the reminder is already completed by the patient, then there's no need to check whether it's outdated.
            if (completed) {
                continue;
            }
            if (outdated){
                continue;
            }
            Duration period = reminder.getDuration();
            Timestamp stamp = reminder.getTimestamp();
//          current time
            Timestamp now = Timestamp.valueOf(today);
//          expected finish timestamp
            LocalDateTime time_limit = stamp.toLocalDateTime();
            time_limit = time_limit.plus(period);
            stamp = stamp.valueOf(time_limit);
            int diff = stamp.compareTo(now);
//            if timestamp + duration < now then outdated=true;
            if (diff < 0){
                reminder.setOutdated(true);
//                reminderRepository.setOutdated(reminder.getRid());
                reminderRepository.save(reminder);
                res++;
            }
        }
        return res;
    }
}
