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

		DaoBd.conecter();
		User user = null;
				String sql = "SELECT * FROM user WHERE userName = '" + userName + "'";
				System.out.println(sql);
				try {   									       		// A la création de l'objet Statement...
					Statement smt = DaoBd.getCn().createStatement(  		// ... possibilité de spécifier des paramètres pour une lecture particulière des résultats de la requête: 
																		// 1er paramètre: la lecture des résultats peut se faire du début vers la fin des données renvoyées, ou inversement 
																		// contrairement à "TYPE_FORWARD_READ" (par défaut) par lequel la consultation des résultats ne se fait qu'en avançant...
							ResultSet.TYPE_SCROLL_INSENSITIVE,      	//... Cependant si la BDD est modifiée pendant son parcours, ces changements n'apparaisseront pas pendant la lecture en cours (pour celà utiliser "TYPE_SCROLL_SENSITIVE") 
																		// 2nd paramètre: les données peuvent être mises à jour ou non
							ResultSet.CONCUR_READ_ONLY) ; 				// Ici lecture seule (par défaut), utiliser "CONCUR_UPDATABLE" pour modifier les données et màj la bdd.
					ResultSet rs = smt.executeQuery(sql) ;				// A l'exécution de la requête sql, l'objet ResultSet récupère et stocke les données.
					if(rs.first())										// On place le curseur sur la 1ère ligne de résultat, si résultat il y a
						user = new User(rs.getInt("idUser"), rs.getString("firstName"),  // Création d'un objet Salarie à partir du contenu de chaque colonne de la BDD correspondant à la ligne retournée
								rs.getString("lastName"), 
								userName, 
								rs.getString("password"));
					else
						System.out.println("Utilisateur:" + userName + " Introuvable");
					
					

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
		// TODO Auto-generated method stub
		String sql =  
				"INSERT INTO user (firstName,lastName,userName,password) VALUES (?,?,?,?);" ;
		try {         
			PreparedStatement pSmt = DaoBd.getCn().prepareStatement(sql);  
			pSmt.setString(1, o.getFirstName());
			pSmt.setString(2, o.getLastName());
			pSmt.setString(3, o.getUserName());
			pSmt.setString(4,  o.getPassword());
			// ... Cet objet Statement permet d'exécuter des requête sql (ici de création) en interrogeant la BDD
			int res = pSmt.executeUpdate() ;      // La requête retourne le nombre de création correctement exécutées  
			//
			String mess= "L'insertion de l'user: " + o.getUserName();
			System.out.println(res== 1 ? mess + " s'est effectué correctement":			// Ternaire: affichage d'un message de résultat en fonction de la bonne exécution de la requête sql	
				" ne s'est pas effectué") ;
			pSmt.close(); 							//Fermeture de l'objet Statement, non obligatoire mais recommandé
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

	

}