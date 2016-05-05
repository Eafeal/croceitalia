/**
 * 
 */
package org.cms.login;

/**
 * @author ConsDonzelliPaolo
 * 
 */
public class SoggettoFactory {

	/**
	 * @return
	 */
	public static SoggettoUtente createSoggettoGuest() {

		SoggettoUtente soggettoUtente = new SoggettoUtente();
		soggettoUtente.setRuolo(SoggettoUtente.GUEST);
		return soggettoUtente;
	}
}
