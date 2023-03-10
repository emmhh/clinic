package com.example.clinic;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Patient;
import com.example.clinic.model.Reminder;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.ReminderRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableScheduling
public class ClinicApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}

	@Bean
	CommandLineRunner init(DoctorRepository doctorRepository,
						   PatientRepository patientRepository,
						   ReminderRepository reminderRepository){
		return args -> {
//			Populate data to the DB. 2000 patients, 500 doctors, Each doctor manage 40 patients.
//			Each patient has 10-20 records each day.
//			various priorities for each reminder are also created
//			i -> number of doctors
			for(int i=1;i<=5;++i){
				Doctor doc = new Doctor("doctor"+i,
						"doctor"+i+"@gmail.com",
						"123");
				doctorRepository.save(doc);
				List<Patient> patientList = new ArrayList<Patient>();
//				j->number of patients for each doctor
				for(int j=1;j<=5;j++){
					Patient pat = new Patient("patient"+(5*(i-1)+j),
							"patient"+(5*(i-1)+j)+"@gmail.com",
							"123",
							doc);
					patientList.add(pat);
					patientRepository.save(pat);
//					Day range of the data creation
					for(int D=13;D<=23;D++){
//						a few reminders each day
						for(int k=5;k<=15;k++){
							DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
							Date date = dateFormat.parse(D+"/2/2023");
							long time = date.getTime();
							Timestamp day = new Timestamp(time);
//							high priority reminder creation
							if (j%2==1){
								Reminder reminder = new Reminder("Stay Hydrated!!!",
										false,
										false,
										Duration.ofHours(1),
										3,
										doc,
										pat);
								reminder.setTimestamp(day);
//									pat.setReminders(List.of(reminder));
								reminderRepository.save(reminder);
//							low priority reminder creation if it is divisible by 10
							} else if (j%10==0) {
								Reminder reminder = new Reminder("Exercise 15 mins!",
										false,
										false,
										Duration.ofHours(12),
										1,
										doc,
										pat);
								reminder.setTimestamp(day);
//									pat.setReminders(List.of(reminder));
								reminderRepository.save(reminder);
//							medium priority reminders creation
							} else{
								Reminder reminder = new Reminder("Call the doctor for updates!",
										false,
										false,
										Duration.ofHours(2),
										2,
										doc,
										pat);
								reminder.setTimestamp(day);
//									pat.setReminders(List.of(reminder));
								reminderRepository.save(reminder);
							}
						}
					}
					patientRepository.save(pat);

				}
//				doc.setPatients(patientList);
				doctorRepository.save(doc);
			}
		};
	}
}
