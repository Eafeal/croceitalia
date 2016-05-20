package org.cms.controller.croceitalia;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.Table;

import org.cms.jpa.dao.impl.AssoDao;
import org.cms.jpa.object.impl.Model;
import org.springframework.stereotype.Repository;

import it.asso.util.AssoLogger;
@Repository("documento_testata_manager")
public class DocumentoTestataManager2 extends AssoDao{
	
	/**
	 * @return666666
	 */

	@SuppressWarnings("unchecked")
	public List<Documento_Testata> caricaDocumento_Testata() {

		List<Documento_Testata> lista = (List<Documento_Testata>) this.execNamedQuery("Documento_Testata.loadAll");

		return lista;

	}
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
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
	


}
