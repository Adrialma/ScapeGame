package com.adrialma.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;



import com.adrialma.model.Game;
import com.adrialma.model.Puzzle;
import com.adrialma.model.User;

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
		
		String sql =  "SELECT * FROM game WHERE idUser = " + x ;
		ArrayList<Game> list = new ArrayList<Game>();
		try {
			Statement smt = DaoBd.getCn().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY) ;
			ResultSet rs = smt.executeQuery(sql) ;
			while (rs.next()) {									// (La lecture des données retournées se fait toujours ligne par ligne... 
																// ... Ici de la 1ère à la dernière). Ainsi Tant qu'il y a des lignes de résultats retournés...
				Game s = new Game(rs.getInt("idGame"), rs.getDate("date"),  
						rs.getTime("start"), 
						rs.getTime("fin"), 
						rs.getInt("score"),rs.getInt("levelPlayed")); 
				//public Game(int idGame, Date date, Time start, Time fin, int score,  int levelPlayed)
				list.add(s);
			}
			rs.close();
			smt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    
		return list;
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

	@Override
	public ArrayList<Game> getArray(int x, String champ) {
		// TODO Auto-generated method stub
		return null;
	}

}
