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


@Entity
@Table(name = "Banca")
@NamedQuery(name = "Banca.loadAll", query = "SELECT OBJECT(obj) FROM Banca obj")

public class Banca extends Model{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_banca", nullable = false, insertable = true, updatable = false)
	
	private Integer id_banca;
	private String nome;
	private String agenzia;
	private String via;
	private String comune;
	private String cap;
	private String provincia;
	private String bic_swift;
	private String iban;
	
	public Integer getId_banca() {
		return id_banca;
	}
	public void setId_banca(Integer id_banca) {
		this.id_banca = id_banca;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAgenzia() {
		return agenzia;
	}
	public void setAgenzia(String agenzia) {
		this.agenzia = agenzia;
	}
	public String getVia() {
		return via;
	}
	public void setVia(String via) {
		this.via = via;
	}
	public String getComune() {
		return comune;
	}
	public void setComune(String comune) {
		this.comune = comune;
	}
	public String getCap() {
		return cap;
	}
	public void setCap(String cap) {
		this.cap = cap;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getBic_swift() {
		return bic_swift;
	}
	public void setBic_swift(String bic_swift) {
		this.bic_swift = bic_swift;
	}
	public String getIban() {
		return iban;
	}
	public void setIban(String iban) {
		this.iban = iban;
	}

}
