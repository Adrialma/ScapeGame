package com.adrialma.exception;

/**
 * Classe personnalisée pour les exceptions liées aux jeux.
 * Permet de gérer des exceptions spécifiques au domaine de l'application avec un message et un niveau de gravité.
 */
public class GameException extends Exception {
	private static final long serialVersionUID = 1L;
	// Message détaillant l'exception
	private String mess;
	// Niveau de gravité de l'exception
	private int gravite;

	public GameException() {
		super();
	}

	/**
	 * Constructeur avec un message d'erreur.
	 * 
	 * @param mess Le message d'erreur associé à l'exception.
	 */
	public GameException(String mess) {
		super();
		this.mess = mess;
	}

	/**
	 * Constructeur avec un message d'erreur et un niveau de gravité.
	 * 
	 * @param mess Le message d'erreur associé à l'exception.
	 * @param gravite Le niveau de gravité de l'exception.
	 */
	public GameException(String mess, int gravite) {
		super();
		this.mess = mess;
		this.gravite = gravite;
	}

	/**
	 * Récupère le message d'erreur de l'exception.
	 * 
	 * @return Le message d'erreur.
	 */
	@Override
	public String getMessage() {
		return mess;
	}

	/**
	 * Affiche le message d'erreur dans la console.
	 */
	public void printMessage() {
		System.out.println(this.toString());
	}

	/**
	 * Retourne une chaîne de caractères représentant l'exception, incluant le message et le niveau de gravité.
	 * 
	 * @return La représentation textuelle de l'exception.
	 */
	@Override
	public String toString() {
		return "Le program a levé la exception suivante: " + mess + ", gravite=" + gravite ;
	}
}
