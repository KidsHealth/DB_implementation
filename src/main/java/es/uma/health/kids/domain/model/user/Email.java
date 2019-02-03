package es.uma.health.kids.domain.model.user;

import java.util.Objects;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class Email {

    private String emailString;

    public Email(String emailString) {
        this.emailString = emailString;
    }
    
    public String value() {
    	return emailString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Email userEmail = (Email) o;
        return Objects.equals(emailString, userEmail.emailString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(emailString);
    }
}
