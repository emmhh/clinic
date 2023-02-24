package com.example.clinic.service;

import com.example.clinic.model.CustomDoctorDetails;
import com.example.clinic.model.Doctor;
import com.example.clinic.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomDoctorDetailsService implements UserDetailsService {
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private DoctorRepository doctorRepository;
    @Override
    public CustomDoctorDetails loadUserByUsername(String email) throws UsernameNotFoundException{
        Doctor doc = doctorRepository.findByEmail(email);
        if (doc == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomDoctorDetails(doc);
    }
}
