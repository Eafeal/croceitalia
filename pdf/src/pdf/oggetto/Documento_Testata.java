package pdf.oggetto;

public class Documento_Testata {
	private Integer id_documento_testata;
	private Integer fk_id_mezzo;
	private Integer fk_id_banca;
	private Integer fk_id_cliente;
	private Integer num_documento;
	private String anno_documento;
	private String mese_documento;
	private String data_documento;
	private Integer imponibile;
	private Integer IVA;
	private String esente_IVA;
	private String esente_bollo;
	private Integer importo_esente;
	private Integer totale;
	private String stato;
	private String pdf_generato;
	private String nome_file;

	public Documento_Testata(Integer id_documento_testata, Integer fk_id_mezzo, Integer fk_id_banca,
			Integer fk_id_cliente, Integer num_documento, String anno_documento, String mese_documento,
			String data_documento, Integer imponibile, Integer iVA, String esente_IVA, String esente_bollo,
			Integer importo_esente, Integer totale, String stato, String pdf_generato, String nome_file) {
		super();
		this.id_documento_testata = id_documento_testata;
		this.fk_id_mezzo = fk_id_mezzo;
		this.fk_id_banca = fk_id_banca;
		this.fk_id_cliente = fk_id_cliente;
		this.num_documento = num_documento;
		this.anno_documento = anno_documento;
		this.mese_documento = mese_documento;
		this.data_documento = data_documento;
		this.imponibile = imponibile;
		this.IVA = iVA;
		this.esente_IVA = esente_IVA;
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
		return fk_id_mezzo;
	}

	public void setFk_id_mezzo(Integer fk_id_mezzo) {
		this.fk_id_mezzo = fk_id_mezzo;
	}

	public Integer getFk_id_banca() {
		return fk_id_banca;
	}

	public void setFk_id_banca(Integer fk_id_banca) {
		this.fk_id_banca = fk_id_banca;
	}

	public Integer getFk_id_cliente() {
		return fk_id_cliente;
	}

	public void setFk_id_cliente(Integer fk_id_cliente) {
		this.fk_id_cliente = fk_id_cliente;
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

	public Integer getIVA() {
		return IVA;
	}

	public void setIVA(Integer iVA) {
		IVA = iVA;
	}

	public String getEsente_IVA() {
		return esente_IVA;
	}

	public void setEsente_IVA(String esente_IVA) {
		this.esente_IVA = esente_IVA;
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
