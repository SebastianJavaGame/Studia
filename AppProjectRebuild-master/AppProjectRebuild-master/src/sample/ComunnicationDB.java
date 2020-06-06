package sample;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComunnicationDB {
	private static Statement stmt = null;
	
	public static void init(Connection conn) {
		try {
	        stmt = conn.createStatement();
	        
	    } catch (SQLException e ) {
	        e.printStackTrace();
	    }
	}
	
	public static void closeStatement() {
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static List<Person> getAllData() throws SQLException {
		List<Person> list = new ArrayList<Person>();
		String query = "SELECT * FROM Table_1";
		
		ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {      
        	list.add(new Person(
        			rs.getString(1),
        			rs.getString(2),
        			rs.getString(3),
        			rs.getString(4),
        			rs.getString(5)
        			));
        }
        
        return list;
	}
	
	public static void addNewPerson(Person person) {
		try {
			stmt.executeUpdate("INSERT INTO Table_1 " + "VALUES ('" + person.getFirstName() + "','" + person.getLastName() + "','" + person.getBirthday() + "','" + person.getStatement() + "','" + person.getSalary() +"')");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void deletePerson(Person person) {
		try {
			stmt.executeUpdate("DELETE FROM Table_1 WHERE Name='" + person.getFirstName() + "' AND Surname='" + person.getLastName() + "' AND Birthday='" + person.getBirthday() + "' AND Statement='" + person.getStatement() + "' AND Salary='" + person.getSalary() + "'");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
