package es.uma.health.kids.domain.model.message;

import java.time.LocalDateTime;

import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.user.UserId;


public class Message {

    private MessageId id;
    private MessageBody body;
    private LocalDateTime sendedAt;
    private boolean isDoctorTheSender;
    private UserId doctorId;
    private PatientId patientId;

	public Message(
			MessageId id, 
			MessageBody body, 
			LocalDateTime sendedAt, 
			boolean isDoctorTheSender, 
			UserId doctorId,
			PatientId patientId) {
		this.id = id;
		this.body = body;
		this.sendedAt = sendedAt;
		this.isDoctorTheSender = isDoctorTheSender;
		this.doctorId = doctorId;
		this.patientId = patientId;
	}

	public MessageId id() {
        return id;
    }

    public MessageBody body() {
        return body;
    }

    public void body(MessageBody body) {
        this.body = body;
    }

    public LocalDateTime sendedAt() {
        return sendedAt;
    }

    public void sendedAt(LocalDateTime sendedAt) {
        this.sendedAt = sendedAt;
    }

	public boolean isDoctorTheSender() {
		return isDoctorTheSender;
	}

	public void isDoctorTheSender(boolean isDoctorTheSender) {
		this.isDoctorTheSender = isDoctorTheSender;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public UserId doctorId() {
		return doctorId;
	}

	public void doctorId(UserId doctorId) {
		this.doctorId = doctorId;
	}

	public PatientId patientId() {
		return patientId;
	}

	public void patientId(PatientId patientId) {
		this.patientId = patientId;
	}
    
	@Override
	public String toString() {
		return "Message [id=" + id.value() + ", body=" + body.value() + ", sendedAt=" + sendedAt + ", isDoctorTheSender="
				+ isDoctorTheSender + ", doctorId=" + doctorId.value() + ", patientId=" + patientId.value() + "]";
	}
}
