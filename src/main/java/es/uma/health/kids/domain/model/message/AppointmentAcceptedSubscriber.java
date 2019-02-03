package es.uma.health.kids.domain.model.message;

import es.uma.health.kids.domain.event.DomainEventSubscriber;
import es.uma.health.kids.domain.model.event.Event;
import es.uma.health.kids.domain.model.event.EventDescription;
import es.uma.health.kids.domain.model.event.EventRepository;
import es.uma.health.kids.domain.model.event.EventTitle;
import es.uma.health.kids.domain.model.event.EventTopic;
import es.uma.health.kids.domain.model.event.EventVenue;
import es.uma.health.kids.domain.model.patient.PatientId;

public class AppointmentAcceptedSubscriber implements DomainEventSubscriber<AppointmentAccepted> {

	private EventRepository eventRepo;

	public AppointmentAcceptedSubscriber(EventRepository eventRepo) {
		this.eventRepo = eventRepo;
	}

	@Override
	public void handleEvent(AppointmentAccepted aDomainEvent) {
		eventRepo.add(new Event(
				eventRepo.nextIdentity(), 
				new EventTitle("Cita médica"), 
				new EventDescription(Integer.toString(aDomainEvent.appointmentId().value())), 
				new EventTopic("Appointments"), new EventVenue(""),
				aDomainEvent.datetimeProposed(), 
				aDomainEvent.datetimeProposed().plusMinutes(15),
				new PatientId(3)));
	}

	@Override
	public Class<AppointmentAccepted> subscribedToEventType() {
		return AppointmentAccepted.class;
	}
	
}
