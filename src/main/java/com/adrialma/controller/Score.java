package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrialma.dao.GameDAO;
import com.adrialma.dao.PuzzlePlayedDAO;
import com.adrialma.model.Game;
import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Servlet qui gère l'affichage et l'enregistrement du score d'un jeu.
 * Accessible via l'URL '/Score'.
 */
@WebServlet("/Score")
public class Score extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Score() {
		super();
	}

	/**
	 * Gère les requêtes GET pour la servlet Score.
	 * Récupère le jeu courant de l'utilisateur, calcule et enregistre le score, puis redirige vers la page de score.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de la session HTTP
		HttpSession session = request.getSession();

		// Récupération des données de session et des paramètres de requête		
		User user = (User) session.getAttribute("user");

		VerificationService verifService = new VerificationService();
		if (!verifService.checkAll(user)) {
			this.getServletContext().getRequestDispatcher(verifService.getRedirect()).forward(request, response);

		}else {

			Game currentGame = user.getSingleGame();
			currentGame.exec();
			request.setAttribute("gameScore", currentGame.getScore());

			// Apeller de methodes pour enregistrer les resultats dans la BDD
			currentGame.endDates();
			GameDAO gameDAO = new GameDAO();
			gameDAO.add(currentGame,user.getIdUser());
			System.out.println("id game: " + currentGame.getIdGame());


			PuzzlePlayedDAO puzzlePlayedDAO =  new PuzzlePlayedDAO();
			puzzlePlayedDAO.addArray(currentGame.getPuzzles(), currentGame.getIdGame() );

			//Reinitialiser le jeu
			user.flushGames();
			session.setAttribute("user", user); // Mettre à jour l'utilisateur dans la session

			getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeScore.jsp").forward(request, response);
		}
	}

	/**
	 * Gère les requêtes POST pour la servlet Score.
	 * Redirige vers la méthode doGet pour traiter la requête.
	 *
	 * @param request La requête envoyée par le client au serveur.
	 * @param response La réponse que le serveur envoie au client.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
