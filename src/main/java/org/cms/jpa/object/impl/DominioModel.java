package org.cms.jpa.object.impl;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.ModelUser;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;

/**
 * @author consdonzellipaolo
 * 
 *         Questa classe astratta raccoglie in se' tutti gli oggetti che
 *         concorrono a comporre il dominio, ossia che hanno relazioni
 *         padre/figlio tra loro
 */
public abstract class DominioModel implements DominioModel_itf {

	/**
	 * 
	 */
	@Transient
	protected List<DominioModel_itf> children;

	/*
	 * (non-Javadoc)
	 */
	public DominioModel_itf getChild(String uid) throws AssoException {

		Iterator<DominioModel_itf> iterator = getChildren().iterator();
		while (iterator.hasNext()) {
			DominioModel_itf model = iterator.next();
			if (model.getUid().equals(uid)) {
				return model;
			}
		}
		return null;
	}

	/**
	 * @return
	 * @throws AssoException
	 */
	public List<DominioModel_itf> getChildren() throws AssoException {

		if (children != null) {
			return children;
		}
		children = new LinkedList<DominioModel_itf>();

		try {
			RelazioneDao_itf dao = ModelLoader.get();

			List<String> states = ModelUser.getStates();

			Iterator<Relazione> iterator = dao.loadChildren(this, getTipoOggetto()).iterator();
			while (iterator.hasNext()) {
				Relazione relazione = iterator.next();

				String tipofiglio_id = relazione.getTipofiglio_id();
				TipoOggetto tipoOggettoFiglio = (TipoOggetto) dao.findById(TipoOggetto.class, tipofiglio_id);
				String className = tipoOggettoFiglio.getClassName();

				Class<?> classe = Class.forName(className);
				String figlio_id = relazione.getFiglio_id();
				DominioModel_itf figlio = (DominioModel_itf) dao.findById(classe, figlio_id);
				String state = figlio.getState();
				int indexOf = states.indexOf(state);
				if (indexOf >= 0) {
					// L'utente loggato puo' vedere l'oggetto in quello stato
					children.add(figlio);
				}
			}

		} catch (Throwable e) {
			AssoLogger.GetInstance().logError(e);
		}
		return children;
	}

	/**
	 * @param tipo_uid
	 * @return
	 * @throws AssoException
	 */
	public DominioModel_itf getChildType(String tipo_uid) throws AssoException {

		Iterator<DominioModel_itf> iterator = getChildren().iterator();
		while (iterator.hasNext()) {
			DominioModel_itf model = iterator.next();
			if (model.getTipoOggetto().getId().equals(tipo_uid)) {
				return model;
			}
		}
		return null;
	}

	public String getTemplate() {

		return getTipoOggetto().getTemplate();
	}

	/**
	 * @return
	 * @throws AssoException
	 */
	public boolean hasChildren() throws AssoException {

		return !getChildren().isEmpty();
	}

	public boolean inLavorazione() {

		return getState().equals(WORKINPROGRESS);
	}

	@PostLoad
	protected void onPostLoad() {

	}

	@PostPersist
	protected void onPostPersist() {

	}

	@PostRemove
	protected void onPostRemove() {

	}

	@PostUpdate
	protected void onPostUpdate() {

	}

	@PrePersist
	protected void onPrePersist() throws AssoException {

	}

	@PreRemove
	protected void onPreRemove() {

	}

	@PreUpdate
	protected void onPreUpdate() throws AssoException {

	}

	/**
	 * @param model
	 * @throws AssoException
	 */
	public void loadAllComponents() throws AssoException {

		List<DominioModel_itf> components = new LinkedList<DominioModel_itf>();
		Iterator<DominioModel_itf> iterator = getChildren().iterator();
		while (iterator.hasNext()) {
			DominioModel model = (DominioModel) iterator.next();
			model.loadAllComponents(components);
		}
	}

	/**
	 * @param components
	 * @throws AssoException
	 */
	private void loadAllComponents(List<DominioModel_itf> components) throws AssoException {

		// System.out.println("model=" + this);
		if (components.contains(this)) {
			// Sono già stato caricato. Evito la ricorsione
		} else {
			// Non sono stato caricato
			components.add(this);
			Iterator<DominioModel_itf> iterator = getChildren().iterator();
			while (iterator.hasNext()) {
				Model model = (Model) iterator.next();
				// System.out.println("model=" + model);
				// model.loadAllComponents(components);
			}
		}
	}

	/**
	 * @return
	 * @throws AssoException
	 */
	public DominioModel_itf duplicate() throws AssoException {

		DominioModel_itf clone;
		try {
			clone = (DominioModel_itf) this.clone();

			return clone;

		} catch (CloneNotSupportedException error) {

			throw new AssoException(error);
		}

	}

	@Override
	public int compareTo(Model_itf obj) {

		return this.toString().compareTo(obj.toString());

	}

}
