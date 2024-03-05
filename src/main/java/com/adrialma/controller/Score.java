package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.adrialma.model.Game;
import com.adrialma.model.User;
import com.adrialma.service.VerificationService;

/**
 * Servlet implementation class Score
 */
@WebServlet("/Score")
public class Score extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Score() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
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
		// TODO Apeller la methode pour enregistrer les resultats dans la BDD
		
		
		//Reinitialiser les jeux 
		user.flushGames();
        getServletContext().getRequestDispatcher("/WEB-INF/Enigmes/EnigmeScore.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
