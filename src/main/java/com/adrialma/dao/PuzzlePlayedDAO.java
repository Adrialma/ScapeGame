package com.adrialma.dao;


import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adrialma.model.Puzzle;

/**
 * Classe de la couche d'accès aux données pour les puzzles joués.
 * Implémente l'interface Crudable pour fournir des opérations CRUD standard pour les objets Puzzle.
 * Spécifiquement adaptée pour gérer les associations entre puzzles et jeux.
 */
public class PuzzlePlayedDAO implements Crudable<Puzzle> {

	@Override
	public Puzzle get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Puzzle get(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Puzzle> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Puzzle> getArray(int x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Puzzle o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Puzzle o) {
		return false;
	}

	@Override
	public boolean update(Puzzle o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 *  Ajoute une liste de puzzles associés à un identifiant de jeu spécifique dans la base de données.
	 * 
	 * @param puzzles La liste des puzzles à ajouter.
	 * @param idGame L'identifiant du jeu auquel les puzzles sont associés.
	 * @return true si tous les puzzles sont ajoutés avec succès, false sinon.
	 */
	public boolean addArray(List<Puzzle> puzzles, int idGame) {
		boolean insert=true;
		for(Puzzle puzzle:puzzles ) {
			if (!add(puzzle, idGame))
				insert=false;
		}

		return insert;

	}

	/**
	 * Ajoute un puzzle à la base de données avec une association à un jeu spécifique.
	 *
	 * @param o Puzzle à ajouter.
	 * @param x1 Identifiant du jeu auquel le puzzle est associé.
	 * @return true si l'ajout est réussi, sinon false.
	 */
	@Override
	public boolean add(Puzzle o, int x1) {
		// Établissement de la connexion à la base de données
		DaoBd.conecter();

		// Définition de la requête SQL pour insérer un puzzle associé à un jeu spécifique
		String sql = "INSERT INTO game_puzzle (idGame, idPuzzle, time, scorePuzzle) VALUES (?, ?, ?, ?)";

		try (
				// Préparation de la requête SQL pour l'exécution
				PreparedStatement pstmt = DaoBd.getCn().prepareStatement(sql)) {

			// Attribution des valeurs aux paramètres de la requête préparée
			pstmt.setInt(1, x1); // Id du jeu auquel le puzzle est associé
			pstmt.setInt(2, o.getIdPuzzle()); // Id du puzzle
			pstmt.setInt(3, o.getTime()); // Temps passé sur le puzzle
			pstmt.setInt(4, o.getScorePuzzle()); // Score obtenu pour le puzzle

			// Exécution de la requête et récupération du nombre de lignes affectées
			int rowsAffected = pstmt.executeUpdate();

			// Vérification du résultat de l'exécution de la requête
			if (rowsAffected > 0) {
				// Si au moins une ligne est affectée, l'insertion a réussi
				System.out.println("Puzzle enregistré correctement");
			} else {
				// Si aucune ligne n'est affectée, l'insertion a échoué
				System.out.println("Erreur dans l'enregistrement du puzzle");
			}

			// Fermeture de l'objet PreparedStatement
			pstmt.close();

			// Retourne true si l'insertion est réussie, false sinon
			return rowsAffected > 0;
		} catch (SQLException e) {
			// Gestion des exceptions SQL potentielles
			System.out.println(e.getMessage());
			return false;
		}
	}


	@Override
	public boolean add(Puzzle o, int x1, int x2) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public ArrayList<Puzzle> getArray(int x, String champ) {
		// TODO Auto-generated method stub
		return null;
	}

}
