package com.adrialma.dao;

import java.util.ArrayList;

import com.adrialma.model.Puzzle;


public interface Crudable<T> {

public T get(int id);
public T get(String userName); // Methode Ajouté

public ArrayList<T> get() ;
public ArrayList<T> getArray(int x) ; //Methode Ajouté

public boolean delete(T o);
public boolean add(T o);
public boolean update(T o);

}