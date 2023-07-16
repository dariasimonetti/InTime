package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CatalogoBean;
import model.ProductManager;

/**
 * Servlet implementation class CatalogoUomo
 */
@WebServlet("/CatalogoUomo")
public class CatalogoUomo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CatalogoUomo() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductManager pm = new ProductManager();
		ArrayList<CatalogoBean> catalogo = (ArrayList<CatalogoBean>) pm.getCatalogoUomo();
		 // Ottenere i nomi delle sottodirectory dal catalogo
        List<String> subdirectories = new ArrayList<>();
        for (CatalogoBean prodotto : catalogo) {
            String subdirectory = String.valueOf(prodotto.getId());   
            subdirectories.add(subdirectory);
        }
        
        
        ServletContext servletContext = getServletContext();
        // Ottenere i percorsi delle prime immagini per ogni sottodirectory
        List<String> firstImagePaths = pm.getFirstImagePaths(subdirectories, servletContext);
        
        request.setAttribute("prodotti", catalogo);
        request.setAttribute("firstImagePaths", firstImagePaths);
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