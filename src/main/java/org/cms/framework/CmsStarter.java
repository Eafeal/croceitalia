/**
 * 
 */
package org.cms.framework;

import org.cms.jpa.dao.impl.TipoOggettoDao;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author ConsDonzelliPaolo
 * 
 */
public class CmsStarter {

	/**
	 * 
	 */
	private static CmsStarter instance;
	/**
	 * 
	 */
	@Autowired
	protected TipoOggettoDao tipoOggettoDao;

	/**
	 * 
	 */
	private CmsStarter() {
		super();
	}

	/**
	 * 
	 */
	public static void Start() {
		if (instance == null) {
			instance = new CmsStarter();
		}
		instance.load();
	}

	/**
	 * 
	 */
	private void load() {

		tipoOggettoDao.loadAll();

	}

}
