package controller;
import model.DriverManagerConnection;
import model.ProductBean;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Servlet implementation class Catalogo
 */
@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */ 
    public Catalogo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<ProductBean> products = new ArrayList<>();
		Connection newConnection = null;
		try {
        newConnection = DriverManagerConnection.getConnection();
          String q = "SELECT * FROM intime.articolo;";
          Statement s= newConnection.createStatement();
          ResultSet rs=s.executeQuery(q);
          while(rs.next()) {
        	  products.add(new ProductBean(rs.getString("nome"), rs.getString("descrizione"), rs.getInt("quadrante")));
          }
		} catch (Exception e) {
			
		}
		
		request.setAttribute("prodotti", products);
		RequestDispatcher view = request.getRequestDispatcher("JSP/catalogo.jsp");
		view.forward(request, response);


		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}





/*jsp -> sarvlet -> model -> db 

model -> interfaccia al db 
classe java che si connette al db e svolge le funzioni 
*/


