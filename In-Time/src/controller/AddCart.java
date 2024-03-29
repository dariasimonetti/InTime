package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CookieManager;
import model.ProductBean;
import model.ProductManager;

/**
 * Servlet implementation class AddCart
 */
@WebServlet("/AddCart")
public class AddCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCart() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Questo metodo doGet � vuoto perch� la servlet non supporta richieste GET.
	    // Le operazioni di gestione delle richieste HTTP saranno implementate in altri metodi.
		throw new UnsupportedOperationException("Metodo doGet non supportato per questa servlet");
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int id = Integer.parseInt(request.getParameter("Id"));
	     ProductManager pm = new ProductManager();	
	     CookieManager cm = new CookieManager();
	     ProductBean p = pm.getProduct(id);
	     
	     
	     Cookie[] cookies = request.getCookies();
	     Cookie cartCookie = cm.findCookie(cookies, "cart");
	     if (cartCookie == null) {
	    	 ArrayList<ProductBean> cart = new ArrayList<>();
	    	 cart.add(p);
	    	 String encodedValue = cm.listToStringJSON(cart);
	    	 cartCookie = new Cookie("cart",encodedValue);
	    	 cartCookie.setMaxAge(60*60*24*7);
	     
	     }  else {
	    	 String encodedValue = cartCookie.getValue();
	    	 ArrayList<ProductBean> cart = (ArrayList<ProductBean>) cm.jSONStringToList(encodedValue);
	    	 cart.add(p);
	     
	    	 encodedValue = cm.listToStringJSON(cart);
	    	 cartCookie = new Cookie("cart", encodedValue);
	    	 cartCookie.setMaxAge(60*60*24*7);
	    	 
	     }
	     response.addCookie(cartCookie);
	     
	     
	}
		 
	
 
}