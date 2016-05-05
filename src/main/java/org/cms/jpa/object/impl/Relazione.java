package org.cms.jpa.object.impl;

import it.asso.util.AssoException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author consdonzellipaolo
 * 
 */
@Entity
@Table(name = "Relazione")
@NamedQuery(name = "Relazione.loadAll", query = "SELECT OBJECT(obj) FROM Relazione obj")
public class Relazione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String uid;

	private String tipopadre_id;
	private String padre_id;

	private String tipofiglio_id;
	private String figlio_id;

	private int ordine;

	@OneToOne
	@JoinColumn(name = "dominio_uid", nullable = false)
	private Dominio dominio;

	/**
	 * 
	 */
	public Relazione() {
		super();
	}

	public Relazione(Dominio unDominio) {
		super();
		this.dominio = unDominio;
	}

	public Dominio getDominio() {
		return dominio;
	}

	public String getFiglio_id() {
		return figlio_id;
	}

	public int getOrdine() {
		return ordine;
	}

	public String getPadre_id() {
		return padre_id;
	}

	public String getTipofiglio_id() {
		return tipofiglio_id;
	}

	public String getTipopadre_id() {
		return tipopadre_id;
	}

	public String getUid() {
		return uid;
	}

	@PrePersist
	public void onPrePersist() throws AssoException {
		if (this.dominio == null) {
			throw new AssoException("Dominio non valido per l'oggetto: " + this);
		}
	}

	public void setDominio(Dominio dominio) {
		this.dominio = dominio;
	}

	public void setFiglio_id(String figlio_id) {
		this.figlio_id = figlio_id;
	}

	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}

	public void setPadre_id(String padre_id) {
		this.padre_id = padre_id;
	}

	public void setTipofiglio_id(String tipofiglio_id) {
		this.tipofiglio_id = tipofiglio_id;
	}

	public void setTipopadre_id(String tipopadre_id) {
		this.tipopadre_id = tipopadre_id;
	}

	@Override
	public String toString() {

		return tipopadre_id + "/" + padre_id + " ==> " + tipofiglio_id + "/" + figlio_id;
	}

	/**
	 * @param tipoPadre_id
	 * @param padre_id
	 */
	public void setPadre(String tipoPadre_id, String padre_id) {
		this.setTipopadre_id(tipoPadre_id);
		this.setPadre_id(padre_id);

		// List<Model_itf> figli = padre.getChildren();
		// int ordine = figli.size() + 1;
		// this.setOrdine(ordine);
	}

	/**
	 * @param tipo_id
	 * @param figlio_id
	 */
	public void setFiglio(String tipo_id, String figlio_id) {
		setTipofiglio_id(tipo_id);
		setFiglio_id(figlio_id);
	}

}
