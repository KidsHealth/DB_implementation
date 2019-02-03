package es.uma.health.kids.application.service.message;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import es.uma.health.kids.domain.model.message.Message;
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

public class UserSendMessageTest {
	
	private UserRepository userRepo;
	private PatientRepository patientRepo;
	private MessageRepository messageRepo;
	private Message expectedMessage;
	private PatientResponsible aResponsible;
	private Doctor aDoctor;
	
	@Before
	public void setUp() {
		aResponsible = new PatientResponsible(new UserId(1), null, null, null);
		aDoctor = new Doctor(new UserId(2), null, null, null);
		
		Patient aPatient = new Patient(
				new PatientId(1), null, null, 
				aResponsible.id(), 
				aDoctor.id());
		
		expectedMessage = new Message(
				new MessageId(1), 
				null, null,
				false, // I will ignore this.
				aDoctor.id(),
				aPatient.id());
		
		userRepo = UserRepositoryStub.with(aResponsible, aDoctor);
		patientRepo = PatientRepositoryStub.with(aPatient);
		messageRepo = MessageRepositoryStub.empty();
	}
	
	@Test
	public void shouldAResponsibleSendAMessage() {
		
		new UserSendMessage(userRepo, patientRepo, messageRepo).execute(
					new SendMessageRequest(1, 1, null)
				);
		
		Message actualMessage = messageRepo.ofId(expectedMessage.id());
		
		assertNotNull(actualMessage);
		assertEquals(expectedMessage, actualMessage);
		assertEquals(expectedMessage.doctorId(), actualMessage.doctorId());
		assertEquals(expectedMessage.patientId(), actualMessage.patientId());
		assertFalse(actualMessage.isDoctorTheSender());
	
	}
	
	@Test
	public void shouldADoctorSendAMessage() {
		
		new UserSendMessage(userRepo, patientRepo, messageRepo).execute(
					new SendMessageRequest(2, 1, null)
				);
		
		Message actualMessage = messageRepo.ofId(expectedMessage.id());
		
		assertNotNull(actualMessage);
		assertEquals(expectedMessage, actualMessage);
		assertEquals(expectedMessage.doctorId(), actualMessage.doctorId());
		assertEquals(expectedMessage.patientId(), actualMessage.patientId());
		assertTrue(actualMessage.isDoctorTheSender());
	
	}
	
}
