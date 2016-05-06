package org.cms.security;

import it.asso.util.ApplConfig;
import it.asso.util.AssoConst_itf;
import it.asso.util.AssoLogger;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckUser implements Filter {

	/**
	 * The filter configuration object we are associated with. If this value is
	 * null, this filter instance is not currently configured.
	 */
	private FilterConfig	filterConfig	= null;

	/**
	 * @param request
	 * @return
	 * @throws Exception
	 */
	private boolean checkUser(HttpServletRequest request) {

		boolean checkUser = false;

		HttpSession session = request.getSession(false);
		if (session == null) {
			// La Sessione NON esiste
			session = request.getSession(true);
		}

		Utente_itf utente = (Utente_itf) session.getAttribute(AssoConst_itf.UTENTE);
		if (utente == null) {
			// L'utente NON esiste
		} else {
			// L'utente esiste
			if (utente.isAdmin()) {
				checkUser = true;
			}
		}
		return checkUser;
	}

	/**
	 * Take this filter out of service.
	 */
	public void destroy() {

		this.filterConfig = null;

	}

	/**
	 * Time the processing that is performed by all subsequent filters in the
	 * current filter stack, including the ultimately invoked servlet.
	 * 
	 * @param request
	 *            The servlet request we are processing
	 * @param result
	 *            The servlet response we are creating
	 * @param chain
	 *            The filter chain we are processing
	 * @exception IOException
	 *                if an input/output error occurs
	 * @exception ServletException
	 *                if a servlet error occurs
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		if (filterConfig == null)
			return;

		boolean checkUser;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;
		try {
			checkUser = checkUser(req);

		} catch (Exception e) {
			AssoLogger.GetInstance().logError(e);
			AssoLogger.GetInstance().logSecurity(e);
			checkUser = false;
		}

		if (checkUser) {
			// Pass control on to the next filter
			chain.doFilter(request, response);
		} else {
			// REDIRECT to forbidden.view
			String encodeRedirectURI = getEncodedSpringMvcUri(req, res);
			String contextPath = req.getContextPath();

			String dominioContextPath = ApplConfig.GetParameter("Dominio" + contextPath);
			if (dominioContextPath == null) {
				AssoLogger.GetInstance().logInfo("ERRORE! Parametro mancante in configurazione: Dominio" + contextPath);
				dominioContextPath = "site";
			}
			String location = "/" + dominioContextPath + "/login?requestedUri=" + encodeRedirectURI;
			res.sendRedirect(location);
		}
	}

	/**
	 * Questo metodo estrae dall'uri richiesto un altro tipo di uri, denominato
	 * mvc-uri, che serve ad invocare i controller di SpringMVC
	 * 
	 * @param res
	 * @param req
	 * @return
	 */
	private String getEncodedSpringMvcUri(HttpServletRequest req, HttpServletResponse res) {

		String requestURI = req.getRequestURI();
		String contextPath = req.getContextPath();
		String requestedUri = Util.getRequestMvcUri(requestURI, contextPath);
		String encodeRedirectURI = res.encodeRedirectURL(requestedUri);

		return encodeRedirectURI;
	}

	/**
	 * Place this filter into service.
	 * 
	 * @param filterConfig
	 *            The filter configuration object
	 */
	public void init(FilterConfig filterConfig) throws ServletException {

		this.filterConfig = filterConfig;
	}

}
