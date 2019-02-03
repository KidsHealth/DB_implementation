package es.uma.health.kids.infrastructure.persistence.medication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import es.uma.health.kids.domain.model.diseasecontraction.DiseaseContractionId;
import es.uma.health.kids.domain.model.medication.*;

public class DBMedicationRepository implements MedicationRepository {

	Connection con = null;
	private Map<MedicationId, Medication> medications;

	public DBMedicationRepository() {
		String url = "jdbc:mysql://localhost:3306/kidshealth?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		// CAMBIAR AQUI USERNAME Y PASSWORD SI TIENES OTRA DIFERENTE EN TU SERVIDOR DE
		// MYSQL
		String username = "root";
		String password = "password";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		medications = new HashMap<MedicationId, Medication>();
	}

	@Override
	public MedicationId nextIdentity() {
		return new MedicationId(medications.size() + 1);
	}

	// POST
	@Override
	public void add(Medication aMedication) {
		String sql = "INSERT into medication VALUES (?,?,?,?,?,?,?,?);";
		// DB -> id, name, comercial_name, timing, posology, start,end, disease_id
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aMedication.id().value());
			st.setString(2, aMedication.medicineName().value());
			st.setString(3, aMedication.medicineCommercialName().value());
			st.setInt(4, aMedication.timing().value());
			st.setDouble(5, aMedication.posology().value());
			st.setDate(6, java.sql.Date.valueOf(aMedication.startedAt()));
			st.setDate(7, java.sql.Date.valueOf(aMedication.endedAt()));
			st.setInt(8, aMedication.diseaseContractionId().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		medications.put(aMedication.id(), aMedication);
	}

	@Override
	public void update(Medication aMedication) {
		String sql = "UPDATE medication set timing=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aMedication.timing().value());
			st.setInt(2, aMedication.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(aMedication);
	}

	@Override
	public void delete(Medication aMedication) {
		String sql = "DELETE from medication where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aMedication.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		medications.remove(aMedication.id());
	}

	@Override
	public Collection<Medication> all() {
		this.medications = new HashMap<MedicationId, Medication>();
		String sql = "SELECT * from medication;";

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				// DB -> id, name, comercial_name, timing, posology, start,end, disease_id
				MedicationId mi = new MedicationId(rs.getInt(1));
				MedicineName name = new MedicineName(rs.getString(2));
				MedicineCommercialName comn = new MedicineCommercialName(rs.getString(3));
				Timing t = new Timing(rs.getInt(4));
				Posology p = new Posology(rs.getInt(5));
				LocalDate start = rs.getDate(6).toLocalDate();
				LocalDate end = rs.getDate(7).toLocalDate();
				DiseaseContractionId d = new DiseaseContractionId(rs.getInt(8));

				Medication m = new Medication(mi, d, name, comn, t, p, start, end);

				medications.put(m.id(), m);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return medications.values();
	}

	@Override
	public Medication ofId(MedicationId anId) {
		String sql = "SELECT * from medication where id=" + anId.value() + ";";
		Medication m = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				// DB -> id, name, comercial_name, timing, posology, start,end, disease_id
				MedicationId mi = new MedicationId(rs.getInt(1));
				MedicineName name = new MedicineName(rs.getString(2));
				MedicineCommercialName comn = new MedicineCommercialName(rs.getString(3));
				Timing t = new Timing(rs.getInt(4));
				Posology p = new Posology(rs.getInt(5));
				LocalDate start = rs.getDate(6).toLocalDate();
				LocalDate end = rs.getDate(7).toLocalDate();
				DiseaseContractionId d = new DiseaseContractionId(rs.getInt(8));

				m = new Medication(mi, d, name, comn, t, p, start, end);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m;
	}

}
