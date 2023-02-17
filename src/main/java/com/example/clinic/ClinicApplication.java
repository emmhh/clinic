package com.example.clinic;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Patient;
import com.example.clinic.repository.DoctorRepository;
import com.example.clinic.repository.PatientRepository;
import com.example.clinic.repository.ReminderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class ClinicApplication {
	public static void main(String[] args) {
		SpringApplication.run(ClinicApplication.class, args);
	}

	@Bean
	CommandLineRunner init(DoctorRepository doctorRepository,
						   PatientRepository patientRepository,
						   ReminderRepository reminderRepository){
		return args -> {
			for(int i=1;i<=500;++i){
				Doctor doc = new Doctor("doctor"+i,
						"doctor"+i+"@gmail.com",
						"123");
				List<Patient> patientList = new ArrayList<Patient>();
				for(int j=1;j<=40;j++){
					Patient pat = new Patient("patient"+(40*(i-1)+j),
							"patient"+(40*(i-1)+j)+"@gmail.com",
							"123",
							doc);
					patientList.add(pat);
				}
				doc.setPatients(patientList);
				doctorRepository.save(doc);
				patientRepository.saveAll(patientList);
			}
		};
	}
}
