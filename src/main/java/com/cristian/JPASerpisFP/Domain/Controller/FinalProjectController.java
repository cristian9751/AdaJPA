package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.FinalProject;
import com.cristian.JPASerpisFP.model.dao.FinalProjectDAO;
import com.cristian.JPASerpisFP.model.dao.IDao;  

public class FinalProjectController  {

	private FinalProjectDAO dao =  new FinalProjectDAO();
	
	public FinalProjectController() {
		dao = new FinalProjectDAO();
	}
	public List<FinalProject> getAll() {
		List<FinalProject> projects = new ArrayList<FinalProject>();
		projects = dao.findAll();
		return projects;
	}

	public OperationResult save(FinalProject project) {
		try {
			dao.save(project);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}

	public FinalProject getById(Object projectTitle) {
		FinalProject project = dao.findById(projectTitle);
		return project;
	}


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
	
	public Long getTotalCount() {
		return dao.countRegisters();
	}

	
	public int deleteAll() {
		try {
			return dao.deleteAll();
		} catch(Exception e) {
			System.out.println("Se ha producido un error");
		}
		
		return 0;
	}
}
