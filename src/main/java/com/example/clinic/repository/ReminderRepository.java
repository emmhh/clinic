package com.example.clinic.repository;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clinic.model.Reminder;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.net.Inet4Address;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
    @Query("SELECT r FROM Reminder r WHERE r.doctor = ?1")
    List<Reminder> findALLByDid(Optional<Doctor> doctor);
    @Query("SELECT r FROM Reminder r WHERE r.doctor = ?1 and r.patient=?2")
    List<Reminder> findALLByDidPid(Optional<Doctor> doctor, Optional<Patient> patient);
    @Query("SELECT r.patient.name, COUNT(r) FROM Reminder r WHERE r.doctor = ?1 and r.priority=?2 and (r.completed=false or r.outdated=true) group by r.patient")
    List<String> findUnfinishedCountByPriorityAndDoc(Optional<Doctor> doc, Integer priority);
    @Query("SELECT COUNT(r) FROM Reminder r WHERE r.patient = ?1 and r.priority=?2 and (r.completed=false or r.outdated=true)")
    Integer findUnfinishedCountByPriorityAndPatient(Optional<Patient> pat, Integer priority);
    @Query("select r from Reminder r where r.patient=?1")
    List<Reminder> findAllByPatient(Optional<Patient> pat);
//    @Modifying
//    @Query("update Reminder r set r.outdated='true' where r.rid=?1")
//    void setOutdated(Long rid);
}
