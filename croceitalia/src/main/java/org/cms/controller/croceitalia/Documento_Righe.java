package org.cms.controller.croceitalia;

import java.math.BigDecimal;
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

@Entity
@Table(name = "Documento_Righe")
@NamedQuery(name = "Documento_Righe.loadAll", query = "SELECT OBJECT(obj) FROM Documento_Righe obj")
public class Documento_Righe extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento_righe", nullable = false, insertable = true, updatable = false)
	private Integer		id_documento_righe;

	private Integer		fk_id_documento_testata;

	@OneToOne
	@JoinColumn(name = "fk_id_paziente", nullable = false)
	private Paziente	paziente;

	@OneToOne
	@JoinColumn(name = "fk_id_struttura", nullable = false)
	private Struttura	struttura;

	// @ManyToOne(optional = false)
	// @JoinColumn(name = "documento_testata", referencedColumnName =
	// "fk_id_documento_testata")
	// @ManyToOne(optional = false)
	// @JoinColumn(name = "fk_id_documento_testata")
	// @ManyToOne (cascade=CascadeType.ALL)
	// @JoinColumn(name = "fk_id_documento_testata")

	private Integer		num_sedute;
	private String		mese;

	private Integer		km_totali;
	private Integer		km_percorso;

	private String		percorso;

	private String		p_partenza;
	private String		p_arrivo;

	private Integer		ora_sosta;

	@Column(precision = 7, scale = 2)
	private BigDecimal	quota_fissa;

	@Column(precision = 7, scale = 2)
	private BigDecimal	diritto_uscita;

	@Column(precision = 7, scale = 2)
	private BigDecimal	importo;

	private String		usercrea;
	private String		userultv;

	@Temporal(TemporalType.DATE)
	private Date		datacrea;
	@Temporal(TemporalType.DATE)
	private Date		dataultv;

	public Documento_Righe() {

		super();
		this.paziente = new Paziente();
		this.struttura = new Struttura();

		/*
		 * usercrea = "Axel"; userultv = "Axel";
		 * 
		 * datacrea = new Date(); dataultv = new Date();
		 */

		p_arrivo = "";
		p_partenza = "";
		percorso = "";

		// TODO Auto-generated constructor stub
	}

	public Documento_Righe(Integer id_documento_righe, Integer fk_id_documento_testata, /* Integer fk_id_paziente, */
	Integer num_sedute, String mese, Integer km_totali, Integer km_percorso, String percorso, String p_partenza,
			String p_arrivo, Integer ora_sosta, BigDecimal quota_fissa, String diritto_uscita, BigDecimal importo) {

		super();
		this.id_documento_righe = id_documento_righe;
		this.fk_id_documento_testata = fk_id_documento_testata;
		this.paziente = new Paziente();
		this.struttura = new Struttura();
		this.num_sedute = num_sedute;
		this.mese = mese;
		this.km_totali = km_totali;
		this.km_percorso = km_percorso;
		this.percorso = percorso;
		this.p_partenza = p_partenza;
		this.p_arrivo = p_arrivo;
		this.ora_sosta = ora_sosta;
		this.quota_fissa = quota_fissa;
		this.setDiritto_uscita(diritto_uscita);
		this.importo = importo;
	}

	//
	public Integer getId_documento_righe() {

		return id_documento_righe;
	}

	public void setId_documento_righe(Integer id_documento_righe) {

		this.id_documento_righe = id_documento_righe;
	}

	public Integer getFk_id_documento_testata() {

		if (fk_id_documento_testata == null)
			return 0;
		return fk_id_documento_testata;
	}

	public void setFk_id_documento_testata(Integer fk_id_documento_testata) {

		this.fk_id_documento_testata = fk_id_documento_testata;
	}

	public Integer getFk_id_paziente() {

		return paziente.getId_paziente();
	}

	public void setFk_id_paziente(Integer fk_id_paziente) {

		paziente.setId_paziente(fk_id_paziente);
	}

	public Integer getNum_sedute() {

		return num_sedute;
	}

	public void setNum_sedute(Integer num_sedute) {

		this.num_sedute = num_sedute;
	}

	public String getMese() {

		return mese;
	}

	public void setMese(String mese) {

		this.mese = mese;
	}

	public Integer getKm_totali() {

		if (km_totali == null)
			return 0;
		return km_totali;
	}

	public void setKm_totali(Integer km_totali) {

		this.km_totali = km_totali;
	}

	public Integer getKm_percorso() {

		if (km_percorso == null)
			return 0;
		return km_percorso;
	}

	public void setKm_percorso(Integer km_percorso) {

		this.km_percorso = km_percorso;
	}

	public String getPercorso() {

		if (percorso == null)
			return "";
		return percorso;
	}

	public void setPercorso(String percorso) {

		this.percorso = percorso;
	}

	public String getP_partenza() {

		if (p_partenza == null)
			return "";
		return p_partenza;
	}

	public void setP_partenza(String p_partenza) {

		this.p_partenza = p_partenza;
	}

	public String getP_arrivo() {

		if (p_arrivo == null)
			return "";
		return p_arrivo;
	}

	public void setP_arrivo(String p_arrivo) {

		this.p_arrivo = p_arrivo;
	}

	public Integer getOra_sosta() {

		return ora_sosta;
	}

	public void setOra_sosta(Integer ora_sosta) {

		this.ora_sosta = ora_sosta;
	}

	public BigDecimal getQuotaFissa() {

		return quota_fissa;
	}

	public void setQuotaFissa(BigDecimal qF) {

		this.quota_fissa = qF;
	}

	public void setQuotaFissa(String qF) {

		this.quota_fissa = fromStringToBigDecimal(qF);
	}

	public BigDecimal getDiritto_uscita() {

		return diritto_uscita;
	}

	public void setDiritto_uscita(String diritto_uscita) {

		this.diritto_uscita = fromStringToBigDecimal(diritto_uscita);
	}

	public void setDiritto_uscita(BigDecimal diritto_uscita) {

		this.diritto_uscita = diritto_uscita;
	}

	public BigDecimal getImporto() {

		return importo;
	}

	public void setImporto(BigDecimal importo) {

		this.importo = importo;
	}

	public void setImporto(String importo) {

		this.importo = fromStringToBigDecimal(importo);
	}

	public Paziente getPaziente() {

		return paziente;
	}

	public void setPaziente(Paziente paziente) {

		this.paziente = paziente;
	}

	public String getUsercrea() {

		return usercrea;
	}

	public void setUsercrea(String usercrea) {

		this.usercrea = usercrea;
	}

	public String getUserultv() {

		return userultv;
	}

	public void setUserultv(String userultv) {

		this.userultv = userultv;
	}

	public Date getDatacrea() {

		return datacrea;
	}

	public void setDatacrea(Date datacrea) {

		this.datacrea = datacrea;
	}

	public Date getDataultv() {

		return dataultv;
	}

	public void setDataultv(Date dataultv) {

		this.dataultv = dataultv;
	}

	public Struttura getStruttura() {

		return struttura;
	}

	public void setStruttura(Struttura struttura) {

		this.struttura = struttura;
	}

	public BigDecimal getQuota_fissa() {

		return quota_fissa;
	}

	public void setQuota_fissa(BigDecimal quota_fissa) {

		this.quota_fissa = quota_fissa;
	}

	public Integer getFk_id_struttura() {

		return struttura.getId_struttura();
	}

	public void setFk_id_struttura(Integer fk_id_struttura) {

		struttura.setId_struttura(fk_id_struttura);
	}

	public String getPercorsoAndata() {

		String percorsoAndata = getP_partenza() + " - " + getP_arrivo();
		return percorsoAndata;
	}

	public String getPercorsoRitorno() {

		return getP_arrivo() + " - " + getP_partenza();
	}

}
