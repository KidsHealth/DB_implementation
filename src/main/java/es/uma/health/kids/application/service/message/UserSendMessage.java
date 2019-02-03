package es.uma.health.kids.application.service.message;

import es.uma.health.kids.domain.model.message.Message;
import es.uma.health.kids.domain.model.message.MessageBody;
import es.uma.health.kids.domain.model.message.MessageRepository;
import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;

public class UserSendMessage extends SendMessageService {

	public UserSendMessage(UserRepository userRepo, PatientRepository patientRepo, MessageRepository messageRepo) {
		super(userRepo, patientRepo, messageRepo);
	}

	public void execute(SendMessageRequest request) {
		
		User user = userRepo.ofId(
				new UserId(request.userId));
		
		Patient patient = patientRepo.ofId(new PatientId(request.patientId));
		
		checkExistence(user, patient);
		
		Message newMessage = user.sendMessage(messageRepo.nextIdentity(), new MessageBody(request.body), patient);
		
		messageRepo.add(newMessage);
		
	}
	
}
