package it.asso.upload;

import it.asso.freemarker.LangFactory;
import it.asso.util.ApplConfig;
import it.asso.util.AssoException;
import it.asso.util.Dominio_itf;
import it.asso.util.ModelUser;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

import java.io.File;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;

import javax.activation.DataSource;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.DominioModel;
import org.cms.jpa.object.impl.TipoOggetto;

/**
 * @author paolo
 */
@Entity
@Table(name = "DBFile")
@NamedQuery(name = "DBFile.loadAll", query = "SELECT OBJECT(obj) FROM DBFile obj")
public class DBFile extends DominioModel implements File_itf {

	private static final String	DefaultIco		= "unknow.png";
	private static final String	DefaultMimeType	= "application/text";
	public static String		DIR				= "DIR";

	/**
     * 
     */
	private static final String	EMPTY			= "EMPTY";
	public static String		FILE			= "FILE";

	/**
     * 
     */
	public static final String	TOP				= "TOP";

	private String				autore			= "";

	private Blob				blob;
	private String				description		= "";

	@OneToOne
	@JoinColumn(name = "dominio_uid", nullable = false)
	protected Dominio			dominio;

	/**
     * 
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private String				id;

	/**
     * 
     */
	private String				lang			= LangFactory.IT;
	private String				nomefile;
	private String				parent_id;

	private String				state			= WORKINPROGRESS;
	private String				tipo;

	private String				esito;
	private String				idProtocollo;

	/**
     * 
     */
	@OneToOne
	@JoinColumn(name = "tipooggetto_uid", nullable = false)
	protected TipoOggetto		tipoOggetto;

	/**
     * 
     */
	private String				title			= "";

	/**
	 * @return the autore
	 */
	public String getAutore() {

		return this.autore;
	}

	/**
	 * @return the blob_ofs_documento
	 */
	public Blob getBlob() {

		return this.blob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.File_itf#getDataSource()
	 */
	public DataSource getDataSource() throws SQLException {

		long length;
		length = blob.length();
		byte[] bytearray = blob.getBytes(1, (int) length);
		String attachName = getNomefile();
		BufferedDataSource bds = new BufferedDataSource(bytearray, attachName);
		return bds;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {

		return this.description;
	}

	/**
	 * @return the dominio
	 */
	public Dominio_itf getDominio() {

		return this.dominio;
	}

	/**
	 * @return
	 */
	public String getExt() {

		if (isFolder()) {
			return DIR.toLowerCase();
		}
		String[] split = getNomefile().split("[.]");

		if (split.length < 2) {
			return EMPTY.toLowerCase();
		}
		String ext = split[split.length - 1].toLowerCase();

		return ext;
	}

	@Override
	public File getFile() throws AssoException {

		// File file = new File(nomefile);
		//
		// return file;

		throw new AssoException("Metodo non implementato");
	}

	/**
	 * @return
	 */
	public String getHint() {

		return "";
	}

	/**
	 * @return
	 */
	public String getIco() {

		String param = "ico.mini." + getExt();
		String ico = ApplConfig.GetParameter(param);
		if (ico == null) {
			ico = DefaultIco;
		}
		return ico;
	}

	@Override
	public String getId() {

		return id;
	}

	@Override
	public String getIdParent() {

		return parent_id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.File_itf#getInputStream()
	 */
	public InputStream getInputStream() throws AssoException {

		InputStream inputStream;
		try {
			inputStream = blob.getBinaryStream();
		} catch (SQLException e) {
			throw new AssoException("Errore nel blob di " + id, e);
		}
		return inputStream;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {

		return this.lang;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.File_itf#getMimeType()
	 */
	public String getMimeType() {

		String param = "mime." + getExt();
		String mimeType = ApplConfig.GetParameter(param);
		if (mimeType == null) {
			mimeType = DefaultMimeType;
		}
		return mimeType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.promowizard.model.File_itf#getNomefile()
	 */
	public String getNomefile() {

		return this.nomefile;
	}

	/**
	 * @return the parent_id
	 */
	public String getParent_id() {

		return this.parent_id;
	}

	/**
	 * Torna il nome file senza estensione
	 * 
	 * @return
	 */
	public String getPureFileName() {

		String[] split = getNomefile().split("." + getExt());
		String answer = "";
		for (int i = 0; i < split.length; i++) {
			answer += split[i];
		}

		return answer;

	}

	@Override
	public String getState() {

		return state;
	}

	/**
	 * @return the tipo
	 */
	public String getTipo() {

		return this.tipo;
	}

	/**
	 * @return the tipoOggetto
	 */
	public TipoOggetto getTipoOggetto() {

		return this.tipoOggetto;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {

		return this.title;
	}

	@Override
	public String getUid() {

		return nomefile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.upload.File_itf#isDocument()
	 */
	public boolean isDocument() {

		String[] document = {"doc", "pdf", "zip", "xls", "xlsx", "xml"};
		String ext = this.getExt();

		for (int i = 0; i < document.length; i++) {
			String docExt = document[i];
			if (docExt.equals(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @return
	 */
	public boolean isFirstLevel() {

		return this.getTipo().equals(TOP);
	}

	public boolean isUserFirstLevel(String userId) {

		return this.nomefile.equals(userId);
	}

	/**
	 * @return
	 */
	public boolean isFolder() {

		boolean isFolder = DIR.equals(tipo) || TOP.equals(tipo);
		return isFolder;
	}

	public boolean isImage() {

		String[] document = {"gif", "jpg", "jpeg", "bmp", "png", "tif"};
		String ext = this.getExt();

		for (int i = 0; i < document.length; i++) {
			String docExt = document[i];
			if (docExt.equalsIgnoreCase(ext)) {
				return true;
			}
		}
		return false;
	}

	public boolean isExcel() {

		String[] document = {"xls", "xlsx"};
		String ext = this.getExt();

		for (int i = 0; i < document.length; i++) {
			String docExt = document[i];
			if (docExt.equalsIgnoreCase(ext)) {
				return true;
			}
		}
		return false;
	}

	public boolean isCSV() {

		String[] document = {"csv"};
		String ext = this.getExt();

		for (int i = 0; i < document.length; i++) {
			String docExt = document[i];
			if (docExt.equalsIgnoreCase(ext)) {
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.object.impl.DominioModel#onPrePersist()
	 */
	@Override
	protected void onPrePersist() throws AssoException {

		super.onPrePersist();
		setDefault();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.object.impl.DominioModel#onPreUpdate()
	 */
	@Override
	protected void onPreUpdate() throws AssoException {

		super.onPreUpdate();

		if (Util.isEmpty(this.nomefile)) {
			throw new AssoException("Nome file non valido!");
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.util.common.hbo.HObject#preSave()
	 */
	public void preSave() {

		// TODO Auto-generated method stub

	}

	/**
	 * @param autore
	 *            the autore to set
	 */
	public void setAutore(String autore) {

		this.autore = autore;
	}

	/**
	 * @param aBlob
	 */
	public void setBlob(Blob aBlob) {

		this.blob = aBlob;
	}

	/**
	 * Imposto i valori di default e controllo i vari campi
	 * 
	 * @throws AssoException
	 * 
	 */
	private void setDefault() throws AssoException {

		if (Util.isEmpty(this.nomefile)) {
			throw new AssoException("Nome file non valido!");
		}

		if (Util.isEmpty(this.state)) {
			this.state = PRIVATE;
		}

		if (Util.isEmpty(this.autore)) {
			Utente_itf utente = ModelUser.get();
			this.autore = "Sconosciuto - " + utente.getUserId();
		}

		if (Util.isEmpty(this.title)) {
			this.title = this.nomefile;
		}

		if (Util.isEmpty(this.description)) {
			this.description = this.nomefile;
		}
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {

		this.description = description;
	}

	/**
	 * @param dominio
	 *            the dominio to set
	 */
	public void setDominio(Dominio dominio) {

		this.dominio = dominio;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {

		this.id = id;
	}

	/**
	 * @param lang
	 *            the lang to set
	 */
	public void setLang(String lang) {

		this.lang = lang;
	}

	/**
	 * @param nomefile
	 *            the nomefile to set
	 */
	public void setNomefile(String nomefile) {

		this.nomefile = nomefile;
	}

	/**
	 * @param parentId
	 *            the parent_id to set
	 */
	public void setParent_id(String parentId) {

		this.parent_id = parentId;
	}

	/**
	 * @param state
	 *            the state to set
	 */
	public void setState(String state) {

		this.state = state;
	}

	/**
	 * @param tipo
	 *            the tipo to set
	 */
	public void setTipo(String tipo) {

		this.tipo = tipo;
	}

	/**
	 * @param tipoOggetto
	 *            the tipoOggetto to set
	 */
	public void setTipoOggetto(TipoOggetto tipoOggetto) {

		this.tipoOggetto = tipoOggetto;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {

		this.title = title;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {

		String answer = this.getClass().getSimpleName() + ". ";
		answer = "Id=" + id;
		answer += "/NomeFile=" + nomefile;
		return answer;
	}

	public String getEsito() {

		if (this.esito == null)
			return "";
		return esito;
	}

	public String getIdProtocollo() {

		if (this.idProtocollo == null)
			return "";
		return idProtocollo;
	}

	public void setEsito(String esito) {

		this.esito = esito;
	}

	public void setIdProtocollo(String idProtocollo) {

		this.idProtocollo = idProtocollo;
	}
}