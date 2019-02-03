package es.uma.health.kids.infrastructure.persistence.diseasecontraction;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import es.uma.health.kids.domain.model.diseasecontraction.*;
import es.uma.health.kids.domain.model.patient.PatientId;

public class DBDiseaseContraction implements DiseaseContractionRepository{

	Connection con = null;
	protected Map<DiseaseContractionId, DiseaseContraction> diseases;
	
	public DBDiseaseContraction() {
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
		this.diseases = new HashMap<DiseaseContractionId, DiseaseContraction>();
		
	}
	
	@Override
	public DiseaseContractionId nextIdentity() {
		return new  DiseaseContractionId(diseases.size() + 1);
	}

	// POST
	@Override
	public void add(DiseaseContraction aDiseaseContraction) {
		String sql = "INSERT into diseasecontraction VALUES (?,?,?,?,?);";
		// DB -> patientid, id, name, short_name, date
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aDiseaseContraction.patientId().value());
			st.setInt(2, aDiseaseContraction.id().value());
			st.setString(3, aDiseaseContraction.diseaseName().stringName());
			st.setString(4, aDiseaseContraction.diseaseShortName().value());
			st.setDate(5, java.sql.Date.valueOf(aDiseaseContraction.diagnosedAt().toLocalDate()));
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		diseases.put(aDiseaseContraction.id(), aDiseaseContraction);		
	}

	// PUT
	@Override
	public void update(DiseaseContraction aDiseaseContraction) {
		String sql = "UPDATE diseasecontraction set disease_name=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, aDiseaseContraction.diseaseName().stringName());
			st.setInt(2, aDiseaseContraction.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(aDiseaseContraction);		
	}

	// DELETE
	@Override
	public void delete(DiseaseContraction aDiseaseContraction) {
		String sql = "DELETE from diseasecontraction where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aDiseaseContraction.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		diseases.remove(aDiseaseContraction.id());		
	}

	// GET
	@Override
	public Collection<DiseaseContraction> all() {
		this.diseases = new HashMap<DiseaseContractionId, DiseaseContraction>();
		String sql = "SELECT * from diseasecontraction;";

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				// DB -> patientid, id, name, short_name, date
				PatientId p = new PatientId(rs.getInt(1));
				DiseaseContractionId di = new DiseaseContractionId(rs.getInt(2));
				DiseaseName n = new DiseaseName(rs.getString(3));
				DiseaseShortName sn = new DiseaseShortName(rs.getString(4));
				LocalDateTime ld = rs.getTimestamp(5).toLocalDateTime();
				
				DiseaseContraction d = new DiseaseContraction(di, ld, n, sn, p);
				
				diseases.put(d.id(), d);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return diseases.values();
	}

	@Override
	public DiseaseContraction ofId(DiseaseContractionId anId) {
		String sql = "SELECT * from diseasecontraction where id="+anId.value()+";";
		DiseaseContraction d = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				// DB -> patientid, id, name, short_name, date
				PatientId p = new PatientId(rs.getInt(1));
				DiseaseContractionId di = new DiseaseContractionId(rs.getInt(2));
				DiseaseName n = new DiseaseName(rs.getString(3));
				DiseaseShortName sn = new DiseaseShortName(rs.getString(4));
				LocalDateTime ld = rs.getTimestamp(5).toLocalDateTime();
				
				d = new DiseaseContraction(di, ld, n, sn, p);
				
				diseases.put(d.id(), d);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

}
