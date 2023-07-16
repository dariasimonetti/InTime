package model;
 
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class ReviewManager {
	private List<ReviewBean> recensioni;
	
	
	public List<ReviewBean> getReviewsForProduct(int idArticolo) {
        List<ReviewBean> reviewsForProduct = new ArrayList<>();
        Connection newConnection = null;
        try {
        	newConnection = DriverManagerConnection.createDBConnection();
            String query = "SELECT r.Testo AS Recensione, r.Voto, CONCAT(u.Nome, ' ', u.Cognome) AS Utente\r\n" + 
            		"FROM Recensione r\r\n" + 
            		"JOIN Utente u ON r.Id_Utente = u.Id\r\n" + 
            		"WHERE r.Id_Articolo = ?";
                    PreparedStatement p = newConnection.prepareStatement(query);
                    p.setInt(1,idArticolo);
                    ResultSet resultSet = p.executeQuery();
                    while (resultSet.next()) {
                        String recensione = resultSet.getString("Recensione");
                        String utente = resultSet.getString("Utente");
                        
                        double voto = resultSet.getDouble("Voto");
                        
                        reviewsForProduct.add(new ReviewBean(utente,recensione,voto));
                    }
                    p.close();
        } catch(Exception e){
        	e.printStackTrace();
        }  finally {
			
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
        
        return reviewsForProduct;
    }
	
	public void IsertReview(ReviewBean review) {
        Connection newConnection = null;
        try {
        	newConnection = DriverManagerConnection.createDBConnection();
            String query = "INSERT INTO `intime`.`recensione` (`Id_Utente`, `Id_Articolo`, `Voto`, `Testo`) VALUES (?,?,?,?); ";
                    PreparedStatement p = newConnection.prepareStatement(query);
                    p.setInt(1,review.getIdUtente());
                    p.setInt(2, review.getIdArticolo());
                    p.setDouble(3, review.getVoto());
                    p.setString(4,review.getTesto() );
                    int rowsAffected = p.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("La query di inserimento è stata eseguita con successo. Numero di righe interessate: " + rowsAffected);
                    } else {
                        System.out.println("La query di inserimento non ha avuto successo.");
                    }
                    p.close();
        } catch(Exception e){
        	e.printStackTrace();
        }  finally {
			
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
	}
	
	public String ListToStringJSON(Collection<ReviewBean> list) {
		 
		Gson gson = new Gson();
   	    String json = gson.toJson(list);
   	   
	    return json;
	 
	}
	
	public ArrayList<ReviewBean> JSONStringToList(String Cart){
		 
    	 String recensioni;
		try {
			recensioni = URLDecoder.decode(Cart, "UTF-8");
			Gson gson = new Gson();
	    	 ArrayList<ReviewBean> recensioniL = gson.fromJson(recensioni, new TypeToken<ArrayList<ReviewBean>>() {}.getType());
	    	 return recensioniL;
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
    	 return null;
		
	}
}
