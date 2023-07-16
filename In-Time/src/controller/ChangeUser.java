package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.LoginManager;
import model.UserManager;

/**
 * Servlet implementation class ChangeUser
 */
@WebServlet("/ChangeUser")
public class ChangeUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeUser() {
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
		
		UserManager um=new UserManager();
		HttpSession session= request.getSession();
		Integer idInteger = (Integer) session.getAttribute("id");
        String id = String.valueOf(idInteger);
		String nome= request.getParameter("nome");
		
		String cognome= request.getParameter("cognome");
		String telefono=  request.getParameter("cellulare");
		String email = request.getParameter("email");
		String password =  request.getParameter("password");
		
		um.modifica(id, nome, cognome, telefono, email, password);
		
		LoginManager lm= new LoginManager();
		boolean admin= lm.isAdmin(id);
		
		

		if (nome != null && !nome.equals("")) {
		    request.getSession().setAttribute("name", nome);
		}
		if (cognome != null && !cognome.equals("")) {
		    request.getSession().setAttribute("cognome", cognome);
		}
		
		if (email != null && !email.equals("")) {
		    request.getSession().setAttribute("email", email);
		}
		
		
		
		if(admin) {
			response.sendRedirect("Admin");
		} else if(!admin){
			request.setAttribute("successoProfilo", 1);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/AreaUtente");
		    dispatcher.forward(request, response);
		}
		

	}

}
