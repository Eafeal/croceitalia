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


@Repository("strutturaManager")
public class StrutturaManager extends AssoDao {

	/**
	 * TIPOLOGIA STRUTTURA
	 */
	@SuppressWarnings("unchecked")
	public List<Tipologia_Struttura> caricatipoStruttura() {
		
		List<Tipologia_Struttura> list = (List<Tipologia_Struttura>) this.execNamedQuery("Tipologia_Struttura.loadAll");
		return list;
	}

	/**
	 *STRUTTURA
	 */
	@SuppressWarnings("unchecked")
	public List<Struttura> caricaStruttura() {
		
		List<Struttura> list = (List<Struttura>) this.execNamedQuery("Struttura.loadAll");
		return list;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
//	@Override
	public Class<?> getEntityClass() {

		return Struttura.class;
	}
	/**
	 * @param cerca
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Struttura> search(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select str from Struttura str ";
			queryString += " WHERE str.nome      LIKE :cerca  ";
			queryString += "OR str.telefono 	 LIKE :cerca ";
			Query query = em.createQuery(queryString);

			query.setParameter("cerca", "%" + cerca + "%");

			queryString += " ORDER BY str.nome, str.telefono";

			List<Struttura> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	
	@Override
	public void save(Object obj) throws AssoException {
		super.save(obj);
	}

}
