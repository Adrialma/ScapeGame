package com.adrialma.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.adrialma.model.User;

/**
 * Fournit les fonctionnalités CRUD pour les objets User dans la base de données.
 * Permet de récupérer, ajouter, et gérer les utilisateurs en utilisant des requêtes SQL.
 */

public class UserDAO implements Crudable<User>  {

	/**
	 * Récupère un utilisateur de la base de données en utilisant son nom d'utilisateur.
	 *
	 * @param userName Le nom d'utilisateur de l'utilisateur à récupérer.
	 * @return Un objet User correspondant au nom d'utilisateur, ou null si aucun utilisateur n'est trouvé.
	 */
	@Override
	public User get(String userName) {

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
		return null;
	}

	/**
	 * Récupère tous les utilisateurs de la base de données.
	 * 
	 * @return Une liste contenant tous les utilisateurs récupérés.
	 */
	@Override
	public ArrayList<User> get() {
		// Définition de la requête SQL pour sélectionner tous les utilisateurs dans la table 'user'.
		String sql =  "SELECT * FROM user" ;
		// Initialisation d'une liste pour stocker les objets User récupérés.
		ArrayList<User> list = new ArrayList<User>();
		try {
			// Création d'un objet Statement pour exécuter la requête.
			Statement smt = DaoBd.getCn().createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY) ;
			// Exécution de la requête et stockage du résultat dans un ResultSet.
			ResultSet rs = smt.executeQuery(sql) ;
			// Parcours du ResultSet pour créer et ajouter des objets User à la liste.
			while (rs.next()) {	
				// Création d'un nouvel objet User pour chaque ligne du résultat.
				User s = new User(rs.getInt("idUser"), rs.getString("firstName"),  
						rs.getString("lastName"), 
						rs.getString("userName"), 
						rs.getString("password"),false); 
				// Ajout de l'objet User à la liste.
				list.add(s);
			}
			// Fermeture du ResultSet et du Statement pour libérer les ressources.
			rs.close();
			smt.close();
		} catch (SQLException e) {
			// Affichage de l'erreur en cas d'exception SQL.
			e.printStackTrace();
		}
		// Retourne la liste des utilisateurs récupérés.
		return list;
	}

	@Override
	public boolean delete(User o) {
		return false;
	}

	/**
	 * Ajoute un nouvel utilisateur dans la base de données.
	 *
	 * @param o L'objet User à ajouter dans la base de données.
	 * @return true si l'ajout est réussi, false dans le cas contraire.
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

		} catch (Exception ex) { 
			ex.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean update(User o) {
		return false;
	}

	@Override
	public ArrayList<User> getArray(int x) {
		return null;
	}

	@Override
	public boolean add(User o, int x1) {
		return false;
	}

	@Override
	public boolean add(User o, int x1, int x2) {
		return false;
	}

	@Override
	public ArrayList<User> getArray(int x, String champ) {
		return null;
	}
}
