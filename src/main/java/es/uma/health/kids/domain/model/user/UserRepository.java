package es.uma.health.kids.domain.model.user;

public interface UserRepository {

	public UserId nextIdentity();
	
	public User ofId(UserId anId);
	
	public void add(User user);
	
}
