package es.uma.health.kids.application.service.user.patientresponsible;

public class ResponsibleUpdatePatientRequest {

	public int userId;
	public int patientId;
	public String name;
	public String surname;
	public String birthdate;
	public int weight;
	public int height;
	
	public ResponsibleUpdatePatientRequest(int userId, int patientId, String name, String surname, String birthdate,
			int weight, int height) {
		this.userId = userId;
		this.patientId = patientId;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
		this.weight = weight;
		this.height = height;
	}
	
}
