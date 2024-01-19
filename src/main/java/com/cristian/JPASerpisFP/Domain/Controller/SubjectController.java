package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Subject;
import com.cristian.JPASerpisFP.model.dao.IDao;
import com.cristian.JPASerpisFP.model.dao.SubjectDAO;

public class SubjectController implements IController<Subject> {

	
	private IDao<Subject> dao;
	
	public SubjectController() {
		this.dao = new SubjectDAO();
	}
	@Override
	public List<Subject> getAll() {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = dao.findAll();
		return subjects;
		
	}

	@Override
	public OperationResult save(Subject subject) {
		try {
			dao.save(subject);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}

	@Override
	public Subject getById(Object subjectCode) {
		Subject subject = dao.findById(subjectCode);
		return subject;
	}

	@Override
	public OperationResult delete(Object subjectCode) {
		Subject subject = getById(subjectCode);
		if(subject == null) {
			return OperationResult.NOT_EXISTS;
		}
		
		try {
			dao.save(subject);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.COMMON_ERROR;
		}
	}

	@Override
	public Long getTotalCount() {
		return dao.countRegisters();
	}

}
