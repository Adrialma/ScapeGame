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
		// TODO Auto-generated method stub
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

		// Affichage des informations de débogage
		System.out.println("User Playing : " + user);
		System.out.println("Answer : " + answer);

		// Redirection vers la page de connexion si l'utilisateur n'est pas connecté
		if (user == null) {response.sendRedirect("loginPage.jsp"); } 
		
        // Création ou la récupération du jeu actuel
        Game currentGame = user.getSingleGame();
        
     // Redirection vers la page d'accueil si aucun jeu n'est actif
       // if (currentGame == null) {response.sendRedirect("HomePage.jsp"); }  
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

		/*if (answer==null) {   // Si aucune réponse n'est soumise, on affiche le puzzle suivant ou le score si c'est le dernier puzzle
			if (puzzleToPlay >= currentGame.getPuzzles().size() ) { //si c'est le dernier puzzle on affiche le score
				System.out.println("*** Fin de Puzzles ****");
				
				 // Calcul du score en fin de jeu
                currentGame.exec();
                request.setAttribute("gameScore", currentGame.getScore());
                System.out.println("gameScore");
			     // Redirection vers la page de score si y'a plus de jeu
				this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeScore.jsp").forward(request, response);
			}else {

				// afficher un nouveau puzzle
				Puzzle currentPuzzle = user.getSingleGame().getPuzzles().get(puzzleToPlay);
				currentPuzzle.startPuzzle();

				request.getSession().setAttribute("indexPuzzle", puzzleToPlay + 1);  //augmenter la valeur de l'index du puzzle 
				
				System.out.println("User playing new puzzle: /WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp").forward(request, response);
			}
		}else {*/
		if (answer == null) {
            // Gestion de l'affichage du puzzle ou de la fin du jeu
            handlePuzzleDisplay(request, response, user, currentGame, puzzleToPlay);
        } else {
            // Gestion de la réponse soumise
            handlePuzzleResponse(request, response, user, currentGame, puzzleToPlay, answer);
        }
    }
	
	private void handlePuzzleDisplay(HttpServletRequest request, HttpServletResponse response, User user, Game currentGame, int puzzleToPlay) throws ServletException, IOException {
        if (puzzleToPlay >= currentGame.getPuzzles().size()) {
            // Fin du jeu, calculer et afficher le score
            currentGame.exec();
            request.setAttribute("gameScore", currentGame.getScore());
            getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeScore.jsp").forward(request, response);
        } else {
            // Afficher le puzzle suivant
            Puzzle currentPuzzle = currentGame.getPuzzles().get(puzzleToPlay);
            currentPuzzle.startPuzzle();
			request.getSession().setAttribute("indexPuzzle", puzzleToPlay + 1);  //augmenter la valeur de l'index du puzzle 

           // session.setAttribute("indexPuzzle", puzzleToPlay + 1);
            getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp").forward(request, response);
        }
    }
    
	/*


			// C'est pas un nouveau jeu, l'utilisateur a saisie une reponse
			Puzzle currentPuzzle = user.getSingleGame().getPuzzles().get(puzzleToPlay-1);
			currentPuzzle.endPuzzle();
			System.out.println("L'user a passé : " + currentPuzzle.getTime() + " secondes ");

			// Si il a depasé le temps l'utilisateur a perdu
			if(currentPuzzle.getTime() > 100) {
				System.out.println("L'utilisateur a depassé le temp, game over...");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/GameOver.jsp").forward(request, response);
			}

			// verifier que la reponse est correcte
			if (currentPuzzle.checkAnswer(answer)) {

				// afficher page de sortie de l'enigme
				System.out.println("Response Correcte");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeSuivant.jsp").forward(request, response);
			}else {

				//afficher message reponse incorrecte
				request.setAttribute("messageErreur", " Vous ne pouvez pas sortir de cette chambre, essayez autre chose !!! ");
				System.out.println("Response Incorrecte, Reponse correcte: " + currentPuzzle.getAnswer());
				this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp").forward(request, response);
			}		
		}
	}*/
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
