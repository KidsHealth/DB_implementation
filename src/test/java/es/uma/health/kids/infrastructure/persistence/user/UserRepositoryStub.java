package es.uma.health.kids.infrastructure.persistence.user;

import java.util.stream.Stream;

import es.uma.health.kids.domain.model.user.User;
import es.uma.health.kids.domain.model.user.UserRepository;

public class UserRepositoryStub {

	public static UserRepository with(User ...users) {
		UserRepository repo = empty();
		Stream.of(users).forEach(repo::add);
		return repo;
	}

	private static UserRepository empty() {
		return new InMemoryUserRepository();
	}

}
