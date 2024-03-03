package com.adrialma.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.adrialma.model.User;

public class UserDAO implements Crudable<User>  {

	@Override
	public User get(String userName) {
		// TODO Auto-generated method stub

		// Connexion à la base de données
		DaoBd.conecter();

		// Initialisation de l'utilisateur à null
		User user = null;

		// Requête SQL pour récupérer l'utilisateur par son nom d'utilisateur
		String sql = "SELECT * FROM user WHERE userName = '" + userName + "'";
		System.out.println(sql);
		try {   
			// Création d'un objet Statement pour exécuter la requête
			Statement smt = DaoBd.getCn().createStatement(  		
					ResultSet.TYPE_SCROLL_INSENSITIVE,      	
					ResultSet.CONCUR_READ_ONLY) ; 				
			ResultSet rs = smt.executeQuery(sql) ;	

			// Si un résultat est trouvé, créer un objet User à partir des données de la base de données
			if(rs.first())										
				user = new User(rs.getInt("idUser"), rs.getString("firstName"),  
						rs.getString("lastName"), 
						userName, 
						rs.getString("password"),false);
			else
				System.out.println("Utilisateur:" + userName + " Introuvable");

			// Fermeture des ressources
			rs.close();											
			smt.close();
			return user;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public User get(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<User> get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(User o) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @param Object type User
	 * @return true si enregistrement effectué, false si enregistrement non effectué
	 *
	 */
	@Override
	public boolean add(User o) {
		// Requête SQL pour insérer un nouvel utilisateur dans la base de données
		String sql =  
				"INSERT INTO user (firstName,lastName,userName,password) VALUES (?,?,?,?);" ;
		try {   
			// Préparation de la requête SQL
			PreparedStatement pSmt = DaoBd.getCn().prepareStatement(sql);  
			pSmt.setString(1, o.getFirstName());
			pSmt.setString(2, o.getLastName());
			pSmt.setString(3, o.getUserName());
			pSmt.setString(4,  o.getPassword());

			// Exécution de la requête d'insertion
			int res = pSmt.executeUpdate() ;  

			// Affichage d'un message selon le résultat de la requête
			String mess= "L'insertion de l'user: " + o.getUserName();
			System.out.println(res== 1 ? mess + " s'est effectué correctement":				
					" ne s'est pas effectué") ;

			// Fermeture de l'objet PreparedStatement
			pSmt.close(); 

			// Si l'insertion est effectuée avec succès, retourne true, sinon false
			if (res!=1)
				System.err.println("Erreur dans l'insertion");
			else 
				return true;

		} catch (Exception ex) { //TODO personaliser exception
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(User o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<User> getArray(int x) {
		// TODO Auto-generated method stub
		return null;
	}
}
