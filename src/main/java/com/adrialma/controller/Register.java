package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.form.RegisterForm;
import com.adrialma.service.VerificationService;

/**
 * Servlet qui gère le processus d'inscription des utilisateurs.
 * Accessible via l'URL '/Register'.
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
	}

	/**
	 * Gère les requêtes GET pour la page d'inscription.
	 * Affiche la page d'inscription si la connexion à la base de données est disponible,
	 * sinon redirige vers une page d'erreur.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		VerificationService verifService = new VerificationService();
		if (verifService.isConectionBdOk()) {	//Tester la conection a la BD
			// Afficher la page d'inscription si la connexion à la base de données est OK
			this.getServletContext().getRequestDispatcher("/WEB-INF/Register.jsp").forward(request, response);
		}else {
			// Service non disponible en cas d'erreur de connexion à la base de données
			this.getServletContext().getRequestDispatcher("/OutOfService").forward(request, response);
			System.out.println("Pas de conexion a la BD, service non disponible");
		}
	}

	/**
	 * Gère les requêtes POST envoyées par le formulaire d'inscription.
	 * Traite les données du formulaire, enregistre l'utilisateur si les données sont valides,
	 * et redirige soit vers une page de confirmation, soit vers le formulaire d'inscription avec des erreurs.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		System.out.println(request.getParameter("firstName")); 
		System.out.println(request.getParameter("lastName"));
		System.out.println(request.getParameter("userName"));
		System.out.println(request.getParameter("password"));

		VerificationService verifService = new VerificationService();
		if (verifService.isConectionBdOk()) {	//Tester la conection a la BD
			String redirect = traiterRequest(request);
			this.getServletContext().getRequestDispatcher(redirect).forward(request, response);	

		}else { // erreur dans la conexion a la bd
			System.out.println("Pas de conexion a la BD, service non disponible");
			this.getServletContext().getRequestDispatcher("/OutOfService").forward(request, response);	
		}
	}

	/**
	 * Traite les données du formulaire d'inscription et détermine la page de redirection appropriée.
	 *
	 * @param request La requête contenant les données du formulaire.
	 * @return Le chemin vers la page JSP à afficher.
	 */
	public String traiterRequest(HttpServletRequest request) {

		RegisterForm registerForm = new RegisterForm(request);
		request.setAttribute("user", registerForm.getUser());

		if (registerForm.getErrorList().size()<1) {
			// Aucune erreur détectée, redirection vers la page de confirmation d'inscription
			return "/WEB-INF/RegisterOK.jsp";
		}else {
			// Des erreurs ont été détectées, afficher à nouveau le formulaire d'inscription avec les erreurs
			request.setAttribute("errorList", registerForm.getErrorList());
			return "/WEB-INF/Register.jsp";
		}
	}
}
