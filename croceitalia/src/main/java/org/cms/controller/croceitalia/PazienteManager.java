package org.cms.controller.croceitalia;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;

/**
 * @author Paolo
 * 
 */
@Repository("pazienteManager")
public class PazienteManager extends AssoDao {

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Paziente> caricaPazienti() {

		List<Paziente> list = (List<Paziente>) this.execNamedQuery("Paziente.loadAll");
		return list;
	}

	@SuppressWarnings("unchecked")
	public List<Patologia> caricaPatologia() {

		List<Patologia> list = (List<Patologia>) this.execNamedQuery("Patologia.loadAll");
		return list;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
	// @Override
	public Class<?> getEntityClass() {

		return Paziente.class;
	}

	/**
	 * @param cerca
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Paziente> search(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select paz from Paziente paz ";
			queryString += " WHERE paz.cognome      LIKE :cerca  ";
			queryString += " OR paz.nome            LIKE :cerca  ";

			Query query = em.createQuery(queryString);

			query.setParameter("cerca", "%" + cerca + "%");

			queryString += " ORDER BY paz.cognome, paz.nome ";

			List<Paziente> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.dao.impl.AssoDao#save(java.lang.Object)
	 */
	@Override
	public void save(Object obj) throws AssoException {

		super.save(obj);
	}

}
