package es.uma.health.kids.domain.model.user;

import java.util.Objects;

/**
 * @author Miguel González <sosa@uma.es>
 **/
public class UserId {

    protected int identifier;

    public UserId(int identifier) {
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
        UserId userId = (UserId) o;
        return identifier == userId.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    @Override
    public String toString() {
        return "UserId{" +
                "identifier=" + identifier +
                '}';
    }
}
