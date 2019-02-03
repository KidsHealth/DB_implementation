package es.uma.health.kids.domain.model.patient;

import java.util.Objects;


public class PatientId {

    private int identifier;

    public PatientId(int identifier) {
        this.identifier = identifier;
    }
    
    public int value() {
    	return identifier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatientId patientId = (PatientId) o;
        return identifier == patientId.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return Integer.toString(identifier);
    }
}
