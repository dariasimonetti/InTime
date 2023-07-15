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
 * Servlet implementation class CatalogoDonna
 */
@WebServlet("/CatalogoDonna")
public class CatalogoDonna extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoDonna() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<CatalogoBean> catalogo = new ArrayList<>();
		ProductManager Pm = new ProductManager();
		catalogo = Pm.getCatalogoDonna();
		request.setAttribute("prodotti", catalogo);
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