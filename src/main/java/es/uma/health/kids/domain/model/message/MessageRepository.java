package es.uma.health.kids.domain.model.message;

import java.util.Collection;

import es.uma.health.kids.domain.model.patient.PatientId;

public interface MessageRepository {

	public MessageId nextIdentity();
	
	public void add(Message aMessage);
	
	public void update(Message aMessage);

	public void delete(Message aMessage);

	public Collection<Message> all();
	
	public Message ofId(MessageId anId);
	
	public Collection<Message> ofPatient(PatientId patientId);
}
