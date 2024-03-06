package com.adrialma.model;

import java.util.List;

import com.adrialma.dao.GameDAO;
import com.adrialma.dao.PuzzleDAO;
import com.adrialma.dao.UserDAO;

public class Factory {
	List<User> userList;

	public Factory(List<User> userList) {
		super();
		this.userList = userList;
	}

	public Factory() {
		super();
	}

	
	public void getGames() {
		for(User user: userList) {
			GameDAO gameDAO = new GameDAO();
			user.setGames(gameDAO.getArray(user.getIdUser()));	
		}
		
	}
	
	public void getPuzzles() {
		for(User user: userList)
			for (Game game:user.getGames()) {
				PuzzleDAO puzzleDAO = new PuzzleDAO();
				puzzleDAO.getArray(game.getIdGame() , "idGame");
			}
	}
	
	
	
	
	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	
	
	
	
	
	
}
