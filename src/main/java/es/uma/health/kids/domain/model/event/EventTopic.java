package es.uma.health.kids.domain.model.event;

import java.util.Objects;


public class EventTopic {

    private String topicString;

    public EventTopic(String venueString) {
        this.topicString = venueString;
    }
    
    public String value() {
    	return topicString;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        EventTopic that = (EventTopic) o;
        return Objects.equals(topicString, that.topicString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicString);
    }
}
