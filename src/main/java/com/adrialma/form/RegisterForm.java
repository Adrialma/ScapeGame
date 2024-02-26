package com.adrialma.form;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.adrialma.dao.DaoBd;
import com.adrialma.dao.UserDAO;
import com.adrialma.model.User;

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
			
			
		}
		
		if (password.length() < 1)
		{
			errorList.put("password", "Le mot de pass ne doit pas être vide");
		} else if (password.length() < 4) {
			errorList.put("password", "Le mot de pass doit avoir au moin 4 caractères");
		}
		
		
		
		// Valider avec la BD que le nom d'utilisateur n'existe pas
		
					
		if (userNameFound(userName)) {
			System.out.println("pas d'enregistrement, L'utilisateur existe"); //TODO a effacer
			errorList.put("userName", "Nom d'utilisateur pas disponible, choissisez un autre");
		}
		else {
			System.out.println("L'utilisateur n'existe pas, Enregistrement possible"); //TODO a effacer
			UserDAO userDAO = new UserDAO();
			if( !userDAO.add(new User(0, firstName, lastName, userName, password))) {
				//TODO ERREUR DANS L'ENREGISTREMENT (ERREUR DANS LE PROCESUS CRUDE), AFFICHER MESSAGE
				//TODO lever exeption
				errorList.put("bderror", "Il y a eu un erreur dans l'enregistrement, Veillez essayer a noveau");
			}
			
		}
		
		
		return  errorList.size() < 1 ; // TRUE si il ya pas de erreur, FALSE si il y des erreurs
		
		
	}
	
	
	
	
	public boolean userNameFound(String userName) {

		UserDAO userDAO = new UserDAO();
		if ( userDAO.get(userName) == null)
			return false; // userName n'existe pas
		else
			return true; // userName existe
		
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
