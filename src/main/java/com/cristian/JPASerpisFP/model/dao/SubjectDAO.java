package com.cristian.JPASerpisFP.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	public List<Subject> findAll() throws Exception {
		List<Subject> subjects = new ArrayList<>();
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT subject FROM Subject subject");
		return query.getResultList();
	}

	@Override
	public Subject findById(Object subjectId) throws Exception {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Subject.class, (int) subjectId);
	}

	@Override
	public void delete(Subject subject) throws Exception {
		performTransaction(entityManager -> {
			entityManager.remove(subject);
		});
		
	}
}
