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
<<<<<<< Updated upstream
		Connection newConnection = null;
		try {
        newConnection = DriverManagerConnection.getConnection();
          String q = ""
		};
		
=======
		String url = "jdbc:mysql://localhost:3306/intime";
		String password = "1234";
		String user = "root";
		try {
			Connection connection = DriverManager.getConnection(url, user, password);
			
			Statement statemant = connection.createStatement();
			ResultSet  rs = statemant.executeQuery("SELECT * FROM articolo;");
			
			while(rs.next()) {
				
				products.add(new ProductBean(rs.getString("Nome"),rs.getFloat("Prezzo"),1));
			}
			
			
			request.setAttribute("prodotti", products);
>>>>>>> Stashed changes
			
			RequestDispatcher view = request.getRequestDispatcher("catalogo.jsp");
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


