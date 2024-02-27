package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.dao.UserDAO;
import com.adrialma.model.User;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp").
		forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		System.out.println("Level played : " + request.getParameter("level"));
		
		//TODO recuperer les infos  du joueur
		UserDAO userDAO = new UserDAO(); //TODO a modifier, cree une user temporel (a changer pour l'info du joueur logged in)
		User userLogged= userDAO.get("gartles");//TODO a modifier, cree une user temporel (a changer pour l'info du joueur logged in)
		
		request.getSession().setAttribute("currentUser", userLogged);
		
		//pour recuperer le user logged
		//request.getSession().getAttribute("currentUser");
		
		
		// init Game
		userLogged.play(Integer.parseInt(request.getParameter("level")) );
	
		// montrer la view (afficher userName)
		
		
		doGet(request, response);
	}
	
	
	
	
	

}
