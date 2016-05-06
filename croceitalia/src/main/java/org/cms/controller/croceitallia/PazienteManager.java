package org.cms.controller.croceitallia;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

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

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
	//@Override
	public Class<?> getEntityClass() {

		return Paziente.class;
	}

	/**
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Utente_itf> search(ModelMap modelMap) throws Exception {

		String cognome = (String) modelMap.get("cognome");

		if (Util.isNotEmpty(cognome)) {
			cognome = "%" + cognome + "%";
		}

		EntityManager em = null;
		try {

			em = getEntityManager();

			String queryString = "select Paziente from Paziente Paziente ";
			queryString += " WHERE Paziente.cognome LIKE :cognome  ";

			Query query = em.createQuery(queryString);

			query.setParameter("cognome", cognome);

			queryString += " ORDER BY ute.userId ";

			List<Utente_itf> answer = query.getResultList();

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
	//@Override
	public void save(Object obj) throws AssoException {
		super.save(obj);
	}

}
