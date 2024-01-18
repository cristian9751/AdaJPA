package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.cristian.JPASerpisFP.Domain.Entity.FinalProject;
import static com.cristian.JPASerpisFP.model.PersistenceManager.*;

public class FinalProjectDAO implements IDao<FinalProject> {

	@Override
	public void save(FinalProject project) throws Exception {
		performTransaction(entityManager -> {
			entityManager.persist(project);
		});
		
	}

	@Override
	public List<FinalProject> findAll() throws Exception {
		EntityManager entityManager = getEntityManager();
		Query query = entityManager.createQuery("SELECT project FROM FinalProject project");
		return query.getResultList();
	}

	@Override
	public FinalProject findById(Object projectId) throws Exception {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(FinalProject.class, (int) projectId);
	}

	@Override
	public void delete(FinalProject project) throws Exception {
		performTransaction(entityManager -> {
			entityManager.remove(project);
		});
		
	}

}
