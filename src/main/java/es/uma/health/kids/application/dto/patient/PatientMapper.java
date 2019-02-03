package es.uma.health.kids.application.dto.patient;

import java.time.format.DateTimeFormatter;
import java.util.Optional;

import es.uma.health.kids.application.dto.shared.Mapper;
import es.uma.health.kids.domain.model.patient.Patient;

public class PatientMapper implements Mapper<Patient, PatientDTO> {

	@Override
	public PatientDTO toDTO(Patient entity) {
		return new PatientDTO(
				entity.id().value(), 
				Optional.ofNullable(entity.fullName()).map(f -> f.name()).orElse(null), 
				Optional.ofNullable(entity.fullName()).map(f -> f.surname()).orElse(null),
				Optional.ofNullable(entity.birthdate()).map(
						b -> b.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))).orElse(null), 
				Optional.ofNullable(entity.height()).map(h -> h.value()).orElse(null), 
				Optional.ofNullable(entity.weight()).map(w -> w.value()).orElse(null)
				);
	}

	@Override
	public Patient toEntity(PatientDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

}
