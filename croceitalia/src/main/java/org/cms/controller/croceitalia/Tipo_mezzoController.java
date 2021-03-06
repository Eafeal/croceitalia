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
public class Tipo_mezzoController extends EditCmsController{
	
	@Autowired(required = true)
	protected Tipo_mezzoManager _tipo_mezzoManager;
	
	@Autowired(required = true)
	protected MezzoManager _mezzoManager;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "tipo_mezzo/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Tipo_mezzo> tipo_mezziList = _tipo_mezzoManager.caricaTipo_mezzi();

		modelAndView.addObject("Lista", tipo_mezziList);

		String viewName = "croceitalia/tipo_mezzo/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}
	
	@RequestMapping(value = "tipo_mezzo/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Tipo_mezzo> lista = _tipo_mezzoManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/tipo_mezzo/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "tipo_mezzo/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Tipo_mezzo tipo_mezzo) {

		ModelAndView modelAndView = getModelAndView(request);
		
		try {
			_tipo_mezzoManager.save(tipo_mezzo);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Tipo_mezzo> tipo_mezziList = _tipo_mezzoManager.caricaTipo_mezzi();
		modelAndView.addObject("Lista", tipo_mezziList);
		
		String viewName = "croceitalia/tipo_mezzo/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
	
	@RequestMapping(value = "tipo_mezzo/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			// List<SoggettoUtente> soggetti = userDao.caricaSoggettiUtente();
			// modelAndView.addObject("soggetti", soggetti);

			modelAndView.setViewName("croceitalia/tipo_mezzo/create");

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
	@RequestMapping(value = "tipo_mezzo/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Tipo_mezzo tipo_mezzo) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_tipo_mezzoManager.update(tipo_mezzo);
			request.setAttribute("esito", "ok");
			String viewName = "forward:/edit/tipo_mezzo/update/" + tipo_mezzo.getId_tipo_mezzo();
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
	@RequestMapping(value = "tipo_mezzo/update/{user_id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Tipo_mezzo tipo_mezzo = (Tipo_mezzo) _tipo_mezzoManager.findById(user_id);
			modelAndView.addObject("tipo_mezzo", tipo_mezzo);
			modelAndView.setViewName("croceitalia/tipo_mezzo/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "tipo_mezzo/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			List<Mezzo> tipo_mezzi = _mezzoManager.listaPerTipo_mezzi(id);
//			String a="";
//			for(int i=0;i<patologie.size();i++){
//				a=a+"  "+ patologie.get(i).getNum_documento().toString();
//			}
			if (tipo_mezzi.size() > 0) {
				modelAndView.addObject("messaggio", "Mezzo utilizzato, cancellazione impossibile.");
				
			} else {
				_tipo_mezzoManager.deleteById(id);
				modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
			}
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Tipo_mezzo> tipo_mezziList = _tipo_mezzoManager.caricaTipo_mezzi();
		modelAndView.addObject("Lista", tipo_mezziList);

		String viewName = "croceitalia/tipo_mezzo/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

}
