package com.adrialma.dao;


import java.sql.PreparedStatement;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.adrialma.model.Puzzle;

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
	
	public boolean addArray(List<Puzzle> puzzles, int idGame) {
		boolean insert=true;
		for(Puzzle puzzle:puzzles ) {
			if (!add(puzzle, idGame))
				insert=false;
		}
		
		return insert;
			
		}

	@Override
	public boolean add(Puzzle o, int x1) {
		DaoBd.conecter();
		String sql = "INSERT INTO game_puzzle (idGame, idPuzzle, time, scorePuzzle) VALUES (?, ?, ?, ?)";
        try (
            PreparedStatement pstmt = DaoBd.getCn().prepareStatement(sql)) {
            pstmt.setInt(1, x1) ;
            pstmt.setInt(2,o.getIdPuzzle()); 
            pstmt.setInt(3, o.getTime());
            pstmt.setInt(4, o.getScorePuzzle());
            
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) 
            	System.out.println("Puzzle enregistre correctement");
            else 
            	System.out.println("Erreur dans l'enregistrement du puzzle");
			
            
            pstmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
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
