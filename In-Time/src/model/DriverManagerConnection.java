package model;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;

import java.util.*;

public class DriverManagerConnection  {
	
	private DriverManagerConnection() {
		
	}

	private static List<Connection> freeDbConnections;

	static {
		freeDbConnections = new LinkedList<>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	public static synchronized Connection createDBConnection() throws SQLException {
		Connection newConnection = null;
        String ip = "localhost";
        String port = "3306";
        String db = "intime";
        String username = "root";
        String password = "root";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", username, password);


        return newConnection;
	}

	public static synchronized void releaseConnection(Connection connection) {
		if(connection != null) freeDbConnections.add(connection);
	}
}