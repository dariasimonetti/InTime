package model;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpSession;



public class UserManager {

	public int Registrati(String nome, String cognome, boolean isadmin, String telefono, String email,String password, HttpSession session ){
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
					System.out.println("email gia presente");
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
			
	        System.out.println("La passswordss" + encryptedPassword);
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
		} finally {
			try {
				newConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
	
	public int Modifica(String id, String nome, String cognome, String telefono, String email,String password) {
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			
			con=DriverManagerConnection.createDBConnection();
			
			String set="SET ";
			
			if(!nome.isEmpty()) {
				set=set+"Nome = '"+ nome + "',";	
			}
			if(!cognome.isEmpty()) {
				set=set+"Cognome = '"+ cognome + "',";	
			}
			if(!telefono.isEmpty()) {
				set=set+"Telefono = '"+ telefono + "',";	
			}
			if(!email.isEmpty()) {
				set=set+"Email = '"+ email + "',";
			}
			if(!password.isEmpty()) {
				String encryptedPassword = encryptSHA512(password);
				set=set+"Pass = '"+ encryptedPassword + "',";
			}
			
			set=set.substring(0,  set.length() - 1);
			
			String query="UPDATE utente "+set+" WHERE Id=?";
			ps=con.prepareStatement(query);
			
			
			
			ps.setString(1, id);
			
			
			int count = ps.executeUpdate();
			if (count>0) {
				return 1;
			} else {
				return 0;
			}
			
			
			
		} catch (Exception e){
			System.out.println(e);
			return -1;
	}finally {
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		}
		return utente;
		
	}
}
