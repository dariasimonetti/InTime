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
		// TODO Auto-generated method stub
		CheckoutManager chm= new CheckoutManager();
		
		CookieManager cm = new CookieManager();
		Cookie[] cookies = request.getCookies();
		Cookie cartCookie = cm.findCookie(cookies, "cart");
		String encodedValue = cartCookie.getValue();
		ArrayList<ProductBean> cart = cm.JSONStringToList(encodedValue);
		
		HttpSession session= request.getSession();
		int id= (Integer) session.getAttribute("id");
	
		
		String nomeS, cognomeS, email, via, civico, cap, citta, numC, nomeC, cognomeC, datascad, cvc;
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
		
		int errD=0, errC=0;
		
		if(nomeS==null &&cognomeS==null && email==null && via==null && civico==null && cap==null && citta==null) {
			ds= (DatiSpedizioneBean) request.getAttribute("datiS");		
			if(ds==null) {
				errD=1;
				request.setAttribute("errore", -1);
			}
		} else {
			chm.nuovaSpedizione(id, nomeS, cognomeS, via, civico, cap, citta);
		}
		
		if(numC==null && nomeC==null && cognomeC==null && datascad==null && cvc==null) {
			carta= (PagamentoBean) request.getAttribute("carta");
			if(cart==null) {
				errC=1;
				request.setAttribute("errore", -1);
			}
		} else {
			chm.nuovaCarta(id, nomeC, cognomeC, numC, datascad);
		} 
		
		System.out.println("prova1");
		
		if(errC==0 && errD==0) { //i dati di sped e il metodo di pagamento ci sono
			//riempire tabella ordine e contiene nel db
			chm.inserisciOrdine(id, cart);
			//azzerarecookie carrello
			cartCookie.setMaxAge(0);
			response.addCookie(cartCookie);
			//ritornare alla pagina carrello che di conseguenza si è svuotata puoi imporstare l'attribbuto errore a 1 per segnalare il successo e far uscire un alter con un messaggio di successo (vedi alert di aggunta al carrello)
			request.setAttribute("errore", 0);
			RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
			view.forward(request, response);
		} else {
			//ritorni sempre nella pagina carrello considerando che se sei entrato in questo if hai l'attributo errore con valore -1 che quindi all'interno del carrello puoi controllare per farti uscire il messaggio di errore
			RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
			view.forward(request, response);
		}
		
		
		
	}

}
