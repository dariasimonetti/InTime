package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import model.UserManager;

/**
 * Servlet implementation class UserRegister
 */
@WebServlet("/UserRegister")
public class UserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserRegister() {
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
		
		
		String nome= request.getParameter("nome");
		String cognome= request.getParameter("cognome");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		String cellulare= request.getParameter("cellulare");
		boolean admin=false;
		String adminValue = request.getParameter("admin");
	    if (adminValue != null && adminValue.equals("true")) {
	        admin = true;
	    }
		HttpSession session = request.getSession();
		
UserManager uM = new UserManager();

		
		if (uM.registrati(nome, cognome, admin, cellulare, email, password,session)!= -2) {
			session.setAttribute("name", nome);
			session.setAttribute("cognome", cognome);
			session.setAttribute("email", email);
			
		}
		
			RequestDispatcher view = request.getRequestDispatcher("index.jsp");
			view.forward(request, response);
		
	}

}
