package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.FinalProject;
import com.cristian.JPASerpisFP.model.dao.FinalProjectDAO;
import com.cristian.JPASerpisFP.model.dao.IDao;  

public class FinalProjectController implements IController<FinalProject> {

	private IDao<FinalProject> dao;
	
	public FinalProjectController() {
		dao = new FinalProjectDAO();
	}
	@Override
	public List<FinalProject> getAll() {
		List<FinalProject> projects = new ArrayList<FinalProject>();
		projects = dao.findAll();
		return projects;
	}

	@Override
	public OperationResult save(FinalProject project) {
		try {
			dao.save(project);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}

	@Override
	public FinalProject getById(Object projectTitle) {
		FinalProject project = dao.findById(projectTitle);
		return project;
	}

	@Override
	public OperationResult delete(Object projectTitle) {
		FinalProject project = getById(projectTitle);
		if(project == null) {
			return OperationResult.NOT_EXISTS;
		}
		
		try {
			dao.delete(project);
			return OperationResult.OK;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return OperationResult.COMMON_ERROR;
		}
	}

	@Override
	public Long getTotalCount() {
		return dao.countRegisters();
	}

}
