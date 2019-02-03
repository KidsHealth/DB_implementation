package es.uma.health.kids.domain.model.event;

import java.util.Objects;


public class EventTitle {

    private String titleString;

    public EventTitle(String titleString) {
        this.titleString = titleString;
    }
    
    public String value() {
    	return titleString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventTitle that = (EventTitle) o;
        return Objects.equals(titleString, that.titleString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titleString);
    }
}
