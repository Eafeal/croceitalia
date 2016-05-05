package org.cms.jpa.dao.impl;

import it.asso.util.AssoException;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.Table;

import org.cms.jpa.object.impl.AssoDao_itf;
import org.cms.jpa.object.impl.Model_itf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ConsDonzelliPaolo
 * 
 *         Sono i DAO che NON appartengono al Dominio. Appartengono al Business
 */
public abstract class AssoDao implements AssoDao_itf {

	/**
    *
    */
	@Autowired
	@PersistenceUnit(unitName = "pu_cms")
	private EntityManagerFactory entityManagerFactory;

	/**
	 * @param em
	 */
	@Override
	public void close(EntityManager em) {

		// em.getDelegate();
		if (em.isOpen()) {
			// System.out.println("Close em");
			em.close();
		}
	}

	@Override
	public void deleteById(Class<?> aClass, String id) throws AssoException {

		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			em = getEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			// Trovo l'oggetto in questione
			Model_itf model = (Model_itf) em.find(aClass, id);

			// Cancello l'oggetto
			em.remove(model);

			// Commit the transaction
			em.getTransaction().commit();

		} catch (Throwable e) {
			// Rollback the transaction
			error(transaction, e);
		} finally {
			// Close this EntityManager
			if (em != null) {
				close(em);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#deleteById(java.lang.String)
	 */
	@Override
	public void deleteById(String id) throws AssoException {

		deleteById(getEntityClass(), id);
	}

	/**
	 * @param transaction
	 * @param e
	 * @throws AssoException
	 */
	protected final void error(EntityTransaction transaction, Throwable e) throws AssoException {

		boolean active = transaction.isActive();
		if (active) {
			transaction.rollback();
		}
		AssoException assoException = new AssoException(e);
		throw assoException;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#findById(java.lang.Class, java.lang.String)
	 */
	@Override
	public Object findById(Class<?> classe, String id) {

		EntityManager em = getEntityManager();
		Object oggetto = em.find(classe, id);

		close(em);

		return oggetto;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#findById(java.lang.String)
	 */
	@Override
	public Object findById(String id) {

		return findById(getEntityClass(), id);
	}

	/**
	 * @return
	 */
	@Override
	public EntityManager getEntityManager() {

		// if (_entityManager != null && _entityManager.isOpen()) {
		// return _entityManager;
		// }
		// _entityManager = getEntityManagerFactory().createEntityManager();
		// return _entityManager;

		return getEntityManagerFactory().createEntityManager();
	}

	/**
	 * @return
	 */
	protected EntityManagerFactory getEntityManagerFactory() {

		if (entityManagerFactory == null) {
			createEntityManagerFactory();
		}

		return entityManagerFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getTableName()
	 */
	@Override
	public String getTableName() {

		Table annotation = getEntityClass().getAnnotation(javax.persistence.Table.class);
		String tableName = annotation.name();

		return tableName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#save(java.util.List)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void save(List<Object> objectList) throws AssoException {

		EntityManager em = null;
		EntityTransaction transaction = null;

		try {
			em = getEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Iterator<Object> iterator = objectList.iterator();
			while (iterator.hasNext()) {
				Object obj = iterator.next();
				em.persist(obj);
			}
			// Commit the transaction
			transaction.commit();
		} catch (Throwable e) {
			// Rollback the transaction
			error(transaction, e);
		} finally {
			// Close this EntityManager
			if (em != null) {
				close(em);
			}
		}

	}

	/*
	 * 
	 * Esegue la INSERT dei nuovi oggetti
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#save(java.lang.Object)
	 */
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public void save(Object obj) throws AssoException {

		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			em = getEntityManager();
			transaction = em.getTransaction();
			transaction.begin();
			em.persist(obj);
			transaction.commit();
		} catch (Throwable e) {
			// Rollback the transaction
			error(transaction, e);
		} finally {
			// Close this EntityManager
			if (em != null) {
				close(em);
			}
		}
	}

	/**
	 * @param sql
	 * @param parameters
	 * @return
	 */
	@SuppressWarnings({ "unchecked" })
	public List select(String sql, Map<String, Object> parameters) {

		EntityManager em = getEntityManager();

		Query query = em.createQuery(sql);
		Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			query.setParameter(entry.getKey(), entry.getValue());
		}

		List resultList = query.getResultList();

		return resultList;
	}

	public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {

		this.entityManagerFactory = entityManagerFactory;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#update(java.util.List)
	 */
	@Override
	public void update(List<Object> objectList) throws AssoException {

		EntityManager em = null;
		EntityTransaction transaction = null;

		try {
			em = getEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			Iterator<Object> iterator = objectList.iterator();
			while (iterator.hasNext()) {
				Object obj = iterator.next();

				boolean isManaged = em.contains(obj);
				if (!isManaged) {
					// System.out.println("isManaged=" + isManaged);
					// em.refresh(obj);
				}
				em.merge(obj);
			}
			// Commit the transaction
			transaction.commit();
		} catch (Throwable e) {
			// Rollback the transaction
			error(transaction, e);
		} finally {
			// Close this EntityManager
			if (em != null) {
				close(em);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 */
	@Override
	public void update(Object obj) throws AssoException {

		update(getEntityManager(), obj);
	}

	/**
	 * @param em
	 * @param obj
	 * @throws AssoException
	 */
	public void update(EntityManager em, Object obj) throws AssoException {

		EntityTransaction transaction = null;
		try {
			transaction = em.getTransaction();
			transaction.begin();

			em.merge(obj);

			transaction.commit();

		} catch (Exception e) {
			error(transaction, e);
		} finally {
			// Close this EntityManager
			if (em != null) {
				close(em);
			}
		}
	}

	/**
	 * @return
	 */
	public List<?> execNamedQuery(String namedQuery) {

		EntityManager em = getEntityManager();
		List<?> resultList = em.createNamedQuery(namedQuery).getResultList();

		return resultList;
	}

	/**
	 * 
	 */
	public void createEntityManagerFactory() {

		String persistenceUnitName = "pu_cms";
		entityManagerFactory = Persistence.createEntityManagerFactory(persistenceUnitName, System.getProperties());

		// EntityManager em = entityManagerFactory.createEntityManager();
	}
}
