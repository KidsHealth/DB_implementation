package es.uma.health.kids.application.service.message;

public class SendMessageRequest {

	public int userId;
	public int patientId;
	public String body;
	
	public SendMessageRequest(int userId, int patientId, String body) {
		this.userId = userId;
		this.patientId = patientId;
		this.body = body;
	}
	
}
