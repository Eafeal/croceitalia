package org.cms.controller.croceitalia;

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

import it.asso.util.AssoException;

@Controller
public class BancaController extends EditCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected BancaManager _bancaManager;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping(value = "banca/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Banca> bancheList = _bancaManager.caricaBanche();
		modelAndView.addObject("Lista", bancheList);

		String viewName = "croceitalia/banca/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}
	
	@RequestMapping(value = "banca/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Banca> lista = _bancaManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/banca/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "banca/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Banca banca) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			_bancaManager.save(banca);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Banca> bancheList = _bancaManager.caricaBanche();
		modelAndView.addObject("Lista", bancheList);

		String viewName = "croceitalia/banca/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}

	@RequestMapping(value = "banca/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			// List<SoggettoUtente> soggetti = userDao.caricaSoggettiUtente();
			// modelAndView.addObject("soggetti", soggetti);

			modelAndView.setViewName("croceitalia/banca/create");

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
	@RequestMapping(value = "banca/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Banca banca) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_bancaManager.update(banca);
			request.setAttribute("esito", "ok");
			String viewName = "forward:/edit/banca/update/" + banca.getId_banca();
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
	@RequestMapping(value = "banca/update/{user_id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Banca banca = (Banca) _bancaManager.findById(user_id);
			modelAndView.addObject("banca", banca);
			Object esito = request.getAttribute("esito");
			// if (esito != null) {
			// modelAndView.addObject("esito", "ok");/* chiave valore */
			// } else {
			// modelAndView.addObject("esito", "");
			// }
			modelAndView.setViewName("croceitalia/banca/update");
			
			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "banca/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,	@PathVariable("id") String id) {
		
		ModelAndView modelAndView = getModelAndView(request);
		try {
			_bancaManager.deleteById(id);			
			modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
		
		List<Banca> bancheList = _bancaManager.caricaBanche();
		modelAndView.addObject("Lista", bancheList);

		String viewName = "croceitalia/banca/list";
		modelAndView.setViewName(viewName);
		
		return modelAndView;
	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "banca/delete/{id}/{pageId}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
			@PathVariable("pageId") String pageId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_bancaManager.deleteById(id);

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
