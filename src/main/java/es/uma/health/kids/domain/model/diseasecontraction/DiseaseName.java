package es.uma.health.kids.domain.model.diseasecontraction;

import java.util.Objects;


public class DiseaseName {

    private String stringName;

    public DiseaseName(String stringName) {
        this.stringName = stringName;
    }

    public String stringName() {
        return stringName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DiseaseName that = (DiseaseName) o;
        return Objects.equals(stringName, that.stringName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stringName);
    }
}
