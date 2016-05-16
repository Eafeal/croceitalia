package org.cms.controller.croceitalia;

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

@Repository("mezzoManager")
public class MezzoManager extends AssoDao {
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Mezzo> caricaMezzi() {

		List<Mezzo> list = (List<Mezzo>) this.execNamedQuery("Mezzo.loadAll");

		return list;

	}
	@SuppressWarnings("unchecked")
	public List<Tipo_mezzo> caricaTipomezzo() {

		List<Tipo_mezzo> lista = (List<Tipo_mezzo>) this.execNamedQuery("Tipo_mezzo.loadAll");

		return lista;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
	//@Override
	public Class<?> getEntityClass() {

		return Mezzo.class;
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
