package com.adrialma.model;

import java.time.Duration;
import java.time.LocalTime;

/**
 * La classe Puzzle représente une énigme dans le jeu.
 */
public class Puzzle {
	private int idPuzzle;
	private String description;
	private int level; // Peut être représenté par un String (ex: "Facile = 3", "Moyen = 2", "Difficile= 1")
	private String answer;// la soulution de l'énigme 
	private LocalTime timeStart; // L'heure de début de l'enigme
	private LocalTime timeFin; // L'heure de fin de l'enigme
	private int time; // Le temps passé dans l'énigme en secondes
	private int scorePuzzle;//stocke le resultat calculé avec la méthode scoreCalculate

	// Constructeur avec tous les paramètres
	public Puzzle(int idPuzzle, String description, int level, String answer, int time, int scorePuzzle) {
		super();
		this.idPuzzle = idPuzzle;
		this.description = description;
		this.level = level;
		this.answer = answer;
		this.time = time;
		this.scorePuzzle = scorePuzzle;
	}

	public Puzzle(int idPuzzle, String description, int level, String answer) {
		super();
		this.idPuzzle = idPuzzle;
		this.description = description;
		this.level = level;
		this.answer = answer;
	}
	
	

	public Puzzle(int idPuzzle, String description, int scorePuzzle) {
		super();
		this.idPuzzle = idPuzzle;
		this.description = description;
		this.scorePuzzle = scorePuzzle;
	}

	// Méthode pour démarrer l'énigme et enregistrer l'heure de début
	public void startPuzzle() {
		timeStart = LocalTime.now();
	}

	// Méthode pour terminer l'énigme, enregistrer l'heure de fin et calculer le temps écoulé
	public int endPuzzle() {
		timeFin = LocalTime.now();
		Duration duration = Duration.between(timeStart, timeFin);
		time = (int) duration.getSeconds(); // Calcul du temps en secondes
		return time;
	}

	// Méthode pour calculer le score de l'énigme
	public void scoreCalculate() {
		scorePuzzle = (100 - time + 1) / level;
	}

	/**
	 * Vérification de la réponse saissie par l'utilisateur
	 * 
	 * @param userAnswer - response de l'utilisateur
	 * @return true si response est correcte, false en cas contraire
	 */
	public boolean checkAnswer(String userAnswer) {
		return userAnswer.equals(answer); // Comparaison de la réponse de l'utilisateur avec la solution
	}

	// Getters et Setters
	public int getIdPuzzle() {
		return idPuzzle;
	}

	public void setIdPuzzle(int idPuzzle) {
		this.idPuzzle = idPuzzle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getScorePuzzle() {
		return scorePuzzle;
	}

	public void setScorePuzzle(int scorePuzzle) {
		this.scorePuzzle = scorePuzzle;
	}

	public LocalTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalTime getTimeFin() {
		return timeFin;
	}

	public void setTimeFin(LocalTime timeFin) {
		this.timeFin = timeFin;
	}

	@Override
	public String toString() {
		return "Puzzle [idPuzzle=" + idPuzzle + ", description=" + description + ", level=" + level + ", answer="
				+ answer + ", time=" + time + ", scorePuzzle=" + scorePuzzle + "]";
	}
}
