package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckoutManager;
import model.CookieManager;
import model.DatiSpedizioneBean;
import model.PagamentoBean;
import model.ProductBean;

/**
 * Servlet implementation class CompletaOrdine
 */
@WebServlet("/CompletaOrdine")
public class CompletaOrdine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletaOrdine() {
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
		
		CheckoutManager chm= new CheckoutManager();
		
		CookieManager cm = new CookieManager();
		Cookie[] cookies = request.getCookies();
		Cookie cartCookie = cm.findCookie(cookies, "cart");
		String encodedValue = cartCookie.getValue();
		ArrayList<ProductBean> cart = cm.JSONStringToList(encodedValue);
		
		HttpSession session= request.getSession();
		int id= (Integer) session.getAttribute("id");
	
		
		String nomeS; 
		String cognomeS;
		String email;
		String via;
		String civico;
		String cap;
		String citta;
		String numC;
		String nomeC;
		String cognomeC;
		String datascad;
		String cvc;
		
		DatiSpedizioneBean ds=null;
		PagamentoBean carta=null;
		
		nomeS=request.getParameter("nomeS");
		cognomeS=request.getParameter("cognomeS");
		email=request.getParameter("email");
		via=request.getParameter("via");
		civico=request.getParameter("civico");
		cap=request.getParameter("cap");
		citta=request.getParameter("citta");
		numC=request.getParameter("numC");
		nomeC=request.getParameter("nomeC");
		cognomeC=request.getParameter("cognomeC");
		datascad=request.getParameter("datascad");
		cvc=request.getParameter("cvc");
		
		int errD=0;
		int errC=0;
		int errore=0;
		
		if(nomeS==null &&cognomeS==null && email==null && via==null && civico==null && cap==null && citta==null) {
			ds= (DatiSpedizioneBean) request.getAttribute("datiS");		
			if(ds==null) {
				errD=1;
				
			}
		} else {
			chm.nuovaSpedizione(id, nomeS, cognomeS, via, civico, cap, citta);
		}
		
		if(numC==null && nomeC==null && cognomeC==null && datascad==null && cvc==null) {
			carta= (PagamentoBean) request.getAttribute("carta");
			if(carta==null) {
				errC=1;
				
			}
		} else {
			chm.nuovaCarta(id, nomeC, cognomeC, numC, datascad);
		} 
		
		
		
		if(errC==0 && errD==0) { //i dati di sped e il metodo di pagamento ci sono
			//riempire tabella ordine e contiene nel db
			chm.inserisciOrdine(id, cart);
			//azzerarecookie carrello
			cartCookie.setMaxAge(0);
			response.addCookie(cartCookie);
			//ritornare alla pagina carrello che di conseguenza si è svuotata puoi imporstare l'attribbuto errore a 1 per segnalare il successo e far uscire un alter con un messaggio di successo (vedi alert di aggunta al carrello)
			errore=0;
			
		} else {
			//ritorni sempre nella pagina carrello considerando che se sei entrato in questo if hai l'attributo errore con valore -1 che quindi all'interno del carrello puoi controllare per farti uscire il messaggio di errore
			errore=-1;
			
		}
		
		request.setAttribute("errore", errore);
		RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
		view.forward(request, response);
		
		
		
	}

}
