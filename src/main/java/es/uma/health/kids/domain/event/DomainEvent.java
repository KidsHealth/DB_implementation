package es.uma.health.kids.domain.event;

import java.time.LocalDateTime;

public abstract class DomainEvent {

	protected LocalDateTime occurredOn;
	
	public DomainEvent() {
		this.occurredOn = LocalDateTime.now();
	}
	
    public LocalDateTime occurredOn() {
    	return occurredOn;
    }
    
}