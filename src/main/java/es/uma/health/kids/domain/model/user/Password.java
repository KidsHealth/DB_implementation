package es.uma.health.kids.domain.model.user;

import java.util.Objects;

import es.uma.health.kids.security.PasswordUtils;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class Password {

    private String encryptedPasswordString;

    public Password(String encryptedPasswordString) {
        this.encryptedPasswordString = encryptedPasswordString;
    }

    public static Password createNewEncrypted(String password, String salt) {
        return new Password(PasswordUtils.generateSecurePassword(password, salt));
    }
    
    public String value() {
    	return encryptedPasswordString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Password that = (Password) o;
        return Objects.equals(encryptedPasswordString, that.encryptedPasswordString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encryptedPasswordString);
    }
}
