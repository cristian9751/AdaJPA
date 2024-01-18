package com.cristian.JPASerpisFP.Domain.Controller;

import com.cristian.JPASerpisFP.model.dao.IDao;

import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;

public abstract class Controller<T> {
	
	protected IDao<T> dao;
	public Controller(IDao<T> dao) {
		this.dao = dao;
	}
	
	
	public abstract List<T> getAll();
	
	public abstract OperationResult save(T object);
	
	public abstract OperationResult delete(T object);
	
	
}
