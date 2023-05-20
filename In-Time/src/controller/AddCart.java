package controller;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.ArrayList;
import java.net.URLDecoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import model.CatalogoBean;
import model.CookieManager;
import model.ProductBean;
import model.ProductManager;
import javax.servlet.http.Cookie;
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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
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
	    	 ArrayList<ProductBean> cart = new ArrayList<ProductBean>();
	    	 cart.add(p);
	    	 String encodedValue = cm.ListToStringJSON(cart);
	    	 cartCookie = new Cookie("cart",encodedValue);
	    	 cartCookie.setMaxAge(60*60*24*7);
	     
	     }  else {
	    	 String encodedValue = cartCookie.getValue();
	    	 ArrayList<ProductBean> cart = cm.JSONStringToList(encodedValue);
	    	 cart.add(p);
	     
	    	 encodedValue = cm.ListToStringJSON(cart);
	    	 cartCookie = new Cookie("cart", encodedValue);
	    	 cartCookie.setMaxAge(60*60*24*7);
	    	 
	     }
	     response.addCookie(cartCookie);
	     request.setAttribute("Id", id);
	     RequestDispatcher view = request.getRequestDispatcher("Product");
	     view.forward(request, response);
	     
	}
		 
	
 
}