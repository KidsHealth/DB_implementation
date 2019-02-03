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

public class DBDoctorRepository implements DoctorRepository {

	Connection con = null;
	protected Map<UserId, User> doctors;

	public DBDoctorRepository() {
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
		this.doctors = new HashMap<UserId, User>();
	}
	
	@Override
	public UserId nextIdentity() {
		return new UserId(doctors.size() + 1);
	}

	// GET
	@Override
	public User ofId(UserId anId) {
		String sql = "select u.* from user as u join doctor d on u.id = d.User_id where u.id=" + anId.value() + ";";
		Doctor d = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				UserId i = new UserId(rs.getInt(1));
				UserFullName f = new UserFullName(rs.getString(2), rs.getString(3));
				Email e = new Email(rs.getString(4));
				Password p = new Password(rs.getString(5));

				d = new Doctor(i, f, e, p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
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
		sql = "INSERT into doctor VALUES (?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, user.id().value());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doctors.put(user.id(), user);
	}

	// POST 
	@Override
	public void add(Doctor aDoctor) {
		String sql = "INSERT into user VALUES (?,?,?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aDoctor.id().value());
			st.setString(2, aDoctor.fullName().name());
			st.setString(3, aDoctor.fullName().surname());
			st.setString(4, aDoctor.email().value());
			st.setString(5, aDoctor.password().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "INSERT into doctor VALUES (?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aDoctor.id().value());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		doctors.put(aDoctor.id(), aDoctor);
	}

	// PUT
	@Override
	public void update(Doctor aDoctor) {
		String sql = "UPDATE user AS u INNER JOIN doctor AS d ON u.id = d.User_id SET u.password = ? WHERE  d.User_id = ?;";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, aDoctor.password().value());
			st.setInt(2, aDoctor.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(aDoctor);
	}

	// DELETE
	@Override
	public void delete(Doctor aDoctor) {
		String sql = "DELETE from user where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aDoctor.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		sql = "DELETE from doctor where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aDoctor.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		doctors.remove(aDoctor.id());
	}

	// GET
	@Override
	public Collection<User> all() {
		this.doctors = new HashMap<>();
		
		String sql = "select u.* from user as u join doctor d on u.id = d.User_id;";
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				UserId i = new UserId(rs.getInt(1));
				UserFullName f = new UserFullName(rs.getString(2), rs.getString(3));
				Email e = new Email(rs.getString(4));
				Password p = new Password(rs.getString(5));

				Doctor d = new Doctor(i, f, e, p);
				
				doctors.put(d.id(), d);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doctors.values();
	}

	// GET
	@Override
	public Doctor ofEmail(Email anEmail) {
		String sql = "select u.* from user as u join doctor d on u.id = d.User_id where u.email=" + anEmail.value() + ";";
		Doctor d = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				UserId i = new UserId(rs.getInt(1));
				UserFullName f = new UserFullName(rs.getString(2), rs.getString(3));
				Email e = new Email(rs.getString(4));
				Password p = new Password(rs.getString(5));

				d = new Doctor(i, f, e, p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return d;
	}

}
