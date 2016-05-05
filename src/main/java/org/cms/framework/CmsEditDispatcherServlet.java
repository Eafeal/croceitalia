/**
 * 
 */

package org.cms.framework;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.ModelUser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.object.impl.ModelLoader;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Paolo
 * 
 */
@SuppressWarnings("serial")
public class CmsEditDispatcherServlet extends DispatcherServlet {

	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

		StringBuffer requestURL = request.getRequestURL();
		String message = "RequestURL=" + requestURL;
		AssoLogger.GetInstance().logInfo(message);

		try {
			super.doService(request, response);

		} catch (Throwable e) {
			new AssoException(e);
			try {
				response.sendRedirect("/error500.html");
			} catch (Throwable e2) {
				response.getWriter().write("<h1>Si è verificato un errore applicativo.</h1>");
				response.getWriter().write(
						"<h1>Se l'errore dovesse persistere ti preghiamo di segnarlo al nostro servizio clienti</h1>");
				response.getWriter().write("<h1>Grazie.</h1>");
			}
		} finally {
			ModelUser.destroy();
			ModelLoader.destroy();
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.FrameworkServlet#initFrameworkServlet()
	 */
	@Override
	protected void initFrameworkServlet() throws ServletException {

		super.initFrameworkServlet();
	}

}
