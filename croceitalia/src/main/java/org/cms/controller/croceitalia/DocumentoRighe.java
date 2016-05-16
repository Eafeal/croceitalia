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
@Table(name = "Documento_righe")
@NamedQuery(name = "Documento_righe.loadAll", query = "SELECT OBJECT(obj) FROM Documento_righe obj")
public class DocumentoRighe extends Model {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_documento_righe", nullable = false, insertable = true, updatable = false)
	private Integer		id_documento_righe;

	private Integer		fk_id_documento_testata;

	@OneToOne
	@JoinColumn(name = "fk_id_paziente", nullable = false)
	private Paziente	paziente;

	private Integer		num_sedute;
	private String		mese;
	private Integer		km_totali;
	private Integer		km_percorso;
	private String		percorso;
	private String		p_partenza;
	private String		p_arrivo;
	private Integer		ora_sosta;
	private Integer		QF;
	private String		diritto_uscita;
	private Integer		importo_doc;

	public DocumentoRighe() {

		super();
		// TODO Auto-generated constructor stub
	}

	public DocumentoRighe(Integer id_documento_righe, Integer fk_id_documento_testata,/* Integer fk_id_paziente, */
	Integer num_sedute, String mese, Integer km_totali, Integer km_percorso, String percorso, String p_partenza,
			String p_arrivo, Integer ora_sosta, Integer qF, String diritto_uscita, Integer importo_doc) {

		super();
		this.id_documento_righe = id_documento_righe;
		this.fk_id_documento_testata = fk_id_documento_testata;
		this.paziente = new Paziente();
		this.num_sedute = num_sedute;
		this.mese = mese;
		this.km_totali = km_totali;
		this.km_percorso = km_percorso;
		this.percorso = percorso;
		this.p_partenza = p_partenza;
		this.p_arrivo = p_arrivo;
		this.ora_sosta = ora_sosta;
		this.QF = qF;
		this.diritto_uscita = diritto_uscita;
		this.importo_doc = importo_doc;
	}

	public Integer getId_documento_righe() {

		return id_documento_righe;
	}

	public void setId_documento_righe(Integer id_documento_righe) {

		this.id_documento_righe = id_documento_righe;
	}

	public Integer getFk_id_documento_testata() {

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

		return km_totali;
	}

	public void setKm_totali(Integer km_totali) {

		this.km_totali = km_totali;
	}

	public Integer getKm_percorso() {

		return km_percorso;
	}

	public void setKm_percorso(Integer km_percorso) {

		this.km_percorso = km_percorso;
	}

	public String getPercorso() {

		return percorso;
	}

	public void setPercorso(String percorso) {

		this.percorso = percorso;
	}

	public String getP_partenza() {

		return p_partenza;
	}

	public void setP_partenza(String p_partenza) {

		this.p_partenza = p_partenza;
	}

	public String getP_arrivo() {

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

	public Integer getQF() {

		return QF;
	}

	public void setQF(Integer qF) {

		QF = qF;
	}

	public String getDiritto_uscita() {

		return diritto_uscita;
	}

	public void setDiritto_uscita(String diritto_uscita) {

		this.diritto_uscita = diritto_uscita;
	}

	public Integer getImporto_doc() {

		return importo_doc;
	}

	public void setImporto_doc(Integer importo_doc) {

		this.importo_doc = importo_doc;
	}

}
