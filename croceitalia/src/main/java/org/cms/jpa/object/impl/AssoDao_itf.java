package org.cms.jpa.object.impl;

import it.asso.util.AssoException;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * @author ConsDonzelliPaolo
 * 
 *         E' il padre di tutti i DAO
 * 
 */
public interface AssoDao_itf {

	void close(EntityManager em);

	void deleteById(Class<?> aClass, String id) throws AssoException;

	void deleteById(String id) throws AssoException;

	Object findById(Class<?> classe, String id) throws AssoException;

	Object findById(String id);

	String getTableName();

	Class<?> getEntityClass();

	EntityManager getEntityManager();

	void save(List<Object> objectList) throws AssoException;

	void save(Object obj) throws AssoException;

	void update(List<Object> obj) throws AssoException;

	void update(Object obj) throws AssoException;

}
