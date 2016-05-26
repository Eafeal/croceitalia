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
public class Tipologia_strutturaController extends EditCmsController{
	
	@Autowired(required = true)
	protected Tipologia_strutturaManager _tipo_strutturaManager;
	
	@Autowired(required = true)
	protected StrutturaManager _strutturaManager;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "tipo_struttura/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Tipologia_Struttura> tipo_struttureList = _tipo_strutturaManager.caricaTipo_strutture();

		modelAndView.addObject("Lista", tipo_struttureList);

		String viewName = "croceitalia/tipo_struttura/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}
	
	@RequestMapping(value = "tipo_struttura/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Tipologia_Struttura> lista = _tipo_strutturaManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/tipo_struttura/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "tipo_struttura/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Tipologia_Struttura tipo_struttura) {

		ModelAndView modelAndView = getModelAndView(request);
		
		try {
			_tipo_strutturaManager.save(tipo_struttura);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Tipologia_Struttura> tipo_struttureList = _tipo_strutturaManager.caricaTipo_strutture();
		modelAndView.addObject("Lista", tipo_struttureList);
		
		String viewName = "croceitalia/tipo_struttura/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
	
	@RequestMapping(value = "tipo_struttura/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			// List<SoggettoUtente> soggetti = userDao.caricaSoggettiUtente();
			// modelAndView.addObject("soggetti", soggetti);

			modelAndView.setViewName("croceitalia/tipo_struttura/create");

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
	@RequestMapping(value = "tipo_struttura/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Tipologia_Struttura tipo_struttura) {

		ModelAndView modelAndView = getModelAndView(request);
		

		try {
			
			if (tipo_struttura==null) {
				throw new AssoException("Tipo Struttura non valido");
			}
			
			_tipo_strutturaManager.update(tipo_struttura);
			
			
			request.setAttribute("esito", "ok");
			String viewName = "forward:/edit/tipo_struttura/update/" + tipo_struttura.getId_tipologia_struttura();
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
	@RequestMapping(value = "tipo_struttura/update/{user_id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Tipologia_Struttura tipo_struttura = (Tipologia_Struttura) _tipo_strutturaManager.findById(user_id);
			modelAndView.addObject("tipo_struttura", tipo_struttura);
			modelAndView.setViewName("croceitalia/tipo_struttura/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "tipo_struttura/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			List<Struttura> tipo_strutture = _strutturaManager.listaPerTipo_strutture(id);
//			String a="";
//			for(int i=0;i<patologie.size();i++){
//				a=a+"  "+ patologie.get(i).getNum_documento().toString();
//			}
			if (tipo_strutture.size() > 0) {
				modelAndView.addObject("messaggio", "Struttura utilizzata, cancellazione impossibile.");
				
			} else {
				_tipo_strutturaManager.deleteById(id);
				modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
			}
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Tipologia_Struttura> tipo_struttureList = _tipo_strutturaManager.caricaTipo_strutture();
		modelAndView.addObject("Lista", tipo_struttureList);

		String viewName = "croceitalia/tipo_struttura/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

}
