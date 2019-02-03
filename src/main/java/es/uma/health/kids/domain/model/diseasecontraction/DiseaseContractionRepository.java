package es.uma.health.kids.domain.model.diseasecontraction;

import java.util.Collection;

public interface DiseaseContractionRepository {

	public DiseaseContractionId nextIdentity();
	
	public void add(DiseaseContraction aDiseaseContraction);
	
	public void update(DiseaseContraction aDiseaseContraction);

	public void delete(DiseaseContraction aDiseaseContraction);

	public Collection<DiseaseContraction> all();
	
	public DiseaseContraction ofId(DiseaseContractionId anId);
	
}
