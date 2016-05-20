package org.cms.controller.croceitalia;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;

@Repository("documento_testata_manager")
public class DocumentoTestataManager extends AssoDao {

	/**
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Documento_Testata> listaDocumento_Testata() {

		List<Documento_Testata> lista = (List<Documento_Testata>) this.execNamedQuery("Documento_Testata.loadAll ");
		return lista;

	}

	// GESTIRE LA QUERY
	public List<Documento_Testata> descrescente() {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("SELECT OBJECT(obj) FROM Documento_Testata obj order by obj.num_documento desc");

		@SuppressWarnings("unchecked")
		List<Documento_Testata> lista = query.getResultList();
		close(em);
		return lista;
	}

	public Class<?> getEntityClass() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Object obj) throws AssoException {

		super.save(obj);
	}

	@SuppressWarnings("unchecked")
	public List<Documento_Testata> listaPerBanche(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			int xx = Integer.parseInt(cerca);
			String queryString = "select doc from Documento_Testata doc ";
			queryString += " WHERE doc.banca.id_banca = :cerca  ";
			queryString += " ORDER BY doc.anno_documento desc, doc.num_documento desc";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", xx);

			List<Documento_Testata> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Documento_Testata> listaPerMezzi(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			int xx = Integer.parseInt(cerca);
			String queryString = "select doc from Documento_Testata doc ";
			queryString += " WHERE doc.mezzo.id_mezzo = :cerca  ";
			queryString += " ORDER BY doc.anno_documento desc, doc.num_documento desc";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", xx);

			List<Documento_Testata> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Documento_Testata> listaPerClienti(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			int xx = Integer.parseInt(cerca);
			String queryString = "select doc from Documento_Testata doc ";
			queryString += " WHERE doc.cliente.id_cliente = :cerca  ";
			queryString += " ORDER BY doc.anno_documento desc, doc.num_documento desc";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", xx);

			List<Documento_Testata> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Documento_Testata> listaPerPaziente(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			int xx = Integer.parseInt(cerca);
			String queryString = "select doc from Documento_Testata doc ";
			queryString += " WHERE doc.id_documento_testata in (select distinct rig.fk_id_documento_testata from Documento_Righe rig where rig.paziente.id_paziente = :cerca ) ";
			queryString += " ORDER BY doc.anno_documento desc, doc.num_documento desc";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", xx);

			List<Documento_Testata> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Documento_Testata> listaPerStruttura(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			int xx = Integer.parseInt(cerca);
			String queryString = "select doc from Documento_Testata doc ";
			queryString += " WHERE doc.id_documento_testata in (select distinct rig.fk_id_documento_testata from Documento_Righe rig where rig.struttura.id_struttura = :cerca ) ";
			queryString += " ORDER BY doc.anno_documento desc, doc.num_documento desc";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", xx);

			List<Documento_Testata> answer = query.getResultList();

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
