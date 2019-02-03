package es.uma.health.kids.domain.model.message;

import es.uma.health.kids.domain.event.DomainEvent;

public class AppointmentRejected extends DomainEvent {
	
	private MessageId appointmentId;

	public AppointmentRejected(MessageId appointmentId) {
		super();
		this.appointmentId = appointmentId;
	}

	public MessageId appointmentId() {
		return appointmentId;
	}
	
}
