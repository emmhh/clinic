package com.example.clinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.sql.Timestamp;
import java.time.Period;

@Entity
@Table(name="Reminder")
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long rid;
    private String txt;

//    , insertable = true, updatable = true
    @Column(name="timestamp", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp timestamp;
    private Boolean completed;
    //    outdated is updated and managed in backend
    private Boolean outdated = false;
    private Period duration;
    private Integer priority = 0;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="did")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Doctor doctor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pid")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Patient patient;

    public Reminder() {
    }
    public Reminder(String txt, Boolean completed, Boolean outdated, Period duration, Integer priority) {
        this.txt = txt;
        this.completed = completed;
        this.outdated = outdated;
        this.duration = duration;
        this.priority = priority;
    }
    public Reminder(Long rid, String txt, Boolean completed, Boolean outdated, Period duration, Integer priority, Doctor doctor, Patient patient) {
        this.rid = rid;
        this.txt = txt;
        this.completed = completed;
        this.outdated = outdated;
        this.duration = duration;
        this.priority = priority;
        this.doctor = doctor;
        this.patient = patient;
    }

    public Reminder(String txt, Boolean completed, Boolean outdated, Period duration, Integer priority, Doctor doctor, Patient patient) {
        this.txt = txt;
        this.completed = completed;
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

    // timestamp not changeable so no setter for this attribute
    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
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

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
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
    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
