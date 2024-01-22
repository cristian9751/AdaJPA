package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
		TypedQuery<Subject> query = entityManager.createNamedQuery(Subject.SEARCH_ALL, null);
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
			Subject managedSubject = entityManager.merge(subject);
			entityManager.remove(managedSubject);
		});
		
	}

	@Override
	public Long countRegisters() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Long> query = entityManager.createNamedQuery(Subject.COUNT_ALL, Long.class);
		return query.getSingleResult();
	}

	@Override
	public int deleteAll() throws Exception {
		int[] result = new int[1];
		performTransaction(entityManager -> {
			Query query = entityManager.createNamedQuery(Subject.DELETE_ALL);
			result[0] = query.executeUpdate();
		});
		
		return result[0];
	}
	
	@Override
	public void update(Subject subject) throws Exception {
		performTransaction(entityManager -> {
			entityManager.merge(subject);
		});
	}
	
	
	public List<Subject> findByHours(int hours) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Subject> query = entityManager.createNamedQuery(Subject.SEARCH_HOURS, Subject.class);
		query.setParameter("hours", hours);
		return query.getResultList();
		
	}

}
