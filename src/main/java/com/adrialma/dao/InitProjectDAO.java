package com.adrialma.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe d'initialisation et de configuration de la base de données pour le projet.
 * Offre des fonctionnalités pour établir des connexions à la base de données, vérifier son existence,
 * créer des bases de données et des tables nécessaires, et peupler les tables avec des données initiales.
 */
public class InitProjectDAO {

	private static Connection conn; // Connexion partagée à la base de données

	/**
	 * Établit une connexion globale à la base de données MySQL sans spécifier une base de données particulière.
	 * Utilise des identifiants de connexion prédéfinis.
	 */
	public static void connect() {
		try {
			//étape 1: charger la classe driver
			//  Class.forName("com.mysql.jdbc.Driver");
			if (conn == null || conn.isClosed()) { 
				//étape 2: créer l'objet de connexion
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306?useSSL=false", "adrian", ""); // Open a connection
				System.out.println("Conected...");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Établit une connexion à une base de données MySQL spécifique par son nom.
	 * @param bdName Le nom de la base de données à laquelle se connecter.
	 */
	public static void connect(String bdName) {
		try {
			//étape 1: charger la classe driver
			//  Class.forName("com.mysql.jdbc.Driver");
			if (conn == null || conn.isClosed()) { 
				//étape 2: créer l'objet de connexion
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost:3306/" + bdName + "?useSSL=false", "adrian", ""); // Open a connection
				System.out.println("Conected...");	
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Vérifie si une base de données spécifique existe.
	 * @param dbName Le nom de la base de données à vérifier.
	 * @return true si la base de données existe, false sinon.
	 */
	public boolean checkDBExists(String dbName) {
		try {
			connect();
			ResultSet resultSet = conn.getMetaData().getCatalogs();
			while (resultSet.next()) {
				String databaseName = resultSet.getString(1);
				if(databaseName.equals(dbName)) {
					return true;
				}
			}
			resultSet.close();   
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * Crée une nouvelle base de données si elle n'existe pas déjà.
	 * @param dbName Le nom de la base de données à créer.
	 * @return Vrai si la création est réussie, faux en cas d'échec.
	 */
	public boolean createBDD(String dbName) {
		try
		{
			//étape 3: créer l'objet statement 
			Statement stmt = conn.createStatement();
			//étape 4: exécuter la requéte
			System.out.println("Création de base de données...");
			stmt.executeUpdate("CREATE DATABASE " + dbName);
			System.out.println("Base de données crée avec succés...");
			closeConnection();
			return true;
		}
		catch(Exception e){ 
			System.out.println(e);
		}
		return false;
	}

	public boolean executeStatement(String sqlString) {
		try
		{
			//étape 3: créer l'objet statement 
			Statement stmt = conn.createStatement();
			//étape 4: exécuter la requéte
			stmt.executeUpdate(sqlString); 
			return true;
		}
		catch(Exception e){ 
			System.out.println(e);
		}
		return false;
	}

	// Méthodes similaires pour créer les tables spécifiques (`createTableUser`, `createTablePuzzle`, `createTableGame`, `createTableGamePuzzle`) et initialiser la table `Puzzle`.
	public boolean createTableUser() {
		String sqlString = "CREATE TABLE `user` (\n"
				+ "  `idUser` int NOT NULL AUTO_INCREMENT,\n"
				+ "  `firstName` varchar(45) COLLATE utf8mb3_bin NOT NULL,\n"
				+ "  `lastName` varchar(45) COLLATE utf8mb3_bin NOT NULL,\n"
				+ "  `userName` varchar(45) COLLATE utf8mb3_bin NOT NULL,\n"
				+ "  `password` varchar(256) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,\n"
				+ "  PRIMARY KEY (`idUser`),\n"
				+ "  UNIQUE KEY `userID_UNIQUE` (`idUser`),\n"
				+ "  UNIQUE KEY `username_UNIQUE` (`userName`)\n"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin";
		if(executeStatement(sqlString)) {
			System.out.println("Table User created"); 
			return true;	
		}
		else 
			System.out.println("Erreur dans la creation de table User");
		return false;
	}

	public boolean createTablePuzzle() {
		String sqlString = "CREATE TABLE `puzzle` (\n"
				+ "  `idPuzzle` int NOT NULL AUTO_INCREMENT,\n"
				+ "  `description` varchar(45) NOT NULL,\n"
				+ "  `level` int NOT NULL,\n"
				+ "  `answer` varchar(45) NOT NULL,\n"
				+ "  PRIMARY KEY (`idPuzzle`),\n"
				+ "  UNIQUE KEY `idPuzzle_UNIQUE` (`idPuzzle`)\n"
				+ ") ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci";
		if(executeStatement(sqlString)) {
			System.out.println("Table Puzzle created"); 
			return true;	
		}
		else 
			System.out.println("Erreur dans la creation de table Puzzle");
		return false;
	}

	public boolean createTableGame() {
		String sqlString = "CREATE TABLE `game` (\n"
				+ "  `idGame` int NOT NULL AUTO_INCREMENT,\n"
				+ "  `date` date NOT NULL,\n"
				+ "  `start` time NOT NULL,\n"
				+ "  `fin` time NOT NULL,\n"
				+ "  `score` int NOT NULL,\n"
				+ "  `levelPlayed` int NOT NULL,\n"
				+ "  PRIMARY KEY (`idGame`),\n"
				+ "  UNIQUE KEY `idGame_UNIQUE` (`idGame`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin";
		if(executeStatement(sqlString)) {
			System.out.println("Table Game created"); 
			return true;	
		}
		else 
			System.out.println("Erreur dans la creation de table Game");
		return false;
	}

	public boolean createTableGamePuzzle() {
		String sqlString = "CREATE TABLE `game_puzzle` (\n"
				+ "  `idGame` int NOT NULL,\n"
				+ "  `idPuzzle` int NOT NULL,\n"
				+ "  `time` int NOT NULL,\n"
				+ "  `scorePuzzle` int NOT NULL,\n"
				+ "  PRIMARY KEY (`idGame`,`idPuzzle`),\n"
				+ "  KEY `idPuzzle_idx` (`idPuzzle`),\n"
				+ "  CONSTRAINT `idGame` FOREIGN KEY (`idGame`) REFERENCES `game` (`idGame`),\n"
				+ "  CONSTRAINT `idPuzzle` FOREIGN KEY (`idPuzzle`) REFERENCES `puzzle` (`idPuzzle`)\n"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8mb3_bin COMMENT='table dynamique contient les puzzles jouées à chaque partie'";
		if(executeStatement(sqlString)) {
			System.out.println("Table Game Puzzle created"); 
			return true;
		}
		else 
			System.out.println("Erreur dans la creation de table Game Puzzle");
		return false;
	}

	public boolean initTablePuzzle() {
		String sqlString1 ="insert into puzzle values (1,'Chiffre Manquant',3,21)";
		String sqlString2 ="insert into puzzle values (2,'Fleurs',3,5)";
		String sqlString3 ="insert into puzzle values (3,'cartes Fibonacci',3,77)";
		if(executeStatement(sqlString1) && executeStatement(sqlString2) && executeStatement(sqlString3)) {
			System.out.println("Table Puzzle actualisée"); 
			return true;
		}
		else 
			System.out.println("Erreur dans l'insertion des donnes dans table puzzle");
		return false;
	}

	/**
	 * Ferme la connexion à la base de données.
	 */
	public void closeConnection() {
		try {
			//étape 5: fermez l'objet de connexion
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// Getters et setters pour la connexion à la base de données.
	public static Connection getConn() {
		return conn;
	}

	public static void setConn(Connection conn) {
		InitProjectDAO.conn = conn;
	}
}