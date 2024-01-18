package com.cristian.JPASerpisFP.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.cristian.JPASerpisFP.Domain.Entity.Group;
import static com.cristian.JPASerpisFP.model.PersistenceManager.*;

public class GroupDAO implements IDao<Group> {

	@Override
	public void save(Group group) throws Exception {
		performTransaction(entityManager -> {
			entityManager.persist(group);
		});
		
	}

	@Override
	public List<Group> findAll() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Group> query = entityManager.createQuery("SELECT  g FROM Group g", Group.class);
		return query.getResultList();
	}

	@Override
	public Group findById(Object groupCode) {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Group.class, (int) groupCode);
	}

	@Override
	public void delete(Group group) throws Exception {
		performTransaction(entityManager -> {
			Group managedGroup = entityManager.merge(group);
			entityManager.remove(managedGroup);
		});
		
	}

	@Override
	public Long countRegisters() {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(g) FROM Group g", Long.class);
		return query.getSingleResult();
	}

}
