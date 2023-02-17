package com.example.clinic.model;


import jakarta.persistence.*;
import com.example.clinic.model.Patient;

import java.util.List;

@Entity
@Table(name="Doctor")
public class Doctor {
    @Id
    @SequenceGenerator(name="controller",
            sequenceName="controller",
            allocationSize = 1)
    private Long did;
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy="doctor")
    private List<Patient> patients;

    @OneToMany(mappedBy="doctor")
    private List<Reminder> reminders;

    public Doctor() {
    }

    public Doctor(Long did, String name, String email, String password, List<Patient> patients, List<Reminder> reminders) {
        this.did = did;
        this.name = name;
        this.email = email;
        this.password = password;
        this.patients = patients;
        this.reminders = reminders;
    }

    public Doctor(String name, String email, String password, List<Patient> patients, List<Reminder> reminders) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.patients = patients;
        this.reminders = reminders;
    }

    public Long getDid() {
        return did;
    }

    public void setDid(Long did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public List<Reminder> getReminders() {
        return reminders;
    }

    public void setReminders(List<Reminder> reminders) {
        this.reminders = reminders;
    }
}
