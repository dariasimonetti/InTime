package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CheckoutManager;

/**
 * Servlet implementation class AggiornaAreaUtente
 */
@WebServlet("/AggiornaAreaUtente")
public class AggiornaAreaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AggiornaAreaUtente() {
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
		
		HttpSession session= request.getSession();
		int id= (Integer) session.getAttribute("id");
		int operazione= (Integer) Integer.parseInt(request.getParameter("Operazione"));
		
		if(operazione ==1) {
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("ChangeUser");
		    dispatcher.forward(request, response);
		    
		} else if(operazione==2) {
			
			String nomeS=request.getParameter("nomeS");
			String cognomeS=request.getParameter("cognomeS");
			String via=request.getParameter("via");
			String civico=request.getParameter("civico");
			String cap=request.getParameter("cap");
			String citta=request.getParameter("citta");
			
			CheckoutManager cm=new CheckoutManager();
			cm.nuovaSpedizione(id, nomeS, cognomeS, via, civico, cap, citta);
			
			request.setAttribute("successoSpedizione", 1);
			
			RequestDispatcher view = request.getRequestDispatcher("AreaUtente");
			view.forward(request, response);
			
		} else if (operazione==3) {
			
			String numC=request.getParameter("numC");
			String nomeC=request.getParameter("nomeC");
			String cognomeC=request.getParameter("cognomeC");
			String datascad=request.getParameter("datascad");
			
			CheckoutManager cm=new CheckoutManager();
			cm.nuovaCarta(id, nomeC, cognomeC, numC, datascad);
			
			request.setAttribute("successoCarta", 1);
			
			RequestDispatcher view = request.getRequestDispatcher("AreaUtente");
			view.forward(request, response);
		}
	}

}
