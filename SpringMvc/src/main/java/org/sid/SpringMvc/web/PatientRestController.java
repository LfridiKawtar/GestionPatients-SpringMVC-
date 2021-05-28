package org.sid.SpringMvc.web;
import java.util.List;
import org.sid.SpringMvc.dao.PatientRepository;
import org.sid.SpringMvc.entities.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PatientRestController {
	@Autowired
	private PatientRepository patientRepository;
	
	@GetMapping(path="/listPatients")

	public List<Patient> list()
	{
		return patientRepository.findAll();
	}

	@GetMapping(path="/patients/{id}")

	public Patient getOne(@PathVariable Long id)
	{
		return patientRepository.findById(id).get();
	}
}
