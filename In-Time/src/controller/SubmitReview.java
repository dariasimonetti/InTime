package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReviewBean;
import model.ReviewManager;

/**
 * Servlet implementation class SubmitReview
 */
@WebServlet("/SubmitReview")
public class SubmitReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String testo = request.getParameter("testo");
	    int idArticolo = Integer.parseInt(request.getParameter("idArticolo"));
	    int idUtente = Integer.parseInt(request.getParameter("idUtente"));
	    double voto = Double.parseDouble(request.getParameter("voto"));
	    System.out.println(idArticolo);
	    ReviewBean review = new ReviewBean(idUtente,idArticolo,voto,testo);
	    ReviewManager rm = new ReviewManager();
	    rm.IsertReview(review);
	    
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
