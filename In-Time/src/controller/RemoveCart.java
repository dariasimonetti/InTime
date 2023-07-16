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
 * Servlet implementation class RemoveCart
 */
@WebServlet("/RemoveCart")
public class RemoveCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveCart() {
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
		int id = Integer.parseInt(request.getParameter("id"));
		Cookie[] cookies = request.getCookies();
		CookieManager cm = new CookieManager();
		Cookie cartCookie = cm.findCookie(cookies, "cart");
		String encodedValue = cartCookie.getValue();
		ProductBean removedProduct= null;
		ArrayList<ProductBean> cart = cm.JSONStringToList(encodedValue);
		for(ProductBean p:cart) {
			if(id==p.getId()) {
				   removedProduct = p;
			}
		}
		cart.remove(removedProduct);
		if(cart.isEmpty()) {
			cartCookie.setMaxAge(0);
		}else {
			encodedValue = cm.ListToStringJSON(cart);
	   	    cartCookie = new Cookie("cart", encodedValue);
	   	    
		}
		
		response.addCookie(cartCookie);
   	    response.sendRedirect(request.getContextPath() + "/Cart");
		
	}
	}