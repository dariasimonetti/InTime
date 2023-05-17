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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;




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
        // TODO Auto-generated constructor stub
    }
    public void init() throws ServletException {
        super.init();
       baseDirectory = getServletContext().getRealPath("/img");
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
		            System.out.println("ERRORE NELLA CREAZIONE DELLA DIRECTORY");
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


