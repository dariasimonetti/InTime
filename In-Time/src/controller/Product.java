package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductBean;
import model.ProductManager;

@WebServlet("/Product")
public class Product extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public Product() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Questo metodo doGet è vuoto perché la servlet non supporta richieste GET.
	    // Le operazioni di gestione delle richieste HTTP saranno implementate in altri metodi.
		throw new UnsupportedOperationException("Metodo doGet non supportato per questa servlet");
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductManager pm = new ProductManager();

        int id = Integer.parseInt(request.getParameter("Id"));

        ProductBean p = pm.getProduct(id);

        request.setAttribute("product", p);

        // Retrieve the subdirectory name
        String subdirectory = request.getParameter("nomeSub");
        

        // Get the image paths based on the subdirectory
        List<String> imagePaths = getImagePaths(subdirectory);

        // Set the imagePaths as a request attribute
        request.setAttribute("imagePaths", imagePaths);

        // Forward the request to the ProductPage.jsp
        RequestDispatcher view = request.getRequestDispatcher("ProductPage.jsp");
        view.forward(request, response);
    }

    private List<String> getImagePaths(String subdirectory) throws IOException {
        List<String> imagePaths = new ArrayList<>();
        ServletContext servletContext = getServletContext();
        String contextPath = servletContext.getContextPath();

        String basePath = servletContext.getRealPath("/");
        Path directoryPath = Paths.get(basePath, "img", subdirectory);

        if (Files.isDirectory(directoryPath)) {
            Files.walk(directoryPath)
                    .filter(Files::isRegularFile)
                    .map(Path::toString)
                    .map(path -> path.replace(basePath, contextPath + "/"))
                    .forEach(imagePaths::add);
        }

        return imagePaths;
    }
}
