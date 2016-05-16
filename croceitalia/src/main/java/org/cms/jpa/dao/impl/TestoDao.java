package org.cms.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.tipi.impl.cms.Testo;
import org.springframework.stereotype.Repository;

@Repository("testoDao")
public class TestoDao extends DominioDao {

	public List<Testo> getObjects(String type) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("select obj from Testo obj where obj.tipoOggetto.tipo=:type ");
		query.setParameter("type", type);
		@SuppressWarnings("unchecked")
		List<Testo> resultList = query.getResultList();
		close(em);
		return resultList;
	}

	@SuppressWarnings("unchecked")
	public List<Testo> loadAll(String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("SELECT OBJECT(obj) FROM Testo obj where obj.dominio.uid=:dominioUid");
		query.setParameter("dominioUid", dominioUid);
		List<Testo> resultList = query.getResultList();
		close(em);
		return resultList;
	}

//	@Override
	public Class<?> getEntityClass() {
		return Testo.class;
	}

}
