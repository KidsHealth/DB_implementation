package es.uma.health.kids.domain.model.message;

import java.time.LocalDateTime;

import es.uma.health.kids.domain.event.DomainEvent;

public class AppointmentAccepted extends DomainEvent {

	private MessageId appointmentId;
	private LocalDateTime datetimeProposed;

	public AppointmentAccepted(MessageId appointmentId, LocalDateTime datetimeProposed) {
		super();
		this.appointmentId = appointmentId;
		this.datetimeProposed = datetimeProposed;
	}

	public MessageId appointmentId() {
		return appointmentId;
	}
	
	public LocalDateTime datetimeProposed() {
		return datetimeProposed;
	}
	
}
