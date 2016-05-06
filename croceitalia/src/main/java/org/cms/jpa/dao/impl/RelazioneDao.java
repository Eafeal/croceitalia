package org.cms.jpa.dao.impl;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.cms.jpa.object.impl.DominioModel_itf;
import org.cms.jpa.object.impl.Relazione;
import org.cms.jpa.object.impl.RelazioneDao_itf;
import org.cms.jpa.object.impl.TipoOggetto;
import org.cms.jpa.object.impl.TipoOggetto_itf;
import org.springframework.stereotype.Repository;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Repository("relazioneDao")
public class RelazioneDao extends DominioDao implements RelazioneDao_itf {

	@Override
	public void deleteById(Class<?> aClass, String id) throws AssoException {

		EntityManager em = null;
		EntityTransaction transaction = null;
		try {
			em = getEntityManager();
			transaction = em.getTransaction();
			transaction.begin();

			// Trovo l'oggetto in questione
			Object model = em.find(aClass, id);

			// Cancello l'oggetto
			em.remove(model);

			// Commit the transaction
			em.getTransaction().commit();

		} catch (Throwable e) {
			// Rollback the transaction
			error(transaction, e);
		} finally {
			// Close this EntityManager
			if (em != null) {
				close(em);
			}
		}
	}

	@Override
	public Class<?> getEntityClass() {
		return Relazione.class;
	}

	/**
	 * @param idPadre
	 * @param idFiglio
	 * @throws AssoException
	 */
	@SuppressWarnings("unchecked")
	public void deleteById(String idPadre, String idFiglio)
			throws AssoException {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("select obj from Relazione obj where obj.padre_id=:idPadre and obj.figlio_id=:idFiglio ");

		query.setParameter("idPadre", idPadre);
		query.setParameter("idFiglio", idFiglio);
		List<Relazione> resultList = query.getResultList();
		if (resultList.isEmpty()) {
			throw new AssoException("Relazione non trovata");
		} else {
			Relazione rel = resultList.get(0);
			String id = rel.getUid();
			deleteById(id);
			close(em);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#findByUid(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Relazione findByUid(String uid, String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("select obj from Relazione rel where rel.uid=:uid");
		query.setParameter("uid", uid);
		Relazione answer = (Relazione) query.getSingleResult();
		close(em);
		return answer;
	}

	/**
	 * @param dominioUid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Relazione> loadAll(String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("SELECT OBJECT(obj) FROM Relazione obj where obj.dominio.uid=:dominioUid");
		query.setParameter("dominioUid", dominioUid);
		List<Relazione> resultList = query.getResultList();
		close(em);
		return resultList;
	}

	@Override
	public List<Relazione> loadChildren(EntityManager em,
			DominioModel_itf padre, TipoOggetto_itf tipo) {

		Query query = em
				.createQuery("select obj from Relazione obj where obj.padre_id=:idPadre and obj.tipopadre_id=:idTipoPadre  and obj.dominio.uid=:idDominio order by obj.ordine asc");

		query.setParameter("idDominio", padre.getDominio().getUid());
		query.setParameter("idPadre", padre.getId());
		query.setParameter("idTipoPadre", tipo.getId());

		@SuppressWarnings("unchecked")
		List<Relazione> resultList = query.getResultList();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cms.jpa.manager.itf.ModelDao_itf#loadChildren(org.cms.jpa.object.
	 * itf.Model_itf, org.cms.jpa.object.impl.TipoOggetto)
	 */
	@SuppressWarnings("unchecked")
	public List<Relazione> loadChildren(DominioModel_itf padre,
			TipoOggetto_itf tipo) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("select obj from Relazione obj where obj.padre_id=:idPadre and obj.tipopadre_id=:idTipoPadre  and obj.dominio.uid=:idDominio  order by obj.ordine asc");

		query.setParameter("idDominio", padre.getDominio().getUid());
		query.setParameter("idPadre", padre.getId());
		query.setParameter("idTipoPadre", tipo.getId());
		List<Relazione> resultList = query.getResultList();

		close(em);

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.ModelDao_itf#loadParents(javax.persistence.
	 * EntityManager, org.cms.jpa.object.itf.Model_itf,
	 * org.cms.jpa.object.impl.TipoOggetto)
	 */
	@Override
	public List<Relazione> loadParents(EntityManager em,
			DominioModel_itf figlio, TipoOggetto_itf tipo) {

		Query query = em
				.createQuery("select obj from Relazione obj where obj.figlio_id=:idFiglio and obj.tipofiglio_id=:idTipoFiglio order by obj.ordine asc");

		query.setParameter("idFiglio", figlio.getId());
		query.setParameter("idTipoFiglio", tipo.getId());

		@SuppressWarnings("unchecked")
		List<Relazione> resultList = query.getResultList();

		return resultList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cms.jpa.manager.itf.ModelDao_itf#loadParents(org.cms.jpa.object.itf
	 * .DominioModel_itf, org.cms.jpa.object.impl.TipoOggetto)
	 */
	@Override
	public List<Relazione> loadParents(DominioModel_itf figlio,
			TipoOggetto_itf tipo) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("select obj from Relazione obj where obj.figlio_id=:idFiglio and obj.tipofiglio_id=:idTipoFiglio order by obj.ordine asc");

		query.setParameter("idFiglio", figlio.getId());
		query.setParameter("idTipoFiglio", tipo.getId());

		@SuppressWarnings("unchecked")
		List<Relazione> resultList = query.getResultList();
		close(em);

		return resultList;

	}

	/**
	 * @param relazione
	 * @param tipo
	 * @param figlio
	 */
	public void setFiglio(Relazione relazione, TipoOggetto tipo,
			DominioModel_itf figlio) {
		relazione.setTipofiglio_id(tipo.getId());
		relazione.setFiglio_id(figlio.getId());
	}

	/**
	 * @param relazione
	 * @param tipo
	 * @param padre
	 */
	public void setPadre(Relazione relazione, TipoOggetto_itf tipo,
			DominioModel_itf padre) {
		relazione.setTipopadre_id(tipo.getId());
		relazione.setPadre_id(padre.getId());
		List<Relazione> figli = this.loadChildren(padre, tipo);
		int ordine = figli.size() + 1;
		relazione.setOrdine(ordine);
	}

	/**
	 * @param precedente
	 * @param successivo
	 * @param offset
	 * @throws AssoException
	 */
	private void swap(Relazione precedente, Relazione successivo)
			throws AssoException {

		AssoLogger.GetInstance().logInfo(
				"Scambio: " + precedente + " con " + successivo);

		int prec = precedente.getOrdine();
		int succ = successivo.getOrdine();

		if (prec == succ) {
			succ++;
		}

		precedente.setOrdine(succ);
		successivo.setOrdine(prec);

		List<Object> toBeSaved = new LinkedList<Object>();
		toBeSaved.add(precedente);
		toBeSaved.add(successivo);

		this.update(toBeSaved);

	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 * @throws AssoException
	 */
	public void su(String tipoPadreId, String idPadre, String tipoFiglioId,
			String idFiglio) throws AssoException {

		TipoOggetto tipoOggettoPadre = TipoOggettoDao
				.GetTipoOggetto(tipoPadreId);
		DominioModel_itf padre = (DominioModel_itf) this.findById(
				tipoOggettoPadre, idPadre);

		Iterator<Relazione> iterator = this.loadChildren(padre,
				tipoOggettoPadre).iterator();

		Relazione precedente = iterator.next();
		while (iterator.hasNext()) {
			Relazione corrente = iterator.next();
			String idCorrente = corrente.getFiglio_id();
			if (idCorrente.equals(idFiglio)) {
				swap(precedente, corrente);
				break;
			} else {
				precedente = corrente;
			}
		}

	}

	/**
	 * @param tipoPadreId
	 * @param idPadre
	 * @param tipoFiglioId
	 * @param idFiglio
	 * @throws AssoException
	 */
	public void giu(String tipoPadreId, String idPadre, String tipoFiglioId,
			String idFiglio) throws AssoException {

		TipoOggetto tipoOggettoPadre = TipoOggettoDao
				.GetTipoOggetto(tipoPadreId);
		DominioModel_itf padre = (DominioModel_itf) this.findById(
				tipoOggettoPadre, idPadre);
		Iterator<Relazione> iterator = this.loadChildren(padre,
				padre.getTipoOggetto()).iterator();

		while (iterator.hasNext()) {
			Relazione corrente = iterator.next();
			String idCorrente = corrente.getFiglio_id();
			if (idCorrente.equals(idFiglio)) {
				if (iterator.hasNext()) {
					Relazione successivo = iterator.next();
					swap(corrente, successivo);
				}
				break;
			}
		}
	}

	/**
	 * Ritorna il massimo ordine dei figli del padre.
	 * 
	 * @param oggetto
	 * @param tipoOggetto
	 * @return
	 */
	public int maxOrdine(DominioModel_itf oggetto, TipoOggetto tipoOggetto) {
		int ordine = 0;
		Iterator<Relazione> iterator = this.loadChildren(oggetto, tipoOggetto)
				.iterator();
		while (iterator.hasNext()) {
			Relazione model = iterator.next();
			ordine = Math.max(ordine, model.getOrdine());
		}
		return ordine;
	}

}
