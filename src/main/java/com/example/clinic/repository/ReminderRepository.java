package com.example.clinic.repository;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clinic.model.Reminder;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.net.Inet4Address;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Query("SELECT r FROM Reminder r WHERE r.doctor = ?1")
    List<Reminder> findALlByDid(Optional<Doctor> doctor);
    @Query("SELECT r.patient.name, COUNT(r) FROM Reminder r WHERE r.doctor = ?1 and r.priority=?2 and (r.completed=false or r.outdated=true) group by r.patient")
    List<String> findUnfinishedCountByPriorityAndDoc(Optional<Doctor> doc, Integer priority);
    @Query("select r from Reminder r where r.patient=?1")
    List<Reminder> findAllByPatient(Optional<Patient> pat);
}
