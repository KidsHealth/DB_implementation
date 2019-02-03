package es.uma.health.kids.domain.model.message;

import java.time.LocalDateTime;

import es.uma.health.kids.domain.event.DomainEventPublisher;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.user.UserId;


public class AppointmentRequest extends Message {

	public enum Status {
		REJECTED,
		PENDING,
		ACCEPTED
	}
	
	private LocalDateTime datetimeProposed;
	private LocalDateTime updatedAt;
	private Status status;

	public AppointmentRequest(MessageId id, MessageBody body, LocalDateTime sendedAt, boolean isDoctorTheSender,
			UserId doctorId, PatientId patientId, LocalDateTime datetimeProposed, LocalDateTime updatedAt, Status status) {
		super(id, body, sendedAt, isDoctorTheSender, doctorId, patientId);
		this.datetimeProposed = datetimeProposed;
		this.updatedAt = updatedAt;
		this.status = status;
	}

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
    
    public LocalDateTime datetimeProposed() {
    	return datetimeProposed;
    }
    
    public void datetimeProposed(LocalDateTime datetimeProposed) {
    	this.datetimeProposed = datetimeProposed;
    }

    public Status status() {
        return status;
    }

    public void status(Status status) {
        this.status = status;
    }

    public void reject() {
        this.status = Status.REJECTED;
        this.updatedAt = LocalDateTime.now();
        DomainEventPublisher.instance().publish(new AppointmentRejected(this.id()));
    }

    public void accept() {
        this.status = Status.ACCEPTED;
        this.updatedAt = LocalDateTime.now();
        DomainEventPublisher.instance().publish(new AppointmentAccepted(this.id(), this.datetimeProposed));
    }

    public boolean isRejected() {
        return status.equals(Status.REJECTED);
    }

    public boolean isPending() {
        return status.equals(Status.PENDING);
    }

    public boolean isAccepted() {
        return status.equals(Status.ACCEPTED);
    }
    
}
