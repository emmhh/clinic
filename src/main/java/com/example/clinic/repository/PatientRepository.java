package com.example.clinic.repository;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("select r from Reminder r where r.doctor=?1")
    List<Patient> getPatientsByDoctor(Optional<Doctor> doctor);
}

