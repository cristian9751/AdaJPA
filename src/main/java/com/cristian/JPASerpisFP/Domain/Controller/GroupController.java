package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.Domain.Entity.Student;
import com.cristian.JPASerpisFP.model.dao.IDao;
import com.cristian.JPASerpisFP.model.dao.GroupDAO;

public class GroupController {
	
	private static GroupDAO dao;
	
	
	public GroupController() {
		dao = new GroupDAO();
	}
	
	

	public List<Group> getAll() {
		List<Group> groups = new ArrayList<>();
		groups = dao.findAll();
		return groups;
	}
	
	private static StudentController studentController = new StudentController();

	
	public OperationResult save(Group group) {
	
		try {
			dao.save(group);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}


	public Group getById(Object groupCode) {
		Group group = dao.findById(groupCode);
		return group;
	}
	
	

	public OperationResult delete(Object groupCode, boolean isDeleteConfirmed) {
		
		Group group = getById(groupCode);
		if(group == null) {
			return OperationResult.NOT_EXISTS;
		}
		
		List<Student> students = group.getStudents();
		if(!students.isEmpty() && isDeleteConfirmed) {
			for(Student student : students) {
				studentController.delete(student.getNIA(), true);
			}
		} else if(!students.isEmpty() && !isDeleteConfirmed) {
			return OperationResult.NOT_DELETE;
		}
		students = null;
		
		try {
			dao.delete(group);
			return OperationResult.OK;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return OperationResult.COMMON_ERROR;
		}
		
		
	}
	
		
		
	
	
	public Long getTotalCount() {
		return dao.countRegisters();
	}
	
	
	
	public List<Group> getByClassroom(String classroom) {
		List<Group> groups = new ArrayList<Group>();
		groups = dao.findByClassroom(classroom);
		return groups;
	}
	
	public List<Group> getByDescription(String description) {
		List<Group> groups = new ArrayList<Group>();
		groups = dao.getByDescription(description);
		return groups;
		}
	
	
	public  int deleteAll() {
		int res = 0;
		try {
			res = dao.deleteAll();
		} catch(Exception e) {
			System.out.println("No se han podido eliminar los grupos porque tienen alumnos asocidos");
		}
		return res;
	}
		
}
	

