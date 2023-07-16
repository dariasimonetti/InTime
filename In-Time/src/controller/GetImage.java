package controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


/**
 * Servlet implementation class GetImagesServlet
 */
@WebServlet("/getImages")
public class GetImage extends HttpServlet {
    private static final long serialVersionUID = 1L;
    

    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetImage() {
        super();
        
    }

    public void init() throws ServletException {
        super.init();
      
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String subdirectory = request.getParameter("subdirectory");
        List<String> imagePaths = getImagePaths(subdirectory);

        JsonObject responseData = new JsonObject();
        responseData.add("imagePaths", new Gson().toJsonTree(imagePaths));

        // Impostare il tipo di contenuto della risposta come JSON
        response.setContentType("application/json");
        // Scrivere i dati JSON come risposta
        response.getWriter().write(responseData.toString());
        }
    

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
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
