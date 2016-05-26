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

@Repository("patologiaManager")

public class PatologiaManager extends AssoDao{
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Patologia> caricaPatologie() {

		List<Patologia> list = (List<Patologia>) this.execNamedQuery("Patologia.loadAll");

		return list;

	}
	
	//@Override
	public Class<?> getEntityClass() {
		return Patologia.class;
	}
	
	/**
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Patologia> search(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select ptl from Patologia ptl ";
			queryString += " WHERE ptl.descrizione      LIKE :cerca  ";
			Query query = em.createQuery(queryString);

			query.setParameter("cerca", "%" + cerca + "%");

//			queryString += " ORDER BY str.nome, str.telefono";

			List<Patologia> answer = query.getResultList();

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
