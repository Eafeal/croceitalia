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
@Table(name = "Mezzo")
@NamedQuery(name = "Mezzo.loadAll", query = "SELECT OBJECT(obj) FROM Mezzo obj")
public class Mezzo extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_mezzo", nullable = false, insertable = true, updatable = false)
	private Integer		id_mezzo;

	@OneToOne
	@JoinColumn(name = "fk_tipo_mezzo", nullable = false)
	private Tipo_mezzo	tipo_mezzo;

	private String		targa;
	private Integer		num_posti;
	private String		descrizione;
	@Column(name = "QF", precision = 7, scale = 2)
	private BigDecimal	qf;

	@Column(name = "costo_km", precision = 7, scale = 2)
	private BigDecimal	costo_km;

	@Column(name = "franchigia_km", precision = 7, scale = 2)
	private BigDecimal	franchigia_km;

	private String		distretto;

	public Mezzo() {
        super();
		this.tipo_mezzo = new Tipo_mezzo();
	}

	public Integer getId_mezzo() {

		return id_mezzo;
	}

	public void setId_mezzo(Integer id_mezzo) {

		this.id_mezzo = id_mezzo;
	}

	public Integer getFk_tipo_mezzo() {

		// if (tipo_cliente == null) tipo_cliente = new Tipo_cliente();
		return tipo_mezzo.getId_tipo_mezzo();
	}

	public void setFk_tipo_mezzo(Integer fk_tipo_mezzo) {

		// if (tipo_cliente == null) tipo_cliente = new Tipo_cliente();
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
	public Integer getNum_posti() {

		if (num_posti == null)
			num_posti = 0;
		return num_posti;
	}

	public void setNum_posti(Integer num_posti) {

		if (num_posti == null)
			num_posti = 0;
		this.num_posti = num_posti;
	}

	public String getDescrizione() {

		return descrizione;
	}

	public void setDescrizione(String descrizione) {

		this.descrizione = descrizione;
	}

	public BigDecimal getQf() {

		if (qf == null)
			return BigDecimal.ZERO;
		return qf;
	}

	public void setQf(BigDecimal qf) {

		if (qf == null)
			qf = BigDecimal.ZERO;
		this.qf = qf;
	}

	public void setQf(String qf) {

		this.qf = fromStringToBigDecimal(qf);

	}

	public BigDecimal getCosto_km() {

		if (costo_km == null)
			return BigDecimal.ZERO;
		return costo_km;
	}

	public void setCosto_km(BigDecimal costo_km) {

		if (costo_km == null)
			costo_km = BigDecimal.ZERO;
		this.costo_km = costo_km;
	}

	public void setCosto_km(String costo_km) {

		this.costo_km = fromStringToBigDecimal(costo_km);

	}

	public BigDecimal getFranchigia_km() {

		if (franchigia_km == null)
			return BigDecimal.ZERO;
		return franchigia_km;
	}

	public void setFranchigia_km(BigDecimal franchigia_km) {

		if (franchigia_km == null)
			franchigia_km = BigDecimal.ZERO;
		this.franchigia_km = franchigia_km;
	}

	public void setFranchigia_km(String franchigia_km) {

		this.franchigia_km = fromStringToBigDecimal(franchigia_km);

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
