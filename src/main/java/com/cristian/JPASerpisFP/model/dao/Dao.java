package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

public interface Dao<T> {
	public void save(T object) throws Exception;
	public List<T> findAll() throws Exception;
	public void update(T object) throws Exception;
	public T findById(Object object) throws Exception;
	public void delete(T object) throws Exception;
}
