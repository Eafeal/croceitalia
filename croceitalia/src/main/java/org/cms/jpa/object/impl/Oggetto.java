package org.cms.jpa.object.impl;

import it.asso.freemarker.LangFactory;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author consdonzellipaolo
 * 
 */
@Entity
@Table(name = "Oggetto")
public class Oggetto extends DominioModel {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String uid;

	private String title = "";

	private String description = "description";

	private String lang = LangFactory.IT;
	private String state = WORKINPROGRESS;

	private String code = "";

	/**
	 * 
	 */
	@OneToOne
	@JoinColumn(name = "tipooggetto_uid", nullable = false)
	protected TipoOggetto tipoOggetto;

	@OneToOne
	@JoinColumn(name = "dominio_uid", nullable = false)
	protected Dominio dominio;

	/**
	 * 
	 */
	public Oggetto() {

		super();
	}

	/**
	 * @return the code
	 */
	public String getCode() {

		return this.code;
	}

	public String getDescription() {

		return description;
	}

	public Dominio getDominio() {

		return dominio;
	}

	public String getId() {

		return id;
	}

	public String getLang() {

		return lang;
	}

	//@Override
	public String getState() {

		return state;
	}

	public TipoOggetto getTipoOggetto() {

		return tipoOggetto;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {

		return this.title;
	}

	public String getUid() {

		return uid;
	}

	//@Override
	public boolean inLavorazione() {

		return state.equals(WORKINPROGRESS);
	}

	//@Override
	@PrePersist
	public void onPrePersist() {

		if (uid == null) {
			this.uid = "Name";
		}
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {

		this.code = code;
	}

	public void setDescription(String description) {

		this.description = description;
	}

	public void setDominio(Dominio dominio) {

		this.dominio = dominio;
	}

	public void setId(String id) {

		this.id = id;
	}

	public void setLang(String lang) {

		this.lang = lang;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {

		this.state = state;
	}

	public void setTipoOggetto(TipoOggetto tipoOggetto) {

		this.tipoOggetto = tipoOggetto;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {

		this.title = title;
	}

	public void setUid(String uid) {

		this.uid = uid;
	}

	//@Override
	public String toString() {

		TipoOggetto tipoOggetto = getTipoOggetto();
		String tipo;
		if (tipoOggetto != null) {
			tipo = tipoOggetto.getTipo();
		} else {
			tipo = "/null";
		}
		return tipo + "/" + uid;
	}
}
