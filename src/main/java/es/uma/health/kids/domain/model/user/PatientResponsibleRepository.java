package es.uma.health.kids.domain.model.user;

import java.util.Collection;

public interface PatientResponsibleRepository extends UserRepository {

	public void add(PatientResponsible aResponsible);
	
	public void update(PatientResponsible aResponsible);

	public void delete(PatientResponsible aResponsible);

	public Collection<PatientResponsible> all();

	public PatientResponsible ofEmail(Email anEmail);
	
}
