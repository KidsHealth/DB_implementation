package es.uma.health.kids.application.dto.message;

import java.util.Collection;

import es.uma.health.kids.domain.model.patient.Patient;

public class ConversationDTO {

	public Patient patient;
	public Collection<MessageDTO> messages;

	public ConversationDTO(Patient patient, Collection<MessageDTO> messages) {
		this.patient = patient;
		this.messages = messages;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((messages == null) ? 0 : messages.hashCode());
		result = prime * result + ((patient == null) ? 0 : patient.hashCode());
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
		ConversationDTO other = (ConversationDTO) obj;
		if (messages == null) {
			if (other.messages != null)
				return false;
		} else if (!messages.equals(other.messages))
			return false;
		if (patient == null) {
			if (other.patient != null)
				return false;
		} else if (!patient.equals(other.patient))
			return false;
		return true;
	}
	
}
