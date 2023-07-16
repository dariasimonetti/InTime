package controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;



import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Servlet implementation class AddImages
 */

@MultipartConfig
public class AddImages extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String baseDirectory;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddImages() {
        super();
        
    }
    public void init() throws ServletException {
        super.init();
       baseDirectory = getServletContext().getRealPath("/img");
    }
    
    private static final Logger logger = Logger.getLogger(AddImages.class.getName());
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Questo metodo doGet è vuoto perché la servlet non supporta richieste GET.
	    // Le operazioni di gestione delle richieste HTTP saranno implementate in altri metodi.
		throw new UnsupportedOperationException("Metodo doGet non supportato per questa servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		    // Ottieni l'input stream dell'immagine
		    Part imagePart = request.getPart("image");
		    InputStream imageInputStream = imagePart.getInputStream();

		    // Ottieni il percorso di salvataggio fornito dall'utente
		    String savePath = request.getParameter("savePath");

		    // Directory di base predefinita
		    

		 // Verifica se il percorso fornito dall'utente è una sottodirectory della directory di base
		    String fullSavePath = baseDirectory + File.separator + savePath;
		    

		    // Crea la sottodirectory se non esiste
		    File directory = new File(fullSavePath);
		    if (!directory.exists()) {
		        boolean created = directory.mkdir();
		        if (!created) {
		            logger.log(Level.WARNING, "Errore nella creazione della directory");
		            return;
		        }
		    }

		    
		    

		    // Salva l'immagine nella cartella specificata
		    String fileName = imagePart.getSubmittedFileName();
		    String filePath = fullSavePath + File.separator + fileName;
		    Files.copy(imageInputStream, Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

		    RequestDispatcher view = request.getRequestDispatcher("Inventory");
			view.forward(request, response);
		}

	}


