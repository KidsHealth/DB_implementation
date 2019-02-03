package es.uma.health.kids.domain.model.event;

import java.util.Objects;


public class EventVenue {

    private String venueString;

    public EventVenue(String venueString) {
        this.venueString = venueString;
    }

    public String value() {
    	return venueString;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventVenue that = (EventVenue) o;
        return Objects.equals(venueString, that.venueString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(venueString);
    }
}
