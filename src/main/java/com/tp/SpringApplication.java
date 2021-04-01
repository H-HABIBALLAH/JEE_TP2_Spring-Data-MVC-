package com.tp;


import com.tp.dao.PatientRepository;
import com.tp.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.time.LocalDate;

@SpringBootApplication
public class SpringApplication implements CommandLineRunner {
    @Autowired
    private PatientRepository patientRepository;

    public static void main(String[] args){
        ApplicationContext ctx = org.springframework.boot.SpringApplication.run(SpringApplication.class,args);
    }

    @Override
    public void run(String... args){
        patientRepository.save(new Patient(null,"HABIB ALLAH", LocalDate.now(),1000,false));
        patientRepository.save(new Patient(null,"JIBAR", LocalDate.now(),2000,true));
        patientRepository.save(new Patient(null,"ABOUAMIR", LocalDate.now(),3000,false));
        patientRepository.save(new Patient(null,"HAMMOU", LocalDate.now(),4000,true));

        patientRepository.findAll().forEach(p ->{
            System.out.println(p);
        });
    }
}
