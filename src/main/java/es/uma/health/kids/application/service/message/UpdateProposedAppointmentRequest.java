package es.uma.health.kids.application.service.message;

public class UpdateProposedAppointmentRequest {

	public int userId;
	public int messageId;
	public boolean accepting;
	
	public UpdateProposedAppointmentRequest(int userId, int messageId, boolean accepting) {
		this.userId = userId;
		this.messageId = messageId;
		this.accepting = accepting;
	}
	
}
