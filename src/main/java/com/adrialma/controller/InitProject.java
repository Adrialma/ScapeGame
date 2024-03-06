package com.adrialma.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.adrialma.dao.InitProjectDAO;

/**
 * Ce servlet est responsable de l'initialisation de la base de données pour l'application.
 * Il vérifie si la base de données nécessaire pour l'application existe, et si non,
 * il procède à la création de cette base de données ainsi qu'à la création des tables nécessaires.
 */
@WebServlet("/InitProject")
public class InitProject extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InitProject() {
		super();
	}

	/**
	 * Méthode doGet appelée par le serveur lorsque une requête HTTP GET est reçue pour ce servlet.
	 * @param request L'objet HttpServletRequest contenant la requête du client.
	 * @param response L'objet HttpServletResponse pour envoyer une réponse au client.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Chemin de redirection par défaut après l'initialisation
		String redirect ="/";
		// Nom de la base de données à vérifier ou à créer
		String bdName = "testing";
		// Création d'une instance de InitProjectDAO pour interagir avec la base de données
		InitProjectDAO initProj= new InitProjectDAO();

		// Vérifie si la base de données existe déjà
		if (initProj.checkDBExists(bdName)) {
			System.out.println("la BDD existe, pas de creation, redirection vers l'index");
			// Ferme la connexion à la base de données
			initProj.closeConnection();
		}
		else {
			System.out.println("la bd n'existe pas, creation de la bdd");
			// Crée la base de données et les tables nécessaires
			initProj.createBDD(bdName);
			InitProjectDAO.connect(bdName); //connection a la bdd creé
			initProj.createTableUser();
			initProj.createTablePuzzle();
			initProj.createTableGame();
			initProj.createTableGamePuzzle();
			initProj.initTablePuzzle();
			// Ferme la connexion à la base de données
			initProj.closeConnection();
		}

		// Redirection vers le chemin spécifié après l'initialisation de la base de données
		this.getServletContext().getRequestDispatcher(redirect).forward(request, response);
	}

	/**
	 * Méthode doPost appelée par le serveur lorsqu'une requête HTTP POST est reçue.
	 * Cette méthode redirige simplement vers doGet, permettant le même traitement pour les requêtes POST.
	 * @param request L'objet HttpServletRequest contenant la requête du client.
	 * @param response L'objet HttpServletResponse pour envoyer une réponse au client.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
