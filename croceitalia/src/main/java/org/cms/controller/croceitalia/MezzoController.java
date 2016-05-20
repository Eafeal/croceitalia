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
public class MezzoController extends EditCmsController{
	
	/**
	 * 
	 */
	@Autowired(required = true)
	protected MezzoManager _mezzoManager;
	
	@Autowired(required = true)
	protected DocumentoTestataManager _documentoManager;

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
	
	@RequestMapping(value = "mezzo/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Mezzo> lista = _mezzoManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/mezzo/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "mezzo/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Mezzo mezzo) {

		ModelAndView modelAndView = getModelAndView(request);
		
		String costo=request.getParameter("c_km");
		String franchigia = request.getParameter("f_km");
		String qf = request.getParameter("qfs");
		
		
		if (qf.equals("")) qf = "0";
		mezzo.setQf(qf);
		
		if (costo.equals("")) costo = "0";
		mezzo.setCosto_km(costo);
		
		if (franchigia.equals("")) franchigia = "0";
		mezzo.setFranchigia_km(franchigia);
		
		try {
			_mezzoManager.save(mezzo);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
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
		
		String costo=request.getParameter("c_km");
		String franchigia = request.getParameter("f_km");
		String qf = request.getParameter("qfs");
		
		
		if (qf.equals("")) qf = "0";
		mezzo.setQf(qf);
		
		if (costo.equals("")) costo = "0";
		mezzo.setCosto_km(costo);
		
		if (franchigia.equals("")) franchigia = "0";
		mezzo.setFranchigia_km(franchigia);
		
		try {
			_mezzoManager.update(mezzo);
			request.setAttribute("esito", "ok");
			String viewName = "forward:/edit/mezzo/update/" + mezzo.getId_mezzo();
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
	@RequestMapping(value = "mezzo/update/{user_id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Mezzo mezzo = (Mezzo) _mezzoManager.findById(user_id);
			modelAndView.addObject("mezzo", mezzo);/*chiave valore*/
			Object esito = request.getAttribute("esito");
			
			List<Tipo_mezzo> tipo_mezzo = _mezzoManager.caricaTipomezzo();
			modelAndView.addObject("tipo_mezzo", tipo_mezzo);
			modelAndView.setViewName("croceitalia/mezzo/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	
	@RequestMapping(value = "mezzo/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);
		
		try {
			List<Documento_Testata> mezzi = _documentoManager.listaPerBanche(id);
			String a="";
			for(int i=0;i<mezzi.size();i++){
				a=a+" "+ mezzi.get(i).getNum_documento().toString();
			}
			if (mezzi.size() > 0) {
				modelAndView.addObject("messaggio", "Cliente utilizzato, cancellazione impossibile. Il cliente è utilizzato nei documenti numero: "+a);
			} else {
			_mezzoManager.deleteById(id);			
			modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
			}
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}


		List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
		modelAndView.addObject("Lista", mezziList);

		String viewName = "croceitalia/mezzo/list";
		modelAndView.setViewName(viewName);
		
		return modelAndView;
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
