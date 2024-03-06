package com.adrialma.dao;

import java.util.ArrayList;

/**
 * Interface générique définissant les opérations CRUD pour les entités de type T.
 * Permet l'implémentation standardisée des opérations de base de données pour différentes entités.
 *
 * @param <T> Le type d'entité sur lequel l'interface CRUD sera appliquée.
 */
public interface Crudable<T> {

	/**
	 * Récupère une entité par son identifiant unique.
	 *
	 * @param id L'identifiant unique de l'entité à récupérer.
	 * @return L'entité correspondant à l'identifiant, ou null si aucune entité n'est trouvée.
	 */
	public T get(int id);

	/**
	 * Récupère une entité par un identifiant utilisateur ou un nom d'utilisateur.
	 *
	 * @param userName L'identifiant utilisateur ou le nom d'utilisateur de l'entité à récupérer.
	 * @return L'entité correspondant à l'identifiant utilisateur ou au nom d'utilisateur, ou null si aucune entité n'est trouvée.
	 */
	public T get(String userName); 

	/**
	 * Récupère toutes les entités de type T.
	 *
	 * @return Une liste contenant toutes les entités récupérées.
	 */
	public ArrayList<T> get() ;

	/**
	 * Récupère une liste d'entités basée sur un critère spécifique représenté par 'x'.
	 *
	 * @param x Le critère utilisé pour filtrer les entités à récupérer.
	 * @return Une liste d'entités correspondant au critère spécifié.
	 */
	public ArrayList<T> getArray(int x) ; //Methode Ajouté

	/**
	 * Supprime une entité spécifique de la base de données.
	 *
	 * @param o L'entité à supprimer.
	 * @return true si l'entité a été supprimée avec succès, false sinon.
	 */
	public boolean delete(T o);

	/**
	 * Ajoute une nouvelle entité dans la base de données.
	 *
	 * @param o L'entité à ajouter.
	 * @return true si l'entité a été ajoutée avec succès, false sinon.
	 */
	public boolean add(T o);

	/**
	 * Ajoute une nouvelle entité avec un paramètre supplémentaire.
	 *
	 * @param o L'entité à ajouter.
	 * @param x1 Un paramètre supplémentaire requis pour l'ajout.
	 * @return true si l'entité a été ajoutée avec succès, false sinon.
	 */
	public boolean add(T o, int x1);

	/**
	 * Ajoute une nouvelle entité avec deux paramètres supplémentaires.
	 *
	 * @param o L'entité à ajouter.
	 * @param x1 Le premier paramètre supplémentaire requis pour l'ajout.
	 * @param x2 Le deuxième paramètre supplémentaire requis pour l'ajout.
	 * @return true si l'entité a été ajoutée avec succès, false sinon.
	 */
	public boolean add(T o, int x1, int x2);

	/**
	 * Met à jour une entité existante dans la base de données.
	 *
	 * @param o L'entité à mettre à jour.
	 * @return true si l'entité a été mise à jour avec succès, false sinon.
	 */
	public boolean update(T o);

}