package org.cms.controller.croceitalia;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;

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
@Table(name = "Cliente")
@NamedQuery(name = "Cliente.loadAll", query = "SELECT OBJECT(obj) FROM Cliente obj order by obj.ragione_sociale")
public class Cliente extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente", nullable = false, insertable = true, updatable = false)
	private Integer			id_cliente;

	@OneToOne
	@JoinColumn(name = "fk_tipo_cliente", nullable = false)
	private Tipo_cliente	tipo_cliente;

	private String			ragione_sociale;
	private String			telefono1;
	private String			telefono2;
	private String			email;
	@Column(name = "p_iva") 	
	private String			partitaIva;
	private String			via;
	private String			comune;
	private String			cap;
	private String			provincia;
	@Column(name = "cf") 	
	private String			codiceFiscale;
	@Column(name = "qf", precision = 7, scale = 2)
	private BigDecimal		quotaFissa;

	
	
	
	
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

		return tipo_cliente.getId_tipo_cliente();
	}

	public void setFk_tipo_cliente(Integer fk_tipo_cliente) {

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

	public String getPartitaIva() {

		return partitaIva;
	}

	public void setPartitaIva(String p_iva) {

		this.partitaIva = p_iva;
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

	public String getCodiceFiscale() {

		if (codiceFiscale == null)
			return "";
		return codiceFiscale;
	}

	public void setCodiceFiscale(String cf) {

		this.codiceFiscale = cf;
	}

	public BigDecimal getQuotaFissa() {

		if (quotaFissa == null)
			return BigDecimal.ZERO;
		return quotaFissa;
	}

	public void setQuotaFissa(BigDecimal qf) {
		if (qf == null)
			qf = BigDecimal.ZERO;
		this.quotaFissa = qf;
	}

	public void setQuotaFissa(String qf) {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();

		String pattern = "#.##0,0#";
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setParseBigDecimal(true);

		try {
			this.quotaFissa = (BigDecimal) decimalFormat.parse(qf);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Tipo_cliente getTipo_cliente() {

		return tipo_cliente;
	}

	public void setTipo_cliente(Tipo_cliente tipo_cliente) {

		this.tipo_cliente = tipo_cliente;
	}

}
