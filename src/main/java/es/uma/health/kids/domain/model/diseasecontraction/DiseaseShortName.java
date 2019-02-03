package es.uma.health.kids.domain.model.diseasecontraction;

import java.util.Objects;


public class DiseaseShortName {

    private String shortNameString;

    public DiseaseShortName(String shortNameString) {
        this.shortNameString = shortNameString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiseaseShortName that = (DiseaseShortName) o;
        return Objects.equals(shortNameString, that.shortNameString);
    }

    public String value() {
		return shortNameString;
	}

	public void setShortNameString(String shortNameString) {
		this.shortNameString = shortNameString;
	}

	@Override
    public int hashCode() {
        return Objects.hash(shortNameString);
    }
}
