package es.uma.health.kids.domain.model.medication;

public class MedicationId {

	private int identifier;

	public MedicationId(int identifier) {
		this.identifier = identifier;
	}
	
	public int value() {
		return identifier;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + identifier;
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
		MedicationId other = (MedicationId) obj;
		if (identifier != other.identifier)
			return false;
		return true;
	}
	
}
