package controller;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class GetImage
 */
@WebServlet("/GetImage")
public class GetImage extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Ottieni il percorso relativo dell'immagine dalla richiesta
        String imageRelativePath = request.getParameter("image");
        String baseDirectory="/img";

        // Costruisci il percorso completo dell'immagine nel server
        String imageServerPath = baseDirectory + File.separator + imageRelativePath;
        System.out.println(imageServerPath);
        // Leggi il file immagine dal server come array di byte
        File imageFile = new File(imageServerPath);
        byte[] imageData = Files.readAllBytes(imageFile.toPath());

        // Imposta il tipo di contenuto dell'immagine nella risposta HTTP
        String contentType = getServletContext().getMimeType(imageServerPath);
        response.setContentType(contentType);

        // Scrivi i dati dell'immagine nella risposta HTTP
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(imageData);
        outputStream.close();
    }
}
