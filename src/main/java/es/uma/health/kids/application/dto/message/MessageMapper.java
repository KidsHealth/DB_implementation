package es.uma.health.kids.application.dto.message;

import java.time.format.DateTimeFormatter;

import es.uma.health.kids.application.dto.shared.Mapper;
import es.uma.health.kids.domain.model.message.Message;

public class MessageMapper implements Mapper<Message, MessageDTO> {

	@Override
	public MessageDTO toDTO(Message entity) {
		return new MessageDTO(
				entity.id().value(), 
				entity.body().value(), 
				entity.sendedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), 
				entity.isDoctorTheSender(), 
				entity.doctorId().value(), 
				entity.patientId().value());
	}

	@Override
	public Message toEntity(MessageDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
