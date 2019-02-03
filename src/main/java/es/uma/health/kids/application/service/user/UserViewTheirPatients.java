package es.uma.health.kids.application.service.user;

import java.util.Collection;
import static java.util.stream.Collectors.toList;

import es.uma.health.kids.application.dto.patient.PatientDTO;
import es.uma.health.kids.application.dto.patient.PatientMapper;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserDoesNotExistException;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;

public class UserViewTheirPatients {

	private UserRepository userRepo;
	private PatientRepository patientRepo;
	private PatientMapper patientMapper;

	public UserViewTheirPatients(UserRepository userRepo, PatientRepository patientRepo, PatientMapper patientMapper) {
		this.userRepo = userRepo;
		this.patientRepo = patientRepo;
		this.patientMapper = patientMapper;
	}

	public Collection<PatientDTO> execute(UserViewTheirPatientsRequest request) {
		
		User user = userRepo.ofId(new UserId(request.userId));
		
		checkUserExistence(user);
		
		Collection<PatientDTO> patients = null;
		
		if (user.isDoctor()) {
			patients = patientRepo.ofDoctor(user.id()).stream().map(patientMapper::toDTO).collect(toList());
		} else {
			patients = patientRepo.ofResponsible(user.id()).stream().map(patientMapper::toDTO).collect(toList());
		}
		
		return patients;
	}
	
	private void checkUserExistence(User user) {
		if (null == user) {
			throw new UserDoesNotExistException();
		}
	}
	
}
