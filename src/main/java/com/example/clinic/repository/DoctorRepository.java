package com.example.clinic.repository;

import com.example.clinic.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT d FROM Doctor d WHERE d.email = ?1")
    Doctor findByEmail(String email);
    @Query("Select d from Doctor d")
    List<Doctor> findDoctors();
}
