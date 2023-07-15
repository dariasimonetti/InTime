 package controller;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ReviewBean;
import model.ReviewManager;
/**
 * Servlet implementation class Review
 */
@WebServlet("/Review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Review() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 
		  
		  String idS = request.getParameter("id");
		  int id =Integer.parseInt(idS);
	      List<ReviewBean> recensioni;
	      String JsnonRece;
	      ReviewManager rm = new ReviewManager();
	      recensioni = rm.getReviewsForProduct(id);
	      JsnonRece = rm.ListToStringJSON(recensioni);
	      System.out.println(JsnonRece);
	      response.setContentType("application/json");
          PrintWriter out = response.getWriter();
          out.print(JsnonRece);
          out.flush();
	      
	      
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 
	}

}
