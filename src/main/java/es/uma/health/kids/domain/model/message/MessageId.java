package es.uma.health.kids.domain.model.message;

import java.util.Objects;


public class MessageId {

    private int identifier;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageId messageId = (MessageId) o;
        return identifier == messageId.identifier;
    }

    @Override
    public int hashCode() {
        return Objects.hash(identifier);
    }

    public MessageId(int identifier) {
        this.identifier = identifier;
    }
    
    public int value() {
    	return identifier;
    }
}
