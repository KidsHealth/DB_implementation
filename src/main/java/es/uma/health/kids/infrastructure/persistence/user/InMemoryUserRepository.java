package es.uma.health.kids.infrastructure.persistence.user;

import java.util.HashMap;
import java.util.Map;

import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.domain.model.user.UserRepository;

public class InMemoryUserRepository implements UserRepository {
	
	protected Map<UserId, User> users;

	public InMemoryUserRepository() {
		this.users = new HashMap<UserId, User>();
	}

	@Override
	public UserId nextIdentity() {
		return new UserId(users.size() + 1);
	}

	@Override
	public User ofId(UserId anId) {
		return users.get(anId);
	}

	@Override
	public void add(User user) {
		users.put(user.id(), user);
	}

}
