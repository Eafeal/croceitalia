package org.cms.login;

import it.asso.util.AssoException;
import it.asso.util.ModelUser;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.Model;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Entity
@Table(name = "Utente")
public class Utente extends Model implements Utente_itf {

	/**
     * 
     */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false, insertable = true, updatable = true)
	private String			id;

	/**
	 * La coppia dominio/userId mi permette di definire utenti con lo stesso id
	 * su domini diversi
	 */
	private String			userId;

	@OneToOne
	@JoinColumn(name = "dominio_uid", nullable = false)
	protected Dominio		dominio;

	/**
     * 
     */
	@OneToOne
	@JoinColumn(name = "soggetto_id", nullable = false)
	private SoggettoUtente	soggettoUtente;

	/**
     * 
     */
	@Column(name = "S_PSWD_CFRT_CORR")
	private String			password;

	private String			stato;
	private String			lingua;

	private String			cognome;
	private String			nome;
	private String			email;

	@Temporal(TemporalType.TIMESTAMP)
	private Timestamp		E_ULT_LOG_IN;

	private int				Q_TNT_ACCS;

	/**
     * 
     */
	private String			telefono;
	private String			cellulare;
	private String			codiceFiscale;

	private String			indirizzo;
	private String			cap;
	private String			comune;
	private String			prov;
	private String			privacy;

	private String			convenzione;

	private String			note;

	/**
	 * 
	 */
	@Temporal(TemporalType.DATE)
	private Timestamp		insertTime;
	@Temporal(TemporalType.DATE)
	private Timestamp		lastUpdateTime;

	private String			userLastOperation;

	/**
	 * 
	 */
	private String			partitaIva;
	private String			ragioneSociale;

	// utilizzo il campo della data scadenza password per scadenza user
	@Temporal(TemporalType.DATE)
	private Date			dataScadenza;

	private String			S_PSWD_CFRT_1;
	private String			S_PSWD_CFRT_2;
	private String			S_PSWD_CFRT_3;
	private String			S_PSWD_CFRT_4;
	private String			S_PSWD_CFRT_5;
	private String			S_PSWD_CFRT_6;
	private String			S_PSWD_INI;

	private String			T_DMD_SEGRETA;
	private String			T_RSP_SEGRETA;

	public final String		attivo			= "00";
	public final String		inRegistrazione	= "01";
	public final String		inProva			= "02";
	public final String		sospeso			= "99";

	/**
	 * 
	 */
	public Utente() {

		super();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getNominativo()
	 */
	public String getCdfiscPiva() {

		if (this.isPersonaFisica()) {
			if (this.codiceFiscale == null) {
				return "Unknown Subject Type";
			}
			return this.codiceFiscale;
		}
		if (this.isPersonaGiuridica()) {
			if (this.partitaIva == null) {
				return "Unknown Subject Type";
			}
			return this.partitaIva;
		}

		return "Unknown Subject Type";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getCellulare()
	 */
	public String getCellulare() {

		return this.cellulare;
	}

	/**
	 * @return the codiceFiscale
	 */
	public String getCodiceFiscale() {

		if (this.codiceFiscale == null)
			return "";
		return codiceFiscale;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getCognome()
	 */
	public String getCognome() {

		return this.cognome;
	}

	/**
	 * @return
	 */
	public Date getDataScadenza() {

		return this.dataScadenza;
	}

	/**
	 * @return
	 */
	public Dominio getDominio() {

		return dominio;
	}

	public Timestamp getE_ULT_LOG_IN() {

		return this.E_ULT_LOG_IN;
	}

	public String getEmail() {

		return this.email;
	}

	public String getId() {

		return this.id;
	}

	/**
	 * @return the insertTime
	 */
	public Timestamp getInsertTime() {

		return this.insertTime;
	}

	/**
	 * @return the lastUpdateTime
	 */
	public Timestamp getLastUpdateTime() {

		return this.lastUpdateTime;
	}

	public String getLingua() {

		return this.lingua;
	}

	public String getNome() {

		return this.nome;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getNominativo()
	 */
	//@Override
	public String getNominativo() {

		return this.nome + " " + this.cognome;
	}

	/**
	 * @return the partitaIva
	 */
	public String getPartitaIva() {

		if (this.partitaIva == null)
			return "";
		return partitaIva;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {

		return password;
	}

	public int getQ_TNT_ACCS() {

		return this.Q_TNT_ACCS;
	}

	/**
	 * @return the ragioneSociale
	 */
	public String getRagioneSociale() {

		if (this.ragioneSociale == null)
			return "";
		return ragioneSociale;
	}

	public String getS_PSWD_CFRT_1() {

		return this.S_PSWD_CFRT_1;
	}

	public String getS_PSWD_CFRT_2() {

		return this.S_PSWD_CFRT_2;
	}

	public String getS_PSWD_CFRT_3() {

		return this.S_PSWD_CFRT_3;
	}

	public String getS_PSWD_CFRT_4() {

		return this.S_PSWD_CFRT_4;
	}

	public String getS_PSWD_CFRT_5() {

		return this.S_PSWD_CFRT_5;
	}

	public String getS_PSWD_CFRT_6() {

		return this.S_PSWD_CFRT_6;
	}

	public String getS_PSWD_INI() {

		return this.S_PSWD_INI;
	}

	/**
	 * @return the soggettoUtente
	 */
	public SoggettoUtente getSoggetto() {

		return this.soggettoUtente;
	}

	/**
	 * @return the soggettoUtente
	 */
	public SoggettoUtente getSoggettoUtente() {

		return soggettoUtente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getStates()
	 */
	//@Override
	public List<String> getStates() {

		return this.soggettoUtente.getObjectStates();

	}

	public String getStato() {

		return this.stato;
	}

	public String getT_DMD_SEGRETA() {

		return this.T_DMD_SEGRETA;
	}

	public String getT_RSP_SEGRETA() {

		return this.T_RSP_SEGRETA;
	}

	/**
	 * @return
	 */
	public String getTelefono() {

		if (this.telefono == null)
			return "";
		return this.telefono;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getUserId()
	 */
	public String getUserId() {

		return this.userId;
	}

	/**
	 * @return
	 */
	public String getUserLastOperation() {

		return userLastOperation;
	}

	/*
	 * Torna true se l'utente è un amministratore. Altrimenti false.
	 * 
	 * @see org.cms.login.Utente_itf#isAdmin()
	 */
	//@Override
	public boolean isAdmin() {

		return this.soggettoUtente.isAdmin();
	}

	/**
	 * Torna sempre true in quanto se sono uno User vuol dire che mi sono loggato.
	 */
	public boolean isLogged() {

		return true;
	}

	/**
	 * @return
	 */
	public boolean isPersonaFisica() {

		return !isPersonaGiuridica();
	}

	/**
	 * @return
	 */
	public boolean isPersonaGiuridica() {

		return Util.isNotEmpty(partitaIva);
	}

	/*
	 * Torna true se l'utenete è scaduto. Altrimenti false.
	 * 
	 * @see it.asso.util.Utente_itf#isScaduto()
	 */
	//@Override
	public boolean isScaduto() {

		if (dataScadenza == null)
			return false;
		Date oggi = Calendar.getInstance().getTime();
		return oggi.after(dataScadenza);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.object.impl.Model#onPrePersist()
	 */
	//@Override
	protected void onPrePersist() throws AssoException {

		if (Util.isEmpty(this.password)) {
			throw new AssoException("La password non può essere vuota");
		}

		if (this.S_PSWD_INI == null) {
			this.S_PSWD_INI = this.password;
		}
		long time = Calendar.getInstance().getTimeInMillis();
		this.insertTime = new Timestamp(time);

		onPreUpdate();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.jpa.object.impl.Model#onPreUpdate()
	 */
	//@Override
	@PreUpdate
	protected void onPreUpdate() {

		super.onPreUpdate();

		long time = Calendar.getInstance().getTimeInMillis();
		this.lastUpdateTime = new Timestamp(time);

		Utente_itf utenteLoggato = ModelUser.get();
		if (utenteLoggato.getDominio() == null) {
			// Sono in fase di login. L'utente loggato sono io, non un Guest
			utenteLoggato = this;
		}
		this.userLastOperation = utenteLoggato.getDominio().getUid() + "@" + utenteLoggato.getUserId();
	}

	/**
	 * @param cellulare
	 */
	public void setCellulare(String cellulare) {

		this.cellulare = cellulare;
	}

	/**
	 * @param codiceFiscale
	 *            the codiceFiscale to set
	 */
	public void setCodiceFiscale(String codiceFiscale) {

		this.codiceFiscale = codiceFiscale;
	}

	public void setCognome(String cognome) {

		this.cognome = cognome;
	}

	public void setDataScadenza(Date dataScadenza) {

		this.dataScadenza = dataScadenza;
	}

	/**
	 * @param dominio
	 */
	public void setDominio(Dominio dominio) {

		this.dominio = dominio;
	}

	public void setEmail(String email) {

		this.email = email;
	}

	public void setId(String id) {

		this.id = id;
	}

	/**
	 * @param insertTime
	 *            the insertTime to set
	 */
	public void setInsertTime(Timestamp insertTime) {

		this.insertTime = insertTime;
	}

	/**
	 * @param lastUpdateTime
	 *            the lastUpdateTime to set
	 */
	public void setLastUpdateTime(Timestamp lastUpdateTime) {

		this.lastUpdateTime = lastUpdateTime;
	}

	public void setLingua(String lingua) {

		this.lingua = lingua;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	/**
	 * @param partitaIva
	 *            the partitaIva to set
	 */
	public void setPartitaIva(String partitaIva) {

		this.partitaIva = partitaIva;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {

		this.password = password;
	}

	/**
	 * @param ragioneSociale
	 *            the ragioneSociale to set
	 */
	public void setRagioneSociale(String ragioneSociale) {

		this.ragioneSociale = ragioneSociale;
	}

	/**
	 * @param soggettoUtente
	 *            the soggettoUtente to set
	 */
	public void setSoggetto(SoggettoUtente soggettoUtente) {

		this.soggettoUtente = soggettoUtente;
	}

	/**
	 * @param soggettoUtente
	 *            the soggettoUtente to set
	 */
	public void setSoggettoUtente(SoggettoUtente soggettoUtente) {

		this.soggettoUtente = soggettoUtente;
	}

	public void setStato(String stato) {

		this.stato = stato;
	}

	public void setT_DMD_SEGRETA(String tDMDSEGRETA) {

		this.T_DMD_SEGRETA = tDMDSEGRETA;
	}

	public void setT_RSP_SEGRETA(String tRSPSEGRETA) {

		this.T_RSP_SEGRETA = tRSPSEGRETA;
	}

	public void setTelefono(String telefono) {

		this.telefono = telefono;
	}

	/**
	 * @param eULTLOGIN
	 */
	public void setTimeLastLogin(Timestamp eULTLOGIN) {

		this.E_ULT_LOG_IN = eULTLOGIN;
	}

	public void setUserId(String userId) {

		this.userId = userId;
	}

	public void setUserLastOperation(String userLastOperation) {

		this.userLastOperation = userLastOperation;
	}

	public boolean isAttivo() {

		return this.stato.equals(this.attivo);
	}

	public boolean isInRegistrazione() {

		return this.stato.equals(this.inRegistrazione);
	}

	public boolean isSospeso() {

		return this.stato.equals(this.sospeso);
	}

	public boolean isProva() {

		return this.stato.equals(this.inProva);
	}

	public String getIndirizzo() {

		if (this.indirizzo == null)
			return "";
		return indirizzo;
	}

	public String getCap() {

		if (this.cap == null)
			return "";
		return cap;
	}

	public String getComune() {

		if (this.comune == null)
			return "";
		return comune;
	}

	public String getProv() {

		if (this.prov == null)
			return "";
		return prov;
	}

	public void setIndirizzo(String indirizzo) {

		this.indirizzo = indirizzo;
	}

	public void setCap(String cap) {

		this.cap = cap;
	}

	public void setComune(String comune) {

		this.comune = comune;
	}

	public void setProv(String prov) {

		this.prov = prov;
	}

	public String getPrivacy() {

		return privacy;
	}

	public void setPrivacy(String privacy) {

		this.privacy = privacy;
	}

	public String getConvenzione() {

		if (convenzione == null)
			return "";
		return convenzione;
	}

	public void setConvenzione(String convenzione) {

		this.convenzione = convenzione;
	}

	public String getNote() {

		if (note == null)
			return "";
		return note;
	}

	public void setNote(String note) {

		this.note = note;
	}
}
