package es.uma.health.kids.domain.model.patient;

import java.util.Objects;


public class Height {

    private int heightValue;

    public Height(int heightValue) {
        this.heightValue = heightValue;
    }
    
    public int value() {
    	return heightValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Height that = (Height) o;
        return heightValue == that.heightValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(heightValue);
    }

    @Override
    public String toString() {
        return heightValue + " cm";
    }
}
