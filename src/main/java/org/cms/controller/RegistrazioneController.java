package org.cms.controller;

import it.asso.util.ApplConfig;
import it.asso.util.AssoApplEMail;
import it.asso.util.AssoException;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.object.impl.Dominio;
import org.cms.login.UserDao;
import org.cms.login.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Paolo
 * 
 */
@Controller
public class RegistrazioneController extends SiteCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected UserDao	userDao;

	String				_serverHome	= ApplConfig.GetParameter("serverHome");

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

	@RequestMapping(value = "croceitalia/registrazione", method = RequestMethod.POST)
	public ModelAndView registrazione(HttpServletRequest request, HttpServletResponse response, Utente utente) {

		ModelAndView modelAndView = getModelAndView(request);
		try {

			// verifico se la mail è già presente

			Dominio dominio = getDominio(request);
			Utente utenteCheck = (Utente) userDao.getUtenteByEmail(utente.getEmail(), dominio);

			if (utenteCheck == null) {

				// se non esiste procedo con la registrazione e invio mail

				userDao.save(utente);

				try {
					inviaEmailCredenziali(utente, "Registazione avvenuta con successo.");
					modelAndView.addObject("esito", "1");
					modelAndView.addObject("utente", utente);

				} catch (Throwable e) {
					new AssoException("Non e' stato possibile inviare la mail di notifica", e);
				}
			} else {

				if (utenteCheck.isInRegistrazione())
					modelAndView.addObject("esito", "2");
				if (utenteCheck.isAttivo())
					modelAndView.addObject("esito", "3");
				modelAndView.addObject("utente", utenteCheck);
			}

			// String viewName = "redirect:/index.html";
			// modelAndView.setViewName(viewName);

			modelAndView.addObject("UserMsg", "");
			modelAndView.setViewName("croceitalia/registrazione/result");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "croceitalia/conferma-registrazione/{user_id}", method = RequestMethod.GET)
	public ModelAndView confermaRegistrazione(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Utente utente = (Utente) userDao.findById(user_id);

			if (utente.isInRegistrazione()) {
				utente.setStato(utente.inProva);

				Calendar today = Calendar.getInstance();
				today.add(Calendar.DATE, 7);
				utente.setDataScadenza(new Timestamp(today.getTimeInMillis()));

				userDao.update(utente);

				// modelAndView.setViewName("edit/utente/update");

				modelAndView.addObject("esito", "5");
				modelAndView.addObject("utente", utente);

				modelAndView.setViewName("croceitalia/registrazione/result");
			}

			else {
				modelAndView.setViewName("redirect:/site/croceitalia/genera/xml");
			}

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "croceitalia/recupero-istruzioni/{user_id}", method = RequestMethod.GET)
	public ModelAndView recuperoIstruzioni(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {

			// verifico se la mail è già presente

			Utente utente = (Utente) userDao.findById(user_id);

			try {
				inviaEmailCredenziali(utente, "Recupero credenziali.");
				modelAndView.addObject("esito", "4");
				modelAndView.addObject("utente", utente);

			} catch (Throwable e) {
				new AssoException("Non e' stato possibile inviare la mail di notifica", e);
			}

			// String viewName = "redirect:/index.html";
			// modelAndView.setViewName(viewName);

			modelAndView.addObject("UserMsg", "");

			modelAndView.setViewName("croceitalia/registrazione/result");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "croceitalia/password-dimenticata/{user_id}", method = RequestMethod.GET)
	public ModelAndView passwordDimenticata(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {

			// verifico se la mail è già presente

			Dominio dominio = getDominio(request);
			Utente utente = (Utente) userDao.getUtenteByEmail(user_id, dominio);

			if (utente == null) {
				modelAndView.addObject("esito", "7");
				modelAndView.addObject("utente", "");
			} else {
				try {
					inviaEmailRecuperoPassword(utente, "Recupero credenziali.");
					modelAndView.addObject("esito", "6");
					modelAndView.addObject("utente", utente);

				} catch (Throwable e) {
					new AssoException("Non e' stato possibile inviare la mail di notifica", e);
				}
			}
			// String viewName = "redirect:/index.html";
			// modelAndView.setViewName(viewName);

			modelAndView.addObject("UserMsg", "");

			modelAndView.setViewName("croceitalia/registrazione/result");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "croceitalia/password-dimenticata", method = RequestMethod.GET)
	public ModelAndView passwordDimenticata(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {

			modelAndView.setViewName("croceitalia/registrazione/recuperoPassword");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	public void inviaEmailCredenziali(Utente utente, String oggetto) {

		try {
			AssoApplEMail mail = new AssoApplEMail(utente.getEmail());

			mail.addBCC(ApplConfig.GetParameter("to"));

			String subject = "730-PRECOMPILATO-FROM-EXCEL-TO-XML " + oggetto;
			mail.setSubject(subject);
			String body = "";
			body += "730-PRECOMPILATO-FROM-EXCEL-TO-XML <br>" + oggetto;
			body += "<br><br>";
			body += "Di seguito le tue credenzili di accesso e registrazione.";
			body += "<br><br>";
			body += "Tipologia utente: <strong>" + utente.getSoggettoUtente().getId() + " - "
					+ utente.getSoggettoUtente().getDescription() + "</strong>";
			body += "<br><br>";
			body += "Ragione sociale: <strong>" + utente.getRagioneSociale() + "</strong>";
			body += "<br><br>";
			body += "Cognome: <strong>" + utente.getCognome() + "</strong>";
			body += "<br><br>";
			body += "Nome: <strong>" + utente.getNome() + "</strong>";
			body += "<br><br>";
			body += "UserId: <strong>" + utente.getUserId() + "</strong>";
			body += "<br><br>";
			body += "Password: <strong>" + utente.getPassword() + "</strong>";
			body += "<br><br>";
			body += "<a href=\"" + _serverHome + "/site/croceitalia/conferma-registrazione/" + utente.getId()
					+ "\">Clicca qui per completare la tua registrazione</a>";
			body += "<br><br>";
			body += "Stampa e/o conserva questa email per gli accessi futuri.";
			mail.setBody(body, "text/html");
			mail.send();
			mail = null;

		} catch (Throwable e) {
			new AssoException("Non e' stato possibile inviare la mail di notifica", e);
		}
	}

	public void inviaEmailRecuperoPassword(Utente utente, String oggetto) {

		try {
			AssoApplEMail mail = new AssoApplEMail(utente.getEmail());

			mail.addBCC(ApplConfig.GetParameter("to"));

			String subject = "730-PRECOMPILATO-FROM-EXCEL-TO-XML " + oggetto;
			mail.setSubject(subject);
			String body = "";
			body += "730-PRECOMPILATO-FROM-EXCEL-TO-XML <br>" + oggetto;
			body += "<br><br>";
			body += "Di seguito le tue credenzili di accesso e registrazione.";
			body += "<br><br>";
			body += "UserId: <strong>" + utente.getUserId() + "</strong>";
			body += "<br><br>";
			body += "Password: <strong>" + utente.getPassword() + "</strong>";
			body += "<br><br>";
			body += "<a href=\"" + _serverHome + "/login.html\">Clicca qui per accedere al servizio</a>";
			body += "<br><br>";
			body += "Stampa e/o conserva questa email per gli accessi futuri.";
			mail.setBody(body, "text/html");
			mail.send();
			mail = null;

		} catch (Throwable e) {
			new AssoException("Non e' stato possibile inviare la mail di notifica", e);
		}

	}
}
