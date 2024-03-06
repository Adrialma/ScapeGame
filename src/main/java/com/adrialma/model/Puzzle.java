package com.adrialma.model;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Représente une énigme individuelle dans le jeu. Chaque énigme a un niveau de difficulté,
 * une description, une solution attendue, et un mécanisme de suivi du temps passé sur l'énigme.
 */
public class Puzzle {
	private int idPuzzle;
	private String description;
	private int level; // Niveau de difficulté de l'énigme (3 pour Facile, 2 pour Moyen, 1 pour Difficile)
	private String answer;// la soulution de l'énigme 
	private LocalTime timeStart; // L'heure de début de l'enigme
	private LocalTime timeFin; // L'heure de fin de l'enigme
	private int time; // Le temps passé dans l'énigme en secondes
	private int scorePuzzle;//stocke le resultat calculé avec la méthode scoreCalculate

	/**
	 * Constructeur complet pour créer une énigme avec tous les détails nécessaires.
	 * 
	 * @param idPuzzle Identifiant unique de l'énigme.
	 * @param description Description textuelle de l'énigme.
	 * @param level Niveau de difficulté de l'énigme.
	 * @param answer La solution de l'énigme.
	 * @param time Temps passé sur l'énigme, en secondes.
	 * @param scorePuzzle Score calculé de l'énigme.
	 */
	public Puzzle(int idPuzzle, String description, int level, String answer, int time, int scorePuzzle) {
		super();
		this.idPuzzle = idPuzzle;
		this.description = description;
		this.level = level;
		this.answer = answer;
		this.time = time;
		this.scorePuzzle = scorePuzzle;
	}

	/**
	 * Constructeur simplifié pour initialiser une énigme sans temps ni score.
	 * Utilisé principalement lors de la création d'une nouvelle énigme.
	 * 
	 * @param idPuzzle Identifiant unique de l'énigme.
	 * @param description Description textuelle de l'énigme.
	 * @param level Niveau de difficulté de l'énigme.
	 * @param answer La solution de l'énigme.
	 */
	public Puzzle(int idPuzzle, String description, int level, String answer) {
		super();
		this.idPuzzle = idPuzzle;
		this.description = description;
		this.level = level;
		this.answer = answer;
	}

	/**
	 * Démarre l'énigme en enregistrant l'heure de début.
	 */
	public void startPuzzle() {
		timeStart = LocalTime.now();
	}

	/**
	 * Termine l'énigme, enregistre l'heure de fin, calcule et retourne le temps écoulé.
	 * 
	 * @return Le temps passé sur l'énigme, en secondes.
	 */
	public int endPuzzle() {
		timeFin = LocalTime.now();
		Duration duration = Duration.between(timeStart, timeFin);
		time = (int) duration.getSeconds(); // Calcul du temps en secondes
		return time;
	}

	/**
	 * Calcule le score de l'énigme basé sur le temps passé et le niveau de difficulté.
	 */
	public void scoreCalculate() {
		scorePuzzle = (100 - time + 1) / level;
	}

	/**
	 * Vérifie si la réponse fournie par l'utilisateur est correcte.
	 * 
	 * @param userAnswer La réponse de l'utilisateur.
	 * @return true si la réponse est correcte, false sinon.
	 */
	public boolean checkAnswer(String userAnswer) {
		return userAnswer.equals(answer); 
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
