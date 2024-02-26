package com.adrialma.model;

import java.util.ArrayList;


public interface Crudable<T> {

public T get(int id);
public ArrayList<T> get() ;
public boolean delete(T o);
public boolean add(T o);
public boolean update(T o);

}