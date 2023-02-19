package com.example.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Patient")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pid;
    private String name;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="did")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Doctor doctor;
//    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL)
//    private List<Reminder> reminders;
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public void setReminders(List<Reminder> reminders) {
//        this.reminders = reminders;
//    }

    public Doctor getDoctor() {
        return doctor;
    }

//    public List<Reminder> getReminders() {
//        return reminders;
//    }

    public Patient() {
    }

    public Patient(Long pid, String name, String email) {
        this.pid = pid;
        this.name = name;
        this.email = email;
    }
    //    db will generate id for us
    public Patient(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Patient(String name, String email, String password, Doctor doctor) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.doctor = doctor;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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

    @Override
    public String toString() {
        return "Patient{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

