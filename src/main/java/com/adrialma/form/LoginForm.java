package com.adrialma.form;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adrialma.dao.UserDAO;
import com.adrialma.model.User;

public class LoginForm {
	private User user;
	private HashMap<String, String> errorList;
	private static final Logger LOGGER = Logger.getLogger(LoginForm.class.getName());// rajout d'attribut

	// Constructeur de la classe
	public LoginForm(HttpServletRequest request) {
		errorList = new HashMap<>();

		// Récupération des paramètres de la requête
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		// Validation du nom d'utilisateur et du mot de passe
		validateUserName(userName);
		validatePassword(password);

		// Si aucune erreur de validation n'est détectée
		if (errorList.isEmpty()) {
			UserDAO userDAO = new UserDAO();
			User user = userDAO.get(userName);

			// Si l'utilisateur existe dans la base de données
			if (user == null) {
				// Si le mot de passe ne correspond pas à celui enregistré pour cet utilisateur
				errorList.put("userNameError", "Le nom d'utilisateur n'est pas correct.");
			} else if (!user.checkPassword(password)) {
				errorList.put("passwordError", "Le mot de passe n'est pas correct.");
			} else {
				// Connexion réussie
				this.user = user;
				user.connect(); // Mise à jour de l'état de connexion de l'utilisateur
			}
		}

		// Si des erreurs sont détectées, les enregistrer et les ajouter à la requête
		if (!errorList.isEmpty()) {
			logErrors(errorList);
			request.setAttribute("errors", errorList);
		}
	}

	// Méthode pour valider le nom d'utilisateur
	private void validateUserName(String userName) {
		if (userName == null || userName.trim().isEmpty()) {
			errorList.put("userName", "Le nom d'utilisateur ne doit pas être vide.");
		}
	}

	// Méthode pour valider le mot de passe
	private void validatePassword(String password) {
		if (password == null || password.trim().isEmpty()) {
			errorList.put("password", "Le mot de passe ne doit pas être vide.");
		}
	}

	// Méthode pour enregistrer les erreurs dans la console ou un fichier journal
	private void logErrors(HashMap<String, String> errors) {
		StringBuilder errorMsg = new StringBuilder("Erreurs lors de la tentative de connexion :\n");
		for (HashMap.Entry<String, String> error : errors.entrySet()) {
			errorMsg.append(error.getKey()).append(": ").append(error.getValue()).append("\n");
		}

		LOGGER.log(Level.WARNING, errorMsg.toString());
	}

	// Getters et setters pour les attributs de la classe
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
