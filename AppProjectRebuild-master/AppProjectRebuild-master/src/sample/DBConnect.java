package sample;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	private static Connection conn = null;
	
	public static void runDateBase() {
        try {
 
        	String dbURL = "jdbc:sqlserver://wsql2016.wsei.lublin.pl;databaseName=InspectApp;";
            String user = "student32754";
            String pass = "8B49";
            conn = DriverManager.getConnection(dbURL, user, pass);
            if (conn != null) {
                DatabaseMetaData dm = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver name: " + dm.getDriverName());
                System.out.println("Driver version: " + dm.getDriverVersion());
                System.out.println("Product name: " + dm.getDatabaseProductName());
                System.out.println("Product version: " + dm.getDatabaseProductVersion());
            }else {
            	System.out.println("error");
            }
 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
	}
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static void closeConnect() {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
