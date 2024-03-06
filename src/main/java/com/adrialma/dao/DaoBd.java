package com.adrialma.dao;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/**
 * Gestionnaire de la connexion à la base de données.
 * Fournit des méthodes statiques pour établir, récupérer et fermer la connexion avec la base de données.
 */
public class DaoBd {
	// Variable de connexion statique et volatile pour garantir une unique instance visible dans tous les threads.
	private static volatile Connection cn;

	/**
	 * Établit une connexion avec la base de données en utilisant JNDI pour rechercher la DataSource.
	 * La méthode est thread-safe pour éviter la création de multiples connexions dans un contexte multithread.
	 */
	public static void conecter() {
		try {
			// Synchronisation pour éviter plusieurs initialisations en environnement multithread.
			synchronized (DaoBd.class) {
				if (cn == null || cn.isClosed()) { // Vérifie également si la connexion est fermée.
					Context ctx = new InitialContext();

					// Recherche de la DataSource via JNDI
					DataSource dataSource = (DataSource) ctx.lookup("java:/comp/env/jdbc/dbMysql");

					cn = dataSource.getConnection();

					System.out.println("Connect BD ok...");
				}
			}
		} catch (Exception e) {
			System.out.println("Connect BD nok..." + e.getMessage());
		}
	}

	/**
	 * Retourne l'objet Connection actuel.
	 * 
	 * @return L'objet Connection actuel. Peut être null si aucune connexion n'a été établie ou si elle a été fermée.
	 */
	public static Connection getCn() {
		return cn;
	}

	/**
	 * Ferme la connexion à la base de données si elle est ouverte.
	 */
	public static void closeConnection() {
		if (cn != null) {
			try {
				cn.close();
				cn = null; // réinitialiser cn à null après la fermeture.
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
