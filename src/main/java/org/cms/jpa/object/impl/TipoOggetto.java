package org.cms.jpa.object.impl;

import it.asso.util.AssoException;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;


@Entity
@Table(name = "TipoOggetto")
public class TipoOggetto implements TipoOggetto_itf {

	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String uid;
	private String tipo;

	private String descrizione;

	private String className;
	private String path;

	private String editPath;

	/**
	 * 
	 */
	@ManyToMany
	@JoinTable(name = "tipioggettoxdomini", joinColumns = @JoinColumn(name = "tipooggetto_uid", referencedColumnName = "uid"), inverseJoinColumns = @JoinColumn(name = "dominio_uid", referencedColumnName = "uid"))
	private Collection<Dominio> domini;

	/**
	 * 
	 */
	public TipoOggetto() {
		super();
	}

	public String getClassName() {
		return className;
	}

	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * @return the domini
	 */
	public Collection<Dominio> getDomini() {
		return this.domini;
	}

	public String getEditPath() {
		return editPath;
	}

	public String getId() {
		return this.uid;
	}

	public String getPath() {
		return path;
	}

	public String getSimpleName() throws ClassNotFoundException {
		Class<?> classe = Class.forName(className);
		return classe.getSimpleName();
	}

	public String getTemplate() {

		String template = path + "/" + tipo.toLowerCase() + ".ftl";
		// template = "/quellidellunedi/" + path + "/" + tipo.toLowerCase() +
		// ".ftl";

		return template;
	}

	public String getTipo() {
		return tipo;
	}

	public Class<?> getTypeClass() throws AssoException {

		try {
			Class<?> classe = Class.forName(className);
			return classe;
		} catch (ClassNotFoundException error) {
			throw new AssoException(error);
		}
	}

	public String getUid() {
		return this.uid;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	/**
	 * @param domini
	 *            the domini to set
	 */
	public void setDomini(Collection<Dominio> domini) {
		this.domini = domini;
	}

	public void setEditPath(String editPath) {
		this.editPath = editPath;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		String answer = this.uid + "/" + this.tipo;
		return answer;
	}

}
