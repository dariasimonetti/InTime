package controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import java.util.logging.Logger;

@WebServlet("/deleteImages")
public class DeleteImages extends HttpServlet {
    /**
	 * 
	 */
	
	private static final Logger logger = Logger.getLogger(DeleteImages.class.getName());
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String[] selectedImages = request.getParameterValues("selectedImages");
        

        // Directory in cui si trovano le immagini da cancellare
        String basePath = getServletContext().getRealPath("/");
        
        String newPath = basePath.replace("In-Time\\", "");


        // Effettua l'operazione di cancellazione delle immagini
        for (String imagePath : selectedImages) {
            String fullPath = newPath + imagePath;
            Path imageToDelete = Paths.get(fullPath);
            try {
                Files.deleteIfExists(imageToDelete);
            } catch (IOException e) {
            	logger.severe(e.getMessage());
                // Gestione dell'errore in caso di problemi durante la cancellazione dell'immagine
            }
        }
        
  

        // Invia la risposta al client
        RequestDispatcher view = request.getRequestDispatcher("Inventory");
        view.forward(request, response);
    }
}
