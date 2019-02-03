package es.uma.health.kids.application.service.user.patientresponsible;

public class ResponsibleDeletePatientRequest {

	public int userId;
	public int patientId;
	
	public ResponsibleDeletePatientRequest(int userId, int patientId) {
		this.userId = userId;
		this.patientId = patientId;
	}
	
}
