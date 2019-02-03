package es.uma.health.kids.domain.model.diseasecontraction;

import java.time.LocalDateTime;

import es.uma.health.kids.domain.model.patient.PatientId;

public class DiseaseContraction {

    private DiseaseContractionId id;
    private LocalDateTime diagnosedAt;
    private DiseaseName diseaseName;
    private DiseaseShortName diseaseShortName; 
    private PatientId patientId;
	
    public DiseaseContraction(DiseaseContractionId id, LocalDateTime diagnosedAt, DiseaseName diseaseName,
			DiseaseShortName diseaseShortName, PatientId patientId) {
		this.id = id;
		this.diagnosedAt = diagnosedAt;
		this.diseaseName = diseaseName;
		this.diseaseShortName = diseaseShortName;
		this.patientId = patientId;
	}

	public LocalDateTime diagnosedAt() {
		return diagnosedAt;
	}

	public void diagnosedAt(LocalDateTime diagnosedAt) {
		this.diagnosedAt = diagnosedAt;
	}

	public DiseaseName diseaseName() {
		return diseaseName;
	}

	public void diseaseName(DiseaseName diseaseName) {
		this.diseaseName = diseaseName;
	}

	public DiseaseShortName diseaseShortName() {
		return diseaseShortName;
	}

	public void diseaseShortName(DiseaseShortName diseaseShortName) {
		this.diseaseShortName = diseaseShortName;
	}

	public PatientId patientId() {
		return patientId;
	}

	public void patientId(PatientId patientId) {
		this.patientId = patientId;
	}

	public DiseaseContractionId id() {
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
		DiseaseContraction other = (DiseaseContraction) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
