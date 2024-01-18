package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cristian.JPASerpisFP.Domain.Entity.Subject;
import static com.cristian.JPASerpisFP.model.PersistenceManager.*;

public class SubjectDAO implements IDao<Subject> {

	@Override
	public void save(Subject subject) throws Exception {
		performTransaction(entityManager -> {
			entityManager.persist(subject);
		});
		
	}

	@Override
	public List<Subject> findAll() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Subject> query = entityManager.createQuery("SELECT subject FROM Subject subject", Subject.class);
		return query.getResultList();
	}

	@Override
	public Subject findById(Object subjectCode) {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Subject.class, (int) subjectCode);
	}

	@Override
	public void delete(Subject subject) throws Exception {
		performTransaction(entityManager -> {
			entityManager.remove(subject);
		});
		
	}

	@Override
	public Long countRegisters() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(subject) FROM Subject subject", Long.class);
		return query.getSingleResult();
	}

}
