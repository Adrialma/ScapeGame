package com.adrialma.model;

import java.util.List;
import java.util.ArrayList;
import java.security.MessageDigest;

/**
 * La classe User représente un utilisateur du système. Elle encapsule toutes les informations et
 * comportements relatifs à un utilisateur, y compris son identification, ses informations personnelles,
 * la gestion de ses mots de passe et la manipulation de sa liste de jeux.
 */
public class User {
	private int idUser;
	private String firstName;
	private String lastName;
	private String userName;
	private String password; // Contient le mot de passe chiffré pour une meilleure sécurité
	private List<Game> games = new ArrayList<>(); // Liste des jeux associés à l'utilisateur
	private Boolean isconnected = false; // Indique si l'utilisateur est connecté

	/**
	 * Constructeur complet pour initialiser un nouvel utilisateur avec tous les champs nécessaires,
	 * y compris une liste de jeux. Le mot de passe est automatiquement chiffré.
	 * 
	 * @param idUser    Identifiant unique de l'utilisateur.
	 * @param firstName Prénom de l'utilisateur.
	 * @param lastName  Nom de famille de l'utilisateur.
	 * @param userName  Nom d'utilisateur pour l'authentification.
	 * @param password  Mot de passe en clair qui sera chiffré.
	 * @param games     Liste des jeux associés à l'utilisateur.
	 */
	public User(int idUser, String firstName, String lastName, String userName, String password, List<Game> games) {
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.setPassword(password); //Chiffre et définit le mot de passe
		this.games = games;
	}

	/**
	 * Constructeur simplifié sans la liste des jeux. Utilisé pour créer un utilisateur sans jeux associés.
	 * 
	 * @param idUser    Identifiant unique de l'utilisateur.
	 * @param firstName Prénom de l'utilisateur.
	 * @param lastName  Nom de famille de l'utilisateur.
	 * @param userName  Nom d'utilisateur pour l'authentification.
	 * @param password  Mot de passe en clair qui sera chiffré.
	 */
	public User(int idUser, String firstName, String lastName, String userName, String password) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.setPassword(password); //Chiffre et définit le mot de passe
	}

	/**
	 * Constructeur pour un utilisateur avec mot de passe déjà chiffré, utilisé lors de la récupération
	 * d'un utilisateur depuis une base de données où le mot de passe est déjà stocké sous forme chiffrée.
	 * 
	 * @param idUser       Identifiant unique de l'utilisateur.
	 * @param firstName    Prénom de l'utilisateur.
	 * @param lastName     Nom de famille de l'utilisateur.
	 * @param userName     Nom d'utilisateur pour l'authentification.
	 * @param password     Mot de passe chiffré.
	 * @param hasPassword  Indicateur que le mot de passe est déjà chiffré (toujours vrai dans ce cas).
	 */
	public User(int idUser, String firstName, String lastName, String userName, String password, boolean hassPassword) {
		super();
		this.idUser = idUser;
		this.firstName = firstName;
		this.lastName = lastName;
		this.userName = userName;
		this.password = password;
	}

	/**
	 * Méthode pour chiffrer et définir le mot de passe de l'utilisateur. Cette méthode est utilisée
	 * pour mettre à jour le mot de passe de l'utilisateur en s'assurant qu'il est correctement chiffré.
	 * 
	 * @param pw Le nouveau mot de passe en clair à chiffrer et à enregistrer.
	 */
	public void setPassword(String pw) {
		this.password = hashPassword(pw);
	}

	/**
	 * Méthode privée pour chiffrer le mot de passe avec SHA-256
	 * @param pass
	 * @return
	 */
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

	/**
	 * Vérifie si le mot de passe fourni correspond au mot de passe chiffré stocké pour cet utilisateur.
	 * Utilise la méthode hashPassword pour chiffrer le mot de passe fourni avant la comparaison.
	 * 
	 * @param candidate Le mot de passe à vérifier.
	 * @return true si le mot de passe fourni correspond au mot de passe de l'utilisateur, false sinon.
	 */
	public boolean checkPassword(String candidate) {
		return this.password.equals(hashPassword(candidate));
	}

	/**
	 * Connecte l'utilisateur en mettant à jour son statut de connexion à true.
	 * Utilisé pour contrôler l'accès de l'utilisateur à certaines fonctionnalités du système.
	 */
	public void connect() {
		this.isconnected = true;
	}

	/**
	 * Déconnecte l'utilisateur en mettant à jour son statut de connexion à false.
	 * Utile pour limiter l'accès lorsque l'utilisateur n'est plus actif dans la session.
	 */
	public void disconnect() {
		this.isconnected = false;
	}

	/**
	 * Permet à l'utilisateur de démarrer un nouveau jeu avec un niveau de difficulté spécifié.
	 * Finalise le jeu en cours s'il y en a un avant de démarrer un nouveau jeu.
	 * 
	 * @param level Le niveau de difficulté du nouveau jeu.
	 */
	public void play(int level) {
		System.out.println("User.play() method initialized"); //TODO a effacer
		// Vérifie s'il y a un jeu en cours et le termine si nécessaire
		if (!games.isEmpty() && getSingleGame() != null) {
			getSingleGame().exec(); // Calcule le score final du jeu actuel
		}
		// Création d'un nouveau jeu avec les énigmes du niveau de difficulté choisi
		Game newGame = new Game();
		newGame.setLevelPlayed(level);
		newGame.getPuzzels(level);
		newGame.initDates();
		games.add(newGame);
	}

	/**
	 * Récupère le dernier jeu auquel l'utilisateur a joué ou null s'il n'y a pas de jeux dans la liste.
	 * Utile pour obtenir le jeu actuel sans avoir besoin de connaître l'indice spécifique dans la liste.
	 * 
	 * @return Le dernier jeu de la liste des jeux de l'utilisateur ou null si la liste est vide.
	 */
	public Game getSingleGame() {
		return games.isEmpty() ? null : games.get(games.size() - 1);
	}

	/**
	 * Ajoute un nouveau jeu à la liste des jeux de l'utilisateur ou met à jour le dernier jeu si nécessaire.
	 * Cette méthode s'assure que la liste des jeux contient toujours le jeu actuel de l'utilisateur.
	 * 
	 * @param currentGame Le jeu à ajouter ou à mettre à jour dans la liste des jeux de l'utilisateur.
	 */
	public void setSingleGame(Game currentGame) {
		// Si la liste des jeux est vide ou le dernier jeu n'est pas actif, ajoutez le nouveau jeu
		if (games.isEmpty() || !games.get(games.size() - 1).equals(currentGame)) {
			games.add(currentGame);
		} else {
			// Si le dernier jeu de la liste est déjà le jeu actuel, cette méthode peut être utilisée pour mettre à jour ses informations si nécessaire
		}
	}

	/**
	 * Efface la liste des jeux de l'utilisateur, utile pour réinitialiser l'historique des jeux ou lors de la création d'un nouvel utilisateur.
	 */
	public void flushGames() {
		this.games =new ArrayList<>();
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
