package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



public class ProductManager {
	private static final Logger logger = Logger.getLogger(ProductManager.class.getName());

	public List<CatalogoBean> getCatalogo(){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		Statement s=null;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = "SELECT Id,Prezzo,Nome FROM intime.articolo";
          s= newConnection.createStatement();
          ResultSet rs=s.executeQuery(q);
          while(rs.next()) {
        	   catalogo.add(new CatalogoBean(rs.getInt("id"),rs.getString("nome"),rs.getFloat("prezzo")));
          }
          
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if (s != null) {
	                s.close();
	            }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return catalogo;
	}
	
	public List<CatalogoBean> getCatalogoFiltrato(float partire, float fino, String tipo, String genere){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		PreparedStatement ps=null;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = null;
          

          if (tipo != null && genere != null) {
              q = "SELECT Id, Prezzo, Nome FROM intime.articolo WHERE Prezzo > ? AND Prezzo < ? AND Tipo = ? AND Genere = ?";
              ps = newConnection.prepareStatement(q);
              ps.setFloat(1, partire);
              ps.setFloat(2, fino);
              ps.setString(3, tipo);
              ps.setString(4, genere);
          } else if (tipo != null) {
              q = "SELECT Id, Prezzo, Nome FROM intime.articolo WHERE Prezzo > ? AND Prezzo < ? AND Tipo = ?";
              ps = newConnection.prepareStatement(q);
              ps.setFloat(1, partire);
              ps.setFloat(2, fino);
              ps.setString(3, tipo);
          } else if (genere != null) {
              q = "SELECT Id, Prezzo, Nome FROM intime.articolo WHERE Prezzo > ? AND Prezzo < ? AND Genere = ?";
              ps = newConnection.prepareStatement(q);
              ps.setFloat(1, partire);
              ps.setFloat(2, fino);
              ps.setString(3, genere);
          } else {
              q = "SELECT Id, Prezzo, Nome FROM intime.articolo WHERE Prezzo > ? AND Prezzo < ?";
              ps = newConnection.prepareStatement(q);
              ps.setFloat(1, partire);
              ps.setFloat(2, fino);
          }

          ResultSet rs = ps.executeQuery();

          while (rs.next()) {
              catalogo.add(new CatalogoBean(rs.getInt("id"), rs.getString("nome"), rs.getFloat("prezzo")));
          }
          
          
		} catch (Exception e) {
			 logger.log(Level.SEVERE, "Si è verificato un errore", e);
		}  finally {
			try {
				if (ps != null) {
	                ps.close();
	            }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return catalogo;
	}
	
	public List<CatalogoBean> getCatalogoUomo(){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		Statement s= null;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = "SELECT Id,Prezzo,Nome FROM intime.articolo WHERE Genere='Uomo' Or Genere='Unisex'";
          s= newConnection.createStatement();
          ResultSet rs=s.executeQuery(q);
          while(rs.next()) {
        	   catalogo.add(new CatalogoBean(rs.getInt("id"),rs.getString("nome"),rs.getFloat("prezzo")));
          }
          
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if (s != null) {
	                s.close();
	            }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return catalogo;
	}
	
	public List<CatalogoBean> getCatalogoDonna(){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		Statement  s=null;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = "SELECT Id,Prezzo,Nome FROM intime.articolo WHERE Genere='Donna' or Genere='Unisex'";
          s= newConnection.createStatement();
          ResultSet rs=s.executeQuery(q);
          while(rs.next()) {
        	   catalogo.add(new CatalogoBean(rs.getInt("id"),rs.getString("nome"),rs.getFloat("prezzo")));
          }
          
          
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if (s != null) {
	                s.close();
	            }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return catalogo;
	}
	
	public List<CatalogoBean> getCatalogoCinturino(){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		Statement s = null;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = "SELECT Id,Prezzo,Nome FROM intime.articolo WHERE Tipo='Cinturino'";
          s= newConnection.createStatement();
          ResultSet rs=s.executeQuery(q);
          while(rs.next()) {
        	   catalogo.add(new CatalogoBean(rs.getInt("id"),rs.getString("nome"),rs.getFloat("prezzo")));
          }
          
          
		} catch (Exception e) {
			e.printStackTrace();
			
		}  finally {
			try {
				if (s != null) {
	                s.close();
	            }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return catalogo;
	}
	
	
	public List<CatalogoBean> getCatalogoOrologio(){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		Statement  s = null;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = "SELECT Id,Prezzo,Nome FROM intime.articolo WHERE Tipo='Orologio'";
          s= newConnection.createStatement();
          ResultSet rs=s.executeQuery(q);
          while(rs.next()) {
        	   catalogo.add(new CatalogoBean(rs.getInt("id"),rs.getString("nome"),rs.getFloat("prezzo")));
          }
          
          
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			try {
				if (s != null) {
	                s.close();
	            }
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return catalogo;
	}
	
	
	
	public ProductBean getProduct(int id) {
		Connection c = null;
		try {
		c = DriverManagerConnection.createDBConnection();
		PreparedStatement ps = c.prepareStatement("SELECT * FROM intime.articolo WHERE id=?");
		ps.setInt(1, id);
		 
		ResultSet rs=  ps.executeQuery();
		 while(rs.next()) {
			 return new ProductBean(id,rs.getString("nome"),rs.getString("descrizione"),rs.getFloat("Prezzo"),rs.getString("Materiale"),rs.getString("misura"),rs.getString("marca"),rs.getString("genere"),rs.getString("tipo"),rs.getFloat("sconto"),rs.getInt("quantita"));
			
		 }
		 
		} catch (Exception e) {
			 e.printStackTrace();
		}  finally {
			
			DriverManagerConnection.releaseConnection(c);
		    
		}
		
		
		return null;
	}
	public List<OrderBean> getOrdini(int id){
		ArrayList<OrderBean> ordini= new ArrayList<>();
		Connection newConnection = null;
		PreparedStatement ps;
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          String q = "SELECT * FROM ordine where Id_cliente=?";
          
          ps= newConnection.prepareStatement(q);
          ps.setInt(1, id);
          
          ResultSet rs=ps.executeQuery();
          while(rs.next()) {
        	   
        	   ordini.add(new OrderBean(rs.getInt("Id"),rs.getInt("Id_Cliente"), rs.getFloat("PrezzoTotale"),rs.getDate("DataO")));
      		   }
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return ordini;
	}
	
	public List<CatalogoBean> getCatalogoSearch(String cerca){
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		Connection newConnection = null;
		PreparedStatement s = null;
		String q = "select *  from Articolo where Nome LIKE ?";
		try {
          newConnection = DriverManagerConnection.createDBConnection();
          s = newConnection.prepareStatement(q);
          s.setString(1, cerca + "%");
          ResultSet rs= s.executeQuery();
          while(rs.next()) {
        	   catalogo.add(new CatalogoBean(rs.getInt("id"),rs.getString("nome"),rs.getFloat("prezzo")));
          }
          
          
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			try {
				if (s != null) {
	                s.close();
	            }
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			DriverManagerConnection.releaseConnection(newConnection);
		    
		}
		
		return catalogo;
	}
}