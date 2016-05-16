package org.cms.controller.croceitalia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cms.jpa.object.impl.Model;

@Entity
@Table(name = "Paziente")
@NamedQuery(name = "Paziente.loadAll", query = "SELECT OBJECT(obj) FROM Paziente obj order by obj.cognome, obj.nome")
public class Paziente extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_paziente", nullable = false, insertable = true, updatable = false)
	private Integer		id_paziente;

	@OneToOne
	@JoinColumn(name = "fk_id_patologia", nullable = false)
	private Patologia	id_patologia;

	private String		nome;
	private String		cognome;
	private String		telefono1;
	private String		telefono2;
	private String		sesso;

	@Temporal(TemporalType.DATE)
	private Date		data_nascita;

	private String		via;
	private String		comune;
	private String		cap;
	private String		provincia;

	public Paziente() {

		this.id_patologia = new Patologia();
	}

	public Integer getFk_id_patologia() {

		return id_patologia.getId_patologia();
	}

	public void setFk_id_patologia(Integer fk_id_patologia) {

		if (this.id_patologia == null)
			this.id_patologia = new Patologia();
		id_patologia.setId_patologia(fk_id_patologia);
	}

	/**
	 * @return the id_paziente
	 */
	public Integer getId_paziente() {

		return id_paziente;
	}

	/**
	 * @param id_paziente
	 *            the id_paziente to set
	 */
	public void setId_paziente(Integer id_paziente) {

		this.id_paziente = id_paziente;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {

		if (nome == null)
			return "";
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * @return the cognome
	 */
	public String getCognome() {

		if (cognome == null)
			return "";
		return cognome;
	}

	/**
	 * @param cognome
	 *            the cognome to set
	 */
	public void setCognome(String cognome) {

		this.cognome = cognome;
	}

	/**
	 * @return the telefono1
	 */
	public String getTelefono1() {

		if (telefono1 == null)
			return "";
		return telefono1;
	}

	/**
	 * @param telefono1
	 *            the telefono1 to set
	 */
	public void setTelefono1(String telefono1) {

		this.telefono1 = telefono1;
	}

	/**
	 * @return the telefono2
	 */
	public String getTelefono2() {

		if (telefono2 == null)
			return "";
		return telefono2;
	}

	/**
	 * @param telefono2
	 *            the telefono2 to set
	 */
	public void setTelefono2(String telefono2) {

		this.telefono2 = telefono2;
	}

	/**
	 * @return the sesso
	 */
	public String getSesso() {

		if (sesso == null)
			return "";
		return sesso;
	}

	/**
	 * @param sesso
	 *            the sesso to set
	 */
	public void setSesso(String sesso) {

		this.sesso = sesso;
	}

	/**
	 * @return the data_nascita
	 */
	public Date getData_nascita() {

		// if (data_nascita == null)
		// return "";
		return data_nascita;
	}

	/**
	 * @param data_nascita
	 *            the data_nascita to set
	 */
	public void setData_nascita(Date data_nascita) {

		this.data_nascita = data_nascita;
	}

	/**
	 * @return the via
	 */
	public String getVia() {

		if (via == null)
			return "";
		return via;
	}

	/**
	 * @param via
	 *            the via to set
	 */
	public void setVia(String via) {

		this.via = via;
	}

	/**
	 * @return the comune
	 */
	public String getComune() {

		if (comune == null)
			return "";
		return comune;
	}

	/**
	 * @param comune
	 *            the comune to set
	 */
	public void setComune(String comune) {

		this.comune = comune;
	}

	/**
	 * @return the cap
	 */
	public String getCap() {

		if (cap == null)
			return "";
		return cap;
	}

	/**
	 * @param cap
	 *            the cap to set
	 */
	public void setCap(String cap) {

		this.cap = cap;
	}

	/**
	 * @return the provincia
	 */
	public String getProvincia() {

		if (provincia == null)
			return "";
		return provincia;
	}

	/**
	 * @param provincia
	 *            the provincia to set
	 */
	public void setProvincia(String provincia) {

		this.provincia = provincia.toUpperCase();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String answer = "Paziente nome=" + nome + " cognome=" + cognome;
		return answer;

	}
}
