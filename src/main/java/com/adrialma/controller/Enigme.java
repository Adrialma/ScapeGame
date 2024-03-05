package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.w3c.dom.html.HTMLIFrameElement;

import com.adrialma.model.Game;
import com.adrialma.model.Puzzle;
import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Servlet implementation class Enigme
 */
@WebServlet("/Enigme")
public class Enigme extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Enigme() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		VerificationService verifService = new VerificationService();
		
		if (!verifService.checkAll(user)) {
			this.getServletContext().getRequestDispatcher(verifService.getRedirect()).forward(request, response);
			
		}else
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
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

	private void handlePuzzleResponse(HttpServletRequest request, HttpServletResponse response, User user, Game currentGame, int puzzleToPlay, String answer) throws ServletException, IOException {
        Puzzle currentPuzzle = currentGame.getPuzzles().get(puzzleToPlay - 1);
        currentPuzzle.endPuzzle();
        if (currentPuzzle.getTime() > 100) {
            getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/GameOver.jsp").forward(request, response);
        } else if (currentPuzzle.checkAnswer(answer)) {
            getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeSuivant.jsp").forward(request, response);
        } else {
            request.setAttribute("messageErreur", "Vous ne pouvez pas sortir de cette chambre, essayez autre chose !!!");
            getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp").forward(request, response);
        }
    }
}
