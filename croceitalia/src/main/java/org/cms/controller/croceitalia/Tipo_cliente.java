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
@Table(name = "Tipo_cliente")
@NamedQuery(name = "Tipo_cliente.loadAll", query = "SELECT OBJECT(tipo) FROM Tipo_cliente tipo")
public class Tipo_cliente extends Model {

	/**
     * 
     */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipo_cliente", nullable = false, insertable = true, updatable = false)
	private Integer	id_tipo_cliente;

	private String	descrizione;

	public Integer getId_tipo_cliente() {

		return id_tipo_cliente;
	}

	public void setId_tipo_cliente(Integer id_tipo_cliente) {

		this.id_tipo_cliente = id_tipo_cliente;
	}

	public String getDescrizione() {

		return descrizione;
	}

	public void setDescrizione(String descrizione) {

		this.descrizione = descrizione;
	}

}
