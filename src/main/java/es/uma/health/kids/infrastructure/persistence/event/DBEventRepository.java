package es.uma.health.kids.infrastructure.persistence.event;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

import es.uma.health.kids.domain.model.event.*;
import es.uma.health.kids.domain.model.message.MessageId;
import es.uma.health.kids.domain.model.patient.PatientId;

public class DBEventRepository implements EventRepository {

	Connection con = null;
	private Map<EventId, Event> events;
	
	public DBEventRepository() {
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
		
		events = new HashMap<EventId, Event>();
	}
	
	// GET
	@Override
	public EventId nextIdentity() {
		return new EventId(events.size() + 1);
	}

	// POST 
	@Override	
	public void add(Event anEvent) {
		String sql = "INSERT into event VALUES (?,?,?,?,?);";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, anEvent.id().value());
			st.setString(2, anEvent.title().value());
			st.setString(3, anEvent.description().value());
			st.setString(4, anEvent.venue().value());
			st.setTimestamp(5, java.sql.Timestamp.valueOf(anEvent.startDatetime()));
			st.setTimestamp(6, java.sql.Timestamp.valueOf(anEvent.endDatetime()));
			st.setString(7, anEvent.topic().value());
			st.setInt(8,anEvent.patientId().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		events.put(anEvent.id(), anEvent);
	}

	// PUT
	@Override
	public void update(Event anEvent) {
		String sql = "UPDATE event set description=? where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, anEvent.description().value());
			st.setInt(2, anEvent.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		add(anEvent);
	}

	// DELETE
	@Override
	public void delete(Event anEvent) {
		String sql = "DELETE from event where id=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, anEvent.id().value());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		events.remove(anEvent.id());
	}

	// GET 
	@Override
	public Collection<Event> all() {
		
		events = new HashMap<EventId, Event>();
		String sql = "SELECT * from event;";

		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				EventId i = new EventId(rs.getInt(1));
				EventTitle t = new EventTitle(rs.getString(2));
				EventDescription d = new EventDescription(rs.getString(3));
				EventVenue v = new EventVenue(rs.getString(4));
				LocalDateTime start = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime end = rs.getTimestamp(6).toLocalDateTime();
				EventTopic c = new EventTopic(rs.getString(7));
				PatientId p = new PatientId(rs.getInt(8));

				Event e = new Event(i, t, d, c, v,start,end, p);
				
				events.put(e.id(), e);	
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return events.values();
	}
	
	// GET
	@Override
	public Event ofId(EventId anId) {
		String sql = "SELECT * from event WHERE id=" + anId.value() + ";";
		Event e = null;
		Statement st;
		try {
			st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				EventId i = new EventId(rs.getInt(1));
				EventTitle t = new EventTitle(rs.getString(2));
				EventDescription d = new EventDescription(rs.getString(3));
				EventVenue v = new EventVenue(rs.getString(4));
				LocalDateTime start = rs.getTimestamp(5).toLocalDateTime();
				LocalDateTime end = rs.getTimestamp(6).toLocalDateTime();
				EventTopic c = new EventTopic(rs.getString(7));
				PatientId p = new PatientId(rs.getInt(8));

				e = new Event(i, t, d, c, v,start,end, p);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return e;
	}
	
	public Event ofAppointment(MessageId appointmentId) {
		return events.values().stream()
				.filter(e -> e.topic().value().equals("Appointments"))
				.filter(e -> Integer.parseInt(e.description().value()) == appointmentId.value())
				.findFirst()
				.get();
	}

}
