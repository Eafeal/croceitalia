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

@Entity
@Table(name = "Documento_Testata")
@NamedQuery(name = "Documento_Testata.loadAll", query = "SELECT OBJECT(obj) FROM Documento_Testata obj ")
public class DocumentoTestata {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento_testata", nullable = false, insertable = true, updatable = false)
	private Integer	id_documento_testata;

	@OneToOne
	@JoinColumn(name = "fk_id_mezzo", nullable = false)
	private Mezzo	mezzo;

	@OneToOne
	@JoinColumn(name = "fk_id_banca", nullable = false)
	private Banca	banca;

	@OneToOne
	@JoinColumn(name = "fk_id_cliente", nullable = false)
	private Cliente	cliente;

	private Integer	num_documento;
	private String	anno_documento;
	private String	mese_documento;
	private String	data_documento;
	private Integer	imponibile;
	private Integer	iva;
	private String	esente_iva;
	private String	esente_bollo;
	private Integer	importo_esente;
	private Integer	totale;
	private String	stato;
	private String	pdf_generato;
	private String	nome_file;

	public DocumentoTestata() {

		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentoTestata(Integer id_documento_testata,/*
														 * Integer fk_id_mezzo, Integer fk_id_banca,
														 * Integer fk_id_cliente,
														 */Integer num_documento, String anno_documento,
			String mese_documento, String data_documento, Integer imponibile, Integer iva, String esente_iva,
			String esente_bollo, Integer importo_esente, Integer totale, String stato, String pdf_generato,
			String nome_file) {

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

	public String getData_documento() {

		return data_documento;
	}

	public void setData_documento(String data_documento) {

		this.data_documento = data_documento;
	}

	public Integer getImponibile() {

		return imponibile;
	}

	public void setImponibile(Integer imponibile) {

		this.imponibile = imponibile;
	}

	public Integer getIva() {

		return iva;
	}

	public void setIVA(Integer iva) {

		iva = iva;
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

	public Integer getImporto_esente() {

		return importo_esente;
	}

	public void setImporto_esente(Integer importo_esente) {

		this.importo_esente = importo_esente;
	}

	public Integer getTotale() {

		return totale;
	}

	public void setTotale(Integer totale) {

		this.totale = totale;
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

}
