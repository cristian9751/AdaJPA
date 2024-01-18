package com.cristian.JPASerpisFP.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cristian.JPASerpisFP.Domain.Entity.Student;

import static com.cristian.JPASerpisFP.model.PersistenceManager.*;

public class StudentDAO implements IDao<Student> {

	@Override
	public void save(Student student) throws Exception {
		performTransaction(entityManager -> {
			entityManager.persist(student);
		});
		
	}

	@Override
	public List<Student> findAll() throws Exception {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT student FROM Student student");
		return query.getResultList();
	}

	

	@Override
	public Student findById(Object studentId) throws Exception {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Student.class, (String) studentId);
	}

	@Override
	public void delete(Student student) throws Exception {
		performTransaction(entityManager -> {
			entityManager.remove(student);
		});
		
	}
	
	 
	public List<Student> findByName(String name) {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT s FROM Students s WHERE s.NOMBRE = :name");
		return query.getResultList();
	}
	
	
	

}
