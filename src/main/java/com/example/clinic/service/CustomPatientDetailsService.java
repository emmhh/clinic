//package com.example.clinic.service;
//
//import com.example.clinic.model.CustomPatientDetails;
//import com.example.clinic.model.Doctor;
//import com.example.clinic.model.Patient;
//import com.example.clinic.repository.DoctorRepository;
//import com.example.clinic.repository.PatientRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//public class CustomPatientDetailsService implements UserDetailsService {
//    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
//    @Autowired
//    private PatientRepository patientRepository;
//    @Override
//    public CustomPatientDetails loadUserByUsername(String email) throws UsernameNotFoundException{
//        Patient pat = patientRepository.findByEmail(email);
//        if (pat == null){
//            throw new UsernameNotFoundException("User not found");
//        }
//        System.out.print("patient login !");
//        return new CustomPatientDetails(pat);
//    }
//}
