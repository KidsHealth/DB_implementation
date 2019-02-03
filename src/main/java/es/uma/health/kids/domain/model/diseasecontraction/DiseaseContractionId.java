package es.uma.health.kids.domain.model.diseasecontraction;

import java.util.Objects;


public class DiseaseContractionId {

    private int identifier;

    public DiseaseContractionId(int identifier) {
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
        DiseaseContractionId that = (DiseaseContractionId) o;
        return identifier == that.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

}
