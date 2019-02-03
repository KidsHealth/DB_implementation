package es.uma.health.kids.domain.model.medication;

import java.util.Objects;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class Timing {

    private int eachHours;

    public Timing(int eachHours) {
        this.eachHours = eachHours;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Timing that = (Timing) o;
        return eachHours == that.eachHours;
    }

    @Override
    public int hashCode() {
        return Objects.hash(eachHours);
    }
}
