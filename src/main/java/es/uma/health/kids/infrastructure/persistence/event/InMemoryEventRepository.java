package es.uma.health.kids.infrastructure.persistence.event;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.Map;

import es.uma.health.kids.domain.model.event.Event;
import es.uma.health.kids.domain.model.event.EventId;
import es.uma.health.kids.domain.model.event.EventRepository;
import es.uma.health.kids.domain.model.message.MessageId;

public class InMemoryEventRepository implements EventRepository {

	private Map<EventId, Event> events;
	
	public InMemoryEventRepository(Map<EventId, Event> events) {
		this.events = events;
	}

	@Override
	public EventId nextIdentity() {
		return new EventId(events.size() + 1);
	}

	@Override
	public void add(Event anEvent) {
		events.put(anEvent.id(), anEvent);
	}

	@Override
	public void update(Event anEvent) {
		add(anEvent);
	}

	@Override
	public void delete(Event anEvent) {
		events.remove(anEvent.id());
	}

	@Override
	public Collection<Event> all() {
		return events.values().stream().map(event -> ofId(event.id())).collect(toList());
	}

	@Override
	public Event ofId(EventId anId) {
		Event event = events.get(anId);
		return new Event(
				event.id(), 
				event.title(), 
				event.description(), 
				event.topic(), 
				event.venue(), 
				event.startDatetime(), 
				event.endDatetime(),
				event.patientId());
	}
	
	public Event ofAppointment(MessageId appointmentId) {
		return events.values().stream()
				.filter(e -> e.topic().value().equals("Appointments"))
				.filter(e -> Integer.parseInt(e.description().value()) == appointmentId.value())
				.findFirst()
				.get();
	}

}
