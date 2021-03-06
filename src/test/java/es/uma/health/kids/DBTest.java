package es.uma.health.kids;

import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.user.UserId;
import es.uma.health.kids.infrastructure.persistence.diseasecontraction.DBDiseaseContraction;
import es.uma.health.kids.infrastructure.persistence.event.DBEventRepository;
import es.uma.health.kids.infrastructure.persistence.message.DBMessageRepository;
import es.uma.health.kids.infrastructure.persistence.patient.DBPatientRepository;
import es.uma.health.kids.infrastructure.persistence.user.DBUserRepository;

public class DBTest {

	public static void main(String[] args) {
		// EVENT
		DBEventRepository repo_1 = new DBEventRepository();

		System.out.println(repo_1.all().toString());
		
		// PATIENT
		DBPatientRepository repo_2 = new DBPatientRepository();

		System.out.println("Patients: "+repo_2.all().size());
		System.out.println(repo_2.ofId(new PatientId(2)));
		System.out.println(repo_2.all().toString());
		
		// MESSAGE
		DBMessageRepository repo_3 = new DBMessageRepository();

		System.out.println(repo_3.all().toString());
		
		// PATIENT
		DBUserRepository repo_4 = new DBUserRepository();

		System.out.println(repo_4.ofId(new UserId(3)).toString());
		
		// DISEASE CONTRACTION
		
		DBDiseaseContraction repo_5 = new DBDiseaseContraction();
		
		System.out.println(repo_5.all().toString());
	}

}
