package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.form.LoginForm;
import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Servlet qui gère le processus de connexion des utilisateurs.
 * Accessible via l'URL '/Login', elle permet aux utilisateurs de se connecter à l'application.
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
	 * Gère les requêtes GET pour la page de connexion.
	 * Affiche la page de connexion si la connexion à la base de données est disponible,
	 * sinon redirige vers une page d'erreur.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VerificationService verifService = new VerificationService();
		if (verifService.isConectionBdOk()) {	//Tester la conection a la BD
			this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);	 // Rediriger vers la page de login
		}else {
			
			this.getServletContext().getRequestDispatcher("/OutOfService").forward(request, response);

		}
	}

	/**
	 * Gère les requêtes POST envoyées par le formulaire de connexion.
	 * Traite les données du formulaire, valide l'utilisateur, et redirige soit vers la page d'accueil,
	 * soit vers le formulaire de connexion avec des messages d'erreur.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VerificationService verifService = new VerificationService();
		if (verifService.isConectionBdOk()) {	//Tester la conection a la BD
			String redirect = traiterRequest(request);
			this.getServletContext().getRequestDispatcher(redirect).forward(request, response); 
		}
		else {
			System.out.println("Pas de conexion a la BD, service non disponible");
			this.getServletContext().getRequestDispatcher("/OutOfService").forward(request, response);
		}
	}

	/**
	 * Traite les informations du formulaire de connexion et détermine la redirection appropriée.
	 *
	 * @param request La requête contenant les données du formulaire de connexion.
	 * @return Le chemin vers la page JSP à afficher.
	 */
	public String traiterRequest(HttpServletRequest request) {

		// Traiter les informations du formulaire de login
		LoginForm loginForm = new LoginForm(request);

		if (loginForm.getErrorList().isEmpty()) {  // si il y a pas d'erreur

			request.getSession().setAttribute("user", loginForm.getUser());   // Stocker l'utilisateur dans la session
			User user2 = (User) request.getSession().getAttribute("user");
			System.out.println("user2 " + user2);

			// Rediriger vers la page d'accueil 
			return "/WEB-INF/HomePage.jsp";

		} else {
			// Connexion échouée, erreurs dans les donnes, afficher la page d'erreur
			request.setAttribute("errorList", loginForm.getErrorList());

			// Re-afficher la page de login avec les erreurs
			return "/WEB-INF/Login.jsp";
		}
	}
}
