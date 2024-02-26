package com.adrialma.form;

import com.adrialma.model.User;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

public class RegisterForm {
	private User user;
	private HashMap<String, String> errorList;

	// Constructeur qui initialise le formulaire à partir de la requête HTTP
	public RegisterForm(HttpServletRequest request) {
		super();

		errorList = new HashMap<String, String>();

		// Valide les champs du formulaire
		boolean isValid = validateField(
				request.getParameter("firstName"),
				request.getParameter("lastName"),
				request.getParameter("userName"),
				request.getParameter("password")
				);

		// Crée un nouvel utilisateur si tous les champs sont valides
		if (isValid) {
			user = new User(0, // ID utilisateur, à remplacer par une valeur générée ou récupérée de la BD
					request.getParameter("firstName"),
					request.getParameter("lastName"),
					request.getParameter("userName"),
					request.getParameter("password"), // le mot de passe est chiffré avant stockage
					null // Liste des jeux, peut être initialisée ici ou plus tard
					);
		}
	}

	// Valide les champs et ajoute des erreurs à errorList si nécessaire
	public boolean validateField(String firstName, String lastName, String userName, String password) {
		//Appel à la méthode validateName
		validateName("firstName", firstName);
		validateName("lastName", lastName);

		//Vérification des champs username et password
		if (userName == null || userName.trim().isEmpty()) {
			errorList.put("userName", "Le nom d'utilisateur ne doit pas être vide");
		} else if (userName.length() < 5) {
			errorList.put("userName", "Le nom d'utilisateur doit contenir au moins 5 caractères");
		}

		if (password == null || password.trim().isEmpty()) {
			errorList.put("password", "Le mot de passe ne doit pas être vide");
		} else if (password.length() < 4) {
			errorList.put("password", "Le mot de passe doit avoir au moins 4 caractères");
		}

		// Retourne true si il n'y a pas d'erreurs, false sinon
		return errorList.isEmpty();
	}

	//Méthode privée pour affichage des erreurs pour les champs nom et prénom
	private void validateName(String fieldName, String value) {
		if (value == null || value.trim().isEmpty()) {
			errorList.put(fieldName, "Le " + fieldName + " ne doit pas être vide");
		} else if (value.length() < 4) {
			errorList.put(fieldName, "Le " + fieldName + " doit avoir au moins 4 caractères");
		} else if (!value.matches("[a-zA-Z\\s]+")) {
			errorList.put(fieldName, "Le " + fieldName + " ne doit pas contenir de chiffres");
		}
	}

	// Getters et Setters
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
