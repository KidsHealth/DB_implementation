package es.uma.health.kids.application.service.user.patientresponsible;

public class ResponsibleAddPatientRequest {

	public int userId;
	public String name;
	public String surname;
	public String birthdate;
	
	public ResponsibleAddPatientRequest(int userId, String name, String surname, String birthdate) {
		this.userId = userId;
		this.name = name;
		this.surname = surname;
		this.birthdate = birthdate;
	}

}
