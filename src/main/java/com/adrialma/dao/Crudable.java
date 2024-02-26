package com.adrialma.dao;

import java.util.ArrayList;


public interface Crudable<T> {

public T get(int id);
public T get(String userName);
public ArrayList<T> get() ;
public boolean delete(T o);
public boolean add(T o);
public boolean update(T o);

}