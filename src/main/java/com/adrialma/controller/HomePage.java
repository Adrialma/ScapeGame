package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Implémentation du Servlet pour la page d'accueil. Cette classe étend HttpServlet et fournit
 * des méthodes pour gérer les requêtes GET et POST afin d'afficher la page d'accueil et de traiter les actions de l'utilisateur,
 * telles que jouer à un niveau de jeu.
 */
@WebServlet("/HomePage")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomePage() {
		super();
	}

	/**
	 * Traite les requêtes HTTP GET. Cette méthode vérifie si la connexion à la base de données
	 * est OK. Sinon, elle redirige la requête vers une page "OutOfService". Autrement, elle redirige
	 * la requête vers HomePage.jsp pour le rendu de la page d'accueil.
	 * 
	 * @param request Objet HttpServletRequest qui contient la requête faite par le client au servlet
	 * @param response Objet HttpServletResponse qui contient la réponse que le servlet envoie au client
	 * @throws ServletException si la requête pour le GET ne peut être gérée
	 * @throws IOException si une erreur d'entrée ou de sortie est détectée quand le servlet traite la requête GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		VerificationService verifService=new VerificationService();
		if(!verifService.isConectionBdOk()) {
			// Redirige vers la page OutOfService si la connexion à la base de données n'est pas OK
			this.getServletContext().getRequestDispatcher("OutOfService").forward(request, response);
		}else {
			// Redirige vers HomePage.jsp si la connexion à la base de données est OK
			this.getServletContext().getRequestDispatcher("/WEB-INF/HomePage.jsp").forward(request, response);
		}
	}

	/**
	 * Traite les requêtes HTTP POST. Cette méthode traite les actions de l'utilisateur, telles que jouer à un
	 * niveau de jeu. Elle affiche le niveau joué dans la console, récupère l'utilisateur connecté
	 * depuis la session, vérifie si la connexion à la base de données et la connexion de l'utilisateur sont valides,
	 * met à jour l'état du jeu de l'utilisateur, et redirige vers la page appropriée en fonction du résultat.
	 * 
	 * @param request Objet HttpServletRequest qui contient la requête faite par le client au servlet
	 * @param response Objet HttpServletResponse qui contient la réponse que le servlet envoie au client
	 * @throws ServletException si la requête pour le POST ne peut être gérée
	 * @throws IOException si une erreur d'entrée ou de sortie est détectée quand le servlet traite la requête POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Affiche le niveau joué dans la console
		System.out.println("Level played: " + request.getParameter("level"));

		// Récupération de la session HTTP
		HttpSession session = request.getSession();

		// Récupération de l'utilisateur connecté depuis la session
		User userLogged = (User) session.getAttribute("user");

		VerificationService verifService=new VerificationService();
		if (!verifService.checkAll(userLogged)){ // verifier conexion Bd et user logged
			this.getServletContext().getRequestDispatcher(verifService.getRedirect()).forward(request, response); 
		}else {
			// Si un utilisateur est connecté, exécution de la logique de jeu
			userLogged.play(Integer.parseInt(request.getParameter("level")));

			// Mise à jour de l'attribut "currentUser" dans la session avec l'utilisateur connecté 
			session.setAttribute("user", userLogged);
			session.setAttribute("indexPuzzle", 0);

			// Transfert de la requête et de la réponse à la JSP pour affichage
			this.getServletContext().getRequestDispatcher("/Enigme").forward(request, response);
		}
	}
}
