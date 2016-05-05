package org.cms.jpa.object.impl;

import it.asso.util.Dominio_itf;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Dominio")
@NamedQuery(name = "Dominio.loadAll", query = "SELECT OBJECT(dom) FROM Dominio dom")
public class Dominio implements Dominio_itf {

	/**
	 * 
	 */
	@Id
	private String uid;

	private String descrizione;
	private String url;
	private String pathCSS;

	/**
	 * 
	 */
	public Dominio() {

		super();
	}

	public String getDescrizione() {

		return descrizione;
	}

	public String getPathCSS() {

		return pathCSS;
	}

	public String getUrl() {

		return url;
	}

	public void setDescrizione(String descrizione) {

		this.descrizione = descrizione;
	}

	public void setPathCSS(String pathCSS) {

		this.pathCSS = pathCSS;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	@Override
	public String toString() {

		return uid;
	}

	public String getUid() {

		return uid;
	}

	public void setUid(String uid) {

		this.uid = uid;
	}

}
