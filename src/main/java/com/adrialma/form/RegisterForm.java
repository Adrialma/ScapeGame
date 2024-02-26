package com.adrialma.form;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;

import com.adrialma.dao.UserDAO; 
import com.adrialma.model.User;

public class RegisterForm {
    private User user; // Instance de User pour stocker les informations de l'utilisateur après validation
    private HashMap<String, String> errorList; // Liste pour stocker les erreurs de validation

    // Constructeur qui prend la requête HTTP comme argument
    public RegisterForm(HttpServletRequest request) {
        errorList = new HashMap<>(); // Initialisation de la liste des erreurs

        // Récupération des paramètres de la requête
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // Appel aux méthodes de validation pour chaque champ
        validateName("firstName", firstName);
        validateName("lastName", lastName);
        validateUserName(userName);
        validatePassword(password);

        // Si aucune erreur n'est détectée, tente de créer l'utilisateur
        if (errorList.isEmpty()) {
            UserDAO userDAO = new UserDAO(); // Création d'une instance de UserDAO pour les opérations de base de données

            // Vérifie si le nom d'utilisateur est disponible
            if (userDAO.get(userName) == null) {
                // Crée l'objet User si le nom d'utilisateur est disponible
                user = new User(0, firstName, lastName, userName, password); 
                
                // Tente d'ajouter l'utilisateur à la base de données
                if (!userDAO.add(user)) {
                    // Si l'ajout échoue, ajoute une erreur à la liste
                    errorList.put("bdError", "Problème lors de l'enregistrement, veuillez réessayer.");
                }
            } else {
                // Si le nom d'utilisateur est déjà pris, ajoute une erreur
                errorList.put("userName", "Nom d'utilisateur déjà utilisé.");
            }
        }
    }

    // Valide les noms et prénoms
    private void validateName(String fieldName, String value) {
        if (value == null || value.trim().isEmpty()) {
            errorList.put(fieldName, "Le " + fieldName + " ne doit pas être vide.");
        } else if (value.length() < 4) {
            errorList.put(fieldName, "Le " + fieldName + " doit avoir au moins 4 caractères.");
        } else if (!value.matches("[a-zA-Z\\s]+")) {
            errorList.put(fieldName, "Le " + fieldName + " ne doit pas contenir de chiffres.");
        }
    }

    // Valide le nom d'utilisateur
    private void validateUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            errorList.put("userName", "Le nom d'utilisateur ne doit pas être vide.");
        } else if (userName.length() < 5) {
            errorList.put("userName", "Le nom d'utilisateur doit contenir au moins 5 caractères.");
        }
    }

    // Valide le mot de passe
    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            errorList.put("password", "Le mot de passe ne doit pas être vide.");
        } else if (password.length() < 4) {
            errorList.put("password", "Le mot de passe doit avoir au moins 4 caractères.");
        }
    }

    // Getters et Setters pour user et errorList
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
