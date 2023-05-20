package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProductManager {
	public ArrayList<CatalogoBean> getCatalogo(){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = "SELECT Id,Prezzo,Nome FROM intime.articolo";
          Statement s= newConnection.createStatement();
          ResultSet rs=s.executeQuery(q);
          while(rs.next()) {
        	   catalogo.add(new CatalogoBean(rs.getInt("id"),rs.getString("nome"),rs.getFloat("prezzo")));
          }
		} catch (Exception e) {
			
		}
		
		return catalogo;
	}
	public ProductBean getProduct(int id) {
		Connection c = null;
		try {
		c = DriverManagerConnection.createDBConnection();
		PreparedStatement Ps = c.prepareStatement("SELECT * FROM intime.articolo WHERE id=?");
		Ps.setInt(1, id);
		 
		ResultSet rs=  Ps.executeQuery();
		 while(rs.next()) {
			 ProductBean p = new ProductBean(id,rs.getString("nome"),rs.getString("descrizione"),rs.getFloat("Prezzo"),rs.getString("Materiale"),rs.getString("misura"),rs.getString("marca"),rs.getString("genere"),rs.getString("tipo"),rs.getFloat("sconto"),rs.getInt("quantita"));
			 return p;
		 }
		 
		} catch (Exception e) {
			 System.out.println(e);
		}
		
		
		return null;
	}
}