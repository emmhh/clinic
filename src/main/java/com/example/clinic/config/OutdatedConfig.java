package com.example.clinic.config;

import com.example.clinic.service.ReminderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class OutdatedConfig {
    private ReminderService reminderService;
    @Autowired
    public OutdatedConfig(ReminderService reminderService){
        this.reminderService = reminderService;
    }
//    second, minute, hour, day of month, month, day(s) of week
//    "0 0 8-10 * * *" = 8, 9 and 10 o'clock of every day.
//    @Scheduled(cron = "0 0 0 * * ?") //daily update
    @Scheduled(cron = "0/10 * * * * ?") //update every 10 seconds
    public void scheduledUpdateOutdated() {
        int count = reminderService.updateAllOutdatedAttribute();
        System.out.println(
                "Updated" + count + " the reminder outdated attribute - " + System.currentTimeMillis() / 1000);
    }
}
