package org.cms.jpa.dao.impl;

import it.asso.util.AssoException;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.object.impl.AssoDao_itf;
import org.cms.jpa.object.impl.ModelLoader;
import org.cms.jpa.object.impl.TipoOggetto;
import org.springframework.stereotype.Repository;

/**
 * @author consdonzellipaolo
 * 
 */
@Repository("tipoOggettoDao")
public class TipoOggettoDao extends AssoDao {

	/**
	 * 
	 */
	public TipoOggettoDao() {
		super();
	}

	/**
	 * 
	 */
	private static List<TipoOggetto> TipiOggetto;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.manager.itf.AssoDao_itf#getEntityClass()
	 */
	@Override
	public Class<?> getEntityClass() {
		return TipoOggetto.class;
	}

	/**
	 * @param dominio_uid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<TipoOggetto> loadAll() {

		AssoDao_itf dao = ModelLoader.get();
		EntityManager em = dao.getEntityManager();
		Query query = em
				.createQuery("SELECT OBJECT(obj) FROM TipoOggetto obj order by obj.tipo asc");
		TipiOggetto = query.getResultList();
		dao.close(em);

		return TipiOggetto;
	}

	/**
	 * @return
	 */
	public static List<TipoOggetto> GetListaTipiOggetti() {
		if (TipiOggetto == null) {
			loadAll();
		}
		return TipiOggetto;
	}

	/**
	 * Questo metodo ritorna la lista dei tipi oggetti interpretati dalla classe
	 * passata come argumento. Ad Esempio, se l'argomento passato è
	 * Oggetto.class allora il metodo ritorna: ColBanner DettaglioPartitaQDL Div
	 * DivRicercaMarchio ElencoPartite ElencoScores ElencoSquadre Lingua MenuTop
	 * MenuVertical Testata.
	 * 
	 * Sono escludi tutti gli oggetti che sono "realizzati" con classi diverse:
	 * Anchor, Image, Dominio
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 */
	public static List<TipoOggetto> GetListaTipiOggettiClasse(Class<?> classe) {
		if (TipiOggetto == null) {
			loadAll();
		}

		List<TipoOggetto> list = new LinkedList<TipoOggetto>();
		Iterator<TipoOggetto> iterator = TipiOggetto.iterator();
		while (iterator.hasNext()) {
			TipoOggetto tipoOggetto = iterator.next();
			String className = tipoOggetto.getClassName();
			try {
				if (classe == Class.forName(className)) {
					list.add(tipoOggetto);
				}
			} catch (ClassNotFoundException error) {
				// Do Nothing
			}
		}

		return list;
	}

	/**
	 * @param tipoOggetto_uid
	 * @return
	 * @throws AssoException
	 */
	public static TipoOggetto GetTipoOggetto(String tipoOggetto_uid)
			throws AssoException {

		Iterator<TipoOggetto> iterator = GetListaTipiOggetti().iterator();
		while (iterator.hasNext()) {
			TipoOggetto tipoOggetto = iterator.next();
			if (tipoOggetto.getUid().equals(tipoOggetto_uid)) {
				return tipoOggetto;
			}
		}
		throw new AssoException("TipoOggetto inesistente: " + tipoOggetto_uid);
	}

}
