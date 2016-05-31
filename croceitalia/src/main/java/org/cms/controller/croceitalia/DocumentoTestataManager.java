package org.cms.controller.croceitalia;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

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

	@Override
	public Class<?> getEntityClass() {

		return Documento_Testata.class;
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

	@SuppressWarnings("unchecked")
	public Integer nextNumDocumento(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			// int xx = Integer.parseInt(cerca);
			String queryString = "select max(doc.num_documento) from Documento_Testata doc ";
			queryString += " WHERE doc.anno_documento = :cerca ";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", cerca);
			Integer answer = (Integer) query.getSingleResult();

			return answer + 1;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	@SuppressWarnings("unchecked")
	public Date nextDataDocumento(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();
			// int xx = Integer.parseInt(cerca);
			String queryString = "select max(doc.data_documento) from Documento_Testata doc ";
			queryString += " WHERE doc.anno_documento = :cerca ";
			Query query = em.createQuery(queryString);
			query.setParameter("cerca", cerca);
			Date answer = (Date) query.getSingleResult();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	public void chiudiDocumento(String id) {
		Documento_Testata documentoTestato = (Documento_Testata) findById(id);
		try {
			documentoTestato.setChiudi();
			update(documentoTestato);
		} catch (AssoException e) {

			e.printStackTrace();
		}
	}

	public void aggiornaTotale(String id, BigDecimal importoRiga) {
		Documento_Testata documento = (Documento_Testata) findById(id);
		try {
			documento.setTotale(documento.getTotale().add(importoRiga));
			documento.setImponibile(documento.getImponibile().add(importoRiga));
			update(documento);
		} catch (AssoException e) {

			e.printStackTrace();
		}
	}

	public void sottraiTotale(String id, BigDecimal importoRiga) {
		Documento_Testata documento = (Documento_Testata) findById(id);
		try {
			documento.setTotale(documento.getTotale().subtract(importoRiga));
			documento.setImponibile(documento.getImponibile().subtract(importoRiga));
			update(documento);
		} catch (AssoException e) {

			e.printStackTrace();
		}
	}

	public byte[] getPdf(Documento_Testata testata, List<Documento_Righe> righe) {

		if (!testata.isPdfGenerato()) {

			System.out.println(testata.getAnno_documento());
			System.out.println(testata.getMese_documento());
			System.out.println(testata.getEsente_bollo());
			System.out.println("----------------------------------");

			try {
				String nomefile = "rimborso_num" + testata.getNum_documento() + ".pdf";
				GeneraPdf2 generaPdf2 = new GeneraPdf2(nomefile, testata, righe);
				generaPdf2.stampa();

				testata.setPdf_generato("S");
				testata.setNome_file(nomefile);
				update(testata);

				// leggere il pdf generato
				// todo
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} else {

		}

		return null;
	}

}
