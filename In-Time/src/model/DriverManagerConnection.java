package model;
import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Logger;
import java.util.*;

public class DriverManagerConnection  {
	private static final Logger logger = Logger.getLogger(DriverManagerConnection.class.getName());
	
	private static List<Connection> freeDbConnections;

    static {
        freeDbConnections = new LinkedList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	logger.severe(e.getMessage());
        } 
    }

    private DriverManagerConnection() {
        
    }
	
	public static synchronized Connection createDBConnection() throws SQLException {
		if (!freeDbConnections.isEmpty()) {
            // If there are free connections, remove and return one
            return freeDbConnections.remove(0);
        }
		Connection newConnection = null;
        String ip = "localhost";
        String port = "3306";
        String db = "intime";
        String username = "root";
        String password = "root";


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
        	logger.severe(e.getMessage());
        }
        newConnection = DriverManager.getConnection("jdbc:mysql://"+ ip+":"+ port+"/"+db+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", username, password);


        return newConnection;
	}

	public static synchronized void releaseConnection(Connection connection) {
        if (connection != null) {
            // Add the released connection back to the pool
            freeDbConnections.add(connection);
        }
    }
}