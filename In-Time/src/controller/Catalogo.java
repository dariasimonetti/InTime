package controller;

import model.ProductManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CatalogoBean;



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
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductManager pm = new ProductManager();
		ArrayList<CatalogoBean> catalogo = (ArrayList<CatalogoBean>) pm.getCatalogo();
		request.setAttribute("prodotti", catalogo);
		 // Ottenere i nomi delle sottodirectory dal catalogo
        List<String> subdirectories = new ArrayList<>();
        for (CatalogoBean prodotto : catalogo) {
            String subdirectory = String.valueOf(prodotto.getId());   
            subdirectories.add(subdirectory);
        }
        // Ottenere i percorsi delle prime immagini per ogni sottodirectory
        List<String> firstImagePaths = getFirstImagePaths(subdirectories);
        for(String f: firstImagePaths) {
        	System.out.println(f);
        }
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
	private List<String> getFirstImagePaths(List<String> subdirectories) throws IOException {
	    List<String> firstImagePaths = new ArrayList<>();
	    ServletContext servletContext = getServletContext();
	    String contextPath = servletContext.getContextPath();
	    String basePath = servletContext.getRealPath("/");

	    for (String subdirectory : subdirectories) {
	        Path directoryPath = Paths.get(basePath, "img", subdirectory);

	        if (Files.isDirectory(directoryPath)) {
	            Optional<Path> firstImagePath = Files.walk(directoryPath)
	                    .filter(Files::isRegularFile)
	                    .findFirst();

	            if (firstImagePath.isPresent()) {
	                String imagePath = firstImagePath.get().toString();
	                imagePath = imagePath.replace(basePath, contextPath + "/");
	                firstImagePaths.add(imagePath);
	            }
	        }
	    }

	    return firstImagePaths;
	}

}