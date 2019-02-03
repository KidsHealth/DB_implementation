package es.uma.health.kids.domain.model.user;

import java.time.LocalDateTime;

import es.uma.health.kids.domain.model.message.AppointmentRequest;
import es.uma.health.kids.domain.model.message.AppointmentRequest.Status;
import es.uma.health.kids.domain.model.message.Message;
import es.uma.health.kids.domain.model.message.MessageBody;
import es.uma.health.kids.domain.model.message.MessageId;
import es.uma.health.kids.domain.model.patient.Patient;

public abstract class User {
	
	protected UserId id;
    protected UserFullName fullName;
    protected Email email;
    protected Password password;

    public User(UserId id, UserFullName fullName,
            Email email, Password password) {
        this.id = id;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public UserId id() {
        return id;
    }

    public UserFullName fullName() {
        return fullName;
    }
    
    public void fullName(UserFullName fullName) {
    	this.fullName = fullName;
    }

    public Email email() {
        return email;
    }
    
    public void email(Email email) {
    	this.email = email;
    }

    public Password password() {
        return password;
    }
    
    public void password(Password password) {
    	this.password = password;
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
	public abstract boolean isDoctor();
	
	protected abstract void checkMessageAuthorization(Patient patientAbout);
	
	public Message sendMessage(MessageId id, MessageBody body, Patient patientAbout) {
		checkMessageAuthorization(patientAbout);
		return new Message(id, body, LocalDateTime.now(), isDoctor(), patientAbout.doctorId(), patientAbout.id());
	}

	public AppointmentRequest proposeAppointment(
			MessageId id, 
			MessageBody body, 
			LocalDateTime proposedDatetime,
			Patient patientAbout) {
		checkMessageAuthorization(patientAbout);
		return new AppointmentRequest(id, body, LocalDateTime.now(), isDoctor(), 
				patientAbout.doctorId(), patientAbout.id(), proposedDatetime, 
				LocalDateTime.now(), Status.PENDING);
	}
	

    @Override
	public String toString() {
		return "User [id=" + id.value() + ", fullName=" + fullName + ", email=" + email.value() + ", password=" + password.value() + "]";
	}

	
}
