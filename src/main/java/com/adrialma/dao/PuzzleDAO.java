package com.adrialma.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
		
		
		String sql =  "SELECT * FROM puzzle WHERE level = '" + level+"'" ;
		ArrayList<Puzzle> list = new ArrayList<Puzzle>();
		try {
			System.out.println(sql);
			Statement smt = DaoBd.getCn().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY) ;
			ResultSet rs = smt.executeQuery(sql) ;
			while (rs.next()) {									// (La lecture des données retournées se fait toujours ligne par ligne... 
																// ... Ici de la 1ère à la dernière). Ainsi Tant qu'il y a des lignes de résultats retournés...
				Puzzle s = new Puzzle(rs.getInt("idPuzzle"),		// ... création d'un nouvel objet Salarie
						rs.getString("description"),
						rs.getInt("level"),
						rs.getString("answer"));
				list.add(s);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    
		return list;
		

	}

}
