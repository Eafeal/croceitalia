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

@Repository("clienteManager")
public class ClienteManager extends AssoDao{
	
	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> caricaClienti() {

		List<Cliente> list = (List<Cliente>) this.execNamedQuery("Cliente.loadAll");

		return list;

	}
	@SuppressWarnings("unchecked")
	public List<Tipo_cliente> caricaTipocliente() {

		List<Tipo_cliente> lista = (List<Tipo_cliente>) this.execNamedQuery("Tipo_cliente.loadAll");

		return lista;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
	//@Override
	public Class<?> getEntityClass() {

		return Cliente.class;
	}

	/**
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> search(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select cln from Cliente cln ";
			queryString += " WHERE cln.ragione_sociale      LIKE :cerca  ";
			queryString += "OR cln.telefono1 	 LIKE :cerca ";
			Query query = em.createQuery(queryString);

			query.setParameter("cerca", "%" + cerca + "%");

//			queryString += " ORDER BY cln.ragione_sociale, cln.telefono1";

			List<Cliente> answer = query.getResultList();

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
	public List<Cliente> listaPerTipo_clienti(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			int xx = Integer.parseInt(cerca);
			String queryString = "select cln from Cliente cln ";
			queryString += " WHERE cln.tipo_cliente.id_tipo_cliente = :cerca ";
			//queryString += " ORDER BY doc.anno_documento desc, doc.num_documento desc";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", xx);

			List<Cliente> answer = query.getResultList();

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
