package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.logging.LogFactory;
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
        // TODO Auto-generated constructor stub
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
		 String orderId = request.getParameter("orderId");
		 BufferedReader reader = request.getReader();
		  String line;
		  StringBuilder requestBody = new StringBuilder();
		  while ((line = reader.readLine()) != null) {
		    requestBody.append(line);
		  }
		  reader.close();

		  // Analizza i dati del corpo della richiesta
		  System.out.println(orderId);

		  // Utilizza l'ID dell'ordine per generare la fattura corrispondente
		  Invoice fattura = new Invoice();
		    byte[] fatturaPDFBytes = fattura.generatePDF(orderId);

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
