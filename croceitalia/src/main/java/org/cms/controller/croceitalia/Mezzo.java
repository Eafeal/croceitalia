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

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Entity
@Table(name = "Mezzo")
@NamedQuery(name = "Mezzo.loadAll", query = "SELECT OBJECT(obj) FROM Mezzo obj")
public class Mezzo extends Model{
	
	/**
     * 
     */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mezzo", nullable = false, insertable = true, updatable = false)
	private Integer id_mezzo;
	
	@OneToOne
	@JoinColumn(name = "fk_tipo_mezzo", nullable = false)
	private Tipo_mezzo tipo_mezzo;
	
	private String targa;
	private String num_posti;
	private String descrizione;
	private String qf;
	private String costo_km;
	private String franchigia_km;
	private String distretto;
	
	public Mezzo() {
		this.tipo_mezzo = new Tipo_mezzo();
	}
	
	public Integer getId_mezzo() {
		return id_mezzo;
	}
	public void setId_mezzo(Integer id_mezzo) {
		this.id_mezzo = id_mezzo;
	}
	public Integer getFk_tipo_mezzo() {
		//if (tipo_cliente == null) tipo_cliente = new Tipo_cliente();
		return tipo_mezzo.getId_tipo_mezzo();
	}
	public void setFk_tipo_mezzo(Integer fk_tipo_mezzo) {
		//if (tipo_cliente == null) tipo_cliente = new Tipo_cliente();		
		tipo_mezzo.setId_tipo_mezzo(fk_tipo_mezzo);
	}
	public String getTarga() {
		return targa;
	}
	public void setTarga(String targa) {
		this.targa = targa;
	}

	/**
	 * @return the num_posti
	 */
	public String getNum_posti() {
		if(num_posti == null) return "";
		return num_posti;
	}
	public void setNum_posti(String num_posti) {
		this.num_posti = num_posti;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public String getQf() {
		return qf;
	}
	public void setQf(String qf) {
		
		this.qf = qf.replace(",", ".");
	}
	public String getCosto_km() {
		return costo_km;
	}
	public void setCosto_km(String costo_km) {
		this.costo_km = costo_km.replace(",", ".");
	}
	public String getFranchigia_km() {
		return franchigia_km;
	}
	public void setFranchigia_km(String franchigia_km) {
		this.franchigia_km = franchigia_km.replace(",", ".");
	}
	public String getDistretto() {
		return distretto;
	}
	public void setDistretto(String distretto) {
		this.distretto = distretto;
	}

	public Tipo_mezzo getTipo_mezzo() {
		return tipo_mezzo;
	}

	public void setTipo_mezzo(Tipo_mezzo tipo_mezzo) {
		this.tipo_mezzo = tipo_mezzo;
	}
	
	

}
