package com.adrialma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.adrialma.model.Puzzle;

public class PuzzleDAO implements Crudable<Puzzle> {

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
	public boolean delete(Puzzle o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Puzzle o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Puzzle o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Puzzle> getArray(int x) {
		// TODO Auto-generated method stub
		int level = x;

		// Construction de la requête SQL pour récupérer les puzzles d'un certain niveau
		String sql =  "SELECT * FROM puzzle WHERE level = '" + level+"'" ;

		// Création d'une liste pour stocker les puzzles récupérés
		ArrayList<Puzzle> list = new ArrayList<Puzzle>();

		try {
			// Exécution de la requête SQL
			System.out.println(sql);
			Statement smt = DaoBd.getCn().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY) ;
			ResultSet rs = smt.executeQuery(sql) ;

			// Parcours des résultats de la requête et création d'objets Puzzle correspondants
			while (rs.next()) {									 			
				Puzzle s = new Puzzle(rs.getInt("idPuzzle"),		
						rs.getString("description"),
						rs.getInt("level"),
						rs.getString("answer"));
				list.add(s); // Ajout du puzzle à la liste
			}

			// Fermeture du ResultSet et du Statement
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list; // Retourne la liste des puzzles récupérés
	}

	@Override
	public boolean add(Puzzle o, int x1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Puzzle o, int x1, int x2) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
	
	
}
