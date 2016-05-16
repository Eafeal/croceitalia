package org.cms.controller.croceitalia;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cms.jpa.object.impl.Model;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Entity
@Table(name = "Tipo_mezzo")
@NamedQuery(name = "Tipo_mezzo.loadAll", query = "SELECT OBJECT(tipo) FROM Tipo_mezzo tipo")

public class Tipo_mezzo extends Model{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_mezzo", nullable = false, insertable = true, updatable = false)
	private Integer id_tipo_mezzo;

	private String descrizione;
	
	
	public Integer getId_tipo_mezzo() {
		return id_tipo_mezzo;
	}

	public void setId_tipo_mezzo(Integer id_tipo_mezzo) {
		this.id_tipo_mezzo = id_tipo_mezzo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

}
