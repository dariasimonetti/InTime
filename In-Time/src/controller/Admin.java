package controller;

import model.AdminManager;

import model.OrderBean;

import model.UserBean;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		AdminManager am = new AdminManager();
		
		ArrayList<Float> info=am.getInfo();
		ArrayList <OrderBean> ordini = am.getOrdini();
		ArrayList <UserBean> utenti=am.getUtenti();
		
		
		
		request.setAttribute("informazioni", info);
		request.setAttribute("ordini", ordini);
		request.setAttribute("utenti", utenti);
		

		
		RequestDispatcher view = request.getRequestDispatcher("Admin.jsp");
		view.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
