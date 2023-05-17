package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminManager;



/**
 * Servlet implementation class AddProduct
 */
@WebServlet("/AddProduct")
public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		AdminManager am= new AdminManager();
		String nome= request.getParameter("nome");
		String descrizione= request.getParameter("descrizione");
		String materiale=  request.getParameter("materiale");
		String marca= request.getParameter("marca");
		String misura=  request.getParameter("misura");
		String genere=  request.getParameter("genere");
		String tipo= request.getParameter("tipo");
		float prezzo= Float.parseFloat(request.getParameter("prezzo"));
		float sconto= Float.parseFloat(request.getParameter("sconto"));
		int quantita= Integer.parseInt(request.getParameter("quantita"));
		
		am.addProduct(nome, descrizione, prezzo, materiale, misura, marca, genere, tipo, sconto, quantita);
		RequestDispatcher view = request.getRequestDispatcher("Inventory");
		view.forward(request, response);
		
	}

}
