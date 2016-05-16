package org.cms.html.anchor;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.DominioDao;
import org.springframework.stereotype.Repository;

@Repository("anchorDao")
public class AnchorDao extends DominioDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
//	@Override
	public Class<?> getEntityClass() {
		return Anchor.class;
	}

	/**
	 * @param type
	 * @return
	 */
	public List<Anchor> getObjects(String type) {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("select obj from " + getTableName()
				+ " obj where obj.tipoOggetto.tipo=:type ");
		query.setParameter("type", type);
		@SuppressWarnings("unchecked")
		List<Anchor> resultList = query.getResultList();
		close(em);
		return resultList;
	}

	/**
	 * @param dominioUid
	 * @return
	 */
	public List<Anchor> loadAll(String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT obj FROM " + getTableName()
				+ " obj where obj.dominio.uid=:dominioUid");
		query.setParameter("dominioUid", dominioUid);
		@SuppressWarnings("unchecked")
		List<Anchor> resultList = query.getResultList();
		close(em);
		return resultList;
	}

}
