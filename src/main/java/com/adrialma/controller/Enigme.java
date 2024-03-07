package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrialma.model.Game;
import com.adrialma.model.Puzzle;
import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Implémentation du Servlet pour la gestion des énigmes. Cette classe étend HttpServlet et offre
 * des méthodes pour gérer les requêtes GET et POST afin de naviguer entre les énigmes et de traiter les réponses des utilisateurs.
 */
@WebServlet("/Enigme")
public class Enigme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Enigme() {
		super();
	}

	/**
	 * Traite les requêtes HTTP GET. Récupère la session de l'utilisateur et vérifie l'état de la session
	 * et de l'utilisateur avec le service de vérification. Si la vérification échoue, redirige vers une page spécifique,
	 * sinon, transfère la gestion à la méthode doPost pour continuer le traitement.
	 *
	 * @param request Objet HttpServletRequest qui contient la requête faite par le client
	 * @param response Objet HttpServletResponse qui contient la réponse que le servlet renvoie au client
	 * @throws ServletException si la requête pour le GET ne peut être gérée
	 * @throws IOException si une erreur d'entrée ou de sortie est détectée lorsque le servlet gère la requête GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Récupération de la session HTTP
		HttpSession session = request.getSession();
		//Récupération de l'utilisateur depuis la session
		User user = (User) session.getAttribute("user");
		// Création d'une instance de VerificationService pour vérifier certaines conditions
		VerificationService verifService = new VerificationService();
		// Vérification si l'utilisateur remplit les conditions requises
		if (!verifService.checkAll(user)) {
			// Si l'utilisateur ne remplit pas les conditions, redirige vers une autre page
			// La page de redirection est déterminée par le service de vérification
			this.getServletContext().getRequestDispatcher(verifService.getRedirect()).forward(request, response);

		}else
			// Si l'utilisateur remplit les conditions, procède avec la méthode doPost
			// Cela indique une réutilisation de la logique de traitement des requêtes POST pour les requêtes GET réussies
			doPost(request, response);
	}

	/**
	 * Traite les requêtes HTTP POST. Gère la logique du jeu en récupérant l'utilisateur, en vérifiant l'état du jeu,
	 * et en traitant la réponse de l'utilisateur à l'énigme actuelle.
	 *
	 * @param request Objet HttpServletRequest qui contient la requête faite par le client
	 * @param response Objet HttpServletResponse qui contient la réponse que le servlet renvoie au client
	 * @throws ServletException si la requête pour le POST ne peut être gérée
	 * @throws IOException si une erreur d'entrée ou de sortie est détectée lorsque le servlet gère la requête POST
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Récupération de la session HTTP
		HttpSession session = request.getSession();

		// Récupération des données de session et des paramètres de requête		
		User user = (User) session.getAttribute("user");
		String answer=request.getParameter("answer");

		VerificationService verifService = new VerificationService();
		if (!verifService.checkAll(user)) {
			this.getServletContext().getRequestDispatcher(verifService.getRedirect()).forward(request, response);

		}else {

			// Affichage des informations de débogage
			System.out.println("User Playing : " + user);
			System.out.println("Answer : " + answer);

			// Création ou la récupération du jeu actuel
			Game currentGame = user.getSingleGame();


			// TODO A Verifier la efficacité de cette condition 
			if (currentGame == null || "restart".equals(request.getParameter("action"))) {
				// Redémarrer ou démarrer un nouveau jeu si aucun jeu actif ou action de redémarrage
				int level = Integer.parseInt(request.getParameter("level")); // Assumer que le niveau est passé en paramètre pour un nouveau jeu
				currentGame = new Game(); // Créer un nouveau jeu
				currentGame.getPuzzels(level); // Initialiser les puzzles du jeu en fonction du niveau
				user.setSingleGame(currentGame); // Mettre à jour le jeu de l'utilisateur
				session.setAttribute("user", user); // Mettre à jour l'utilisateur dans la session
				session.setAttribute("indexPuzzle", 0); // Réinitialiser l'index du puzzle
			}

			int puzzleToPlay = (int) session.getAttribute("indexPuzzle"); //counter recupere l'index du puzzle a jouer
			System.out.println("Counter: " + puzzleToPlay);

			if (answer == null) {
				// Gestion de l'affichage du puzzle ou de la fin du jeu
				handlePuzzleDisplay(request, response, user, currentGame, puzzleToPlay);
			} else {
				// Gestion de la réponse soumise
				handlePuzzleResponse(request, response, user, currentGame, puzzleToPlay, answer);
			}
		}
	}	

	/**
	 * Gère l'affichage de l'énigme courante ou la fin du jeu si toutes les énigmes ont été résolues.
	 */
	private void handlePuzzleDisplay(HttpServletRequest request, HttpServletResponse response, User user, Game currentGame, int puzzleToPlay) throws ServletException, IOException {
		if (puzzleToPlay >= currentGame.getPuzzles().size()) {
			// Fin du jeu, calculer et afficher le score
			getServletContext().getRequestDispatcher("/Score").forward(request, response);
		} else {
			// Afficher le puzzle suivant
			Puzzle currentPuzzle = currentGame.getPuzzles().get(puzzleToPlay);
			currentPuzzle.startPuzzle();
			request.getSession().setAttribute("indexPuzzle", puzzleToPlay + 1);  //augmenter la valeur de l'index du puzzle 

			// session.setAttribute("indexPuzzle", puzzleToPlay + 1);
			getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp").forward(request, response);
		}
	}

	/**
	 * Gère la réponse soumise par l'utilisateur à l'énigme actuelle, vérifiant si elle est correcte et agissant en conséquence.
	 */
	private void handlePuzzleResponse(HttpServletRequest request, HttpServletResponse response, User user, Game currentGame, int puzzleToPlay, String answer) throws ServletException, IOException {
		// Récupération du puzzle actuel en se basant sur l'index du puzzle à jouer.
		Puzzle currentPuzzle = currentGame.getPuzzles().get(puzzleToPlay - 1);
		// Marquage de la fin du puzzle
		currentPuzzle.endPuzzle();
		// Vérification si le temps passé sur le puzzle dépasse une certaine limite (100 ici).
		if (currentPuzzle.getTime() > 100) {
			// Si le temps est dépassé, rediriger l'utilisateur vers une page de fin de jeu (GameOver).
			getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/GameOver.jsp").forward(request, response);
		} else if (currentPuzzle.checkAnswer(answer)) {
			// Si la réponse fournie par l'utilisateur est correcte, rediriger vers la page du prochain puzzle.
			getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeSuivant.jsp").forward(request, response);
		} else {
			// Si la réponse est incorrecte, définir un message d'erreur et rediriger l'utilisateur vers la même page d'énigme
			// pour qu'il puisse essayer à nouveau.
			request.setAttribute("messageErreur", "Vous ne pouvez pas sortir de cette chambre, essayez autre chose !!!");
			getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp").forward(request, response);
		}
	}
}
