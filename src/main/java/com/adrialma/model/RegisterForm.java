package com.adrialma.model;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class RegisterForm {
	private User user;
	private HashMap<String, String> errorList;
	
	public RegisterForm(HttpServletRequest request) {
		super();
		
		errorList = new HashMap<String, String>();
		
		this.validateField(request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("userName"),
				request.getParameter("password"));
		
		user = new User(0, request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("userName"),
				request.getParameter("password"));
		
	}
	
	
	public boolean validateField(String firstName, String lastName, String userName, String password) {
		
		if (firstName.length() < 1)
		{
			errorList.put("firstName", "Le nom ne doit pas être vide");
		}
		
		if (lastName.length() < 1)
		{
			errorList.put("lastName", "Le prenom ne doit pas être vide");
		}
		
		if (userName.length() < 1)
		{
			errorList.put("userName", "Le username ne doit pas être vide");
			
			// TODO Valider avec la BD que le nom d'utilisateur n'existe pas
			// 
		}
		
		if (password.length() < 1)
		{
			errorList.put("password", "Le mot de pass ne doit pas être vide");
		} else if (password.length() < 4) {
			errorList.put("password", "Le mot de pass doit avoir au moin 4 caractères");
		}
		
		
		return  errorList.size() < 1 ; // TRUE si il ya pas de erreur, FALSE si il y des erreurs
		
		
	}

	
	

	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	public HashMap<String, String> getErrorList() {
		return errorList;
	}


	public void setErrorList(HashMap<String, String> errorList) {
		this.errorList = errorList;
	}
	
	
	
	
	
	
	

}
