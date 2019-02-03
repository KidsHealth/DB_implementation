package es.uma.health.kids.infrastructure.persistence.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import es.uma.health.kids.domain.model.user.*;

public class DBPatientResponsibleRepository implements PatientResponsibleRepository {

	Connection con = null;
	protected Map<UserId, User> responsibles;
	
	public DBPatientResponsibleRepository() {
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
		this.responsibles = new HashMap<UserId, User>();
	}
	
	@Override
	public UserId nextIdentity() {
		return new UserId(responsibles.size() + 1);
	}
	
	// GET
	@Override
	public User ofId(UserId anId) {
		String sql = "select u.* from user as u join patientresponsible as r on u.id = r.User_id where u.id=" + anId.value() + ";";
		PatientResponsible r = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				UserId i = new UserId(rs.getInt(1));
				UserFullName f = new UserFullName(rs.getString(2), rs.getString(3));
				Email e = new Email(rs.getString(4));
				Password p = new Password(rs.getString(5));

				r = new PatientResponsible(i, f, e, p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}
	
	// POST
	@Override
	public void add(User user) {
		String sql = "INSERT into user VALUES (?,?,?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, user.id().value());
			st.setString(2, user.fullName().name());
			st.setString(3, user.fullName().surname());
			st.setString(4, user.email().value());
			st.setString(5, user.password().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT into patientresponsible VALUES (?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, " ");
			st.setString(2, " ");
			st.setInt(3, user.id().value());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		responsibles.put(user.id(), user);
	}
	
	// POST
	@Override
	public void add(PatientResponsible aResponsible) {
		String sql = "INSERT into user VALUES (?,?,?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aResponsible.id().value());
			st.setString(2, aResponsible.fullName().name());
			st.setString(3, aResponsible.fullName().surname());
			st.setString(4, aResponsible.email().value());
			st.setString(5, aResponsible.password().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT into patientresponsible VALUES (?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,aResponsible.phoneNumber().value());
			st.setString(2, aResponsible.address().value());
			st.setInt(3, aResponsible.id().value());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		responsibles.put(aResponsible.id(), aResponsible);		
	}
	
	// PUT
	@Override
	public void update(PatientResponsible aResponsible) {
		String sql = "UPDATE patientresponsible AS r INNER JOIN user AS u ON u.id = r.User_id SET r.address=?, r.phone_number = ? WHERE r.User_id = ?;";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,aResponsible.address().value());
			st.setString(2, aResponsible.phoneNumber().value());
			st.setInt(3, aResponsible.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(aResponsible);	
	}
	
	// DELETE
	@Override
	public void delete(PatientResponsible aResponsible) {
		String sql = "DELETE from user where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aResponsible.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "DELETE from patientresponsible where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aResponsible.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		responsibles.remove(aResponsible.id());
	}
	
	// GET
	@Override
	public Collection<User> all() {
		this.responsibles = new HashMap<>();
		
		String sql = "select u.* from user as u join patientresponsible p on u.id = p.User_id;";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				UserId i = new UserId(rs.getInt(1));
				UserFullName f = new UserFullName(rs.getString(2), rs.getString(3));
				Email e = new Email(rs.getString(4));
				Password p = new Password(rs.getString(5));

				PatientResponsible a = new PatientResponsible(i, f, e, p);
				
				responsibles.put(a.id(), a);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return responsibles.values();		
	}
	@Override
	public PatientResponsible ofEmail(Email anEmail) {
		String sql = "select u.* from user as u join patientresponsible p on u.id = p.User_id where u.email=" + anEmail.value() + ";";
		PatientResponsible r = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				UserId i = new UserId(rs.getInt(1));
				UserFullName f = new UserFullName(rs.getString(2), rs.getString(3));
				Email e = new Email(rs.getString(4));
				Password p = new Password(rs.getString(5));

				r = new PatientResponsible(i, f, e, p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return r;
	}

	
}
