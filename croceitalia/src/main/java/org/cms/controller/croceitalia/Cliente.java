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
import org.cms.login.SoggettoUtente;


@Entity
@Table(name = "Cliente")
@NamedQuery(name = "Cliente.loadAll", query = "SELECT OBJECT(obj) FROM Cliente obj order by obj.ragione_sociale")
public class Cliente extends Model{
	
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", nullable = false, insertable = true, updatable = false)
	private Integer id_cliente;
	
	//@Column(name = "fk_tipo_cliente", nullable = false, insertable = true, updatable = false)
	//private Integer fk_tipo_cliente;
	
	@OneToOne
	@JoinColumn(name = "fk_tipo_cliente", nullable = false)
	private Tipo_cliente tipo_cliente;

	private String ragione_sociale;
	private String telefono1;
	private String telefono2;
	private String email;
	private String p_iva;
	private String via;
	private String comune;
	private String cap;
	private String provincia;
	private String cf;
	private String qf;
	
	public Cliente() {
		this.tipo_cliente = new Tipo_cliente();
	}
	
	public Integer getId_cliente() {
		return id_cliente;
	}
	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}
	public Integer getFk_tipo_cliente() {
		//if (tipo_cliente == null) tipo_cliente = new Tipo_cliente();
		return tipo_cliente.getId_tipo_cliente();
	}
	public void setFk_tipo_cliente(Integer fk_tipo_cliente) {
		//if (tipo_cliente == null) tipo_cliente = new Tipo_cliente();		
		tipo_cliente.setId_tipo_cliente(fk_tipo_cliente);
	}
	public String getRagione_sociale() {
		return ragione_sociale;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setRagione_sociale(String ragione_sociale) {
		this.ragione_sociale = ragione_sociale;
	}
	public String getP_iva() {
		return p_iva;
	}
	public void setP_iva(String p_iva) {
		this.p_iva = p_iva;
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
	public String getCf() {
		if(cf == null) return "";
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	public String getQf() {
		if(qf == null) return "";
		return qf;
	}
	public void setQf(String qf) {
		this.qf = qf.replace(",", ".");
	}

	public Tipo_cliente getTipo_cliente() {
		return tipo_cliente;
	}
	
	public void setTipo_cliente(Tipo_cliente tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}
	
}
