package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.cristian.JPASerpisFP.Domain.Entity.Group;
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
	public List<Student> findAll() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Student> query = entityManager.createNamedQuery(Student.SEARCH_ALL, Student.class);
		return query.getResultList();
		
	}

	@Override
	public Student findById(Object NIA) {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Student.class, (String) NIA);
	}

	@Override
	public void delete(Student student) throws Exception {
		performTransaction(entityManager -> {
			Student managedStudent = entityManager.merge(student);
			entityManager.remove(managedStudent);
		});
		
	}

	@Override
	public Long countRegisters() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Long> query = entityManager.createNamedQuery(Student.COUNT_ALL, Long.class);
		return query.getSingleResult();
	}

	@Override
	public int deleteAll() throws Exception {
		int result[] = new int[1];
		performTransaction(entityManager -> {
			Query query = entityManager.createNamedQuery(Student.DELETE_ALL);
			result[0] = query.executeUpdate();
		});
		return result[0];
	}
	
	@Override
	public void update(Student student) throws Exception {
		performTransaction(entityManager -> {
			entityManager.merge(student);
		});
	}
	
	public List<Student> findByGroup(Group group) {
		EntityManager entityManger = getEntityManager();
		
		TypedQuery<Student> query = entityManger.createNamedQuery(Student.SEARCH_GROUP, Student.class);
		query.setParameter("group", group);
		return query.getResultList();
		
	}
	
	public List<Student> findByName(String name) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Student> query = entityManager.createNamedQuery(Student.SEARCH_NAME, Student.class);
		query.setParameter("name", name);
		return query.getResultList();
	}
	
	
	public List<Student> findBySurname(String surname) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Student> query = entityManager.createNamedQuery(Student.SEARCH_SURNAME, Student.class);
		query.setParameter("surname", surname);
		return query.getResultList();
	}
	
	


	
}
