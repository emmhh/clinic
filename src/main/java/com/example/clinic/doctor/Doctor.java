package com.example.clinic.doctor;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name="Doctor")
public class Doctor {
    @Id
    @SequenceGenerator(name="doctor",
            sequenceName = "doctor",
            allocationSize = 1)
    private Long did;
    private String name;
    private String email;
}
