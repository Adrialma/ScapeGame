package com.adrialma.model;

import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;

/**
 * La classe User représente un utilisateur du système.
 */
public class User {
	private int idUser;
	private String firstName;
	private String lastName;
	private String userName;
	private String password; // Contient le mot de passe chiffré pour une meilleure sécurité
	private List<Game> games = new ArrayList<>(); // Liste des jeux associés à l'utilisateur
	private Boolean isconnected = false; // Indique si l'utilisateur est connecté

	// Constructeur pour initialiser un nouvel utilisateur avec tous les champs nécessaires
	public User(int idUser, String firstName, String lastName, String userName, String password, List<Game> games) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.setPassword(password); //Chiffre et définit le mot de passe
		this.games = games;
	}

	// Constructeur sans la liste des jeux
	public User(int idUser, String firstName, String lastName, String userName, String password) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.setPassword(password); //Chiffre et définit le mot de passe
	}

	// Constructeur pour un utilisateur avec mot de passe déjà chiffré
	public User(int idUser, String firstName, String lastName, String userName, String password, boolean hassPassword) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	// Méthode pour définir et chiffrer le mot de passe de l'utilisateur
	public void setPassword(String pw) {
		this.password = hashPassword(pw);
	}

	// Méthode privée pour chiffrer le mot de passe avec SHA-256
	public String hashPassword(String pass) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(pass.getBytes("UTF-8"));
			StringBuilder hexString = new StringBuilder();
			for (byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) hexString.append('0');
				hexString.append(hex);
			}
			return hexString.toString();
		} catch (Exception e) {
			throw new RuntimeException("Erreur lors du hachage du mot de passe", e);
		}
	}

	// Vérifie si le mot de passe fourni correspond au mot de passe chiffré de l'utilisateur
	public boolean checkPassword(String candidate) {
		return this.password.equals(hashPassword(candidate));
	}

	// Connecte l'utilisateur
	public void connect() {
		this.isconnected = true;
	}

	// Déconnecte l'utilisateur
	public void disconnect() {
		this.isconnected = false;
	}

	// Méthode pour jouer à un jeu avec un niveau de difficulté spécifié
	public void play(int level) {
		System.out.println("User.play() method initialized"); //TODO a effacer
		// Création d'un nouveau jeu avec les énigmes du niveau de difficulté choisi
		Game newGame = new Game();
		newGame.getPuzzels(level);
		games.add(newGame);
	}



	/**
	 * Fonction pour recuperer un Single Game quand l'utilisateur est en mode play
	 * @return l'element Game actual, null si il y a plusiers Games dans la list
	 */
	public Game getSingleGame() {
		if (games.size()==1)
			return games.get(0);
		else
			return null;

	}

	// Getters et Setters
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

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", password=" + password + ", games=" + games + ", isconnected=" + isconnected + "]";
	}
}
