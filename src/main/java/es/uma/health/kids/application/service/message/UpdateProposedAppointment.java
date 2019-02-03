package es.uma.health.kids.application.service.message;

import es.uma.health.kids.domain.model.message.AppointmentRequest;
import es.uma.health.kids.domain.model.message.MessageDoesNotExistException;
import es.uma.health.kids.domain.model.message.MessageId;
import es.uma.health.kids.domain.model.message.MessageRepository;
import es.uma.health.kids.domain.model.user.NotAuthorizedException;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserDoesNotExistException;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;

public class UpdateProposedAppointment {
	
	private UserRepository userRepo;
	private MessageRepository messageRepo;
	
	public UpdateProposedAppointment(UserRepository userRepo, MessageRepository messageRepo) {
		this.userRepo = userRepo;
		this.messageRepo = messageRepo;
	}
	
	public void execute(UpdateProposedAppointmentRequest request) {
		
		User user = userRepo.ofId(
				new UserId(request.userId));
		
		if (null == user) {
			throw new UserDoesNotExistException("User does not exist.");
		}
		
		AppointmentRequest apRequest = (AppointmentRequest) messageRepo.ofId(new MessageId(request.messageId));
		
		if (null == apRequest) {
			throw new MessageDoesNotExistException("Message does not exist.");
		}
		
		if ((user.isDoctor() && apRequest.isDoctorTheSender()) || (!user.isDoctor() && !apRequest.isDoctorTheSender())) {
			throw new NotAuthorizedException("The remitent cannot accept or reject the appointment.");
		}
		
		if (request.accepting) {
			apRequest.accept();		
		} else {
			apRequest.reject();
		}
		
	}
}
