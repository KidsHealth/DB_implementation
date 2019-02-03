package es.uma.health.kids.application.service.user.doctor;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientFullName;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.Doctor;
import es.uma.health.kids.domain.model.user.PatientResponsible;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;
import es.uma.health.kids.infrastructure.persistence.patient.PatientRepositoryStub;
import es.uma.health.kids.infrastructure.persistence.user.UserRepositoryStub;

public class DoctorUnassignAPatientTest {

	private UserRepository userRepo;
	private PatientRepository patientRepo;
	private PatientResponsible pResp;
	private Doctor aDoctor;
	private Patient aPatient;
	
	@Before
	public void setUp() {
		pResp = new PatientResponsible(new UserId(1), null, null, null);
		aDoctor = new Doctor(new UserId(30), null, null, null);
		userRepo = UserRepositoryStub.with(pResp, aDoctor);
		aPatient = new Patient(
				new PatientId(2), 
				new PatientFullName("BeforeName", "BeforeSurname"),
				LocalDate.of(2012, 05, 01),
				pResp.id(),
				aDoctor.id());
		patientRepo = PatientRepositoryStub.with(aPatient);
	}

	@Test
	public void shouldADoctorUnassignAPatient() {
		
		assertEquals(Arrays.asList(aPatient), patientRepo.ofDoctor(aDoctor.id()));
		
		new DoctorUnassignAPatient(userRepo, patientRepo).execute(new DoctorUnassignAPatientRequest(
				aDoctor.id().value(),
				2
			));
		
		Patient actualPatient = patientRepo.ofId(new PatientId(2));
		
		assertNull(actualPatient.doctorId());
		assertNull(patientRepo.ofId(new PatientId(2)).doctorId());
		assertTrue(patientRepo.ofDoctor(aDoctor.id()).isEmpty());
	}

}
