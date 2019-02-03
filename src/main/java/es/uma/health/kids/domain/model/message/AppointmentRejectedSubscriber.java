package es.uma.health.kids.domain.model.message;

import es.uma.health.kids.domain.event.DomainEventSubscriber;
import es.uma.health.kids.domain.model.event.Event;
import es.uma.health.kids.domain.model.event.EventRepository;

public class AppointmentRejectedSubscriber implements DomainEventSubscriber<AppointmentRejected> {

	private EventRepository eventRepo;

	public AppointmentRejectedSubscriber(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}

	@Override
	public void handleEvent(AppointmentRejected aDomainEvent) {
		Event event = eventRepo.ofAppointment(aDomainEvent.appointmentId());
		eventRepo.delete(event);
	}

	@Override
	public Class<AppointmentRejected> subscribedToEventType() {
		return AppointmentRejected.class;
	}
	
}
