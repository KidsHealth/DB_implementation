package es.uma.health.kids.application.service.user.patientresponsible;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientFullName;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.NotAuthorizedException;
import es.uma.health.kids.domain.model.user.PatientResponsible;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserDoesNotExistException;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;

public class ResponsibleAddPatient {

	private UserRepository userRepo;
	private PatientRepository patientRepo;
	
	public ResponsibleAddPatient(UserRepository userRepo, PatientRepository patientRepo) {
		this.userRepo = userRepo;
		this.patientRepo = patientRepo;
	}

	public void execute(ResponsibleAddPatientRequest request) {
		
		User user = userRepo.ofId(new UserId(request.userId));
		
		checkUserExistence(user);
		
		checkAuthorization(user);
		
		PatientResponsible pResp = (PatientResponsible)	user;
		
		Patient newPatient = pResp.newPatient(
				patientRepo.nextIdentity(),
				new PatientFullName(request.name, request.surname),
				LocalDate.parse(request.birthdate, DateTimeFormatter.ofPattern("yyyy-MM-dd")
			));
		
		patientRepo.add(newPatient);
	}
	
	private void checkUserExistence(User user) {
		if (null == user) {
			throw new UserDoesNotExistException();
		}
	}

	private void checkAuthorization(User user) {
		if (user.isDoctor()) {
			throw new NotAuthorizedException("Doctors cannot add new patients.");
		}
	}
	
}
