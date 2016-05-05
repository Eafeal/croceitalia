package org.cms.controller;

import it.asso.util.Utente_itf;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.tipi.impl.html.Pagina;
import org.cms.login.GuestUser;
import org.cms.login.UserDao;
import org.cms.login.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Paolo
 * 
 */
@Controller
public class LoginController extends SiteCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected UserDao	userDao;

	/**
	 * L'url di ingresso è della forma:
	 * 
	 * http://quellidellunedi.local/site/login?requestedUri=/edit/home
	 * 
	 * e' stata richiesta la pagina "requestedUri" ma non si è loggati. Prima di accedere alla pagina si richiede
	 * l'autentificazione. Quindi viene mostrata la pagina di LOGIN
	 * 
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "croceitalia/genera/login", method = RequestMethod.GET)
	public ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			Dominio dominio = getDominio(request);
			String dominioId = dominio.getUid();

			Pagina pagina = (Pagina) paginaDao.findByUid("AvcpPasso2", dominio.getUid());
			modelAndView.addObject(PAGINA, pagina);
			String renderPage = pagina.getPathRenderPage();
			// String renderPage = "compile";
			modelAndView.setViewName(renderPage);

			return modelAndView;

		} catch (Exception errore) {
			return error(modelAndView, errore);
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			HttpSession session = getSession(request);

			session.removeAttribute(UTENTE);

			Utente_itf utente = new GuestUser();
			setSessionUser(session, modelAndView, utente);

			String loginMessage = "";
			modelAndView.addObject("loginMessage", loginMessage);
			modelAndView.addObject(ErrorMsg, loginMessage);

			modelAndView.setViewName("croceitalia/croceitalia/login");
			return modelAndView;

		} catch (Exception errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "croceitalia/genera/login", method = RequestMethod.POST)
	public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		String user = request.getParameter("userId");
		String pwd = request.getParameter("password");
		String requestedUri = request.getParameter(RequestedUri);

		try {
			Dominio dominio = getDominio(request);

			Utente utente = (Utente) userDao.getUtente(user, pwd, dominio);

			if (utente == null) {
				String loginMessage = "UserErrata";
				modelAndView.addObject("loginMessage", loginMessage);
				modelAndView.addObject(ErrorMsg, loginMessage);

				// Pagina pagina = (Pagina) paginaDao.findByUid(LoginErrorPage, dominio.getUid());
				// Pagina pagina = (Pagina) paginaDao.findByUid("AvcpPasso2", dominio.getUid());
				// modelAndView.addObject(PAGINA, pagina);
				// String renderPage = "croceitalia/loginError";

				modelAndView.setViewName("croceitalia/croceitalia/login");

				return modelAndView;

			} else if (utente.isScaduto()) {

				String loginMessage = "UserScaduta";
				modelAndView.addObject("loginMessage", loginMessage);
				modelAndView.addObject(ErrorMsg, loginMessage);

				// Pagina pagina = (Pagina) paginaDao.findByUid(LoginErrorPage, dominio.getUid());
				// modelAndView.addObject(PAGINA, pagina);
				// String renderPage = pagina.getPathRenderPage();
				// modelAndView.setViewName(renderPage);

				modelAndView.setViewName("croceitalia/croceitalia/login");

				return modelAndView;

			} else if (utente.getStato().equals("01")) {

				String loginMessage = "UserNonAttiva";
				modelAndView.addObject("loginMessage", loginMessage);
				modelAndView.addObject(ErrorMsg, loginMessage);
				modelAndView.addObject("utente", utente);
				modelAndView.setViewName("croceitalia/croceitalia/login");

				return modelAndView;

			} else {
				HttpSession session = getSession(request);
				setSessionUser(session, modelAndView, utente);
				String tipoUtente = utente.getSoggettoUtente().getId();
				if (tipoUtente.equals("730UserATS"))
					modelAndView.setViewName("redirect:/site/croceitalia/documento/list");
				else
					modelAndView.setViewName("redirect:/site/croceitalia/genera/xml");

				return modelAndView;

			}

		} catch (Exception errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "croceitalia/admin/login", method = RequestMethod.POST)
	public ModelAndView adminLogin(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		String user = request.getParameter("userId");
		String pwd = request.getParameter("password");
		String requestedUri = request.getParameter(RequestedUri);

		try {
			Dominio dominio = getDominio(request);

			Utente_itf utente = userDao.getUtente(user, pwd, dominio);

			if (utente == null) {
				String loginMessage = "UserErrata";
				modelAndView.addObject("loginMessage", loginMessage);
				modelAndView.addObject(ErrorMsg, loginMessage);

				// Pagina pagina = (Pagina) paginaDao.findByUid(LoginErrorPage, dominio.getUid());
				// Pagina pagina = (Pagina) paginaDao.findByUid("AvcpPasso2", dominio.getUid());
				// modelAndView.addObject(PAGINA, pagina);
				// String renderPage = "croceitalia/loginError";

				modelAndView.setViewName("croceitalia/croceitalia/login");

				return modelAndView;
			} else {
				HttpSession session = getSession(request);
				setSessionUser(session, modelAndView, utente);

				modelAndView.setViewName("redirect:/edit/home");

				return modelAndView;

			}

		} catch (Exception errore) {
			return error(modelAndView, errore);
		}
	}

}
