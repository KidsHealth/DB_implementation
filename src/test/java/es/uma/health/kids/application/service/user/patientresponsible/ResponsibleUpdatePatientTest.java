package es.uma.health.kids.application.service.user.patientresponsible;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import es.uma.health.kids.domain.model.patient.Height;
import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientFullName;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.patient.Weight;
import es.uma.health.kids.domain.model.user.Doctor;
import es.uma.health.kids.domain.model.user.NotAuthorizedException;
import es.uma.health.kids.domain.model.user.PatientResponsible;
import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;
import es.uma.health.kids.infrastructure.persistence.patient.PatientRepositoryStub;
import es.uma.health.kids.infrastructure.persistence.user.UserRepositoryStub;

public class ResponsibleUpdatePatientTest {
	
	private UserRepository userRepo;
	private PatientRepository patientRepo;
	private PatientResponsible pResp;
	private Doctor aDoctor;
	private PatientResponsible randomUser;
	
	@Before
	public void setUp() {
		pResp = new PatientResponsible(new UserId(1), null, null, null);
		randomUser = new PatientResponsible(new UserId(50), null, null, null);
		aDoctor = new Doctor(new UserId(30), null, null, null);
		userRepo = UserRepositoryStub.with(pResp, aDoctor, randomUser);
		patientRepo = PatientRepositoryStub.with(new Patient(
				new PatientId(2), 
				new PatientFullName("BeforeName", "BeforeSurname"),
				LocalDate.of(2012, 05, 01),
				new UserId(1),
				null));
	}
	
	@Test
	public void shouldAResponsibleUpdateAPatient() {
		parametrizedUserTest(pResp);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void shouldADoctorNotUpdateAPatient() {
		parametrizedUserTest(aDoctor);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void shouldARandomUserNotUpdateAPatient() {
		parametrizedUserTest(randomUser);
	}
	
	public void parametrizedUserTest(User user) {
		
		new ResponsibleUpdatePatient(userRepo, patientRepo).execute(new ResponsibleUpdatePatientRequest(
					user.id().value(),
					2,
					"Name",
					"Surname",
					"2012-05-01",
					22000,
					120
				));
		
		Patient actualPatient = patientRepo.ofId(new PatientId(2));
		
		assertEquals(new PatientFullName("Name", "Surname"), actualPatient.fullName());
		assertEquals(LocalDate.of(2012, 5, 1), actualPatient.birthdate());
		assertEquals(new Weight(22000), actualPatient.weight());
		assertEquals(new Height(120), actualPatient.height());
	}

}
