package com.example.clinic.patient;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static java.time.Month.JANUARY;

@Order
@Configuration
public class PatientConfig {
    @Bean
    CommandLineRunner commandLineRunner(PatientRepository repository){
        return args-> {
            Patient marv = new Patient(1L,
                    "marv",
                    29,
                    LocalDate.of(2000, JANUARY, 5),
                    "marv@gmail.com");
            Patient alex = new Patient(1L,
                    "Alex",
                    29,
                    LocalDate.of(2000, JANUARY, 5),
                    "Alex@gmail.com");
            repository.saveAll(
                    List.of(marv, alex)
            );
        };
    }
}
