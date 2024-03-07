package com.adrialma.service;

import java.io.IOException;
import java.net.Socket;

import com.adrialma.dao.DaoBd;
import com.adrialma.exception.GameException;
import com.adrialma.model.User;

/**
 * La classe VerificationService est utilisée pour effectuer diverses vérifications avant de permettre à un utilisateur
 * d'accéder à certaines parties de l'application. Elle vérifie notamment l'état de la connexion à la base de données
 * et si un utilisateur est connecté.
 */
public class VerificationService {
	private String redirect =""; // Chemin de redirection en cas d'échec d'une vérification
	
	/**
     * Constructeur par défaut.
     */
	public VerificationService() {
		super();
	}

	/**
     * Vérifie l'état de la connexion à la base de données et si l'utilisateur est connecté. Met à jour
     * la variable de redirection en fonction de l'état des vérifications.
     * 
     * @param user L'utilisateur à vérifier.
     * @return true si toutes les vérifications sont passées avec succès, false autrement.
	 * @throws GameException 
     */
	public boolean checkAll(User user) {
		boolean bdOK = true;
		boolean userOK = true;
		
		if (!isConectionBdOk()) {
			redirect ="/OutOfService"; // Redirection en cas d'échec de la connexion à la BD
			bdOK = false;
		}
		
		if (!isUserConected(user)) {
			redirect = "/Login"; // Redirection si l'utilisateur n'est pas connecté
			userOK = false;
		}
		
		
		
		
		try {
			if ( userOK==false )
				 throw new GameException("Le user n'est pas connecté.... Envoyer vers la page de login",3);
		} catch (GameException e) {
			e.printMessage();
		}
	
		
	
		
		return bdOK && userOK;
		
	}
	
	 /**
     * Vérifie si la connexion à la base de données est opérationnelle en tentant de se connecter au serveur MySQL.
     * 
     * @return true si la connexion à la base de données est établie avec succès, false autrement.
	 * @throws GameException 
     */
	public boolean isConectionBdOk() {
		// Verifier que le serveur mysql est started
		 boolean isUp = false;
		    try {
		        Socket socket = new Socket("localhost", 3306); // Tente de se connecter au serveur MySQL
		        socket.close(); // Ferme la socket immédiatement après une connexion réussie
		        
		        DaoBd.conecter(); // Tente de se connecter à la base de données
		        if (DaoBd.getCn()!=null) 
		        	isUp = true; // La connexion est réussie 
		    }
		    catch (IOException e)
		    {
		    	
		        DaoBd.closeConnection(); // Ferme la connexion à la base de données en cas d'échec
		        //throw new GameException("Server is down.... Envoyer vers la page d'erreur",5);
		    }
		    
		
		    
		    try {
				if ( isUp==false )
					 throw new GameException("Server is down.... Envoyer vers la page d'erreur",5);
			} catch (GameException e) {
				 e.printMessage();
			}
		    
		    
		    
		return isUp; 
	}
	
	 /**
     * Vérifie si l'utilisateur est actuellement connecté.
     * 
     * @param user L'utilisateur à vérifier.
     * @return true si l'utilisateur n'est pas null, ce qui indique qu'il est connecté, false autrement.
     */
	public boolean isUserConected(User user) {
		return (user == null) ? false : true;
	}

	// Getters et Setters pour la variable redirect.
	public String getRedirect() {
		return redirect;
	}

	public void setRedirect(String redirect) {
		this.redirect = redirect;
	}
	
	@Override
	public String toString() {
		return "VerificationService [redirect=" + redirect + "]";
	}
}
