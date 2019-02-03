package es.uma.health.kids.domain.model.user;

import java.util.Collection;

public interface DoctorRepository extends UserRepository {

	public void add(Doctor aDoctor);
	
	public void update(Doctor aDoctor);

	public void delete(Doctor aDoctor);

	public Collection<User> all();

	public Doctor ofEmail(Email anEmail);
	
}
