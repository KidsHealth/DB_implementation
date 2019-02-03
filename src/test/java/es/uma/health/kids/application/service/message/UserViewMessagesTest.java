package es.uma.health.kids.application.service.message;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import es.uma.health.kids.application.dto.message.ConversationDTO;
import es.uma.health.kids.application.dto.message.MessageMapper;
import es.uma.health.kids.domain.model.message.Message;
import es.uma.health.kids.domain.model.message.MessageBody;
import es.uma.health.kids.domain.model.message.MessageId;
import es.uma.health.kids.domain.model.message.MessageRepository;
import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.Doctor;
import es.uma.health.kids.domain.model.user.PatientResponsible;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;
import es.uma.health.kids.infrastructure.persistence.message.MessageRepositoryStub;
import es.uma.health.kids.infrastructure.persistence.patient.PatientRepositoryStub;
import es.uma.health.kids.infrastructure.persistence.user.UserRepositoryStub;

public class UserViewMessagesTest {

	private UserRepository userRepo;
	private MessageRepository messageRepo;
	private PatientRepository patientRepo;
	Collection<ConversationDTO> expectedConversations;
	private MessageMapper mapper;
	
	@Before
	public void setUp() throws Exception {
		PatientResponsible pResp = new PatientResponsible(new UserId(1), null, null, null);
		Doctor doctor = new Doctor(new UserId(2), null, null, null);
		
		userRepo = UserRepositoryStub.with(pResp, doctor);
		
		Patient patient = new Patient(new PatientId(1), null, null, pResp.id(), doctor.id());
		patientRepo = PatientRepositoryStub.with(patient);
		
		Message message = new Message(new MessageId(1), new MessageBody("Body"), LocalDateTime.now(), false, patient.doctorId(), patient.id());
		messageRepo = MessageRepositoryStub.with(message);
		
		mapper = new MessageMapper();
		
		expectedConversations = asList(new ConversationDTO(patient, asList(mapper.toDTO(message))));
	}

	@Test
	public void shouldAResponsibleViewAllConversations() {
		
		Collection<ConversationDTO> actualConversations = new UserViewMessages(userRepo, patientRepo, messageRepo, mapper).execute(
					new UserViewMessagesRequest(1)
				);
		
		assertEquals(expectedConversations, actualConversations);
	}

}
