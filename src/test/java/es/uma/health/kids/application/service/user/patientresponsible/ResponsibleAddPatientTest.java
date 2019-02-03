package es.uma.health.kids.application.service.user.patientresponsible;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import es.uma.health.kids.domain.model.patient.Patient;
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


public class ResponsibleAddPatientTest {

	private UserRepository userRepo;
	private PatientRepository patientRepo;
	
	@Test
	public void shouldAResponsibleAddANewPatient() {
		PatientResponsible pResp = new PatientResponsible(new UserId(1), null, null, null);
		parametrizedUserTest(pResp);
	}
	
	@Test(expected = NotAuthorizedException.class)
	public void shouldADoctorNotBeAuthorizedToAddNewPatient() {
		Doctor aDoctor = new Doctor(new UserId(1), null, null, null);
		parametrizedUserTest(aDoctor);
	}
	
	public void parametrizedUserTest(User user) {
		userRepo = UserRepositoryStub.with(user);
		patientRepo = PatientRepositoryStub.empty();
		
		new ResponsibleAddPatient(userRepo, patientRepo).execute(new ResponsibleAddPatientRequest(
					1,
					"Name",
					"Surname",
					"2012-05-01"
				));
		
		Patient actualPatient = patientRepo.ofId(new PatientId(1));
		Patient expectedPatient = new Patient(new PatientId(1), null, null, user.id(), null);
		
		assertEquals(expectedPatient, actualPatient);
		assertEquals(expectedPatient.patientResponsibleId(), actualPatient.patientResponsibleId());
	}

}
