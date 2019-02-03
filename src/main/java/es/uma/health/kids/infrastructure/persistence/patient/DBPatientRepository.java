package es.uma.health.kids.infrastructure.persistence.patient;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import es.uma.health.kids.domain.model.patient.*;
import es.uma.health.kids.domain.model.user.UserId;

public class DBPatientRepository implements PatientRepository {

	Connection con = null;
	private Map<PatientId, Patient> patients;
	
	public DBPatientRepository() {
		String url = "jdbc:mysql://localhost:3306/kidshealth?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
		// CAMBIAR AQUI USERNAME Y PASSWORD SI TIENES OTRA DIFERENTE EN TU SERVIDOR DE MYSQL
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
		
		this.patients = new HashMap<>();
	}

	@Override
	public PatientId nextIdentity() {
		return new PatientId(patients.size() + 1);
	}

	// POST
	@Override
	public void add(Patient aPatient) {
		String sql = "INSERT into patient VALUES (?,?,?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aPatient.id().value());
			st.setString(2, aPatient.fullName().name());
			st.setString(3, aPatient.fullName().surname());
			st.setDate(4, (Date) aPatient.birthdate());
			st.setInt(5, aPatient.patientResponsibleId().value());
			st.setInt(6, aPatient.doctorId().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		patients.put(aPatient.id(), aPatient);
	}

	// PUT
	@Override
	public void update(Patient aPatient) {
		String sql = "UPDATE patient set Doctor_user_id=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aPatient.doctorId().value());
			st.setInt(2, aPatient.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(aPatient);
	}

	// DELETE
	@Override
	public void delete(Patient aPatient) {
		String sql = "DELETE from patient where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aPatient.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		patients.remove(aPatient.id());
	}

	// GET
	@Override
	public Map<PatientId, Patient> all() {
		this.patients = new HashMap<>();
		String sql = "SELECT * from patient;";

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PatientId i = new PatientId(rs.getInt(1));
				PatientFullName f = new PatientFullName(rs.getString(2), rs.getString(3));
				Date birth = rs.getDate(4);
				UserId respon = new UserId(rs.getInt(5));
				UserId doctor = new UserId(rs.getInt(6));
				Patient patient = new Patient(i, f, birth, respon, doctor);
				
				patients.put(patient.id(), patient);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patients;
	}

	// GET
	@Override
	public Patient ofId(PatientId anId) {
		String sql = "SELECT * from patient where id="+anId+";";
		Patient p = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PatientId i = new PatientId(rs.getInt(1));
				PatientFullName f = new PatientFullName(rs.getString(2), rs.getString(3));
				Date birth = rs.getDate(4);
				UserId respon = new UserId(rs.getInt(5));
				UserId doctor = new UserId(rs.getInt(6));
				
				p = new Patient(i, f, birth, respon, doctor);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return p;
	}

	// GET
	@Override
	public Map<PatientId, Patient> ofResponsible(UserId responsibleId) {
		this.patients = new HashMap<>();
		String sql = "SELECT * from patient where PatientResponsible_User_id="+responsibleId+";";

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PatientId i = new PatientId(rs.getInt(1));
				PatientFullName f = new PatientFullName(rs.getString(2), rs.getString(3));
				Date birth = rs.getDate(4);
				UserId respon = new UserId(rs.getInt(5));
				UserId doctor = new UserId(rs.getInt(6));
				Patient patient = new Patient(i, f, birth, respon, doctor);
				
				patients.put(patient.id(), patient);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return patients;
	}

	// GET
	@Override
	public Map<PatientId, Patient> ofDoctor(UserId doctorId) {
		this.patients = new HashMap<>();
		String sql = "SELECT * from patient where Doctor_User_id="+doctorId+";";

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				PatientId i = new PatientId(rs.getInt(1));
				PatientFullName f = new PatientFullName(rs.getString(2), rs.getString(3));
				Date birth = rs.getDate(4);
				UserId respon = new UserId(rs.getInt(5));
				UserId doctor = new UserId(rs.getInt(6));
				Patient patient = new Patient(i, f, birth, respon, doctor);
				
				patients.put(patient.id(), patient);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return patients;
	}
}
