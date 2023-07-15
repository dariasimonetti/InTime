package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpSession;

public class LoginManager {
	
	public int Accedi (String email, String pass, HttpSession session) {
		
		Connection newConnection = null;
		
		try {

			newConnection = DriverManagerConnection.createDBConnection();
			String query = "select *  from utente where Email = ? and Pass = ?";
			
			PreparedStatement ps = newConnection.prepareStatement(query);
			
			ps.setString( 1 , email);
			String encryptedPassword = encryptSHA512(pass);
	        ps.setString(2, encryptedPassword);
	        
	        System.out.println(ps.toString());
			
			ResultSet rs = ps.executeQuery();
			System.out.println("prova accedi");
			while (rs.next()) {
				
				rs.getString("nome");
				
				
				
				session.setAttribute("id", rs.getInt("id"));
				session.setAttribute("name", rs.getString("nome"));
				session.setAttribute("cognome", rs.getString("cognome"));
				session.setAttribute("email", rs.getString("email"));
				
				System.out.println("prova");
				
				
				if(rs.getBoolean("isAdmin")==false) {
					return 1;
				}else if(rs.getBoolean("isAdmin")== true) {
					session.setAttribute("admin", rs.getBoolean("isAdmin"));
					return 2;
				}
				
			} 
			
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
			
		} finally {
		    try {
		    	
		    	DriverManagerConnection.releaseConnection(newConnection);
		    	
		    } catch (Exception e) {
		        System.out.println("Error closing connection: " + e.getMessage());
		    }
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
		try {

			con = DriverManagerConnection.createDBConnection();
			String query = "select *  from utente where Id=?";
			
			PreparedStatement ps = con.prepareStatement(query);
			
			ps.setString( 1 , id);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin=rs.getBoolean("isAdmin");
			}
			}catch(Exception e) {
				e.printStackTrace();
			} finally {
			    try {
			    	
			    	DriverManagerConnection.releaseConnection(con);
			    	
			    } catch (Exception e) {
			        System.out.println("Error closing connection: " + e.getMessage());
			    }
			}
		return admin;
	}
}