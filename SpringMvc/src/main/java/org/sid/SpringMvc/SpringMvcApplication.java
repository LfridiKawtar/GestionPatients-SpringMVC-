package org.sid.SpringMvc;

import java.util.Date;

import org.sid.SpringMvc.dao.PatientRepository;
import org.sid.SpringMvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class SpringMvcApplication implements CommandLineRunner {
	@Autowired
	public PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(SpringMvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null,"Kawtar", new Date(),false,45));
		patientRepository.save(new Patient(null,"Kevkava", new Date(),true,688));
		patientRepository.save(new Patient(null,"Amine", new Date(), false,9643));
		patientRepository.findAll().forEach(p -> {
			System.out.println(p.getName());
		});
	}

}
