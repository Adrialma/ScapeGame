package com.adrialma.exception;


public class GameException extends Exception {

	private static final long serialVersionUID = 1L;
	
	private String mess;
	private int gravite;

	
	
	
	public GameException() {
		super();
	}






	public GameException(String mess) {
		super();
		this.mess = mess;
	}
 
	
	
	
	
	
	public GameException(String mess, int gravite) {
		super();
		this.mess = mess;
		this.gravite = gravite;
	}






	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return mess;
	}


	public void printMessage() {
		System.out.println(this.toString());
	}



	@Override
	public String toString() {
		return "Le program a levé la exception suivante: " + mess + ", gravite=" + gravite ;
	}
	
	
	
	
	

}
