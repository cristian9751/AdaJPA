package com.cristian.JPASerpisFP.model.dao;

import com.cristian.JPASerpisFP.Domain.Entity.Enrollment;
import com.cristian.JPASerpisFP.Domain.Entity.Group;

import static com.cristian.JPASerpisFP.model.PersistenceManager.*;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

public class EnrollmentDAO {
	public void delete(Enrollment enrollment) throws Exception {
		performTransaction(entityManager -> {
			Enrollment managedEnrollment = entityManager.merge(enrollment);
			entityManager.remove(managedEnrollment);
		});
	}
	
	public void save(Enrollment enrollment) throws Exception {
		performTransaction(entityManager -> {
			entityManager.persist(enrollment);
		});
		

	}
	
	
	public List<Enrollment> findAll() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Enrollment> query = entityManager.createNamedQuery(Enrollment.SELECT_ALL, Enrollment.class);
		return query.getResultList();
	}
	
	public int deleteAll() throws Exception {
		int[] result = new int[1];
		performTransaction(entityManager -> {
			Query query = entityManager.createNamedQuery(Enrollment.DELETE_ALL);
			result[0] = query.executeUpdate();
		});
		
		return result[0];
	}
}
