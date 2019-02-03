package es.uma.health.kids.domain.model.patient;

import java.time.LocalDate;

import es.uma.health.kids.domain.model.user.UserId;


public class Patient {

    private PatientId id;
    private PatientFullName fullName;
    private Height height;
    private Weight weight;
    private LocalDate birthdate;
    private UserId patientResponsibleId;
    private UserId doctorId;

	public Patient(PatientId id, PatientFullName fullName, LocalDate birthdate, UserId patientResponsibleId,
			UserId doctorId) {
		this.id = id;
		this.fullName = fullName;
		this.birthdate = birthdate;
		this.patientResponsibleId = patientResponsibleId;
		this.doctorId = doctorId;
	}

	public Patient(PatientId id, PatientFullName fullName, Height height, Weight weight, LocalDate birthdate,
			UserId patientResponsibleId, UserId doctorId) {
		this.id = id;
		this.fullName = fullName;
		this.height = height;
		this.weight = weight;
		this.birthdate = birthdate;
		this.patientResponsibleId = patientResponsibleId;
		this.doctorId = doctorId;
	}

	public PatientFullName fullName() {
		return fullName;
	}

	public void fullName(PatientFullName fullName) {
		this.fullName = fullName;
	}

	public Height height() {
		return height;
	}

	public void height(Height height) {
		this.height = height;
	}
	
	public Weight weight() {
		return weight;
	}

	public void weight(Weight weight) {
		this.weight = weight;
	}

	public LocalDate birthdate() {
		return birthdate;
	}

	public void birthdate(LocalDate birthdate) {
		this.birthdate = birthdate;
	}

	public UserId patientResponsibleId() {
		return patientResponsibleId;
	}

	public UserId doctorId() {
		return doctorId;
	}
	
	public void setDoctorId(UserId doctorId) {
		this.doctorId = doctorId;
	}

	public PatientId id() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Patient other = (Patient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
    
	@Override
	public String toString() {
		return "Patient [id=" + id.value() + ", fullName=" + fullName + ", birthdate=" + birthdate + ", patientResponsibleId=" + patientResponsibleId.value() + ", doctorId="
				+ doctorId.value() + "]";
	}
}
