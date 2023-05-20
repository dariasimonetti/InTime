package model;

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
			ps.setString( 2 , pass);
			
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				
				String queryNome = "select nome from utente where Email = ?";
				PreparedStatement ps2 = newConnection.prepareStatement(queryNome);
				ps2.setString( 1 , email);
				
				ResultSet rs2 = ps2.executeQuery();
				
				rs2.next();
				
				String nome = rs2.getString("nome");
				
				System.out.println("Il nome è:" + nome);
				
				session.setAttribute("name", nome);
				
				System.out.println("Sessione nome" + session.getAttribute("name"));
				return 1;
			} else {
				return 0;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			return -1;
			
		}
		
	}
}
