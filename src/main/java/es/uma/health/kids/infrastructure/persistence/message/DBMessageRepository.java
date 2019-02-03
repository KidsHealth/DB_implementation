package es.uma.health.kids.infrastructure.persistence.message;

import java.util.*;
import java.sql.*;
import java.time.LocalDateTime;

import es.uma.health.kids.domain.model.message.*;
import es.uma.health.kids.domain.model.patient.PatientId;
import es.uma.health.kids.domain.model.user.UserId;

public class DBMessageRepository implements MessageRepository {

	Connection con = null;
	private Map<MessageId, Message> messages;
	
	public DBMessageRepository() {
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
		messages = new HashMap<>();
	}

	@Override
	public MessageId nextIdentity() {
		return new MessageId(messages.size() + 1);
	}

	@Override
	public void add(Message aMessage) {
		String sql = "INSERT into message VALUES (?,?,?,?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aMessage.id().value());
			st.setString(2, aMessage.body().value());
			st.setTimestamp(3, java.sql.Timestamp.valueOf(aMessage.sendedAt()));
			st.setBoolean(4, aMessage.isDoctorTheSender());
			st.setInt(5, aMessage.patientId().value());
			st.setInt(6, aMessage.doctorId().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		messages.put(aMessage.id(), aMessage);
	}

	@Override
	public void update(Message aMessage) {
		String sql = "UPDATE message set body=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, aMessage.body().value());
			st.setInt(2, aMessage.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(aMessage);
	}

	@Override
	public void delete(Message aMessage) {
		String sql = "DELETE from message where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, aMessage.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		messages.remove(aMessage.id());
	}

	@Override
	public Collection<Message> all() {
		Map<MessageId, Message> messages = new HashMap<MessageId, Message>();
		String sql = "SELECT * from message;";

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				MessageId i = new MessageId(rs.getInt(1));
				MessageBody t = new MessageBody(rs.getString(2));
				LocalDateTime send = rs.getTimestamp(3).toLocalDateTime();
				boolean doctor = rs.getBoolean(4);
				UserId doctorId = new UserId(rs.getInt(5));
			    PatientId patientId = new PatientId(rs.getInt(6));
				
				Message e = new Message(i, t, send, doctor, doctorId, patientId);
				
				messages.put(e.id(), e);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages.values();
	}

	@Override
	public Message ofId(MessageId anId) {
		String sql = "SELECT * from message WHERE id=" + anId.value() + ";";
		Message m = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				MessageId i = new MessageId(rs.getInt(1));
				MessageBody t = new MessageBody(rs.getString(2));
				LocalDateTime send = rs.getTimestamp(3).toLocalDateTime();
				boolean doctor = rs.getBoolean(4);
				UserId doctorId = new UserId(rs.getInt(5));
			    PatientId patientId = new PatientId(rs.getInt(6));
				
				m = new Message(i, t, send, doctor, doctorId, patientId);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return m;
	}

	@Override
	public Collection<Message> ofPatient(PatientId patientId) {
		Map<MessageId, Message> messages = new HashMap<MessageId, Message>();
		String sql = "SELECT * from message WHERE Patient_id=" + patientId.value() + ";";
		
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				MessageId i = new MessageId(rs.getInt(1));
				MessageBody t = new MessageBody(rs.getString(2));
				LocalDateTime send = rs.getTimestamp(3).toLocalDateTime();
				boolean doctor = rs.getBoolean(4);
				UserId doctorId = new UserId(rs.getInt(5));
			    PatientId pa = new PatientId(rs.getInt(6));
				
				Message e = new Message(i, t, send, doctor, doctorId, pa);
				
				messages.put(e.id(), e);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages.values();
	}

}
