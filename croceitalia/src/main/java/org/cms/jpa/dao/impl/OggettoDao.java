package org.cms.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.object.impl.Oggetto;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author consdonzellipaolo
 * 
 */
@Repository("oggettoDao")
@Transactional(readOnly = true)
public class OggettoDao extends DominioDao {

	/**
	 * 
	 */
	public OggettoDao() {
		super();
	}

	/**
	 * @param type
	 * @return
	 */
	public List<Oggetto> getObjects(String type) {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("select obj from Oggetto obj where obj.tipoOggetto.tipo=:type ");
		query.setParameter("type", type);
		@SuppressWarnings("unchecked")
		List<Oggetto> resultList = query.getResultList();
		close(em);
		return resultList;
	}

	/**
	 * @param dominioUid
	 * @return
	 */

	public List<Oggetto> loadAll(String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT OBJECT(obj) FROM Oggetto obj where obj.dominio.uid=:dominioUid");
		query.setParameter("dominioUid", dominioUid);
		@SuppressWarnings("unchecked")
		List<Oggetto> resultList = query.getResultList();
		close(em);
		return resultList;
	}

//	@Override
	public Class<?> getEntityClass() {
		return Oggetto.class;
	}
}
