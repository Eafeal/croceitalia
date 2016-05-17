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

@Repository("bancaManager")

public class BancaManager extends AssoDao{
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Banca> caricaBanche() {

		List<Banca> list = (List<Banca>) this.execNamedQuery("Banca.loadAll");

		return list;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
	//@Override
	public Class<?> getEntityClass() {

		return Banca.class;
	}

	/**
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Banca> search(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select bnc from Banca bnc ";
			queryString += " WHERE bnc.nome      LIKE :cerca  ";
			Query query = em.createQuery(queryString);

			query.setParameter("cerca", "%" + cerca + "%");

//			queryString += " ORDER BY str.nome, str.telefono";

			List<Banca> answer = query.getResultList();

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
