package es.uma.health.kids.application.service.user.patientresponsible;

import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientDoesNotExistException;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.NotAuthorizedException;
import es.uma.health.kids.domain.model.user.PatientResponsible;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserDoesNotExistException;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;

public class ResponsibleDeletePatient {

	private UserRepository userRepo;
	private PatientRepository patientRepo;
	
	public ResponsibleDeletePatient(UserRepository userRepo, PatientRepository patientRepo) {
		this.userRepo = userRepo;
		this.patientRepo = patientRepo;
	}

	public void execute(ResponsibleDeletePatientRequest request) {
		
		User user = userRepo.ofId(new UserId(request.userId));
		
		Patient patient = patientRepo.ofId(new PatientId(request.patientId));
		
		checkUserExistence(user);
		checkPatientExistence(patient);
		
		checkRoleAuthorization(user);
		
		PatientResponsible pResp = (PatientResponsible)	user;
		
		checkResponsibleAuthorization(pResp, patient);
		
		patientRepo.delete(patient);
		
	}
	
	private void checkUserExistence(User user) {
		if (null == user) {
			throw new UserDoesNotExistException();
		}
	}
	
	private void checkPatientExistence(Patient aPatient) {
		if (null == aPatient) {
			throw new PatientDoesNotExistException();
		}
	}

	private void checkResponsibleAuthorization(PatientResponsible pResp, Patient patient) {
		if (!patient.patientResponsibleId().equals(pResp.id())) {
			throw new NotAuthorizedException("This user is not allowed to update the patient");
		}
	}
	
	private void checkRoleAuthorization(User user) {
		if (user.isDoctor()) {
			throw new NotAuthorizedException("Doctors cannot add new patients.");
		}
	}
	
}
