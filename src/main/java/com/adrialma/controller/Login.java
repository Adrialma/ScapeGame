package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.form.LoginForm;
import com.adrialma.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//Tester la conection a la BD
		//DaoBd.conecter();
		//DaoBd.closeConnection();

		// Rediriger vers la page de login
		this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").
		forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Traiter les informations du formulaire de login
		LoginForm loginForm = new LoginForm(request);

		if (loginForm.getErrorList().isEmpty()) {
			// Connexion réussie
			//HttpSession session = request.getSession();
			//session.setAttribute("user", loginForm.getUser()); // Stocker l'utilisateur dans la session
			request.getSession().setAttribute("user", loginForm.getUser());

			//System.out.println(request.getSession().getAttribute("user"));
			User user2 = (User) request.getSession().getAttribute("user");
			System.out.println("user2 " + user2);

			// Rediriger vers la page d'accueil 
			this.getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp").
			forward(request, response);

		} else {
			// Connexion échouée, erreurs dans les donnes, afficher la page d'erreur
			request.setAttribute("errorList", loginForm.getErrorList());

			// Re-afficher la page de login avec les erreurs
			this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response); 
		}
	}
}


