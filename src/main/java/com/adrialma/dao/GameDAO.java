package com.adrialma.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;



import com.adrialma.model.Game;
import com.adrialma.model.Puzzle;

public class GameDAO implements Crudable<Game>{

	@Override
	public Game get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Game get(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Game> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(Game o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Game o) {
		return false;
		
	}
	

	
	

	@Override
	public boolean update(Game o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Game> getArray(int x) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Game o, int x1) {
		String sql = "INSERT INTO game (date, start, fin, score, levelPlayed, idUser) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DaoBd.getCn();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setDate(1, o.getDate()) ;
            pstmt.setTime(2,o.getStart()); 
            pstmt.setTime(3, o.getFin());
            pstmt.setInt(4, o.getScore());
            pstmt.setInt(5, o.getLevelPlayed());
            pstmt.setInt(6, x1);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        o.setIdGame(generatedKeys.getInt(1)); // Définir l'ID généré sur l'objet
                        generatedKeys.close();
                        
                    }
                }
            }
            pstmt.close(); 
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
	}

	
	
	@Override
	public boolean add(Game o, int x1, int x2) {
		// TODO Auto-generated method stub
		return false;
	}

}
