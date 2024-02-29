package com.adrialma.model;

/**
 * 
 */
public class Puzzle {
	private int idPuzzle;
	private String description;
	private int level; // Peut être représenté par un String (ex: "Facile = 3", "Moyen = 2", "Difficile= 1")
	private String answer;// la soulution de l'énigme 
	
	private int time; // Le temps passé dans l'énigme en secondes
	private int scorePuzzle;//stocke le resultat calculé avec la méthode scoreCalculate
	
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




	public void scoreCalculate(){
		//todo 
		//calcule du score en fonction du temps passé sur la session et le niveau de defficulté
		//time                 ScorePuzzle
		//1 sec                  100 /level
		//2                      99/level
		//...                        ...          .
		//99                      2/level
		//100                    1/level
	}
	
	
	
	
	/**
	 * Vérification de la réponse saissie par l'utilisateur
	 * 
	 * @param userAnswer - response de l'utilisateur
	 * @return true si response est correcte, false en cas contraire
	 */
	public boolean checkAnswer(String userAnswer) {
	
		if (userAnswer.equals(answer))
			return true;
		else
			return false;
	}

	
	
	
	
	
	
	
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




	@Override
	public String toString() {
		return "Puzzle [idPuzzle=" + idPuzzle + ", description=" + description + ", level=" + level + ", answer="
				+ answer + ", time=" + time + ", scorePuzzle=" + scorePuzzle + "]";
	}
	
	
	

}
