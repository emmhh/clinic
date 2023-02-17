package com.example.clinic.repository;

import com.example.clinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.print.Doc;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
