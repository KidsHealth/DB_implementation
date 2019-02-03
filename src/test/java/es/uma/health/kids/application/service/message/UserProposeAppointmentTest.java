package es.uma.health.kids.application.service.message;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import es.uma.health.kids.domain.model.message.AppointmentRequest;
import es.uma.health.kids.domain.model.message.AppointmentRequest.Status;
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

public class UserProposeAppointmentTest {

	private UserRepository userRepo;
	private PatientRepository patientRepo;
	private MessageRepository messageRepo;
	private AppointmentRequest expectedAR;
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
		
		expectedAR = new AppointmentRequest(
				new MessageId(1), 
				null, null,
				false, // actually it is a Schrodinger's appointment request: I will ignore this.
				aDoctor.id(),
				aPatient.id(),
				LocalDateTime.of(2019, 1, 31, 9, 30),
				null,
				Status.PENDING);
		
		userRepo = UserRepositoryStub.with(aResponsible, aDoctor);
		patientRepo = PatientRepositoryStub.with(aPatient);
		messageRepo = MessageRepositoryStub.empty();
	}
	
	@Test
	public void shouldAResponsibleProposeAnAppointment() {
		
		new UserProposeAppointment(userRepo, patientRepo, messageRepo).execute(
					new ProposeAppointmentRequest(1, 1, null, "2019-01-31 09:30")
				);
		
		AppointmentRequest actualAR = (AppointmentRequest) messageRepo.ofId(expectedAR.id());
		
		assertNotNull(actualAR);
		assertEquals(expectedAR, actualAR);
		assertEquals(expectedAR.doctorId(), actualAR.doctorId());
		assertEquals(expectedAR.patientId(), actualAR.patientId());
		assertEquals(expectedAR.datetimeProposed(), actualAR.datetimeProposed());
		assertEquals(expectedAR.status(), actualAR.status());
		assertFalse(actualAR.isDoctorTheSender());
	
	}
	
	@Test
	public void shouldADoctorProposeAnAppointment() {
		
		new UserProposeAppointment(userRepo, patientRepo, messageRepo).execute(
					new ProposeAppointmentRequest(2, 1, null, "2019-01-31 09:30")
				);
		
		AppointmentRequest actualAR = (AppointmentRequest) messageRepo.ofId(expectedAR.id());
		
		assertNotNull(actualAR);
		assertEquals(expectedAR, actualAR);
		assertEquals(expectedAR.doctorId(), actualAR.doctorId());
		assertEquals(expectedAR.patientId(), actualAR.patientId());
		assertEquals(expectedAR.datetimeProposed(), actualAR.datetimeProposed());
		assertEquals(expectedAR.status(), actualAR.status());
		assertTrue(actualAR.isDoctorTheSender());
	
	}

}
