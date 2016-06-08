package org.cms.controller.croceitalia;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

@Entity
@Table(name = "Documento_Testata")
@NamedQuery(name = "Documento_Testata.loadAll", query = "SELECT OBJECT(obj) FROM Documento_Testata obj")
public class Documento_Testata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento_testata", nullable = false, insertable = true, updatable = false)
	private Integer id_documento_testata;

	@OneToOne
	@JoinColumn(name = "fk_id_mezzo", nullable = false)
	private Mezzo mezzo;

	@OneToOne
	@JoinColumn(name = "fk_id_banca", nullable = false)
	private Banca banca;

	@OneToOne
	@JoinColumn(name = "fk_id_cliente", nullable = false)
	private Cliente cliente;

	private Integer num_documento;
	private String anno_documento;
	private String mese_documento;

	@Temporal(TemporalType.DATE)
	private Date data_documento;

	@Column(precision = 10, scale = 2)
	private BigDecimal imponibile;

	@Column(precision = 10, scale = 2)
	private BigDecimal iva;

	private String esente_iva;
	private String esente_bollo;

	@Column(precision = 10, scale = 2)
	private BigDecimal importo_esente;

	@Column(precision = 10, scale = 2)
	private BigDecimal totale;

	private String stato;
	private String pdf_generato;
	private String nome_file;
	private String CIG;

	private String usercrea;
	private String userultv;

	@Temporal(TemporalType.DATE)
	private Date datacrea;
	@Temporal(TemporalType.DATE)
	private Date dataultv;

	// @OneToMany
	// @JoinColumn(name = "id_documento_testata", nullable = false)
	// @OneToMany( fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy =
	// "documento_testata")
	// @OneToMany(mappedBy = "documento_testata")
	private List<Documento_Righe> righe;

	public Documento_Testata() {

		super();

		esente_bollo = "S";
		esente_iva = "S";

		this.mezzo = new Mezzo();
		this.banca = new Banca();
		this.cliente = new Cliente();

		imponibile = BigDecimal.ZERO;
		iva = BigDecimal.ZERO;
		importo_esente = BigDecimal.ZERO;
		totale = BigDecimal.ZERO;

		pdf_generato = "N"; // NO
		stato = "A"; // APERTO

		usercrea = "Axel";
		userultv = "Axel";

		datacrea = new Date();
		dataultv = new Date();

		// CIG = "";

	}

	public Documento_Testata(Integer id_documento_testata,
			/*
			 * Integer fk_id_mezzo, Integer fk_id_banca, Integer fk_id_cliente,
			 */Integer num_documento, String anno_documento, String mese_documento, Date data_documento,
			BigDecimal imponibile, BigDecimal iva, String esente_iva, String esente_bollo, BigDecimal importo_esente,
			BigDecimal totale, String stato, String pdf_generato, String nome_file) {

		super();
		this.id_documento_testata = id_documento_testata;
		this.mezzo = new Mezzo();
		this.banca = new Banca();
		this.cliente = new Cliente();
		this.num_documento = num_documento;
		this.anno_documento = anno_documento;
		this.mese_documento = mese_documento;
		this.data_documento = data_documento;
		this.imponibile = imponibile;
		this.iva = iva;
		this.esente_iva = esente_iva;
		this.esente_bollo = esente_bollo;
		this.importo_esente = importo_esente;
		this.totale = totale;
		this.stato = stato;
		this.pdf_generato = pdf_generato;
		this.nome_file = nome_file;
	}

	public Integer getId_documento_testata() {

		return id_documento_testata;
	}

	public void setId_documento_testata(Integer id_documento_testata) {

		this.id_documento_testata = id_documento_testata;
	}

	public Integer getFk_id_mezzo() {

		return mezzo.getId_mezzo();
	}

	public void setFk_id_mezzo(Integer fk_id_mezzo) {

		mezzo.setId_mezzo(fk_id_mezzo);
	}

	public Integer getFk_id_banca() {

		return banca.getId_banca();
	}

	public void setFk_id_banca(Integer fk_id_banca) {

		banca.setId_banca(fk_id_banca);
	}

	public Integer getFk_id_cliente() {

		return cliente.getId_cliente();
	}

	public void setFk_id_cliente(Integer fk_id_cliente) {

		cliente.setId_cliente(fk_id_cliente);
	}

	public Integer getNum_documento() {

		return num_documento;
	}

	public void setNum_documento(Integer num_documento) {

		this.num_documento = num_documento;
	}

	public String getAnno_documento() {

		return anno_documento;
	}

	public void setAnno_documento(String anno_documento) {

		this.anno_documento = anno_documento;
	}

	public String getMese_documento() {

		return mese_documento;
	}

	public void setMese_documento(String mese_documento) {

		this.mese_documento = mese_documento;
	}

	public Date getData_documento() {

		return data_documento;
	}

	public void setData_documento(Date data_documento) {

		this.data_documento = data_documento;
	}

	public BigDecimal getImponibile() {

		return imponibile;
	}

	public void setImponibile(BigDecimal imponibile) {

		this.imponibile = imponibile;
	}

	public void setImponibile(String importo) {

		this.imponibile = fromStringToBigDecimal(importo);
	}

	public BigDecimal getIva() {

		return iva;
	}

	public void setIva(BigDecimal iva) {
		this.iva = iva;
	}

	// POLIMORFISMO
	public void setIva(String importo) {

		this.iva = fromStringToBigDecimal(importo);
	}

	public String getEsenteIva() {

		return esente_iva;
	}

	public void setEsenteIva(String esente_iva) {

		this.esente_iva = esente_iva;
	}

	public String getEsente_bollo() {

		return esente_bollo;
	}

	public void setEsente_bollo(String esente_bollo) {

		this.esente_bollo = esente_bollo;
	}

	public BigDecimal getImporto_esente() {

		return importo_esente;
	}

	public void setImporto_esente(BigDecimal importo_esente) {

		this.importo_esente = importo_esente;
	}

	public void setImporto_esente(String importo_esente) {

		this.importo_esente = fromStringToBigDecimal(importo_esente);
	}

	public BigDecimal getTotale() {

		return totale;
	}

	public void setTotale(BigDecimal totale) {

		this.totale = totale;
	}

	public void setTotale(String totale) {

		this.totale = fromStringToBigDecimal(totale);
		;
	}

	public String getStato() {

		return stato;
	}

	public void setStato(String stato) {

		this.stato = stato;
	}

	public String getPdf_generato() {

		return pdf_generato;
	}

	public void setPdf_generato(String pdf_generato) {

		this.pdf_generato = pdf_generato;
	}

	public String getNome_file() {

		return nome_file;
	}

	public void setNome_file(String nome_file) {

		this.nome_file = nome_file;
	}

	public List<Documento_Righe> getRighe() {
		return righe;
	}

	public void setRighe(List<Documento_Righe> righe) {
		this.righe = righe;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public Banca getBanca() {
		return banca;
	}

	public void setBanca(Banca banca) {
		this.banca = banca;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getEsente_iva() {
		return esente_iva;
	}

	public void setEsente_iva(String esente_iva) {
		this.esente_iva = esente_iva;
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

	private BigDecimal fromStringToBigDecimal(String importo) {

		DecimalFormatSymbols symbols = new DecimalFormatSymbols();

		String pattern = "#.##0,0#";
		DecimalFormat decimalFormat = new DecimalFormat();
		decimalFormat.setParseBigDecimal(true);

		// parse the string
		try {
			return (BigDecimal) decimalFormat.parse(importo);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return BigDecimal.ZERO;
	}

	public boolean isAperto() {
		if (stato == null)
			return true;

		return stato.equals("A");
	}

	public String descrizioneStato() {
		String a;
		if (isAperto()) {// di default è già true
			a = "aperto";
		} else {
			a = "chiuso";
		}
		return a;
	}

	public boolean isChiuso() {
		if (stato == null)
			return false;

		return stato.equals("C");
	}

	public void setChiudi() {

		chiudi();
	}

	private void chiudi() {
		stato = "C";
	}

	private void riapri() {
		stato = "A";
	}

	public boolean isPdfGenerato() {
		if (pdf_generato == null)
			return false;

		return pdf_generato.equals("S"); // SI
	}

	private void rigenera_pdf() {
		pdf_generato = "N"; // NO
	}

	public String getCIG() {
		return CIG.toUpperCase();
	}

	public void setCIG(String cIG) {
		CIG = cIG.toUpperCase();
	}

	public String getData_String_documento() {
		SimpleDateFormat sdf = new SimpleDateFormat(); // creo l'oggetto
		sdf.applyPattern("dd/MM/yyyy");
		String dataStr = sdf.format(data_documento);

		return dataStr;
	}

}
