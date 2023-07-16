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

import model.CookieManager;
import model.DatiSpedizioneBean;
import model.PagamentoBean;
import model.ProductBean;
import model.UserManager;

/**
 * Servlet implementation class Checkout
 */
@WebServlet("/Checkout")
public class Checkout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkout() {
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
		
		
		CookieManager cm = new CookieManager();
		Cookie[] cookies = request.getCookies();
		Cookie cartCookie = cm.findCookie(cookies, "cart");
		
		HttpSession session= request.getSession();
		UserManager um=new UserManager();
		
		String id= session.getAttribute("id").toString();
		DatiSpedizioneBean ds= um.datiSpedizione(id);
		request.setAttribute("datiS", ds);
		
		PagamentoBean card= um.metodopag(id);
		request.setAttribute("carta", card);
		
		String encodedValue = cartCookie.getValue();
		
   	    ArrayList<ProductBean> cart = cm.JSONStringToList(encodedValue);
   	    
   	    request.setAttribute("carrello",cart);
   	    
   	    
		RequestDispatcher view = request.getRequestDispatcher("Checkout.jsp");
		view.forward(request, response);
	}

}
