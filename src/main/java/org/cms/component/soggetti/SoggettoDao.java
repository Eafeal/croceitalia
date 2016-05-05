package org.cms.component.soggetti;

import java.util.List;

import javax.persistence.EntityManager;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Repository("soggettoDao")
public class SoggettoDao extends AssoDao {

	@Override
	public Class<?> getEntityClass() {
		return Soggetto.class;
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Soggetto> loadAll() {
		EntityManager em = getEntityManager();
		List<Soggetto> resultList = em.createNamedQuery("Soggetto.loadAll").getResultList();

		return resultList;
	}
}
