package org.cms.component.soggetti;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.cms.jpa.object.impl.Model;

/**
 * @author paolo
 */
@Entity
@Table(name = "soggetto")
@NamedQuery(name = "Soggetto.loadAll", query = "SELECT OBJECT(soggetto) FROM Soggetto soggetto order by soggetto.cognome, soggetto.nome  ")
public class Soggetto extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String id;

	private String cognome;

	private String nome;

	private String sesso;
	private int nascita;

	/**
	 * 
	 */
	public Soggetto() {
		super();
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCognome() {
		return this.cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSesso() {
		return this.sesso;
	}

	public void setSesso(String sesso) {
		this.sesso = sesso;
	}

	public int getNascita() {
		return this.nascita;
	}

	public void setNascita(int nascita) {
		this.nascita = nascita;
	}

	@Override
	public String toString() {
		return getNominativo();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.object.impl.Model#compareTo(org.cms.jpa.object.itf.Model_itf)
	 */
	public int compareTo(Soggetto other) {

		return this.getNominativo().compareTo(other.getNominativo());
	}

	/**
	 * @return
	 */
	private String getNominativo() {

		return "" + cognome + " " + nome;

	}

}
