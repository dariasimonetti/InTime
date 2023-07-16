package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;

import model.CookieManager;

import javax.servlet.http.Cookie;
/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Logout() {
        super();
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	HttpSession session = request.getSession();
    	
    	Cookie[] cookies = request.getCookies();
		CookieManager cm = new CookieManager();	 
		Cookie cartCookie = cm.findCookie(cookies, "cart");
		
        session.invalidate();
        
        if (cartCookie != null) {
        	cartCookie.setMaxAge(0);
        	response.addCookie(cartCookie);
        }
        response.sendRedirect("index.jsp");
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
