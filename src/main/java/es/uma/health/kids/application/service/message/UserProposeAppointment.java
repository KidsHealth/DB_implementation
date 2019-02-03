package es.uma.health.kids.application.service.message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import es.uma.health.kids.domain.model.message.AppointmentRequest;
import es.uma.health.kids.domain.model.message.MessageBody;
import es.uma.health.kids.domain.model.message.MessageRepository;
import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;

public class UserProposeAppointment extends SendMessageService {

	public UserProposeAppointment(UserRepository userRepo, PatientRepository patientRepo,
			MessageRepository messageRepo) {
		super(userRepo, patientRepo, messageRepo);
	}

	public void execute(ProposeAppointmentRequest request) {
	
		User user = userRepo.ofId(
				new UserId(request.userId));
		
		Patient patient = patientRepo.ofId(new PatientId(request.patientId));
		
		checkExistence(user, patient);
		
		AppointmentRequest proposedAppointment = user.proposeAppointment(
				messageRepo.nextIdentity(), 
				new MessageBody(request.body), 
				LocalDateTime.parse(
						request.proposedDatetime, 
						DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
				patient);
		
		messageRepo.add(proposedAppointment);
		
	}
	
}
