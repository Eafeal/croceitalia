package org.cms.controller.croceitalia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.cms.jpa.object.impl.Model;

@Entity
@Table(name = "Patologia")
@NamedQuery(name = "Patologia.loadAll", query = "SELECT OBJECT(pato) FROM Patologia pato")
public class Patologia extends Model {

	/**
     * 
     */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_patologia", nullable = false, insertable = true, updatable = false)
	private Integer	id_patologia;

	private String	descrizione;

	public Integer getId_patologia() {

		return id_patologia;
	}

	public void setId_patologia(Integer id_patologia) {

		this.id_patologia = id_patologia;
	}

	public String getDescrizione() {

		return descrizione;
	}

	public void setDescrizione(String descrizione) {

		this.descrizione = descrizione;
	}

	@Override
	public String toString() {

		String answer = "patologia descrizione=" + descrizione;
		return answer;

	}
}
