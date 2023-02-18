package com.example.clinic.config;

import com.example.clinic.model.Patient;
import com.example.clinic.repository.PatientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.boot.CommandLineRunner;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;

@Order
@Configuration
public class PatientConfig {
}
