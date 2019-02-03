package es.uma.health.kids.application.dto.shared;

public interface Mapper<T, TDTO> {
    
	TDTO toDTO(T entity);
	T toEntity(TDTO dto);
	
}
