package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DatiSpedizioneBean;
import model.OrderBean;
import model.PagamentoBean;
import model.ProductManager;
import model.UserBean;
import model.UserManager;

/**
 * Servlet implementation class AreaUtente
 */
@WebServlet("/AreaUtente")
public class AreaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaUtente() {
        super();
        
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Questo metodo doGet è vuoto perché la servlet non supporta richieste GET.
	    // Le operazioni di gestione delle richieste HTTP saranno implementate in altri metodi.
		throw new UnsupportedOperationException("Metodo doGet non supportato per questa servlet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ProductManager pM=new ProductManager();
		HttpSession session = request.getSession();
		
		
		Integer id = (Integer) session.getAttribute("id");
	    
		UserManager uM= new UserManager();
		UserBean utente = uM.getUtente(id);
		request.setAttribute("utente", utente);
		
		String idi= Integer.toString(id);
		DatiSpedizioneBean ds= uM.datiSpedizione(idi);
		request.setAttribute("datisped", ds);
		
		PagamentoBean carta= uM.metodopag(idi);
		request.setAttribute("carta", carta);
		
		ArrayList<OrderBean> ordini = pM.getOrdini(id);
		request.setAttribute("ordini", ordini);
		
		RequestDispatcher view = request.getRequestDispatcher("AreaUtente.jsp");
		view.forward(request, response);
	}

}