package es.uma.health.kids.domain.model.event;

import java.util.Collection;

import es.uma.health.kids.domain.model.message.MessageId;

public interface EventRepository {

	public EventId nextIdentity();
	
	public void add(Event anEvent);
	
	public void update(Event anEvent);

	public void delete(Event anEvent);

	public Collection<Event> all();
	
	public Event ofId(EventId anId);
	
	public Event ofAppointment(MessageId appointmentId);
}
