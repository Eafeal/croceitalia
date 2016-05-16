package org.cms.controller.croceitalia;

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
@Table(name = "Tipologia_Struttura")
@NamedQuery(name = "Tipologia_Struttura.loadAll", query = "SELECT OBJECT(tipologia) FROM Tipologia_Struttura tipologia")
public class Tipologia_Struttura extends Model {

	/**
     * 
     */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_tipologia_struttura", nullable = false, insertable = true, updatable = false)
	private Integer id_tipologia_struttura;

	private String descrizione;
	

	public Integer getId_tipologia_struttura() {
		return id_tipologia_struttura;
	}


	public void setId_tipologia_struttura(Integer id_tipologia_struttura) {
		this.id_tipologia_struttura = id_tipologia_struttura;
	}


	public String getDescrizione() {
		if(descrizione == null) return "";
		return descrizione;
	}


	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}


	@Override
	public String toString() {

		String answer = "tipologia_struttura descrizione="+descrizione;
		return answer;

	}
}
