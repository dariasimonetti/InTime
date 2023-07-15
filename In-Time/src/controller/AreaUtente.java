package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CatalogoBean;
import model.DatiSpedizioneBean;
import model.LoginManager;
import model.OrderBean;
import model.PagamentoBean;
import model.ProductManager;
import model.UserBean;
import model.UserManager;

/**
 * Servlet implementation class AreaUtente
 */
@WebServlet("/AreaUtente")
public class AreaUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AreaUtente() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ProductManager PM=new ProductManager();
		HttpSession session = request.getSession();
		
		
		Integer id = (Integer) session.getAttribute("id");
	    
		UserManager UM= new UserManager();
		UserBean utente = UM.getUtente(id);
		request.setAttribute("utente", utente);
		
		String idi= Integer.toString(id);
		DatiSpedizioneBean ds= UM.datiSpedizione(idi);
		request.setAttribute("datisped", ds);
		
		PagamentoBean carta= UM.metodopag(idi);
		request.setAttribute("carta", carta);
		
		ArrayList<OrderBean> ordini = new ArrayList<>();
		
		ordini = PM.getOrdini(id);
		request.setAttribute("ordini", ordini);
		
		RequestDispatcher view = request.getRequestDispatcher("AreaUtente.jsp");
		view.forward(request, response);
	}

}