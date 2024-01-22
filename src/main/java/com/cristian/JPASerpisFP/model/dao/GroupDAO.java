package com.cristian.JPASerpisFP.model.dao;

import java.util.List;
import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
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
		TypedQuery<Group> query = entityManager.createNamedQuery(Group.SEARCH_ALL, Group.class);
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
		TypedQuery<Long> query = entityManager.createNamedQuery(Group.COUNT_ALL, Long.class);
		return query.getSingleResult();
	}

	@Override
	public int deleteAll() throws Exception {
		int[] result = new int[1];
		performTransaction(entityManager -> {
			Query query = entityManager.createNamedQuery(Group.DELETE_ALL);
			result[0] = query.executeUpdate();
		});
		
		return result[0];
	}
	
	
	public List<Group> findByClassroom(String classroom) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Group> query = entityManager.createNamedQuery(Group.SEARCH_CLASSROOM, Group.class);
		query.setParameter("classroom", "%" + classroom + "%");
		return query.getResultList();
	}
	
	
	public List<Group> getByDescription(String description) {
		EntityManager entityManager = getEntityManager();
		TypedQuery<Group> query = entityManager.createNamedQuery(Group.SEARCH_DESCRIPTION, Group.class);
		query.setParameter("description", "%" + description + "%");
		return query.getResultList();
	}

	@Override
	public void update(Group group) throws Exception {
		performTransaction(entityManager -> {
			entityManager.merge(group);
		});
	}
	
	


}
