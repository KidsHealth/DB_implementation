package es.uma.health.kids.infrastructure.persistence.patient;

import java.util.stream.Stream;

import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.infrastructure.persistence.patient.InMemoryPatientRepository;

public class PatientRepositoryStub {

	public static PatientRepository empty() {
		return new InMemoryPatientRepository();
	}

	public static PatientRepository with(Patient ...patients) {
		PatientRepository repo = empty();
		Stream.of(patients).forEach(repo::add);
		return repo;
	}
}
