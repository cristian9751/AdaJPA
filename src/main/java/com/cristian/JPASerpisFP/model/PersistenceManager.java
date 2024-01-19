package com.cristian.JPASerpisFP.model;

import java.util.function.Consumer;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class PersistenceManager {
	
	private static EntityManagerFactory emf = null;

	
	public static void setFactory() throws Exception {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("SerpisFPPU");
			closeEntityManager(emf.createEntityManager());
			
		}
	}
	
	
	private static void closeEntityManager(EntityManager entityManager) {
		if(entityManager != null && entityManager.isOpen()) {
			entityManager.close();
		}
	}
	
	public static EntityManager getEntityManager() {
		return emf.createEntityManager();
	}
	
	public static void performTransaction(Consumer<EntityManager> operation) throws Exception {
		EntityManager entityManager = getEntityManager();
		EntityTransaction tx = entityManager.getTransaction();
		try {
			tx.begin();
			operation.accept(entityManager);
			tx.commit();
		} catch(Exception e) {
			if(tx.isActive()) {
				tx.rollback();
			}
			throw new Exception(e);
		} finally {
			closeEntityManager(entityManager);
		}
	}

}
