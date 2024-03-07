package com.adrialma.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.adrialma.model.Game;

public class GameDAO implements Crudable<Game>{

	@Override
	public Game get(int id) {
		return null;
	}

	@Override
	public Game get(String userName) {
		return null;
	}

	@Override
	public ArrayList<Game> get() {
		return null;
	}

	@Override
	public boolean delete(Game o) {
		return false;
	}

	@Override
	public boolean add(Game o) {
		return false;
	}

	@Override
	public boolean update(Game o) {
		return false;
	}

	/**
	 * Récupère tous les jeux associés à un utilisateur spécifique de la base de données.
	 * 
	 * @param x L'identifiant de l'utilisateur pour lequel les jeux sont récupérés.
	 * @return Une liste des jeux associés à cet utilisateur.
	 */
	@Override
	public ArrayList<Game> getArray(int x) {
		// Requête SQL pour récupérer les jeux d'un utilisateur.
		String sql =  "SELECT * FROM game WHERE idUser = " + x ; 
		// Initialisation d'une liste vide pour stocker les objets Game récupérés.
		ArrayList<Game> list = new ArrayList<Game>();
		try {
			// Création d'un objet Statement pour exécuter la requête SQL.
			Statement smt = DaoBd.getCn().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY) ;
			// Exécution de la requête et stockage du résultat dans un ResultSet.
			ResultSet rs = smt.executeQuery(sql) ;
			// Itération sur chaque ligne du résultat pour créer des objets Game et les ajouter à la liste.
			while (rs.next()) {									
				Game s = new Game(rs.getInt("idGame"), rs.getDate("date"),  
						rs.getTime("start"), 
						rs.getTime("fin"), 
						rs.getInt("score"),rs.getInt("levelPlayed")); 
				list.add(s);
			}
			// Fermeture du ResultSet et du Statement pour libérer les ressources.
			rs.close();
			smt.close();
		} catch (SQLException e) {
			// Gestion des exceptions SQL et affichage de l'erreur dans la console.
			e.printStackTrace();
		}
		return list; // Retourne la liste des jeux récupérés.
	}

	/**
	 * Ajoute un jeu à la base de données avec une association à un utilisateur spécifique.
	 *
	 * @param o L'objet Game contenant les détails du jeu à ajouter.
	 * @param x1 L'identifiant de l'utilisateur associé au jeu.
	 * @return true si l'ajout est réussi, sinon false.
	 */
	@Override
	public boolean add(Game o, int x1) {
		// Préparation de la requête SQL pour insérer un nouveau jeu avec les détails fournis.
		String sql = "INSERT INTO game (date, start, fin, score, levelPlayed, idUser) VALUES (?, ?, ?, ?, ?, ?)";
		try (Connection conn = DaoBd.getCn();
				PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
			// Paramètres de la requête préparée.
			pstmt.setDate(1, o.getDate()) ;
			pstmt.setTime(2,o.getStart()); 
			pstmt.setTime(3, o.getFin());
			pstmt.setInt(4, o.getScore());
			pstmt.setInt(5, o.getLevelPlayed());
			pstmt.setInt(6, x1); // ID de l'utilisateur associé au jeu.
			int rowsAffected = pstmt.executeUpdate();

			if (rowsAffected > 0) {
				// Récupération de l'ID généré pour le jeu et définition sur l'objet Game.
				try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						o.setIdGame(generatedKeys.getInt(1)); // Définir l'ID généré sur l'objet
						generatedKeys.close();  
					}
				}
			}
			pstmt.close(); 
			return rowsAffected > 0; // Retourne vrai si l'insertion a été un succès
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false; // Retourne faux si une erreur SQL s'est produite
		}
	}

	@Override
	public boolean add(Game o, int x1, int x2) {
		return false;
	}

	@Override
	public ArrayList<Game> getArray(int x, String champ) {
		return null;
	}

}
