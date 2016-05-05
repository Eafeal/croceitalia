/**
 * 
 */

package org.cms.framework;

import it.asso.freemarker.AssoFreemarkerServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@SuppressWarnings("serial")
public class CmsServlet extends AssoFreemarkerServlet {

	/**
	 * 
	 */
	public CmsServlet() {

		super();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init()
	 */
	@Override
	public void init() throws ServletException {

		super.init();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {

		super.init(config);
	}

}
