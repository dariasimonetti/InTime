package controller;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdminManager;
import model.OrderBean;
import model.UserBean;

/**
 * Servlet implementation class FiltroClienti
 */
@WebServlet("/FiltroClienti")
public class FiltroClienti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FiltroClienti() {
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

		 String idClienteParam = request.getParameter("idCliente");
		 
		 int idCliente = Integer.parseInt(idClienteParam);
         ArrayList<UserBean> clientiFiltrati = filtraUtentiPerIDCliente(idCliente);
         

         // Invia i risultati filtrati come risposta JSON
         response.setContentType("application/json");
         PrintWriter out = response.getWriter();
         out.print(convertiInJSON(clientiFiltrati));
         out.flush();
	}
	
	 private ArrayList<UserBean> filtraUtentiPerIDCliente(int idCliente) {
	        ArrayList<UserBean> clientiFiltrati = new ArrayList<>();
	        AdminManager am = new AdminManager();
	        ArrayList<UserBean> clienti = am.getUtenti();

	        for (UserBean cliente : clienti) {
	            if (cliente.getId() == idCliente) {
	            	
	                clientiFiltrati.add(cliente);
	            }
	        }

	        return clientiFiltrati;
	    }
	 
	 private String convertiInJSON(List<UserBean> utenti) {
		    JSONArray jsonArray = new JSONArray();

		    for (UserBean cliente : utenti) {
		        JSONObject jsonObject = new JSONObject();
		        jsonObject.put("idUtente", cliente.getId());
		        jsonObject.put("nome", cliente.getNome());
		        jsonObject.put("cognome", cliente.getCognome());
		        jsonObject.put("email", cliente.getEmail());
		        jsonObject.put("telefono", cliente.getTelefono());

		        jsonArray.put(jsonObject);
		    }

		    return jsonArray.toString();
		}


}
