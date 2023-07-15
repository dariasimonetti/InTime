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
import javax.servlet.http.HttpSession;


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
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		ArrayList<Float> info=new ArrayList<Float>();
		ArrayList <OrderBean> ordini = new ArrayList<OrderBean>();
		ArrayList <UserBean> utenti=new ArrayList<UserBean>();
		
		AdminManager am = new AdminManager();
		
		info = am.getInfo();
		ordini = am.getOrdini();
		utenti = am.getUtenti();
		
		
		request.setAttribute("informazioni", info);
		request.setAttribute("ordini", ordini);
		request.setAttribute("utenti", utenti);
		
		HttpSession session= request.getSession();
		
		RequestDispatcher view = request.getRequestDispatcher("Admin.jsp");
		view.forward(request, response);
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
