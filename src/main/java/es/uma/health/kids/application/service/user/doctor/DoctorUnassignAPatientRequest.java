package es.uma.health.kids.application.service.user.doctor;

public class DoctorUnassignAPatientRequest {

	public int doctorId;
	public int patientId;
	
	public DoctorUnassignAPatientRequest(int doctorId, int patientId) {
		this.doctorId = doctorId;
		this.patientId = patientId;
	}
	
}
