package es.uma.health.kids.application.service.user.patientresponsible;

import static org.junit.Assert.assertTrue;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientFullName;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.Doctor;
import es.uma.health.kids.domain.model.user.NotAuthorizedException;
import es.uma.health.kids.domain.model.user.PatientResponsible;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;
import es.uma.health.kids.infrastructure.persistence.patient.PatientRepositoryStub;
import es.uma.health.kids.infrastructure.persistence.user.UserRepositoryStub;

public class ResponsibleDeletePatientTest {

	private UserRepository userRepo;
	private PatientRepository patientRepo;
	private PatientResponsible pResp;
	private Doctor aDoctor;
	private PatientResponsible randomUser;
	private Patient expectedPatient;
	
	@Before
	public void setUp() {
		pResp = new PatientResponsible(new UserId(1), null, null, null);
		randomUser = new PatientResponsible(new UserId(50), null, null, null);
		aDoctor = new Doctor(new UserId(30), null, null, null);
		userRepo = UserRepositoryStub.with(pResp, aDoctor, randomUser);
		expectedPatient = new Patient(
				new PatientId(2), 
				new PatientFullName("BeforeName", "BeforeSurname"),
				LocalDate.of(2012, 05, 01),
				new UserId(1),
				null);
		patientRepo = PatientRepositoryStub.with(expectedPatient);
	}
	
	@Test
	public void shouldAResponsibleDeleteAPatient() {
		parametrizedUserTest(pResp);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void shouldADoctorNotDeleteAPatient() {
		parametrizedUserTest(aDoctor);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void shouldARandomUserNotDeleteAPatient() {
		parametrizedUserTest(randomUser);
	}
	
	public void parametrizedUserTest(User user) {
		
		new ResponsibleDeletePatient(userRepo, patientRepo).execute(new ResponsibleDeletePatientRequest(
					user.id().value(),
					2
				));
		
		assertTrue(patientRepo.all().isEmpty());
	}
}
