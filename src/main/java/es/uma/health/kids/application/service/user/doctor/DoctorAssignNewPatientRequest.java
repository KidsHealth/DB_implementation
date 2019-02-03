package es.uma.health.kids.application.service.user.doctor;

public class DoctorAssignNewPatientRequest {

	public int doctorId;
	public int patientId;
	
	public DoctorAssignNewPatientRequest(int doctorId, int patientId) {
		this.doctorId = doctorId;
		this.patientId = patientId;
	}
	
}
