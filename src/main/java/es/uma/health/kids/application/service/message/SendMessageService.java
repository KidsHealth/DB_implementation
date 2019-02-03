package es.uma.health.kids.application.service.message;

import es.uma.health.kids.domain.model.message.MessageRepository;
import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientDoesNotExistException;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserDoesNotExistException;
import es.uma.health.kids.domain.model.user.UserRepository;

public abstract class SendMessageService {
	
	protected UserRepository userRepo;
	protected PatientRepository patientRepo;
	protected MessageRepository messageRepo;

	public SendMessageService(UserRepository userRepo, PatientRepository patientRepo,
			MessageRepository messageRepo) {
		this.userRepo = userRepo;
		this.patientRepo = patientRepo;
		this.messageRepo = messageRepo;
	}
	
	protected void checkExistence(User user, Patient patient) {
		if (null == user) {
			throw new UserDoesNotExistException("User does not exist.");
		}
		
		if (null == patient) {
			throw new PatientDoesNotExistException("Patient does not exist.");
		}
	}
}
