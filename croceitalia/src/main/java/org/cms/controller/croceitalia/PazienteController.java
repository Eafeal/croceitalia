package org.cms.controller.croceitalia;

import it.asso.util.AssoException;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.controller.edit.EditCmsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PazienteController extends EditCmsController {

	@Autowired(required = true)
	protected PazienteManager	_pazienteManager;

	/**
	 * @param request
	 * @param response
	 * @param idSezione
	 * @return
	 */
	@RequestMapping(value = "paziente/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {

			// MODEL
			List<Patologia> pato = _pazienteManager.caricaPatologia();
			modelAndView.addObject("patologia", pato);

			List<Paziente> lista = _pazienteManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/paziente/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping(value = "paziente/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Paziente> pazientiList = _pazienteManager.caricaPazienti();
		modelAndView.addObject("Lista", pazientiList);

		List<Patologia> pato = _pazienteManager.caricaPatologia();
		modelAndView.addObject("patologia", pato);

		String viewName = "croceitalia/paziente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

	@RequestMapping(value = "paziente/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Paziente paziente) {

		ModelAndView modelAndView = getModelAndView(request);
		/*
		 * // MODEL
		 * Paziente paziente = new Paziente();
		 * paziente.setNome(paziente1.getNome());
		 * paziente.setCognome(paziente1.getCognome());
		 * paziente.setTelefono1(paziente1.getTelefono1());
		 * paziente.setTelefono2(paziente1.getTelefono2());
		 * paziente.setFk_id_patologia(1);
		 * 
		 * paziente.setSesso(paziente1.getSesso());
		 * 
		 * // Date data_nascita = Calendar.getInstance().getTime();
		 * // paziente.setData_nascita(data_nascita);
		 * 
		 * paziente.setData_nascita(paziente1.getData_nascita());
		 * paziente.setVia(paziente1.getVia());
		 * paziente.setComune(paziente1.getComune());
		 * paziente.setCap(paziente1.getCap());
		 * paziente.setProvincia(paziente1.getProvincia());
		 */
		try {
			_pazienteManager.save(paziente);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (AssoException e) {
			// Inserimento fallito
			modelAndView.addObject("messaggio", "Inserimento fallito");
		}

		List<Paziente> pazientiList = _pazienteManager.caricaPazienti();

		modelAndView.addObject("ListaPazienti", pazientiList);

		String viewName = "redirect:/edit/paziente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}

	@RequestMapping(value = "paziente/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			// List<SoggettoUtente> soggetti = userDao.caricaSoggettiUtente();
			// modelAndView.addObject("soggetti", soggetti);

			List<Patologia> pato = _pazienteManager.caricaPatologia();
			modelAndView.addObject("patologia", pato);

			modelAndView.setViewName("croceitalia/paziente/create");

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
	@RequestMapping(value = "paziente/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Paziente paziente) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_pazienteManager.update(paziente);

			String viewName = "redirect:/edit/paziente/update/" + paziente.getId_paziente();
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
	@RequestMapping(value = "paziente/update/{user_id}", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Paziente paziente = (Paziente) _pazienteManager.findById(user_id);
			List<Patologia> pato = _pazienteManager.caricaPatologia();

			modelAndView.addObject("paziente", paziente);/* chiave valore */
			modelAndView.addObject("patologia", pato);
			modelAndView.setViewName("croceitalia/paziente/update");

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
	@RequestMapping(value = "paziente/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

		return delete(request, response, id, LIST);
	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "paziente/delete/{id}/{pageId}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
			@PathVariable("pageId") String pageId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_pazienteManager.deleteById(id);

			return pageAfterDelete(request, response, pageId);

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

}
