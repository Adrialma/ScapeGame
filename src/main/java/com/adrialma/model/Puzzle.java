package com.adrialma.model;

import java.util.Date;

public class Puzzle {
	private int idPuzzle;
	private String description;
	private int level; // Peut être représenté par un String (ex: "Facile = 3", "Moyen = 2", "Difficile= 1")
	private String answer;// la soulution de l'énigme
	private int time; // Le temps passé dans l'énigme en secondes
	private int scorePuzzle;

}
//
//time        ScorePuzzle
//1                                   100 /level
//2                                  99/level
//.                                    .
//99                                 2/level
//100                                1/level