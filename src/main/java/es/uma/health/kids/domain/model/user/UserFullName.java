package es.uma.health.kids.domain.model.user;

import java.util.Objects;


public class UserFullName {

    private String nameString;
    private String surnameString;

    public UserFullName(String nameString, String surnameString) {
        this.nameString = nameString;
        this.surnameString = surnameString;
    }
    
    public String name() {
    	return nameString;
    }
    
    public String surname() {
    	return surnameString;
    }
    
    public String toString() {
    	return nameString + " " + surnameString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserFullName that = (UserFullName) o;
        return Objects.equals(nameString, that.nameString) &&
                Objects.equals(surnameString, that.surnameString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameString, surnameString);
    }

}
