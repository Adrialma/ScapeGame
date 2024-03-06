package com.adrialma.dao;

import java.util.ArrayList;

public interface Crudable<T> {

	public T get(int id);
	public T get(String userName); // Methode Ajouté

	public ArrayList<T> get() ;
	public ArrayList<T> getArray(int x) ; //Methode Ajouté

	public boolean delete(T o);
	public boolean add(T o);
	public boolean add(T o, int x1);
	public boolean add(T o, int x1, int x2);
	
	public boolean update(T o);

}