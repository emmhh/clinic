package com.example.clinic.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="Patient")
public class Patient {
    @Id
    @SequenceGenerator(name = "patient",
            sequenceName = "patient",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient"
    )
    private Long pid;
    private String name;
    private String email;
    private String password;
    @ManyToOne(fetch=FetchType.LAZY)
    private Doctor doctor;
    @OneToMany(mappedBy = "patient")
    private List<Reminder> reminders;
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

