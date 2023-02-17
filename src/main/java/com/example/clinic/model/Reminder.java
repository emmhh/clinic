package com.example.clinic.model;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.Period;

@Entity
@Table(name="Reminder")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    private String txt;
    private Time time_stamp;
    private Boolean status;
//    outdated is updated and managed in backend
    private Boolean outdated;
    private Period duration;
    private String priority;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="did")
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pid")
    private Patient patient;

    public Reminder() {
    }

    public Reminder(Long rid, String txt, Time time_stamp, Boolean status, Boolean outdated, Period duration, String priority, Doctor doctor, Patient patient) {
        this.rid = rid;
        this.txt = txt;
        this.time_stamp = time_stamp;
        this.status = status;
        this.outdated = outdated;
        this.duration = duration;
        this.priority = priority;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Reminder(String txt, Time time_stamp, Boolean status, Boolean outdated, Period duration, String priority, Doctor doctor, Patient patient) {
        this.txt = txt;
        this.time_stamp = time_stamp;
        this.status = status;
        this.outdated = outdated;
        this.duration = duration;
        this.priority = priority;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public Time getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(Time time_stamp) {
        this.time_stamp = time_stamp;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getOutdated() {
        return outdated;
    }

    public void setOutdated(Boolean outdated) {
        this.outdated = outdated;
    }

    public Period getDuration() {
        return duration;
    }

    public void setDuration(Period duration) {
        this.duration = duration;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
