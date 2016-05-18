package org.cms.controller.croceitalia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.controller.SiteCmsController;
import org.cms.controller.edit.EditCmsController;
import org.cms.login.SoggettoUtente;
import org.cms.login.Utente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.asso.util.AssoException;


@Controller
public class StrutturaController extends EditCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected StrutturaManager _strutturaManager;
	

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "struttura/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Struttura> strutturaList = _strutturaManager.caricaStruttura();

		modelAndView.addObject("Lista", strutturaList);

		String viewName = "croceitalia/struttura/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @param idSezione
	 * @return
	 */
	@RequestMapping(value = "struttura/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Struttura> lista = _strutturaManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/struttura/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "struttura/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Struttura str) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			_strutturaManager.save(str);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Struttura> struttureList = _strutturaManager.caricaStruttura();
		modelAndView.addObject("Lista", struttureList);

		String viewName = "croceitalia/struttura/list";
		modelAndView.setViewName(viewName);
		
		return modelAndView;

	}
	
	@RequestMapping(value = "struttura/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			List<Tipologia_Struttura> tipologia= _strutturaManager.caricatipoStruttura();
			modelAndView.addObject("tipologia", tipologia);

			modelAndView.setViewName("croceitalia/struttura/create");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}
	
	
	@RequestMapping(value = "struttura/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Struttura struttura) {

		ModelAndView modelAndView = getModelAndView(request);
		
		try {
			_strutturaManager.update(struttura);
			request.setAttribute("esito", "ok");
			String viewName = "forward:/edit/struttura/update/" + struttura.getId_struttura();
			modelAndView.setViewName(viewName);
			return modelAndView;
			
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "struttura/update/{user_id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		
		try {
			Struttura struttura = (Struttura) _strutturaManager.findById(user_id);
			List<Tipologia_Struttura> tipo_struttura = _strutturaManager.caricatipoStruttura();
			
			modelAndView.addObject("tipo_struttura", tipo_struttura);
			modelAndView.addObject("struttura", struttura);/*chiave valore*/
			modelAndView.setViewName("croceitalia/struttura/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	
	@RequestMapping(value = "struttura/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {
		
		ModelAndView modelAndView = getModelAndView(request);	
		
		try {
			_strutturaManager.deleteById(id);			
			modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Struttura> struttureList = _strutturaManager.caricaStruttura();
		modelAndView.addObject("Lista", struttureList);

		String viewName = "croceitalia/struttura/list";
		modelAndView.setViewName(viewName);
		
		return modelAndView;
	}
	

//	@RequestMapping(value = "struttura/delete/{id}/{pageId}", method = RequestMethod.GET)
//	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
//			@PathVariable("pageId") String pageId) {
//
//		ModelAndView modelAndView = getModelAndView(request);
//		try {
//			_strutturaManager.deleteById(id);
//			return pageAfterDelete(request, response, pageId);
//
//		} catch (Throwable errore) {
//			return error(modelAndView, errore);
//		}
//
//	}




}
