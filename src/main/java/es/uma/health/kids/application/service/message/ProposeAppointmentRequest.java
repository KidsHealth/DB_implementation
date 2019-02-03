package es.uma.health.kids.application.service.message;

public class ProposeAppointmentRequest {

	public int userId;
	public int patientId;
	public String body;
	public String proposedDatetime;
	
	public ProposeAppointmentRequest(int userId, int patientId, String body, String proposedDatetime) {
		this.userId = userId;
		this.patientId = patientId;
		this.body = body;
		this.proposedDatetime = proposedDatetime;
	}
	
}
