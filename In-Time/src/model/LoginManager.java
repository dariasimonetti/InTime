package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;

public class LoginManager {
	private static final Logger logger = Logger.getLogger(LoginManager.class.getName());
	public int accedi (String email, String pass, HttpSession session) {
		
		Connection newConnection = null;
		
		try {

			newConnection = DriverManagerConnection.createDBConnection();
			String query = "select *  from utente where Email = ? and Pass = ?";
			
			PreparedStatement ps = newConnection.prepareStatement(query);
			
			ps.setString( 1 , email);
			String encryptedPassword = encryptSHA512(pass);
	        ps.setString(2, encryptedPassword);
	        
	        
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				rs.getString("nome");
				
				
				
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("name", rs.getString("nome"));
				session.setAttribute("cognome", rs.getString("cognome"));
				session.setAttribute("email", rs.getString("email"));
				
				boolean admin=rs.getBoolean("isAdmin");
				
				
				if(!admin) {
					return 1;
				}else if(admin) {
					session.setAttribute("admin", admin);
					return 2;
				}
				
			} 
			
			
		}catch(Exception e) {
			logger.severe(e.getMessage());
			return -1;
			
		} finally {
			
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
			return 0;
		
	}
	
	private String encryptSHA512(String password) throws NoSuchAlgorithmException {
	    MessageDigest md = MessageDigest.getInstance("SHA-512");
	    byte[] hashedPassword = md.digest(password.getBytes());
	    
	    StringBuilder sb = new StringBuilder();
	    for (byte b : hashedPassword) {
	        sb.append(String.format("%02x", b));
	    }
	    
	    return sb.toString();
	}
	
	public boolean isAdmin(String id) {
		boolean admin=false;
		
		Connection con=null;
		PreparedStatement ps=null;
		
		try {

			con = DriverManagerConnection.createDBConnection();
			String query = "select *  from utente where Id=?";
			
			ps = con.prepareStatement(query);
			
			ps.setString( 1 , id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin=rs.getBoolean("isAdmin");
			}
			
			}catch(Exception e) {
				logger.severe(e.getMessage());
			} finally {
				try {
					if (ps != null) {
		                ps.close();
		            }
				} catch (SQLException e) {
					
					logger.severe(e.getMessage());
				}
				DriverManagerConnection.releaseConnection(con);
			    
			}
		return admin;
	}
}