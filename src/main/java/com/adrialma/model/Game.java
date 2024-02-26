package com.adrialma.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {
	private int idGame;
    private Date date; // pour stocker la date de la session
    private Date start; // L'heure de début de la session
    private Date fin; // L'heure de fin de la session
    private int score; //out, la moyenne des scores obtenus dans la classe Puzzle
	private List<Puzzle> puzzles= new ArrayList<Puzzle>();
	private String levelPlayed;// le niveau de defficulté choisi par le joueur
	
	public Game(int idGame, Date date, Date start, Date fin, int score, List<Puzzle> puzzles, String levelPlayed) {
		super();
		this.idGame = idGame;
		this.date = date;
		this.start = start;
		this.fin = fin;
		this.score = score;
		this.puzzles = puzzles;
		this.levelPlayed = levelPlayed;
	}

	public void exec() {
		//todo
		//calcule de la moyenne des scores obtenus dans la classe Puzzle
		//score = sum scorePuzzle/nbr de puzzle
	}
	
	
	
}
