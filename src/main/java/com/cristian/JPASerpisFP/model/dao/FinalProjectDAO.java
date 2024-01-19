package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cristian.JPASerpisFP.Domain.Entity.FinalProject;
import static com.cristian.JPASerpisFP.model.PersistenceManager.*;


public class FinalProjectDAO implements IDao<FinalProject> {

	@Override
	public void save(FinalProject project) throws Exception {
		performTransaction(entityManager ->{
			entityManager.persist(project);
		});
	}

	@Override
	public List<FinalProject> findAll() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<FinalProject> query = entityManager.createQuery("SELECT project FROM FinalProject project", FinalProject.class);
		return query.getResultList();
	}

	@Override
	public FinalProject findById(Object projectTitle) {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(FinalProject.class, (String) projectTitle);
	}

	@Override
	public void delete(FinalProject project) throws Exception {
		performTransaction(entityManager -> {
			FinalProject managedProject = entityManager.merge(project);
			entityManager.remove(managedProject);
		});
		
	}

	@Override
	public Long countRegisters() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(project) FROM FinalProject project", Long.class);
		return query.getSingleResult();
	}

}
