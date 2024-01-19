package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

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
		TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
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
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(s) FROM Student s", Long.class);
		return query.getSingleResult();
	}
	
}
