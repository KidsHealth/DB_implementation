package es.uma.health.kids.domain.model.medication;

import java.util.Objects;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class Posology {

    private int miligrams;

    public Posology(int miligrams) {
        this.miligrams = miligrams;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Posology that = (Posology) o;
        return miligrams == that.miligrams;
    }

    @Override
    public int hashCode() {
        return Objects.hash(miligrams);
    }
}
