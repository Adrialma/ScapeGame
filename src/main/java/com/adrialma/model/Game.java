package com.adrialma.model;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.adrialma.dao.PuzzleDAO;

/**
 * Représente une session de jeu, incluant les détails et la logique pour gérer une partie.
 * Chaque partie est caractérisée par son identifiant, sa date, son heure de début et de fin,
 * le score total, une liste des puzzles joués, et le niveau de difficulté sélectionné par le joueur.
 */
public class Game {
	private int idGame;
	private Date date; // Date de la partie
	private Time start; // Heure de début de la partie
	private Time fin; // Heure de fin de la partie
	private int score; // Score moyen des puzzles
	private List<Puzzle> puzzles = new ArrayList<>(); // Liste des puzzles joués pendant la partie
	private int levelPlayed; // Niveau de difficulté choisi pour la partie

	/**
	 * Constructeur complet permettant d'initialiser une partie avec tous ses attributs.
	 * 
	 * @param idGame Identifiant unique de la partie.
	 * @param date Date de la partie.
	 * @param start Heure de début de la partie.
	 * @param fin Heure de fin de la partie.
	 * @param score Score moyen obtenu dans les puzzles.
	 * @param puzzles Liste des puzzles joués.
	 * @param levelPlayed Niveau de difficulté sélectionné.
	 */
	public Game(int idGame, Date date, Time start, Time fin, int score, List<Puzzle> puzzles, int levelPlayed) {
		super();
		this.idGame = idGame;
		this.date = date;
		this.start = start;
		this.fin = fin;
		this.score = score;
		this.puzzles = puzzles;
		this.levelPlayed = levelPlayed;
	}
	
	
	// Constructeur sans liste de puzzles
		public Game(int idGame, Date date, Time start, Time fin, int score,  int levelPlayed) {
			super();
			this.idGame = idGame;
			this.date = date;
			this.start = start;
			this.fin = fin;
			this.score = score;
			this.levelPlayed = levelPlayed;
		}
	
	
	

	/**
	 * Constructeur par défaut pour initialiser une nouvelle partie sans détails spécifiques.
	 */
	public Game() {
		super();
	}

	/**
	 * Exécute la logique du jeu, calculant notamment le score moyen des puzzles joués.
	 */
	public void exec() {
		// Calcul de la moyenne des scores obtenus dans la classe Puzzle
		for (Puzzle puzzle : puzzles) {
			puzzle.scoreCalculate(); // Calcul du score pour chaque puzzle
			score += puzzle.getScorePuzzle(); // Addition des scores de chaque puzzle
		}

		score = score / puzzles.size(); // Calcul de la moyenne
	}

	/**
	 * Récupère et initialise les puzzles en fonction du niveau de difficulté sélectionné.
	 * 
	 * @param level Niveau de difficulté des puzzles à récupérer.
	 * @return List de puzzels récupérés
	 */
	public void getPuzzels(int level) {
		System.out.println("Getting puzzle list for level: " + level); // TODO message print a mettre dans log.txt si il reste du temps

		PuzzleDAO puzzleDAO = new PuzzleDAO();
		// Récupération des puzzles depuis la base de données
		puzzles= new ArrayList<Puzzle>(puzzleDAO.getArray(level));
		System.out.println("Liste de puzzles");

		for (Puzzle puzzle: puzzles) {
			System.out.println(puzzle);
		}
	}

	/**
	 * Initialise les horaires de début de la partie à la date et l'heure actuelles.
	 */
	public void initDates() {
		date = Date.valueOf(LocalDate.now());
		start = Time.valueOf(LocalTime.now());
	}

	/**
	 * Enregistre l'heure de fin de la partie à l'heure actuelle.
	 */
	public void endDates() {
		fin = Time.valueOf(LocalTime.now());
	}

	// Getters et Setters
	public int getIdGame() {
		return idGame;
	}

	public void setIdGame(int idGame) {
		this.idGame = idGame;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getStart() {
		return start;
	}

	public void setStart(Time start) {
		this.start = start;
	}

	public Time getFin() {
		return fin;
	}

	public void setFin(Time fin) {
		this.fin = fin;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<Puzzle> getPuzzles() {
		return puzzles;
	}

	public void setPuzzles(List<Puzzle> puzzles) {
		this.puzzles = puzzles;
	}

	public int getLevelPlayed() {
		return levelPlayed;
	}

	public void setLevelPlayed(int levelPlayed) {
		this.levelPlayed = levelPlayed;
	}

	@Override
	public String toString() {
		return "Game [idGame=" + idGame + ", date=" + date + ", start=" + start + ", fin=" + fin + ", score=" + score
				+ ", puzzles=" + puzzles + ", levelPlayed=" + levelPlayed + "]";
	}	
}
