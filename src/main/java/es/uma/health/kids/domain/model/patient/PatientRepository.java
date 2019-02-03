package es.uma.health.kids.domain.model.patient;

import java.util.Collection;

import es.uma.health.kids.domain.model.user.UserId;


public interface PatientRepository {

	public PatientId nextIdentity();
	
	public void add(Patient aPatient);
	
	public void update(Patient aPatient);

	public void delete(Patient aPatient);

	public Collection<Patient> all();
	
	public Patient ofId(PatientId anId);
	
	public Collection<Patient> ofResponsible(UserId responsibleId);
	
	public Collection<Patient> ofDoctor(UserId doctorId);
	
	public Collection<Patient> relatedWith(UserId userId);
}
