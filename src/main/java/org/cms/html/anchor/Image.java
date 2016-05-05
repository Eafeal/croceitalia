/**
 * 
 */
package org.cms.html.anchor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.DominioModel;
import org.cms.jpa.object.impl.TipoOggetto;

/**
 * @author consdonzellipaolo
 * 
 */
@Entity
@Table(name = "Anchor")
public class Image extends DominioModel {

	@Id
	private String id;

	private String title;

	private String uid;
	private String description;

	private String url;
	private String style;

	/**
	 * 
	 */
	private String lang;

	private String state;

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
	public Image() {
		super();
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

	@Override
	public String getState() {
		return state;
	}

	/**
	 * @return the style
	 */
	public String getStyle() {
		return this.style;
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

	public String getUrl() {
		return this.url;
	}

	@Override
	public boolean inLavorazione() {
		return state.equals(WORKINPROGRESS);
	}

	@Override
	@PrePersist
	public void onPrePersist() {
		if (title == null) {
			this.title = "Title";
		}
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

	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param style
	 *            the style to set
	 */
	public void setStyle(String style) {
		this.style = style;
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

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
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
