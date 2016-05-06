package org.cms.jpa.dao.impl;

import it.asso.util.AssoException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.DominioModel_itf;
import org.cms.jpa.object.impl.Relazione;
import org.cms.jpa.object.impl.TipoOggetto;
import org.cms.jpa.tipi.impl.html.Pagina;
import org.springframework.stereotype.Repository;

@Repository("paginaDao")
public class PaginaDao extends DominioDao {

	public List<Pagina> loadAll(String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em.createQuery("select obj from " + getTableName()
				+ " obj where obj.dominio.uid=:dominioUid");
		query.setParameter("dominioUid", dominioUid);
		@SuppressWarnings("unchecked")
		List<Pagina> resultList = query.getResultList();
		close(em);
		return resultList;
	}

	/**
	 * @return
	 */
	public Class<?> getEntityClass() {
		return Pagina.class;
	}

	/**
	 * @param dominio
	 * @param pagina
	 * @return
	 * @throws AssoException
	 */
	private String getNewUid(String dominio_uid, String pagina_uid)
			throws AssoException {

		String new_uid = "Copy of " + pagina_uid;
		for (int j = 1; j < 100; j++) {
			try {
				this.findByUid(new_uid, dominio_uid);
			} catch (NoResultException error) {
				// L'oggetto NON esiste.
				// Il nome è buono
				return new_uid;
			}
			// L'oggetto esiste.
			new_uid = "Copy " + j + " of " + pagina_uid;
		}
		throw new AssoException(this.getClass().getSimpleName()
				+ ": Impossibile determinare un uid valido");
	}

	/**
	 * @param pagina
	 * @return
	 * @throws AssoException
	 */
	public Pagina clone(Pagina pagina, Pagina clone) throws AssoException {

		clone.setId(null);

		Dominio dominio = clone.getDominio();
		String uid = getNewUid(dominio.getUid(), pagina.getUid());
		clone.setUid(uid);

		TipoOggetto tipoOggetto = new TipoOggetto();
		tipoOggetto.setUid(pagina.getTipoOggetto().getUid());
		clone.setTipoOggetto(tipoOggetto);

		// Devo salvare il clone per avere il nuovo id
		this.save(clone);

		List<Object> relazioni = new LinkedList<Object>();
		Iterator<DominioModel_itf> iterator = pagina.getChildren().iterator();
		while (iterator.hasNext()) {
			DominioModel_itf figlio = iterator.next();
			// Devo creare una nuova relazione
			Relazione relazione = new Relazione(dominio);

			String tipoOggettoPadre_id = clone.getTipoOggetto().getId();
			String padre_id = clone.getId();
			relazione.setPadre(tipoOggettoPadre_id, padre_id);

			String tipoOggettoFiglio_id = figlio.getTipoOggetto().getId();
			String figlio_id = figlio.getId();
			relazione.setFiglio(tipoOggettoFiglio_id, figlio_id);

			relazioni.add(relazione);
		}

		this.save(relazioni);

		return clone;
	}
}
