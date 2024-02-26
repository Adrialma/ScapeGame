package com.adrialma.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Game {
	private int idGame;
    private Date date; // pour stocker la date de la session
    private Date start; // L'heure de début de la session
    private Date fin; // L'heure de fin de la session
    private int score; //out, à calculer en fonction du temps passé et la diffuculté
	private List<Puzzle> puzzles= new ArrayList<Puzzle>();

}
//score = sum scorePuzzle/nbr de puzzle