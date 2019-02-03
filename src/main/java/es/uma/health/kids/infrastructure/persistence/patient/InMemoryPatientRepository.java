package es.uma.health.kids.infrastructure.persistence.patient;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import es.uma.health.kids.domain.model.patient.Patient;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.patient.PatientRepository;
import es.uma.health.kids.domain.model.user.UserId;

public class InMemoryPatientRepository implements PatientRepository {

	private Map<PatientId, Patient> patients;
	
	public InMemoryPatientRepository() {
		this.patients = new HashMap<>();
	}

	@Override
	public PatientId nextIdentity() {
		return new PatientId(patients.size() + 1);
	}

	@Override
	public void add(Patient aPatient) {
		patients.put(aPatient.id(), aPatient);
	}

	@Override
	public void update(Patient aPatient) {
		add(aPatient);
	}

	@Override
	public void delete(Patient aPatient) {
		patients.remove(aPatient.id());
	}

	@Override
	public Collection<Patient> all() {
		return patients.values().stream().map(patient -> ofId(patient.id())).collect(toList());
	}

	@Override
	public Patient ofId(PatientId anId) {
		Patient patient = patients.get(anId);
		return new Patient(patient.id(), patient.fullName(), patient.height(), patient.weight(), patient.birthdate(),
			patient.patientResponsibleId(), patient.doctorId());
	}

	@Override
	public Collection<Patient> ofResponsible(UserId responsibleId) {
		return all().stream().filter(p -> p.patientResponsibleId().equals(responsibleId)).collect(toList());
	}

	@Override
	public Collection<Patient> ofDoctor(UserId doctorId) {
		return all().stream().filter(p -> (null != p.doctorId() && p.doctorId().equals(doctorId))).collect(toList());
	}
	
	public Collection<Patient> relatedWith(UserId userId) {
		return all().stream().filter(p -> {
			return p.patientResponsibleId().equals(userId) || p.doctorId().equals(userId);
		}).collect(toList());
	}

}
