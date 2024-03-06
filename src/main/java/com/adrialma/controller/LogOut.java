package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet qui gère la déconnexion des utilisateurs.
 * Accessible via l'URL '/LogOut', elle invalide la session de l'utilisateur et le redirige vers la page de connexion.
 */
@WebServlet("/LogOut")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogOut() {
		super();
	}

	/**
	 * Gère les requêtes GET en invalidant la session de l'utilisateur et en le redirigeant vers la page de connexion.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de la session courante de l'utilisateur.
		HttpSession session = request.getSession();

		// Invalidation de la session pour effacer toutes les données de session et déconnecter l'utilisateur.
		session.invalidate();

		// Redirection de l'utilisateur vers la page de connexion après la déconnexion.
		this.getServletContext().getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
	}

	/**
	 * Gère les requêtes POST en les redirigeant vers la méthode doGet.
	 * Cela assure que la déconnexion peut être traitée indifféremment des méthodes GET ou POST.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

