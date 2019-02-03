package es.uma.health.kids.domain.model.patient;

import java.util.Objects;


public class PatientFullName {

    private String nameString;
    private String surnameString;

    public PatientFullName(String nameString, String surnameString) {
        this.nameString = nameString;
        this.surnameString = surnameString;
    }
    
    public String name() {
    	return nameString;
    }
    
    public String surname() {
    	return surnameString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PatientFullName that = (PatientFullName) o;
        return Objects.equals(nameString, that.nameString) &&
                Objects.equals(surnameString, that.surnameString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameString, surnameString);
    }

    @Override
    public String toString() {
        return nameString + " " + surnameString;
    }
}
