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
	public List<Mezzo> search(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select mzz from Mezzo mzz ";
			queryString += " WHERE mzz.targa      LIKE :cerca  ";
			queryString += "OR mzz.descrizione 	 LIKE :cerca ";
			Query query = em.createQuery(queryString);

			query.setParameter("cerca", "%" + cerca + "%");

			queryString += " ORDER BY mzz.targa, mzz.descrizione";

			List<Mezzo> answer = query.getResultList();

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
	
	@SuppressWarnings("unchecked")
	public List<Mezzo> listaPerTipo_mezzi(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			int xx = Integer.parseInt(cerca);
			String queryString = "select mez from Mezzo mez ";
			queryString += " WHERE mez.tipo_mezzo.id_tipo_mezzo = :cerca ";
			//queryString += " ORDER BY doc.anno_documento desc, doc.num_documento desc";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", xx);

			List<Mezzo> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

}
