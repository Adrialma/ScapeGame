package com.adrialma.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.adrialma.dao.PuzzleDAO;

/**
 * 
 */
public class Game {
	private int idGame;
    private Date date; // pour stocker la date de la session
    private Date start; // L'heure de début de la session
    private Date fin; // L'heure de fin de la session
    private int score; //out, la moyenne des scores obtenus dans la classe Puzzle
	private List<Puzzle> puzzles= new ArrayList<Puzzle>();
	private int levelPlayed;// le niveau de defficulté choisi par le joueur
	
	public Game(int idGame, Date date, Date start, Date fin, int score, List<Puzzle> puzzles, int levelPlayed) {
		super();
		this.idGame = idGame;
		this.date = date;
		this.start = start;
		this.fin = fin;
		this.score = score;
		this.puzzles = puzzles;
		this.levelPlayed = levelPlayed;
	}
	
	

	public Game() {
		super();
	}



	public void exec() {
		//todo
		//calcule de la moyenne des scores obtenus dans la classe Puzzle
		//score = sum scorePuzzle/nbr de puzzle
	}
	
	
	
	/**
	 * methode qui recupere les puzzels selon le level choissi
	 * @param level
	 * @return List de puzzels 
	 */
	public void getPuzzels(int level) {
		System.out.println("Getting puzzle list for level: " + level); // TODO message print a mettre dans log.txt si il reste du temps
		
		PuzzleDAO puzzleDAO = new PuzzleDAO();
		puzzles= new ArrayList<Puzzle>(puzzleDAO.getArray(level));
		
		System.out.println("Liste de puzzles");
		 
		for (Puzzle puzzle: puzzles) {
			System.out.println(puzzle);
		}
		
		
		
	}

	/***************** Getters and Setters****************************/

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



	public Date getStart() {
		return start;
	}



	public void setStart(Date start) {
		this.start = start;
	}



	public Date getFin() {
		return fin;
	}



	public void setFin(Date fin) {
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
