package model;
import java.sql.Connection;

import java.util.logging.Logger;


import java.sql.SQLException;
import java.sql.*;
import java.util.*;


public class AdminManager {
	
	private static final Logger logger = Logger.getLogger(AdminManager.class.getName());
	public List <ProductBean> getCatalogo(){
		
		ArrayList <ProductBean> prodotti= new ArrayList <>();
		
		Connection con = null;
		Statement s=null;
		try {
			
			con = DriverManagerConnection.createDBConnection();
			String query= "SELECT * FROM ARTICOLO";
			s = con.createStatement();
			ResultSet rs = s.executeQuery(query);
			
			while (rs.next()) {
				
				prodotti.add(new ProductBean(rs.getInt("id"), rs.getString("nome"),rs.getString("descrizione"),rs.getFloat("prezzo"), rs.getString("materiale"), rs.getString("misura"),rs.getString("marca"), rs.getString("genere"), rs.getString("tipo"),rs.getFloat("sconto"),rs.getInt("quantita")));
				
			}
			
			
			
		} catch(Exception e){
			logger.severe(e.getMessage());
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		
		return prodotti;
	}
	
	public void removeProduct(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		PreparedStatement ps2=null;

		try {
		    con = DriverManagerConnection.createDBConnection();

		    
		    int idd = Integer.parseInt(id);
		    String query = "DELETE FROM articolo WHERE Id = ?";
		    ps = con.prepareStatement(query);
		    ps.setInt(1, idd);

		    ps.executeUpdate();

		    

		} catch (SQLException e) {
			logger.severe("Error executing query: " + e.getMessage());
		    try {

			    
			    int idd = Integer.parseInt(id);
			    String query = "UPDATE articolo set Quantita = 0 WHERE Id = ?";
			    ps2 = con.prepareStatement(query);
			    ps2.setInt(1, idd);

			    ps2.executeUpdate();
			    } catch(Exception e1) {
			    	e1.printStackTrace();
			    }
		    
		} catch (NumberFormatException e) {
			logger.severe(e.getMessage());
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
	}
	
	public void addProduct(String nome, String descrizione, float prezzo, String materiale, String misura,
            String marca, String genere,String tipo, float sconto, int quantita){
		
		Connection con= null;
		PreparedStatement ps=null;
		
		try {
			
			con= DriverManagerConnection.createDBConnection();
			String q="INSERT INTO articolo "
					+ "(`Prezzo`, `Descrizione`, `Nome`, `Materiale`, `Misura`, `Marca`, `Quantita`, `Sconto`, `Genere`, `Tipo`)"
					+ "Values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			ps = con.prepareStatement(q);
			
			ps.setFloat(1, prezzo);
			ps.setString(2, descrizione);
			ps.setString(3, nome);
			ps.setString(4, materiale);
			ps.setString(5, misura);
			ps.setString(6, marca);
			ps.setInt(7, quantita);
			ps.setFloat(8, sconto);
			ps.setString(9, genere);
			ps.setString(10, tipo);
			
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		
	}
	
	public void changeProduct(String id, String nome, String descrizione, String prezzo, String materiale, String misura,
            String marca, String genere,String tipo, String sconto, String quantita) {
		
		
		Connection con=null;
		PreparedStatement ps=null;
		
		try {
			
			con=DriverManagerConnection.createDBConnection();
			
			String query = "UPDATE articolo SET ";
			List<String> updates = new ArrayList<>();

			if (!prezzo.isEmpty()) {
			    updates.add("Prezzo = ?");
			}
			if (!descrizione.isEmpty()) {
			    updates.add("Descrizione = ?");
			}
			if (!nome.isEmpty()) {
			    updates.add("Nome = ?");
			}
			if(!materiale.isEmpty()) {
				updates.add("Materiale = ?");
				
			}
			if(!misura.isEmpty()) {
				updates.add("Misura = ?");
				
			}
			if(!quantita.isEmpty()) {
				updates.add("Quantita = ?");
				
			}
			if(!marca.isEmpty()) {
				updates.add("Marca = ?");
				
			}
			if(!sconto.isEmpty()) {
				updates.add("Sconto = ?");
				
			}
			if(!genere.isEmpty()) {
				updates.add("Genere = ?");
				
			}
			if(!tipo.isEmpty()) {
				updates.add("Prezzo = ?");
				
			}
			

			query += String.join(", ", updates);
			query += " WHERE Id = ?";

			ps = con.prepareStatement(query);

			int paramIndex = 1;

			if (!prezzo.isEmpty()) {
			    ps.setString(paramIndex++, prezzo);
			}
			if (!descrizione.isEmpty()) {
			    ps.setString(paramIndex++, descrizione);
			}
			if (!nome.isEmpty()) {
			    ps.setString(paramIndex++, nome);
			}
			if(!materiale.isEmpty()) {
				ps.setString(paramIndex++, materiale);
				
			}
			if(!misura.isEmpty()) {
				ps.setString(paramIndex++, misura);
				
			}
			if(!quantita.isEmpty()) {
				ps.setString(paramIndex++, quantita);
				
			}
			if(!marca.isEmpty()) {
				ps.setString(paramIndex++, marca);
				
			}
			if(!sconto.isEmpty()) {
				ps.setString(paramIndex++, sconto);
				
			}
			if(!genere.isEmpty()) {
				ps.setString(paramIndex++, genere);
				
			}
			if(!tipo.isEmpty()) {
				ps.setString(paramIndex++, tipo);
				
			}
			// Imposta altri parametri per gli altri campi che desideri aggiornare

			ps.setString(paramIndex, id);
			
			
			
			ps.executeUpdate();

			
		} catch (Exception e){
			logger.severe(e.getMessage());
	} finally {
		
		DriverManagerConnection.releaseConnection(con);
	    
	}
}
	
	
	public List<Float> getInfo() {
		
		ArrayList <Float> info=new ArrayList<>();
		Connection con=null;
		PreparedStatement s=null;
		try {
			
			con = DriverManagerConnection.createDBConnection();
			
			//customer
			String query= "SELECT COUNT(Id) AS Conteggio FROM utente ";
			s=con.prepareStatement(query);
			ResultSet rs=s.executeQuery();
			
			while(rs.next()) {
			info.add(rs.getFloat("Conteggio"));
			
			}
		
			//Items
			String query2= "SELECT COUNT(Id) AS c FROM articolo";
			s=con.prepareStatement(query2);
			rs=s.executeQuery(query2);
			while(rs.next()) {
				info.add(rs.getFloat("c"));
				}
			
			//orders
			String query3= "SELECT COUNT(Id) AS cc FROM ordine";
			s=con.prepareStatement(query3);
			rs=s.executeQuery(query3);
			while(rs.next()) {
				info.add(rs.getFloat("cc"));
				}
			
			
			//income
			String query4= "SELECT SUM(PrezzoTotale) as somma FROM ordine";
			s=con.prepareStatement(query4);
			rs=s.executeQuery();
			while(rs.next()) {
				info.add(rs.getFloat("somma"));
				}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		
		 return info;
	}
	
	public List<OrderBean> getOrdini(){
		
		ArrayList<OrderBean> ordini = new ArrayList<>();
		
		Connection con=null;
		Statement s=null;
		
		try {
			
			con = DriverManagerConnection.createDBConnection();
			
			String query="SELECT * FROM ordine";
			s= con.createStatement();
			ResultSet rs= s.executeQuery(query);
			
			while (rs.next()) {
				ordini.add(new OrderBean(rs.getInt("id"), rs.getInt("id_Cliente"), rs.getFloat("prezzototale"), rs.getDate("DataO")));
			}
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
			
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		
		return ordini;
	}
	
	public List<UserBean> getUtenti(){
		ArrayList<UserBean> utenti= new ArrayList<>();
		Connection con=null;
		Statement s= null;
		
		try {
			
			con=DriverManagerConnection.createDBConnection();
			String query="SELECT * FROM utente";
			s= con.createStatement();
			ResultSet rs= s.executeQuery(query);
			
			while(rs.next()) {
				utenti.add(new UserBean(rs.getInt("Id"), rs.getString("Nome"), rs.getString("Cognome"), rs.getBoolean("isAdmin"), rs.getString("Telefono"), rs.getString("Email"), ""));
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			
			DriverManagerConnection.releaseConnection(con);
		    
		}
		return utenti;
	}
	
}
