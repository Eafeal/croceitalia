/**
 * 
 */
package org.cms.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.tipi.impl.cms.Articolo;
import org.springframework.stereotype.Repository;

/**
 * @author consdonzellipaolo
 * 
 */
@Repository("articoloDao")
public class ArticoloDao extends DominioDao {

	@Override
	public Class<?> getEntityClass() {
		return Articolo.class;
	}

	/**
	 * @param type
	 * @return
	 */
	public List<Articolo> getObjects(String type) {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("select obj from " + getTableName()
				+ " obj where obj.tipoOggetto.tipo=:type ");
		query.setParameter("type", type);
		@SuppressWarnings("unchecked")
		List<Articolo> resultList = query.getResultList();
		close(em);
		return resultList;
	}

	/**
	 * @param dominioUid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Articolo> loadAll(String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT obj FROM " + getTableName()
				+ " obj where obj.dominio.uid=:dominioUid");
		query.setParameter("dominioUid", dominioUid);
		List<Articolo> resultList = query.getResultList();
		close(em);
		return resultList;
	}
}
