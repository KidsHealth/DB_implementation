package es.uma.health.kids.domain.model.event;

import java.util.Objects;


public class EventDescription {

    private String descriptionString;

    public EventDescription(String descriptionString) {
        this.descriptionString = descriptionString;
    }

    public String value() {
    	return descriptionString;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventDescription that = (EventDescription) o;
        return Objects.equals(descriptionString, that.descriptionString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(descriptionString);
    }
}
