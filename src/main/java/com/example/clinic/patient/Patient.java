package com.example.clinic.patient;

import jakarta.persistence.*;

import java.time.LocalDate;

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
    private Integer age;
    private LocalDate dob;
    private String email;

    public Patient() {
    }

    public Patient(Long pid, String name, Integer age, LocalDate dob, String email) {
        this.pid = pid;
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.email = email;
    }
    //    db will generate id for us
    public Patient(String name, Integer age, LocalDate dob, String email) {
        this.name = name;
        this.age = age;
        this.dob = dob;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
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
                ", age=" + age +
                ", dob=" + dob +
                ", email='" + email + '\'' +
                '}';
    }
}

