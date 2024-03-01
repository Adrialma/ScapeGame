package com.adrialma.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
				
				
		int puzzleToPlay = (int) session.getAttribute("indexPuzzle"); //counter recupere l'index du puzzle a jouer
		User user = (User) session.getAttribute("user");
		String answer=request.getParameter("answer");
		
		System.out.println("User Playing : " + user);
		System.out.println("Answer : " + answer);
		System.out.println("Counter: " + puzzleToPlay);
		
		if (user == null) {response.sendRedirect("loginPage.jsp"); } 
			

		if (answer==null) {   // Jouer un nouveau puzzle
			if (puzzleToPlay == user.getSingleGame().getPuzzles().size() ) { //si c'est le dernier puzzle on affiche le score
				System.out.println("*** Fin de Puzzles ****");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeScore.jsp").forward(request, response);
				
			}else {
				
				// afficher un nouveau puzzle
				Puzzle currentPuzzle = user.getSingleGame().getPuzzles().get(puzzleToPlay);
				currentPuzzle.startPuzzle();
	
				request.getSession().setAttribute("indexPuzzle", puzzleToPlay + 1);  //augmenter la valeur de l'index du puzzle 
				/**************/
				System.out.println("User playing new puzzle: /WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/Enigme" + currentPuzzle.getIdPuzzle() + ".jsp").forward(request, response);
				
			}
				
			
			
		}else {
			
			// C'est pas un nouveau jeu, l'utilisateur a saisie une reponse
			
			Puzzle currentPuzzle = user.getSingleGame().getPuzzles().get(puzzleToPlay-1);
			currentPuzzle.endPuzzle();
			
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
		
		

	}
	

}
