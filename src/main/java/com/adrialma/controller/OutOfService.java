package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrialma.dao.DaoBd;


/**
 * Servlet qui gère les cas où l'application n'est pas en mesure de fournir le service demandé.
 * Peut être utilisé pour signaler une maintenance en cours, des problèmes de connexion à la base de données,
 * ou toute autre situation empêchant l'application de fonctionner normalement.
 */
@WebServlet("/OutOfService")
public class OutOfService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OutOfService() {
		super();
	}

	/**
	 * Traite les requêtes GET en redirigeant les utilisateurs vers une page d'erreur ou d'information
	 * spécifique, indiquant que le service est actuellement hors service ou indisponible.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Detruire toutes les conexions avant d'envoyer l'utilisateur vers out of service
		HttpSession session = request.getSession();
		session.invalidate();
		DaoBd.closeConnection();


		// Redirection vers une page JSP dédiée, informant l'utilisateur de l'indisponibilité du service.
		this.getServletContext().getRequestDispatcher("/WEB-INF/OutOfService.jsp").forward(request, response);
	}

	/**
	 * Traite les requêtes POST de la même manière que les requêtes GET, en redirigeant vers la page d'erreur ou d'information.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
