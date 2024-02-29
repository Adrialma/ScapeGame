package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("Level played: " + request.getParameter("level"));

		// Récupération de la session HTTP
		HttpSession session = request.getSession();

		// Récupération de l'utilisateur connecté depuis la session
		User userLogged = (User) session.getAttribute("user");
		System.out.println(userLogged);

		if (userLogged == null) {
			// Si aucun utilisateur n'est connecté, redirection vers la page de connexion
			response.sendRedirect("loginPage.jsp");
		} else {
			// Si un utilisateur est connecté, exécution de la logique de jeu
			userLogged.play(Integer.parseInt(request.getParameter("level")));

			// Mise à jour de l'attribut "currentUser" dans la session avec l'utilisateur connecté 
			session.setAttribute("user", userLogged);
			session.setAttribute("indexPuzzle", 0);
			
			// Transfert de la requête et de la réponse à la JSP pour affichage
			this.getServletContext().getRequestDispatcher("/Enigme").
			forward(request, response);
		}

	}
}
