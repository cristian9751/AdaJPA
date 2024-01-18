package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.model.dao.IDao;
import com.cristian.JPASerpisFP.model.dao.GroupDAO;

public class GroupController implements IController<Group> {
	
	private static IDao<Group> dao;
	
	public GroupController() {
		dao = new GroupDAO();
	}
	@Override
	public List<Group> getAll() {
		List<Group> groups = new ArrayList<>();
		groups = dao.findAll();
		return groups;
	}

	@Override
	public OperationResult save(Group group) {
		try {
			dao.save(group);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}

	@Override
	public Group getById(Object groupCode) {
		Group group = dao.findById(groupCode);
		return group;
	}
	@Override
	public OperationResult delete(Object groupCode) {
		Group group = getById(groupCode);
		if(group == null) {
			return OperationResult.NOT_EXISTS;
		}
		
		try {
			dao.delete(group);
			return OperationResult.OK;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return OperationResult.COMMON_ERROR;
		}
		
		
	}
	
	
	public Long getTotal() {
		return dao.countRegisters();
	}
	

}
