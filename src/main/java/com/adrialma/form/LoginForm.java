package com.adrialma.form;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import com.adrialma.dao.UserDAO;
import com.adrialma.model.User;

public class LoginForm {
    private User user;
    private HashMap<String, String> errorList;

    public LoginForm(HttpServletRequest request) {
        errorList = new HashMap<>();

        // Récupération des paramètres de la requête
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        // Validation du nom d'utilisateur et du mot de passe
        validateUserName(userName);
        validatePassword(password);

        if (errorList.isEmpty()) {
            UserDAO userDAO = new UserDAO();
            User user = userDAO.get(userName);
System.out.println(errorList);
            if (user != null && user.checkPassword(password)) {
            	
                // Connexion réussie
                this.user = user;
                user.connect(); // Mise à jour de l'état de connexion de l'utilisateur
            } else {
                // Échec de la connexion
                errorList.put("loginError", "Nom d'utilisateur ou mot de passe incorrect.");
                
            }
        }
    }

    // Valider le nom d'utilisateur
    private void validateUserName(String userName) {
        if (userName == null || userName.trim().isEmpty()) {
            errorList.put("userName", "Le nom d'utilisateur ne doit pas être vide.");
        }
    }

    // Valide le mot de passe
    private void validatePassword(String password) {
        if (password == null || password.trim().isEmpty()) {
            errorList.put("password", "Le mot de passe ne doit pas être vide.");
        }
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
