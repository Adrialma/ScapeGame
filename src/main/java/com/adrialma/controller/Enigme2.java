package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Enigme2
 */
@WebServlet("/Enigme2")
public class Enigme2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Enigme2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme2.jsp").
		forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String userAnswer=request.getParameter("answer");
	    // comparaison de userAnswer avec la réponse stockée dans votre BDD
	    // supposons que la réponse correcte est "5"
	    String correctAnswer = "5"; // Remplacez ceci par la récupération de la réponse depuis la BDD
	    response.setContentType("text/plain");
	    response.setCharacterEncoding("UTF-8");
	    if(userAnswer.equals(correctAnswer)) {
	    response.getWriter().write("Correct!");
	    } else {
	      // response.getWriter().write("Incorrect, essayez encore.");
	    	request.setAttribute("messageErreur", "Reponse incorrecte");
			this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme2.jsp").forward(request, response);

	    	
	   }
	    
		
	}

}
