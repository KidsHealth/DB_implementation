package es.uma.health.kids.application.service.user;

import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertEquals;

import java.util.Collection;
import java.util.stream.Stream;

import org.junit.Test;

import es.uma.health.kids.application.dto.patient.PatientDTO;
import es.uma.health.kids.application.dto.patient.PatientMapper;
import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.Doctor;
import es.uma.health.kids.domain.model.user.PatientResponsible;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;
import es.uma.health.kids.infrastructure.persistence.patient.PatientRepositoryStub;
import es.uma.health.kids.infrastructure.persistence.user.UserRepositoryStub;

public class UserViewTheirPatientsTest {

	@Test
	public void shouldAnUserViewTheirPatients() {
		
		UserRepository userRepo = UserRepositoryStub.with(
				new PatientResponsible(new UserId(1), null, null, null),
				new Doctor(new UserId(2), null, null, null)
				);
		Patient patient1 = new Patient(new PatientId(1), null, null, new UserId(1), null);
		Patient patient2 = new Patient(new PatientId(2), null, null, new UserId(1), new UserId(2));
		Patient patient3 = new Patient(new PatientId(3), null, null, new UserId(3), new UserId(2));
		PatientRepository patientRepo = PatientRepositoryStub.with(patient1, patient2, patient3);
		
		PatientMapper mapper = new PatientMapper();
		Collection<PatientDTO> expectedByPatientResponsible = Stream.of(patient1, patient2).map(mapper::toDTO).collect(toList());
		Collection<PatientDTO> expectedByDoctor = Stream.of(patient2, patient3).map(mapper::toDTO).collect(toList());
		
		Collection<PatientDTO> actualByPatientResponsible = new UserViewTheirPatients(
				userRepo, patientRepo, mapper
			).execute(new UserViewTheirPatientsRequest(1));
		
		Collection<PatientDTO> actualByDoctor = new UserViewTheirPatients(
				userRepo, patientRepo, mapper
			).execute(new UserViewTheirPatientsRequest(2));
				
		assertEquals(expectedByPatientResponsible, actualByPatientResponsible);
		assertEquals(expectedByDoctor, actualByDoctor);
		
	}

}
