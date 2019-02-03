package es.uma.health.kids.domain.model.medication;

import java.time.LocalDate;

import es.uma.health.kids.domain.model.diseasecontraction.DiseaseContractionId;

public class Medication {

	private MedicationId id;
	private DiseaseContractionId diseaseContractionId;
	private MedicineName medicineName;
	private MedicineCommercialName medicineCommercialName;
	private Timing timing;
	private Posology posology;
	private LocalDate startedAt;
	private LocalDate endedAt;
	
	public Medication(MedicationId id, DiseaseContractionId diseaseContractionId, MedicineName medicineName,
			MedicineCommercialName medicineCommercialName, Timing timing, Posology posology, LocalDate startedAt,
			LocalDate endedAt) {
		this.id = id;
		this.diseaseContractionId = diseaseContractionId;
		this.medicineName = medicineName;
		this.medicineCommercialName = medicineCommercialName;
		this.timing = timing;
		this.posology = posology;
		this.startedAt = startedAt;
		this.endedAt = endedAt;
	}

	public Medication(MedicationId id, DiseaseContractionId diseaseContractionId, MedicineName medicineName,
			MedicineCommercialName medicineCommercialName, Timing timing, Posology posology, LocalDate startedAt) {
		this.id = id;
		this.diseaseContractionId = diseaseContractionId;
		this.medicineName = medicineName;
		this.medicineCommercialName = medicineCommercialName;
		this.timing = timing;
		this.posology = posology;
		this.startedAt = startedAt;
	}

	public DiseaseContractionId diseaseContractionId() {
		return diseaseContractionId;
	}

	public void diseaseContractionId(DiseaseContractionId diseaseContractionId) {
		this.diseaseContractionId = diseaseContractionId;
	}

	public MedicineName medicineName() {
		return medicineName;
	}

	public void medicineName(MedicineName medicineName) {
		this.medicineName = medicineName;
	}

	public MedicineCommercialName medicineCommercialName() {
		return medicineCommercialName;
	}

	public void medicineCommercialName(MedicineCommercialName medicineCommercialName) {
		this.medicineCommercialName = medicineCommercialName;
	}

	public Timing timing() {
		return timing;
	}

	public void timing(Timing timing) {
		this.timing = timing;
	}

	public Posology posology() {
		return posology;
	}

	public void posology(Posology posology) {
		this.posology = posology;
	}

	public LocalDate endedAt() {
		return endedAt;
	}

	public void endedAt(LocalDate endedAt) {
		this.endedAt = endedAt;
	}

	public MedicationId id() {
		return id;
	}

	public LocalDate startedAt() {
		return startedAt;
	}

	public void startedAt(LocalDate startedAt) {
		this.startedAt = startedAt;
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
		Medication other = (Medication) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
