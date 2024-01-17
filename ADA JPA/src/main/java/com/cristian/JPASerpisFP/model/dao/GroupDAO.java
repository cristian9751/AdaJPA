package com.cristian.JPASerpisFP.model.dao;

import com.cristian.JPASerpisFP.Domain.Entity.Group;
import static com.cristian.JPASerpisFP.model.PersistenceManager.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

public class GroupDAO implements Dao<Group> {
	@Override
	public  void save(Group group) throws Exception {
		performTransaction(entityManager -> {
			entityManager.persist(group);
		});
	}

	@Override
	public List<Group> findAll() throws Exception {
		List<Group> groups = new ArrayList<Group>();
		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT group FROM Group group");
		groups = query.getResultList();
		return groups;
		
	}

	@Override
	public Group findById(Object groupCode) throws Exception {
		EntityManager entityManager = getEntityManager();
		return entityManager.find(Group.class, (int) groupCode);
		
		
	}

	@Override
	public void delete(Group group) throws Exception {
		performTransaction(entityManager -> {
			entityManager.remove(group);
		});
		
	}
	


	
}
