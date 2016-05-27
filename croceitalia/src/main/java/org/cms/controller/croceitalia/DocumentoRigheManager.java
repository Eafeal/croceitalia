package org.cms.controller.croceitalia;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;

@Repository("documento_righe_manager")
public class DocumentoRigheManager extends AssoDao {

	/**
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Documento_Righe> caricaDocumento_Row() {

		List<Documento_Righe> lista = (List<Documento_Righe>) this.execNamedQuery("Documento_Righe.loadAll");

		return lista;

	}

	// recupero le righe dei documenti

	@SuppressWarnings("unchecked")
	public List<Documento_Righe> caricaDocumento_Row_byId(String idd) throws Exception {

		EntityManager em = null;
		int id = Integer.parseInt(idd);
		try {
			em = getEntityManager();

			String queryString = "select righe from Documento_Righe righe ";
			queryString += " WHERE righe.fk_id_documento_testata = :id ";
			Query query = em.createQuery(queryString);

			query.setParameter("id", id);
			List<Documento_Righe> answer = query.getResultList();
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
	public Class<?> getEntityClass() {

		// TODO Auto-generated method stub
		return Documento_Righe.class;
	}

	@SuppressWarnings("unchecked")
	public List<Documento_Righe> caricaRigheDocumento(int idTestata) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select righe from Documento_Righe righe ";
			queryString += " WHERE righe.fk_id_documento_testata = :idTestata ";
			Query query = em.createQuery(queryString);

			query.setParameter("idTestata", idTestata);

			// queryString += " ORDER BY str.nome, str.telefono";

			List<Documento_Righe> answer = query.getResultList();

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
