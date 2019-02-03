package es.uma.health.kids.infrastructure.persistence.message;

import java.util.stream.Stream;

import es.uma.health.kids.domain.model.message.Message;
import es.uma.health.kids.domain.model.message.MessageRepository;

public class MessageRepositoryStub {

	public static MessageRepository empty() {
		return new InMemoryMessageRepository();
	}

	public static MessageRepository with(Message ...messages) {
		MessageRepository repo = new InMemoryMessageRepository();
		Stream.of(messages).forEach(repo::add);
		return repo;
	}

}
