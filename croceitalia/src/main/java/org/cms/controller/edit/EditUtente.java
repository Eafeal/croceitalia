package org.cms.controller.edit;

import it.asso.util.RandomIdentifier;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.login.SoggettoUtente;
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
public class EditUtente extends EditCmsController {

	/**
     * 
     */
	@Autowired(required = true)
	protected UserDao	userDao;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "utente/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			List<SoggettoUtente> soggetti = userDao.caricaSoggettiUtente();
			modelAndView.addObject("soggetti", soggetti);

			String password = RandomIdentifier.GetInstance().getPassword(8);
			modelAndView.addObject("password", password);

			modelAndView.setViewName("edit/utente/create");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "utente/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

		return delete(request, response, id, LIST);
	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "utente/delete/{id}/{pageId}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
			@PathVariable("pageId") String pageId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			userDao.deleteById(id);

			return pageAfterDelete(request, response, pageId);

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	/**
	 * @param request
	 * @param response
	 * @param utente
	 * @return
	 */
	@RequestMapping(value = "utente/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Utente utente) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			userDao.update(utente);

			String viewName = "redirect:/edit/utente/update/" + utente.getId();
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.cms.controller.edit.EditCmsController#getModelAndView(javax.servlet
	 * .http.HttpServletRequest)
	 */
	@Override
	protected ModelAndView getModelAndView(HttpServletRequest request) {

		ModelAndView modelAndView = super.getModelAndView(request);
		Object cognome = modelAndView.getModelMap().get("cognome");
		if (Util.isEmpty(cognome)) {
			modelAndView.addObject("cognome", "");
		}

		modelAndView.addObject("title", "Gestione Utenze");

		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @param idSezione
	 * @return
	 */
	@Override
	@RequestMapping(value = "utente/list", method = RequestMethod.POST)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Utente_itf> lista = userDao.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "edit/utente/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "utente/list", method = RequestMethod.GET)
	public ModelAndView listGet(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			List<Utente_itf> lista = userDao.search("");
			modelAndView.addObject("Lista", lista);

			String viewName = "edit/utente/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param utente
	 * @return
	 */
	@RequestMapping(value = "utente/save", method = RequestMethod.POST)
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Utente utente) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			userDao.save(utente);

			String viewName = "redirect:/edit/utente/update/" + utente.getId();

			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param idSezione
	 * @return
	 * @RequestMapping(value = "utente/list", method = RequestMethod.GET)
	 *                       public ModelAndView search(HttpServletRequest request, HttpServletResponse response) {
	 * 
	 *                       ModelAndView modelAndView = getModelAndView(request);
	 *                       try {
	 *                       String viewName = "edit/utente/list";
	 *                       modelAndView.setViewName(viewName);
	 * 
	 *                       return modelAndView;
	 * 
	 *                       } catch (Throwable errore) {
	 *                       return error(modelAndView, errore);
	 *                       }
	 * 
	 *                       }
	 */
	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "utente/update/{user_id}", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Utente utente = (Utente) userDao.findById(user_id);
			modelAndView.addObject(UTENTE, utente);
			Timestamp eULTLOGIN = utente.getE_ULT_LOG_IN();
			modelAndView.addObject("eULTLOGIN", eULTLOGIN);

			modelAndView.setViewName("edit/utente/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
}
