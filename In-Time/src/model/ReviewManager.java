package model;
 
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.logging.Logger;

public class ReviewManager {

	private static final Logger logger = Logger.getLogger(ReviewManager.class.getName());
	
	public List<ReviewBean> getReviewsForProduct(int idArticolo) {
		
        List<ReviewBean> reviewsForProduct = new ArrayList<>();
        Connection newConnection = null;
        PreparedStatement p=null;
        try {
        	newConnection = DriverManagerConnection.createDBConnection();
            String query = "SELECT r.Testo AS Recensione, r.Voto, CONCAT(u.Nome, ' ', u.Cognome) AS Utente\r\n" + 
            		"FROM Recensione r\r\n" + 
            		"JOIN Utente u ON r.Id_Utente = u.Id\r\n" + 
            		"WHERE r.Id_Articolo = ?";
                    p = newConnection.prepareStatement(query);
                    p.setInt(1,idArticolo);
                    ResultSet resultSet = p.executeQuery();
                    while (resultSet.next()) {
                        String recensione = resultSet.getString("Recensione");
                        String utente = resultSet.getString("Utente");
                        
                        double voto = resultSet.getDouble("Voto");
                        
                        reviewsForProduct.add(new ReviewBean(utente,recensione,voto));
                    }
                    
        } catch(Exception e){
        	logger.severe(e.getMessage());
        }  finally {
        	try {
        		if (p != null) {
                    p.close();
                }
			} catch (SQLException e) {
				
				logger.severe(e.getMessage());
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
        
        return reviewsForProduct;
    }
	
	public void insertReview(ReviewBean review) {
        Connection newConnection = null;
        PreparedStatement p= null;
        try {
        	newConnection = DriverManagerConnection.createDBConnection();
            String query = "INSERT INTO `intime`.`recensione` (`Id_Utente`, `Id_Articolo`, `Voto`, `Testo`) VALUES (?,?,?,?); ";
                    p = newConnection.prepareStatement(query);
                    p.setInt(1,review.getIdUtente());
                    p.setInt(2, review.getIdArticolo());
                    p.setDouble(3, review.getVoto());
                    p.setString(4,review.getTesto() );
                    p.executeUpdate();
                    
                    
        } catch(Exception e){
        	logger.severe(e.getMessage());
        }  finally {
        	try {
        		if (p != null) {
                    p.close();
                }
			} catch (SQLException e) {
				
				logger.severe(e.getMessage());
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
	}
	
	public String listToStringJSON(Collection<ReviewBean> list) {
		 
		Gson gson = new Gson();
   	    return gson.toJson(list);
   	   
	   
	 
	}
	
	public List<ReviewBean> jSONStringToList(String cart){
		 
    	 String recensioni;
		try {
			recensioni = URLDecoder.decode(cart, "UTF-8");
			Gson gson = new Gson();
	    	return gson.fromJson(recensioni, new TypeToken<ArrayList<ReviewBean>>() {}.getType());
	    	 
			
		} catch (UnsupportedEncodingException e) {
			
			logger.severe(e.getMessage());
		}
		
    	 return new ArrayList<>();
		
	}
}
