package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.model.dao.IDao;

public class GroupController extends Controller<Group> {

	public GroupController(IDao<Group> dao) {
		super(dao);
	}

	@Override
	public List<Group> getAll() {
		List<Group> groups = new ArrayList<Group>();
		try {
			groups = dao.findAll();
		} catch(Exception e) {
			System.out.println("Error: No se ha podido obtener el listado de grupos");
		}
		
		return groups;
	}

	@Override
	public OperationResult save(Group group) {
		try {
			dao.save(group);
			return OperationResult.OK;
		} catch(EntityExistsException e) {
			return OperationResult.ALREADY_EXISTS;
		} catch(Exception e) {
			return OperationResult.COMMON_ERROR;
		}
	}

	@Override
	public OperationResult delete(Group group) {
		try {
			dao.delete(group);
			return OperationResult.OK;
		} catch(EntityNotFoundException e) {
			return OperationResult.NOT_EXISTS;
		} catch(Exception e) {
			return OperationResult.COMMON_ERROR;
		}
	}

}
