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
public class PatologiaController extends EditCmsController {
	
	@Autowired(required = true)
	protected PatologiaManager _patologiaManager;
	
	@Autowired(required = true)
	protected PazienteManager _pazienteManager;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "patologia/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Patologia> patologieList = _patologiaManager.caricaPatologie();

		modelAndView.addObject("Lista", patologieList);

		String viewName = "croceitalia/patologia/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}
	
	@RequestMapping(value = "patologia/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Patologia> lista = _patologiaManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/patologia/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "patologia/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Patologia patologia) {

		ModelAndView modelAndView = getModelAndView(request);
		
		try {
			_patologiaManager.save(patologia);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Patologia> patologieList = _patologiaManager.caricaPatologie();
		modelAndView.addObject("Lista", patologieList);
		
		String viewName = "croceitalia/patologia/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
	
	@RequestMapping(value = "patologia/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			// List<SoggettoUtente> soggetti = userDao.caricaSoggettiUtente();
			// modelAndView.addObject("soggetti", soggetti);

			modelAndView.setViewName("croceitalia/patologia/create");

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
	@RequestMapping(value = "patologia/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Patologia patologia) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_patologiaManager.update(patologia);
			request.setAttribute("esito", "ok");
			String viewName = "forward:/edit/patologia/update/" + patologia.getId_patologia();
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
	@RequestMapping(value = "patologia/update/{user_id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Patologia patologia = (Patologia) _patologiaManager.findById(user_id);
			modelAndView.addObject("patologia", patologia);
			modelAndView.setViewName("croceitalia/patologia/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "patologia/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			List<Paziente> patologie = _pazienteManager.listaPerPatologie(id);
//			String a="";
//			for(int i=0;i<patologie.size();i++){
//				a=a+"  "+ patologie.get(i).getNum_documento().toString();
//			}
			if (patologie.size() > 0) {
				modelAndView.addObject("messaggio", "Patologia utilizzata, cancellazione impossibile.");
				
			} else {
				_patologiaManager.deleteById(id);
				modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
			}
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Patologia> patologieList = _patologiaManager.caricaPatologie();
		modelAndView.addObject("Lista", patologieList);

		String viewName = "croceitalia/patologia/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

}
