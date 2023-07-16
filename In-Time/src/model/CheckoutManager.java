package model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class CheckoutManager {
	private static final Logger logger = Logger.getLogger(CheckoutManager.class.getName());
	public void nuovaSpedizione(int id, String nome, String cognome, String via, String civico, String cap, String citta) {
		
		Connection con=null;
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps3=null;
		
		try {
			
			con=DriverManagerConnection.createDBConnection();
			
			String query1="Select * from DatiSpedizione where Idutente=?";
			ps1=con.prepareStatement(query1);
			
			
			ps1.setInt(1,  id);
			
			ResultSet rs= ps1.executeQuery();
			
			if(rs.next()) {
				
				String query="UPDATE DatiSpedizione\n" + 
						"SET Nome = ?, Cognome = ?, Via = ?, Civico = ?, CAP = ?, Citta = ?\n" + 
						"WHERE IdUtente = ?";
				ps=con.prepareStatement(query);
				
				
				ps.setInt(7,  id);
				ps.setString(1, nome);
				ps.setString(2, cognome);
				ps.setString(3, via);
				ps.setString(4, civico);
				int c= Integer.parseInt(cap);
				ps.setInt(5, c);
				ps.setString(6, citta);
				
				ps.executeUpdate();
				
			} else {
				
				String query3="INSERT INTO DatiSpedizione (IdUtente, Nome, Cognome, Via, Civico, CAP, Citta)\n" + 
						"VALUES (?, ?, ?, ?, ?, ?, ?)";
				
				ps3=con.prepareStatement(query3);
				
				ps3.setInt(1,  id);
				ps3.setString(2, nome);
				ps3.setString(3, cognome);
				ps3.setString(4, via);
				ps3.setString(5, civico);
				int c= Integer.parseInt(cap);
				ps3.setInt(6, c);
				ps3.setString(7, citta);
				
				ps3.executeUpdate();
				
			}
			
		}catch (Exception e) {
			logger.severe(e.getMessage());
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
	}
	
	public void nuovaCarta(int id, String nometit, String cognometit, String numc, String scad) {
		
		Connection con= null;
		PreparedStatement ps = null;
		PreparedStatement ps1=null;
		PreparedStatement ps3=null;
		
		try {
			
			con=DriverManagerConnection.createDBConnection();
			
			String query1="Select * from MetodoPagamento where IdUtente =?";
			
			
			ps1=con.prepareStatement(query1);
			ps1.setInt(1,  id);
			
			ResultSet rs= ps1.executeQuery();
			if(rs.next()) {
				
				String query2="UPDATE MetodoPagamento\n" + 
						"SET NomeTitolare = ?, CognomeTitolare = ?, NumeroCarta = ?, Scadenza = ?\n" + 
						"WHERE IdUtente = ?";
				
				ps3=con.prepareStatement(query2);
				
				ps3.setString(1,  nometit);
				ps3.setString(2, cognometit);
				ps3.setString(3, numc);
				ps3.setString(4, scad);
				ps3.setInt(5,  id);
				
				ps3.executeUpdate();
				
				
			} else {

				String query="Insert into MetodoPagamento (IdUtente, NomeTitolare, CognomeTitolare, NumeroCarta, Scadenza)"
						+ "Values (?, ?, ?, ?, ?)";
				
				ps=con.prepareStatement(query);
				
				
				ps.setInt(1,  id);
				ps.setString(2,  nometit);
				ps.setString(3, cognometit);
				ps.setString(4, numc);
				ps.setString(5, scad);
				
				ps.executeUpdate();
				
			}
			
		} catch(Exception e){
			logger.severe(e.getMessage());
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		
	}
	
	public void inserisciOrdine(int id, List<ProductBean> carrello) {
		Connection con=null;
		PreparedStatement ps=null;
		
		double prezzototale=0.0;
		for(ProductBean p : carrello) {
			float psingolo= p.getPrezzo();
			prezzototale+=psingolo;
		}
		
		try {
			
			con=DriverManagerConnection.createDBConnection();
			//calcolo data 
			java.sql.Date data = new java.sql.Date(System.currentTimeMillis());
			
			String query="INSERT INTO Ordine (Id_Cliente, PrezzoTotale, DataO)\n" + 
					"VALUES (?, ?, ?);\n" + 
					"\n";
			
			 ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			
			
			
			ps.setInt(1, id);
			ps.setDouble(2, prezzototale);
			ps.setDate(3, data);
			
			int rowsAffected = ps.executeUpdate();

			// Retrieve the last inserted ID
			int idOrdine = -1;
			if (rowsAffected > 0) {
			    ResultSet generatedKeys = ps.getGeneratedKeys();
			    if (generatedKeys.next()) {
			        idOrdine = generatedKeys.getInt(1);
			    }
			}
			
			
			if(idOrdine!=-1) {
				
				Map<Integer, ProductBeanCounter> prodottiUnici = new HashMap<>();

		        // Itera attraverso gli elementi del carrello
		        for (ProductBean prodotto : carrello) {
		            int idProdotto = prodotto.getId();

		            // Controllo se il prodotto è già presente nella mappa
		            if (prodottiUnici.containsKey(idProdotto)) {
		                // Se presente, incrementa la quantità
		                ProductBeanCounter prodottoCounter = prodottiUnici.get(idProdotto);
		                prodottoCounter.incrementQuantity();
		            } else {
		                // Altrimenti, aggiungi un nuovo oggetto ProductBeanCounter alla mappa
		                ProductBeanCounter prodottoCounter = new ProductBeanCounter(prodotto);
		                prodottiUnici.put(idProdotto, prodottoCounter);
		            }
		        }

		        // Creazione di una lista di prodotti unici con le rispettive quantità
		        ArrayList<ProductBeanCounter> prodottiUniciList = new ArrayList<>(prodottiUnici.values());
		        
		        

		        // Stampa dei prodotti unici con le rispettive quantità
		        for (ProductBeanCounter prodottoCounter : prodottiUniciList) {
		            		            
		            String query2="Insert into Contiene (Id_Ordine,Id_Articolo, Id_Cliente, Quantita_Ordinata, Prezzo_Articolo)"
		            		+ "values (?,?,?, ?, ?)";
		            
		            ps= con.prepareStatement(query2);
		            
		            ps.setInt(1, idOrdine);
		            int idArticolo=prodottoCounter.getProduct().getId();
		            ps.setInt(2, idArticolo);
		            ps.setInt(3,  id);
		            int quantita= prodottoCounter.getQuantity();
		            ps.setInt(4, quantita);
		            float prezzo= prodottoCounter.getProduct().getPrezzo();
		            ps.setFloat(5, prezzo);
		            
		            ps.executeUpdate();
		            
		        }
		        
		        
		        
			}
			
		}catch(Exception e) {
			logger.severe(e.getMessage());
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
	}

}

class ProductBeanCounter {
    private ProductBean product;
    private int quantity;

    public ProductBeanCounter(ProductBean product) {
        this.product = product;
        this.quantity = 1;
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public ProductBean getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
