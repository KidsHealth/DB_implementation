package es.uma.health.kids.domain.model.user;

import es.uma.health.kids.domain.model.patient.Patient;

public class Doctor extends User {
	
    public Doctor(UserId id,
            UserFullName fullName,
            Email email,
            Password password) {
        super(id, fullName, email, password);
    }

	@Override
	public boolean isDoctor() {
		return true;
	}

	@Override
	protected void checkMessageAuthorization(Patient patientAbout) {
		if(!patientAbout.doctorId().equals(id)) {
			throw new NotAuthorizedException();
		}
	}
	
	public void assignPatient(Patient aPatient) {
		if (null != aPatient.doctorId()) {
			throw new IllegalArgumentException("The patient must not have a doctor assigned yet.");
		}
		aPatient.setDoctorId(this.id());
	}
	
	public void unassignPatient(Patient aPatient) {
		if (!aPatient.doctorId().equals(this.id())) {
			throw new NotAuthorizedException("The doctor does not have authorization to perform this action.");
		}
		aPatient.setDoctorId(null);
	}
}
