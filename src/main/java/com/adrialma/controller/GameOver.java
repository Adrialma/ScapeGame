package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet qui gère l'affichage de la page de fin de jeu (GameOver).
 * Lorsque le jeu se termine, cette servlet est invoquée pour rediriger l'utilisateur vers une page spécifiant que le jeu est terminé.
 */
@WebServlet("/GameOver")
public class GameOver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameOver() {
		super();
	}

	/**
	 * Traite les requêtes GET en redirigeant l'utilisateur vers la page GameOver.jsp.
	 * Cette méthode peut être étendue pour inclure des tâches de nettoyage, comme la réinitialisation des données de session liées au jeu.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO clean session, user et tout
		// Cette ligne peut contenir du code pour nettoyer les données de session ou effectuer d'autres tâches liées à la fin du jeu

		// Redirige l'utilisateur vers la page GameOver.jsp pour afficher le message de fin de jeu.
		this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/GameOver.jsp").forward(request, response);
	}

	/**
	 * Traite les requêtes POST de la même manière que les requêtes GET.
	 * Cette méthode est appelée si une requête POST est envoyée à cette servlet, mais par défaut, elle redirige simplement vers la méthode doGet.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
