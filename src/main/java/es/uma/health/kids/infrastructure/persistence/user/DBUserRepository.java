package es.uma.health.kids.infrastructure.persistence.user;

import java.sql.*;
import java.util.*;
import es.uma.health.kids.domain.model.user.*;

public class DBUserRepository implements UserRepository {
	
	Connection con = null;
	protected Map<UserId, User> users;

	public DBUserRepository() {
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
		this.users = new HashMap<UserId, User>();
	}

	@Override
	public UserId nextIdentity() {
		return new UserId(users.size() + 1);
	}

	// GET
	@Override
	public User ofId(UserId anId) {
		String sql = "SELECT * from user where id="+anId+";";
		User u = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				UserId i = new UserId(rs.getInt(1));
				UserFullName f = new UserFullName(rs.getString(2), rs.getString(3));
				Email e = new Email(rs.getString(4));
				Password p = new Password(rs.getString(5));
				
				// u = new User(i, f, e, p);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
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
		users.put(user.id(), user);
	}

}
