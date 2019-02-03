package es.uma.health.kids.domain.model.message;

import java.util.Objects;


public class MessageBody {

    private String bodyString;

    public MessageBody(String bodyString) {
        this.bodyString = bodyString;
    }
    
    public String value() {
    	return bodyString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MessageBody that = (MessageBody) o;
        return Objects.equals(bodyString, that.bodyString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bodyString);
    }
}
