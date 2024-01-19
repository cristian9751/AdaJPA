package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.Domain.Entity.Student;
import com.cristian.JPASerpisFP.model.dao.GroupDAO;
import com.cristian.JPASerpisFP.model.dao.IDao;
import com.cristian.JPASerpisFP.model.dao.StudentDAO;

public class StudentController implements IController<Student> {

	private static IDao<Student> dao;
	
	public StudentController() {
		dao = new StudentDAO();
	}
	
	
	@Override
	public List<Student> getAll() {
		List<Student> students = new ArrayList<Student>();
		students = dao.findAll();
		return students;
	}

	@Override
	public OperationResult save(Student student) {
		try {
			dao.save(student);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}

	@Override
	public Student getById(Object NIA) {
		Student student = dao.findById(NIA);
		return student;
	}

	@Override
	public OperationResult delete(Object  NIA) {
		Student student = getById(NIA);
		if(student == null) {
			return OperationResult.NOT_EXISTS;
		}
		
		try {
			dao.delete(student);
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
