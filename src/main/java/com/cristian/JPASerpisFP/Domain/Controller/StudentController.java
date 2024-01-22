package com.cristian.JPASerpisFP.Domain.Controller;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;


import com.cristian.JPASerpisFP.Domain.Controller.Utils.Enums.OperationResult;
import com.cristian.JPASerpisFP.Domain.Entity.Subject;
import com.cristian.JPASerpisFP.Domain.Entity.Group;
import com.cristian.JPASerpisFP.Domain.Entity.Student;
import com.cristian.JPASerpisFP.model.dao.StudentDAO;

public class StudentController  {

	private static StudentDAO dao;
	
	public StudentController() {
		dao = new StudentDAO();
	}
	

	public List<Student> getAll() {
		List<Student> students = new ArrayList<Student>();
		students = dao.findAll();
		return students;
	}

	public OperationResult save(Student student) {
		try {
			dao.save(student);
			return OperationResult.OK;
		} catch(Exception e) {
			return OperationResult.ALREADY_EXISTS;
		}
	}


	public Student getById(Object NIA) {
		Student student = dao.findById(NIA);
		return student;
	}

	public OperationResult delete(Object  NIA, boolean isDeleteConfirmed) {
		Student student = getById(NIA);
		if(student == null) {
			return OperationResult.NOT_EXISTS;
		}
		
		/**
		 * Condicion que se realiza para saber cuando un estudiante puede ser eliminado
		 * No puede ser eliminada de la base de datos la entidad de estudiante si tiene un projecto
		 * si tiene mas de un modulo o si tiene un modulo pero no es el de FCT
		 */
		if((student.getProject() != null 
				|| student.getSubjects().size() > 1 
				|| !checkFCT(student.getSubjects()))
				&& !isDeleteConfirmed) {
			return OperationResult.NOT_DELETE;
			
		}
		
		try {
			dao.delete(student);
			return OperationResult.OK;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return OperationResult.COMMON_ERROR;
		}
	}


	public Long getTotalCount() {
		return dao.countRegisters();
	}
	
	
	public List<Student> getByGroup(Group group) {
		 List<Student> students = new ArrayList<Student>();
		 students = dao.findByGroup(group);
		 return students;
		 
	}
	
	public List<Student> getByName(String name) {
		List<Student> students = new ArrayList<Student>();
		students = dao.findByName(name);
		return students;
		
	}
	
	public List<Student> getBySurname(String surname) {
		List<Student> students = new ArrayList<Student>();
		students = dao.findBySurname(surname);
		return students;
	}
	
	public  OperationResult enrollStudent(String NIA, Subject subject) {
		Student student = getById(NIA);
		if(student == null) {
			return OperationResult.NOT_EXISTS;
		} 
		
		for(Subject s : student.getSubjects()) {
			if(s.getSubjectCode() == subject.getSubjectCode()) {
				return OperationResult.ALREADY_EXISTS;
				
			}
			
		}
		
		try  {
			student.setSubject(subject);
			dao.update(student);
			return OperationResult.OK;
		} catch(Exception e) {
			System.out.println(e.getMessage());
			return OperationResult.COMMON_ERROR;
		}
		
		
	}
	
	public OperationResult unEnrollStudent(String NIA, Subject subject) {
		Student student = getById(NIA);
		if(student == null) {
			return OperationResult.NOT_EXISTS;
		} 
		
		for(Subject s : student.getSubjects()) {
			if(s.getSubjectCode() == subject.getSubjectCode()) {
				student.removeSubject(subject);
				try {
					dao.update(student);
					return OperationResult.OK;
				} catch(Exception e) {
					System.out.println("Ha  ocurrido un error");
					return OperationResult.COMMON_ERROR;
				}
			}
			
		}
		
		return OperationResult.ALREADY_EXISTS;
	}
	
	public int deleteAll() {
		int result = 0;
		try {
			result = dao.deleteAll();
		} catch(Exception e) {
			System.out.println("No se han podido eliminar los alumnos porque hay alumnos matriculados en presenciales o con tfg");
		}
		return result;
	}
	
	private static boolean checkFCT(Set<Subject> subjects) {
		for(Subject subject : subjects) {
			if(subject.getDescription().contains("FCT")) {
				return true;
			}
		}
		return false;
	}
	
	
	
	
	
	

}
