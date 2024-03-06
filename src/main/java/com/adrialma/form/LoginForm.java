package com.adrialma.form;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.adrialma.dao.UserDAO;
import com.adrialma.model.User;

/**
 * La classe LoginForm gère la validation des données de formulaire de connexion.
 * Elle valide les entrées de l'utilisateur et vérifie ces informations contre la base de données.
 * Si les validations échouent ou si l'utilisateur n'est pas trouvé, elle enregistre les erreurs correspondantes.
 */
public class LoginForm {
	private User user; // L'utilisateur connecté, null si la connexion échoue
	private HashMap<String, String> errorList; // Liste des messages d'erreur de validation
	private static final Logger LOGGER = Logger.getLogger(LoginForm.class.getName()); // Logger pour enregistrer les erreurs

	/**
	 * Constructeur qui traite la requête HTTP pour la connexion d'un utilisateur.
	 * Il extrait les paramètres de la requête, les valide, et tente de connecter l'utilisateur.
	 * Tente de connecter l'utilisateur avec les identifiants fournis. 
	 * Enregistre les erreurs de connexion si nécessaire.
	 * 
	 * @param userName Le nom d'utilisateur saisi.
	 * @param password Le mot de passe saisi.
	 * @param request La requête HTTP contenant les données de connexion.
	 */
	public LoginForm(HttpServletRequest request) {
		errorList = new HashMap<>();

		// Extraction et validation des paramètres de la requête
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

				this.user = user; // Connexion réussie
				user.connect(); // Mise à jour de l'état de connexion de l'utilisateur
			}
		}

		// Si des erreurs sont détectées, les enregistrer et les ajouter à la requête
		if (!errorList.isEmpty()) {
			logErrors(errorList);
			request.setAttribute("errors", errorList);
		}
	}

	/**
	 * Valide le nom d'utilisateur fourni.
	 * 
	 * @param userName Le nom d'utilisateur à valider.
	 */
	private void validateUserName(String userName) {
		if (userName == null || userName.trim().isEmpty()) {
			errorList.put("userName", "Le nom d'utilisateur ne doit pas être vide.");
		}
	}

	/**
	 * Valide le mot de passe fourni.
	 * 
	 * @param password Le mot de passe à valider.
	 */
	private void validatePassword(String password) {
		if (password == null || password.trim().isEmpty()) {
			errorList.put("password", "Le mot de passe ne doit pas être vide.");
		}
	}

	/**
	 * Enregistre les erreurs de connexion dans le journal d'application.
	 * 
	 * @param errors La liste des erreurs à enregistrer.
	 */
	private void logErrors(HashMap<String, String> errors) {
		StringBuilder errorMsg = new StringBuilder("Erreurs lors de la tentative de connexion :\n");
		for (HashMap.Entry<String, String> error : errors.entrySet()) {
			errorMsg.append(error.getKey()).append(": ").append(error.getValue()).append("\n");
		}
		LOGGER.log(Level.WARNING, errorMsg.toString());
	}

	// Getters et setters
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
