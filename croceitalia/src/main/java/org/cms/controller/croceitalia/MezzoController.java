package org.cms.controller.croceitalia;

import it.asso.util.AssoException;
import it.asso.util.RandomIdentifier;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.controller.SiteCmsController;
import org.cms.controller.edit.EditCmsController;
import org.cms.login.SoggettoUtente;
import org.cms.login.UserDao;
import org.cms.login.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MezzoController extends EditCmsController{
	
	/**
	 * 
	 */
	@Autowired(required = true)
	protected MezzoManager _mezzoManager;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "mezzo/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Mezzo> mezziList = _mezzoManager.caricaMezzi();

		modelAndView.addObject("Lista", mezziList);

		String viewName = "croceitalia/mezzo/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

	@RequestMapping(value = "mezzo/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Mezzo mezzo) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_mezzoManager.save(mezzo);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (AssoException e) {
			modelAndView.addObject("messaggio", "Inserimento fallito");
		}

		List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
		modelAndView.addObject("Lista", mezziList);
		String viewName = "croceitalia/mezzo/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
	@RequestMapping(value = "mezzo/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			List<Tipo_mezzo> tipo_mezzo = _mezzoManager.caricaTipomezzo();
			modelAndView.addObject("tipo_mezzo", tipo_mezzo);
			modelAndView.setViewName("croceitalia/mezzo/create");

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
	@RequestMapping(value = "mezzo/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Mezzo mezzo) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_mezzoManager.update(mezzo);

			String viewName = "redirect:/edit/mezzo/update/" + mezzo.getId_mezzo();
			modelAndView.setViewName(viewName);

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
	@RequestMapping(value = "mezzo/update/{user_id}", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Mezzo mezzo = (Mezzo) _mezzoManager.findById(user_id);
			List<Tipo_mezzo> tipo_mezzo = _mezzoManager.caricaTipomezzo();
			
			modelAndView.addObject("tipo_mezzo", tipo_mezzo);
			modelAndView.addObject("mezzo", mezzo);/*chiave valore*/

			modelAndView.setViewName("croceitalia/mezzo/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	
	@RequestMapping(value = "mezzo/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

		return delete(request, response, id, LIST);
	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "mezzo/delete/{id}/{pageId}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
			@PathVariable("pageId") String pageId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_mezzoManager.deleteById(id);

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

}
