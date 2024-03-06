package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.dao.UserDAO;
import com.adrialma.model.Factory;
import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Ce servlet est responsable de l'affichage des jeux disponibles.
 * Il récupère les informations des jeux et des utilisateurs depuis la base de données
 * et les affiche à l'utilisateur via une page JSP spécifiée.
 */
@WebServlet("/ShowGames")
public class ShowGames extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ShowGames() {
		super();
	}

	/**
	 * Traite les requêtes GET pour le servlet.
	 * Vérifie d'abord la connexion à la base de données avant de récupérer les informations des jeux.
	 * Si la connexion à la base de données est valide, il procède à la récupération des informations.
	 * Sinon, il redirige l'utilisateur vers une page d'erreur.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Création d'une instance de VerificationService pour vérifier la connexion à la base de données
		VerificationService verifService = new VerificationService();

		// Vérification de la connexion à la base de données
		if (verifService.isConectionBdOk()) {
			// Si la connexion est OK, récupération des informations des jeux
			getAllInfo(request);
			// Redirection vers la page JSP qui affiche les jeux
			this.getServletContext().getRequestDispatcher("/WEB-INF/ShowGames.jsp").forward(request, response);
		}else {
			// Si la connexion à la base de données échoue, redirection vers une page d'erreur
			this.getServletContext().getRequestDispatcher("/OutOfService").forward(request, response);
			System.out.println("Pas de conexion a la BD, service non disponible");
		}
	}

	/**
	 * Traite les requêtes POST pour le servlet.
	 * Redirige simplement vers la méthode doGet pour traiter la requête,
	 * permettant une gestion unifiée des requêtes GET et POST.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Récupère toutes les informations nécessaires (utilisateurs, jeux, puzzles) depuis la base de données
	 * et les place dans la requête comme attribut pour leur affichage dans la vue JSP.
	 *
	 * @param request L'objet HttpServletRequest où les informations récupérées seront placées.
	 */
	public void getAllInfo(HttpServletRequest request){
		// Création d'une instance de UserDAO pour accéder aux données des utilisateurs
		UserDAO userDAO = new UserDAO();
		// Création d'une instance de Factory pour la manipulation et la récupération des données
		Factory factory = new Factory(userDAO.get());
		// Récupération des jeux et puzzles
		factory.getGames();
		factory.getPuzzles();

		// Affichage de la liste des utilisateurs dans la console (à des fins de débogage)
		for (User user: factory.getUserList())
			System.out.println(user);

		// Placement de la liste des utilisateurs dans l'attribut de la requête pour utilisation dans la vue JSP
		request.setAttribute("userList",factory.getUserList());
	}
}
