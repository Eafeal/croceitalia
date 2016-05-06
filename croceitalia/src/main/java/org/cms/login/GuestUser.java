package org.cms.login;

import it.asso.util.AssoConst_itf;
import it.asso.util.Dominio_itf;
import it.asso.util.Utente_itf;

import java.sql.Timestamp;
import java.util.List;

/**
 * Questa classe rappresenta gli utenti Guest. Un GuestUser non e' loggato, non
 * e' ne' un amministratore ne' un utente normale. Il Guest user puo' vedere
 * solo gli oggetti pubblici.
 * 
 * @author ConsDonzelliPaolo
 * 
 */
public class GuestUser implements Utente_itf {

	/**
	 * 
	 */
	private final SoggettoUtente	soggettoUtente;

	/**
	 * 
	 */
	public GuestUser() {

		super();
		this.soggettoUtente = SoggettoFactory.createSoggettoGuest();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getCognome()
	 */
	public String getCognome() {

		return "User";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getLingua()
	 */
	public String getLingua() {

		return AssoConst_itf.IT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getNome()
	 */
	public String getNome() {

		return "Guest";
	}

	/*
	 * Il Guest user puo' vedere solo gli oggetti pubblici.
	 * 
	 * @see org.cms.login.Utente_itf#getStates()
	 */
	public List<String> getStates() {

		return this.soggettoUtente.getObjectStates();

	}

	/*
	 * Torna sempre false in quanto GuestUser non è mai un amministratore
	 * 
	 * @see org.cms.login.Utente_itf#isAdmin()
	 */
	public boolean isAdmin() {

		return false;
	}

	/*
	 * Torna sempre false in quanto GuestUser non è mai loggato
	 * 
	 * @see org.cms.login.Utente_itf#isLogged()
	 */
	public boolean isLogged() {

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.cms.login.Utente_itf#getUserId()
	 */
	public String getUserId() {

		return "Guest";
	}

	public String getNominativo() {

		return "Guest";
	}

	public String getId() {

		return "Guest";
	}

	public String getCellulare() {

		// TODO Auto-generated method stub
		return null;
	}

	public String getTelefono() {

		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.asso.util.Utente_itf#getDominio()
	 */
	public Dominio_itf getDominio() {

		return null;
	}

	//@Override
	public void setTimeLastLogin(Timestamp eULTLOGIN) {

		// TODO Auto-generated method stub

	}

	//@Override
	public Timestamp getDataScadenza() {

		return null;
	}

	//@Override
	public boolean isScaduto() {

		return false;
	}
}
