package com.adrialma.model;

import java.util.List;

import com.adrialma.dao.GameDAO;
import com.adrialma.dao.PuzzleDAO;

/**
 * Classe Factory dans le package com.adrialma.model.
 * Cette classe sert à manipuler des listes d'utilisateurs, de jeux et de puzzles.
 */
public class Factory {
	// Liste pour stocker les objets User.
	List<User> userList;

	/**
	 * Constructeur de Factory prenant une liste d'utilisateurs.
	 * @param userList Liste des utilisateurs à gérer.
	 */
	public Factory(List<User> userList) {
		super();
		this.userList = userList;
	}

	public Factory() {
		super();
	}

	/**
	 * Méthode pour obtenir les jeux de chaque utilisateur.
	 * Pour chaque utilisateur dans la liste, récupère ses jeux à l'aide de GameDAO et les assigne à l'utilisateur.
	 */
	public void getGames() {
		for(User user: userList) {
			GameDAO gameDAO = new GameDAO();
			user.setGames(gameDAO.getArray(user.getIdUser()));	
		}
	}

	/**
	 * Méthode pour obtenir les puzzles associés à chaque jeu de chaque utilisateur.
	 * Pour chaque utilisateur et chaque jeu, récupère les puzzles correspondants à l'aide de PuzzleDAO.
	 */
	public void getPuzzles() {
		for(User user: userList)
			for (Game game:user.getGames()) {
				PuzzleDAO puzzleDAO = new PuzzleDAO();
				puzzleDAO.getArray(game.getIdGame() , "idGame");
			}
	}

	//Getter et Setter
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
}
