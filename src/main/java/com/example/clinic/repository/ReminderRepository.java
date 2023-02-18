package com.example.clinic.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.clinic.model.Reminder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepository extends JpaRepository<Reminder, Long> {
//    List<Reminder> findALlByDid(Long did);
}
