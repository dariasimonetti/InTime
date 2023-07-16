package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class UserManager {
	
	private static final Logger logger = Logger.getLogger(UserManager.class.getName());

	public int registrati(String nome, String cognome, boolean isadmin, String telefono, String email,String password, HttpSession session ){
		Connection newConnection = null;
		PreparedStatement ps= null;
		PreparedStatement ps2= null;
		PreparedStatement ps3= null;
		
		
		try {

			newConnection = DriverManagerConnection.createDBConnection();
			
			String q3= "Select email from Utente;";
			ps3 = newConnection.prepareStatement(q3);
			ResultSet rs3 = ps3.executeQuery();
			
			while (rs3.next()) {
				
				if ( email.equalsIgnoreCase(rs3.getString("email"))){
					logger.severe("email gia presente");
					
					return -2;
				}
				
			}
			
			
			String query = "insert into Utente(isAdmin,Nome,Cognome,Telefono,Email,Pass) values (?,?,?,?,?,?);";
			ps= newConnection.prepareStatement(query);
			ps.setBoolean(1, isadmin);
			ps.setString( 2 , nome);
			ps.setString( 3 , cognome);
			ps.setString( 4 , telefono);
			ps.setString( 5 , email);
			
			String encryptedPassword = encryptSHA512(password);
	        ps.setString(6, encryptedPassword);
			
	        
			int count = ps.executeUpdate();
			
			
			String q2= "Select * from Utente where email = ?;";
			ps2 = newConnection.prepareStatement(q2);
			ps2.setString(1, email);
			
			ResultSet rs = ps2.executeQuery();
			
			
			
			if (rs.next()) {
				session.setAttribute("name", nome);
				session.setAttribute("id", rs.getInt("id"));
			}
		
			
			
			if (count>0) {
				
				
				
				return 1;
			} else {
				return 0;
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
			return -1;
		}   finally {
			
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
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
	
	public int modifica(String id, String nome, String cognome, String telefono, String email,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			
			con=DriverManagerConnection.createDBConnection();
			

			String query = "UPDATE utente SET ";
			List<String> updates = new ArrayList<>();

			if (!nome.isEmpty()) {
			    updates.add("Nome = ?");
			}
			if (!cognome.isEmpty()) {
			    updates.add("Cognome = ?");
			}
			if (!telefono.isEmpty()) {
			    updates.add("Telefono = ?");
			}
			if(!email.isEmpty()) {
				updates.add("Email = ?");
				
			}
			if(!password.isEmpty()) {
				updates.add("Pass = ?");
			}
			

			query += String.join(", ", updates);
			query += " WHERE Id = ?";

			ps = con.prepareStatement(query);

			int paramIndex = 1;
			if (!nome.isEmpty()) {
				ps.setString(paramIndex++, nome);
			}
			if (!cognome.isEmpty()) {
				ps.setString(paramIndex++, cognome);
			}
			if (!telefono.isEmpty()) {
				ps.setString(paramIndex++, telefono);
			}
			if(!email.isEmpty()) {
				ps.setString(paramIndex++,email);
				
			}
			if(!password.isEmpty()) {
				ps.setString(paramIndex++, password);
			}


			ps.setString(paramIndex, id);
			
			
			int count = ps.executeUpdate();
			if (count>0) {
				return 1;
			} else {
				return 0;
			}
			
			
			
		} catch (Exception e){
			e.printStackTrace();
			return -1;
	} finally {
		
		DriverManagerConnection.releaseConnection(con);
	    
	}

	}
	
	public DatiSpedizioneBean datiSpedizione(String id){
		
		Connection con=null;
		PreparedStatement ps=null;
		DatiSpedizioneBean ds=null;
		
		try {
			
			con= DriverManagerConnection.createDBConnection();
			String query="Select * from DatiSpedizione where IdUtente=?";
			ps=con.prepareStatement(query);
			
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ds= new DatiSpedizioneBean(rs.getString("Nome"), rs.getString("Cognome"), rs.getString("Civico"), rs.getString("Via"), rs.getInt("CAP"), rs.getString("Citta"));
			}
			
			return ds;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		return ds;
	}
	
	public PagamentoBean metodopag(String id) {
		
		Connection con=null;
		PreparedStatement ps=null;
		PagamentoBean card=null;
		
		try {
			
			con= DriverManagerConnection.createDBConnection();
			String query="Select * from MetodoPagamento where IdUtente=?";
			ps=con.prepareStatement(query);
			
			int idu= Integer.parseInt(id);
			ps.setInt(1, idu);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				card= new PagamentoBean(rs.getString("NomeTitolare"), rs.getString("CognomeTitolare"), rs.getString("NumeroCarta"), rs.getString("Scadenza"));
			}
			
			return card;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		return card;
		
	}
	
public UserBean getUtente(int id) {
		
		Connection con=null;
		PreparedStatement ps=null;
		UserBean utente=null;
		
		try {
			
			con= DriverManagerConnection.createDBConnection();
			String query="Select * from Utente where Id=?";
			ps=con.prepareStatement(query);
			
			
			ps.setInt(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				utente= new UserBean(id, rs.getString("Nome"), rs.getString("Cognome"), rs.getBoolean("isAdmin"), rs.getString("Telefono"), rs.getString("Email"), rs.getString("Pass"));
			}
			
			return utente;
			
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		return utente;
		
	}
}
