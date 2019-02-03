package es.uma.health.kids.application.dto.message;

public class MessageDTO {

	public int id;
	public String body;
	public String sendedAt;
	public boolean isDoctorTheSender;
	public int doctorId;
	public int patientId;
	
	public MessageDTO(int id, String body, String sendedAt, boolean isDoctorTheSender, int doctorId, int patientId) {
		this.id = id;
		this.body = body;
		this.sendedAt = sendedAt;
		this.isDoctorTheSender = isDoctorTheSender;
		this.doctorId = doctorId;
		this.patientId = patientId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + doctorId;
		result = prime * result + id;
		result = prime * result + (isDoctorTheSender ? 1231 : 1237);
		result = prime * result + patientId;
		result = prime * result + ((sendedAt == null) ? 0 : sendedAt.hashCode());
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
		MessageDTO other = (MessageDTO) obj;
		if (body == null) {
			if (other.body != null)
				return false;
		} else if (!body.equals(other.body))
			return false;
		if (doctorId != other.doctorId)
			return false;
		if (id != other.id)
			return false;
		if (isDoctorTheSender != other.isDoctorTheSender)
			return false;
		if (patientId != other.patientId)
			return false;
		if (sendedAt == null) {
			if (other.sendedAt != null)
				return false;
		} else if (!sendedAt.equals(other.sendedAt))
			return false;
		return true;
	}
    
}
