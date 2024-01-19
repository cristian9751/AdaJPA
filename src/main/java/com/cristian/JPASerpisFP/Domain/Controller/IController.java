package com.cristian.JPASerpisFP.Domain.Controller;

import com.cristian.JPASerpisFP.model.dao.IDao;

import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;

public interface IController<T> {

	public  List<T> getAll();
	
	public  OperationResult save(T object);
	
	public  T getById(Object object);
	
	public  OperationResult delete(Object object);
	
	public Long getTotalCount();
	
	
}

