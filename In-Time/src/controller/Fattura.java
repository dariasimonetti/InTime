package controller;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Invoice;

/**
 * Servlet implementation class Fattura
 */
@WebServlet("/Fattura")
public class Fattura extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Fattura() {
        super();
        
    }

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
		 String orderId = request.getParameter("orderId");
		 BufferedReader reader = request.getReader();
		  String line;
		  StringBuilder requestBody = new StringBuilder();
		  while ((line = reader.readLine()) != null) {
		    requestBody.append(line);
		  }
		  reader.close();

		  
		  ServletContext servletContext = getServletContext();

		  // Utilizza l'ID dell'ordine per generare la fattura corrispondente
		  Invoice fattura = new Invoice();
		    byte[] fatturaPDFBytes = fattura.generatePDF(orderId, servletContext);

		  // Imposta il tipo di contenuto della risposta come PDF
		    response.setContentType("application/pdf");

		  // Invia il PDF come risposta al client
		    try (ServletOutputStream outputStream = response.getOutputStream()) {
		        outputStream.write(fatturaPDFBytes);
		        outputStream.flush();
		      }

		  // Elimina il file PDF temporaneo dopo l'invio

	}

}
