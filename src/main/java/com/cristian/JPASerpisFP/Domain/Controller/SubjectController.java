package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Student;
import com.cristian.JPASerpisFP.Domain.Entity.Subject;
import com.cristian.JPASerpisFP.model.dao.IDao;
import com.cristian.JPASerpisFP.model.dao.SubjectDAO;

public class SubjectController  {

	
	private SubjectDAO dao;
	
	public SubjectController() {
		this.dao = new SubjectDAO();
	}
	
	public List<Subject> getAll() {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = dao.findAll();
		return subjects;
		
	}

	public OperationResult save(Subject subject) {
		try {
			dao.save(subject);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}

	public Subject getById(Object subjectCode) {
		Subject subject = dao.findById(subjectCode);
		return subject;
	}

	public OperationResult delete(Object subjectCode, boolean isDeleteConfirmed) {
		Subject subject = getById(subjectCode);
		if(subject == null) {
			return OperationResult.NOT_EXISTS;
		}
		
		StudentController studentController = new StudentController();
		
		if(!subject.getStudents().isEmpty() && isDeleteConfirmed) {
			for(Student student : subject.getStudents()) {
				studentController.unEnrollStudent(student.getNIA(), subject);
			}
		} else if(!subject.getStudents().isEmpty() && !isDeleteConfirmed) {
			return OperationResult.NOT_DELETE;
		}
		
		try {
			dao.save(subject);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.COMMON_ERROR;
		}
	}


	public Long getTotalCount() {
		return dao.countRegisters();
	}
	
	public List<Subject> getByHours(int hours) {
		List<Subject> subjects = new ArrayList<Subject>();
		subjects = dao.findByHours(hours);
		return subjects;
	}

	public int deleteAll() {
		try {
			return dao.deleteAll();
		} catch (Exception e) {
			return 0;
		}
	}

}
