package model;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;



public class UserManager {

	
	public int Registrati(String nome, String cognome, boolean isadmin, String telefono, String email,String password, HttpSession session){
		Connection newConnection = null;
		PreparedStatement ps= null;
		
		try {

			newConnection = DriverManagerConnection.createDBConnection();
			String query = "insert into Utente(isAdmin,Nome,Cognome,Telefono,Email,Pass) values (?,?,?,?,?,?);";
			ps= newConnection.prepareStatement(query);
			ps.setBoolean(1, isadmin);
			ps.setString( 2 , nome);
			ps.setString( 3 , cognome);
			ps.setString( 4 , telefono);
			ps.setString( 5 , email);
			ps.setString( 6 , password);
			System.out.println(ps.toString());
			
			int count = ps.executeUpdate();
			if (count>0) {
				
				session.setAttribute("name", nome);
				
				System.out.println("Sessione nome" + session.getAttribute("name"));
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
}
