package org.cms.controller.croceitalia;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.cms.jpa.object.impl.Model;

@Entity
@Table(name = "Struttura")
@NamedQuery(name = "Struttura.loadAll", query = "SELECT OBJECT(obj) FROM Struttura obj order by obj.nome")
public class Struttura extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_struttura", nullable = false, insertable = true, updatable = false)
	private Integer id_struttura;

	@OneToOne
	@JoinColumn(name = "fk_id_tipologia_struttura", nullable = false)
	private Tipologia_Struttura id_tipologia_struttura;
	
//	@Column(name = "fk_id_tipologia_struttura", nullable = false, insertable = true, updatable = false)
//	private Integer fk_id_tipologia_struttura;

	private String nome;
	//INDIRIZZO
	private String via;
	private String comune;
	private String cap;
	private String provincia;
	
	private String descrizione_breve;
	private String cod_regione;
	private String cod_asl;
	private String cod_struttura;
	private String telefono;
	private String email;
	
	public Struttura() {
		this.id_tipologia_struttura = new Tipologia_Struttura();
	}

	public Integer getId_struttura() {
		return id_struttura;
	}


	public void setId_struttura(Integer id_struttura) {
		this.id_struttura = id_struttura;
	}

	public Integer getFk_id_tipologia_struttura() {
		return id_tipologia_struttura.getId_tipologia_struttura();
	}

	public void setFk_id_tipologia_struttura(Integer fk_id_tipologia_struttura) {
		id_tipologia_struttura.setId_tipologia_struttura(fk_id_tipologia_struttura);
	}
	
	public String getNome() {
		if(nome == null) return "";
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getVia() {
		if(via == null) return "";
		return via;
	}

	public void setVia(String via) {
		this.via = via;
	}

	public String getComune() {
		if(comune == null) return "";
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
		this.provincia = provincia.toUpperCase();
	}

	public String getDescrizione_breve() {
		if(descrizione_breve == null) return "";
		return descrizione_breve;
	}

	public void setDescrizione_breve(String descrizione_breve) {
		this.descrizione_breve = descrizione_breve;
	}

	public String getCod_regione() {
		return cod_regione;
	}

	public void setCod_regione(String cod_regione) {
		this.cod_regione = cod_regione;
	}

	public String getCod_asl() {
		return cod_asl;
	}

	public void setCod_asl(String cod_asl) {
		this.cod_asl = cod_asl;
	}

	public String getCod_struttura() {
		return cod_struttura;
	}

	public void setCod_struttura(String cod_struttura) {
		this.cod_struttura = cod_struttura;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		if(email == null) return "";
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}





	
	
	

}
