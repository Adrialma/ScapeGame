package com.adrialma.model;

import java.util.List;
import java.util.ArrayList;


public class User {
	private int idUser;
	private String firstName;
	private String lastName;
	private String userName;
	private String password;//stock le mdp chiffré/déchiffré
	private List<Game> games= new ArrayList<Game>();
	private Boolean isconnected;
	
	public User(int idUser, String firstName, String lastName, String userName, String password, List<Game> games) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
		this.games = games;
	}
	
	
	
	
	
	
	
	public User(int idUser, String firstName, String lastName, String userName, String password) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}







	public void encrypt() {
		//todo
		//chiffrage de mot de passe
		//méthode à définir
		
	}
	
	public void decrypt() {
		//todo
		//dechiffrage du mot de passe
		//méthode à définir
	}
	
	public void connect() {
		//todo
		isconnected = true;
	}
	public void play() {
		//todo
		//played level
		//
		
	}







	public int getIdUser() {
		return idUser;
	}







	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}







	public String getFirstName() {
		return firstName;
	}







	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}







	public String getLastName() {
		return lastName;
	}







	public void setLastName(String lastName) {
		this.lastName = lastName;
	}







	public String getUserName() {
		return userName;
	}







	public void setUserName(String userName) {
		this.userName = userName;
	}







	public String getPassword() {
		return password;
	}







	public void setPassword(String password) {
		this.password = password;
	}







	public List<Game> getGames() {
		return games;
	}







	public void setGames(List<Game> games) {
		this.games = games;
	}







	public Boolean getIsconnected() {
		return isconnected;
	}







	public void setIsconnected(Boolean isconnected) {
		this.isconnected = isconnected;
	}
	
	
	
	
	
	
	
	
	
	
	
}
