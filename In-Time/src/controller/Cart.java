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

import model.CookieManager;
import model.ProductBean;

/**
 * Servlet implementation class Cart
 */
@WebServlet("/Cart")
public class Cart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Cart() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		CookieManager cm = new CookieManager();	 
		Cookie cartCookie = cm.findCookie(cookies, "cart");
		
		if (cartCookie != null) {
		String encodedValue = cartCookie.getValue();
		
   	    ArrayList<ProductBean> cart = (ArrayList<ProductBean>) cm.jSONStringToList(encodedValue);
   	    
   	    request.setAttribute("carrello",cart);
		} else {
			request.setAttribute("carrello", null);
			}
   	    RequestDispatcher view = request.getRequestDispatcher("cart.jsp");
		view.forward(request, response);
		
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       doGet(request,response);
 
	}

}