package org.cms.login;

import it.asso.util.AssoLogger;
import it.asso.util.Dominio_itf;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.AssoDao;
import org.cms.jpa.object.impl.Dominio;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

/**
 * @author Paolo
 * 
 */
@Repository("userDao")
public class UserDao extends AssoDao {

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SoggettoUtente> caricaSoggettiUtente() {

		List<SoggettoUtente> list = (List<SoggettoUtente>) this.execNamedQuery("SoggettoUtente.loadAll");

		return list;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
	@Override
	public Class<?> getEntityClass() {

		return Utente.class;
	}

	/**
	 * @param uid
	 * @param pwd
	 * @param dominio
	 * @return
	 */
	public Utente_itf getUtente(String uid, String pwd, Dominio_itf dominio) {

		EntityManager em = null;
		try {
			em = getEntityManager();
			Query query = em
					.createQuery("select ute from Utente ute where ute.userId=:uid and ute.password=:pwd and ute.dominio.uid=:dominio_uid");
			query.setParameter("uid", uid);
			query.setParameter("pwd", pwd);
			query.setParameter("dominio_uid", dominio.getUid());
			Utente_itf answer = null;
			try {
				answer = (Utente_itf) query.getSingleResult();
			} catch (javax.persistence.NoResultException e) {
				AssoLogger.GetInstance().logError("Nessun utente con questi dati: user=" + uid + " e pwd=" + pwd, e);
				answer = null;
			}

			return answer;

		} catch (Throwable e) {
			AssoLogger.GetInstance().logError("Impossibile Connettersi con user=" + uid + " e pwd=" + pwd, e);
			return null;
		} finally {
			close(em);
		}
	}

	/**
	 * @param uid
	 * @param pwd
	 * @param dominio
	 * @return
	 */
	public Utente_itf login(String uid, String pwd, Dominio_itf dominio) {

		try {
			Utente_itf utente = getUtente(uid, pwd, dominio);

			setTimeLastLogin(utente);

			this.update(utente);

			return utente;

		} catch (Throwable e) {
			AssoLogger.GetInstance().logError("Impossibile Connettersi con user=" + uid + " e pwd=" + pwd, e);
			return null;
		}
	}

	/**
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Utente_itf> search(ModelMap modelMap) throws Exception {

		String cognome = (String) modelMap.get("cognome");

		if (Util.isNotEmpty(cognome)) {
			cognome = "%" + cognome + "%";
		}

		EntityManager em = null;
		try {

			em = getEntityManager();

			String queryString = "select ute from Utente ute ";
			queryString += " WHERE ute.cognome LIKE :cognome  ";
			queryString += " OR ute.ragioneSociale LIKE :cognome  ";

			Query query = em.createQuery(queryString);

			query.setParameter("cognome", cognome);

			queryString += " ORDER BY ute.userId ";

			List<Utente_itf> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	/**
	 * @param utente
	 */
	private void setTimeLastLogin(Utente_itf utente) {

		long time = Calendar.getInstance().getTimeInMillis();
		utente.setTimeLastLogin(new Timestamp(time));
	}

	/**
	 * @param cerca
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public List<Utente_itf> search(String cerca) throws Exception {

		EntityManager em = null;
		try {
			em = getEntityManager();

			String queryString = "select ute from Utente ute ";
			queryString += " WHERE ute.cognome      LIKE :cerca  ";
			queryString += " OR ute.nome            LIKE :cerca  ";
			queryString += " OR ute.ragioneSociale  LIKE :cerca  ";
			queryString += " OR ute.userId          LIKE :cerca  ";

			Query query = em.createQuery(queryString);

			query.setParameter("cerca", "%" + cerca + "%");

			queryString += " ORDER BY ute.ragioneSociale, ute.cognome, ute.nome ";

			List<Utente_itf> answer = query.getResultList();

			return answer;

		} catch (Exception e) {
			AssoLogger.GetInstance()
					.logInfo("Errore nel metodo search della classe " + this.getClass().getSimpleName());
			throw e;
		} finally {
			close(em);
		}
	}

	public Utente_itf getUtenteByEmail(String uid, Dominio dominio) {

		EntityManager em = null;
		try {
			em = getEntityManager();
			Query query = em
					.createQuery("select ute from Utente ute where ute.userId=:uid and ute.dominio.uid=:dominio_uid");
			query.setParameter("uid", uid);
			query.setParameter("dominio_uid", dominio.getUid());
			Utente_itf answer = null;
			try {
				answer = (Utente_itf) query.getSingleResult();
			} catch (javax.persistence.NoResultException e) {
				answer = null;
			}

			return answer;

		} catch (Throwable e) {
			AssoLogger.GetInstance().logError("Impossibile Connettersi con user=" + uid + " e pwd=", e);
			return null;
		} finally {
			close(em);
		}
	}

}
