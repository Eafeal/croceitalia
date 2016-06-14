package org.cms.controller.croceitalia;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.taglibs.standard.tag.el.fmt.ParseDateTag;
import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.ModelUser;
import it.asso.util.Utente_itf;

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
		Utente_itf utente = ModelUser.get();
		
		Documento_Testata documentoTestato = (Documento_Testata) findById(id);
		try {
			documentoTestato.setChiudi();
			
			documentoTestato.setUserultv(utente.getUserId());
			documentoTestato.setDataultv(new Date());
			
			update(documentoTestato);
		} catch (AssoException e) {

			e.printStackTrace();
		}
	}

	public void aggiornaTotale(String id, BigDecimal importoRiga) {
		Utente_itf utente = ModelUser.get();
		
		Documento_Testata documento = (Documento_Testata) findById(id);
		try {
			documento.setTotale(documento.getTotale().add(importoRiga));
			documento.setImponibile(documento.getImponibile().add(importoRiga));
			
			documento.setUserultv(utente.getUserId());
			documento.setDataultv(new Date());
			
			update(documento);
			
		} catch (AssoException e) {
			e.printStackTrace();
		}
	}

	public void sottraiTotale(String id, BigDecimal importoRiga) {
		Utente_itf utente = ModelUser.get();
		
		Documento_Testata documento = (Documento_Testata) findById(id);
		try {
			documento.setTotale(documento.getTotale().subtract(importoRiga));
			documento.setImponibile(documento.getImponibile().subtract(importoRiga));
			
			documento.setUserultv(utente.getUserId());
			documento.setDataultv(new Date());
			
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
	
	@SuppressWarnings("unchecked")
	public List<Documento_Testata> search(String anno,String mezzo,String cliente,String banca,String num_doc,String mese_doc,String cig,String data) throws Exception {
		
		String queryString;
		
		EntityManager em = null;
		
		queryString = "select dt from Documento_Testata dt where 1=1 ";
		
		if(!anno.equals("")){
			queryString += " and dt.anno_documento = :anno  ";
		}
		if(!mezzo.equals("")){
			queryString += " and dt.mezzo.id_mezzo = :mezzo  ";
		}
		if(!cliente.equals("")){
			queryString += " and dt.cliente.id_cliente = :cliente  ";
		}
		if(!banca.equals("")){
			queryString += " and dt.banca.id_banca = :banca  ";
		}
		if(!num_doc.equals("")){
			queryString += " and dt.num_documento = :num_doc  ";
		}
		if(!cig.equals("")){
			queryString += " and dt.CIG = :cig  ";
		}
		if(!mese_doc.equals("")){
			queryString += " and dt.mese_documento = :mese_doc  ";
		}
		if(!data.equals("")){
			queryString += " and dt.data_documento = :data  ";
		}
		try {
				em = getEntityManager();
				
				Query query = em.createQuery(queryString);
				System.out.println(query);
				
				if(!anno.equals("")){
				query.setParameter("anno",anno);
				}
				if(!mese_doc.equals("")){
					query.setParameter("mese_doc",mese_doc);
					}
				if(!mezzo.equals("")){
					int xx = Integer.parseInt(mezzo);
					query.setParameter("mezzo", xx );
				}
				if(!cliente.equals("")){
					int xx1 = Integer.parseInt(cliente);
					query.setParameter("cliente", xx1 );
				}
				if(!banca.equals("")){
					int xx2 = Integer.parseInt(banca);
					query.setParameter("banca", xx2 );
				}
				if(!num_doc.equals("")){
					int xx3 = Integer.parseInt(num_doc);
					query.setParameter("num_doc", xx3 );
				}
				if(!cig.equals("")){
					query.setParameter("cig", cig );
				}
				if(!data.equals("")){
					Date xx4 =stringToDate(data);
					query.setParameter("data", xx4 );
				}
				
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
	
	
		public Date stringToDate(String data) {
			
			Date data1=null;
			
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
			try {
				data1 = df.parse(data);
				System.out.println("Today = " + df.format(data1));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			return data1;
		}
	
		@SuppressWarnings("unchecked")
		public Documento_Testata leggi(String cerca) throws Exception {

			EntityManager em = null;
			try {
				em = getEntityManager();
				int xx = Integer.parseInt(cerca);
				String queryString = "select doc from Documento_Testata doc ";
				queryString += " WHERE doc.id_documento_testata = :cerca  ";
				Query query = em.createQuery(queryString);
				query.setParameter("cerca", xx);

				Documento_Testata answer = (Documento_Testata) query.getResultList();

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
