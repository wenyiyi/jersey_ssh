package com.wyd.dao;

import java.util.List;

interface GenericDao<T> {
	
    T load(int id);
	
	T get(int id);
	
	List<T> findAll();
	
	void persist(T entity);
	
	String save(T entity);
	
	void saveOrUpdate(T entity);
	
	void delete(int id);
	
	void flush();
}
