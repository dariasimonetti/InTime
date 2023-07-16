package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatalogoBean;
import model.ProductManager;

/**
 * Servlet implementation class Filtri
 */
@WebServlet("/Filtri")
public class Filtri extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Filtri() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		float partire=Float.parseFloat(request.getParameter("partire"));
		float fino=Float.parseFloat(request.getParameter("fino"));
		
		String tipo = request.getParameter("tipo");
		String genere = request.getParameter("genere");
		
		
    	
		ProductManager pm = new ProductManager();
		ArrayList<CatalogoBean> catalogo = pm.getCatalogoFiltrato(partire, fino, tipo , genere);
		request.setAttribute("prodotti", catalogo);
		RequestDispatcher view = request.getRequestDispatcher("catalogo.jsp");
		view.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}