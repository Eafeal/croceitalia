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
public class Tipo_clienteController extends EditCmsController{
	
	@Autowired(required = true)
	protected Tipo_clienteManager _tipo_clienteManager;
	
	@Autowired(required = true)
	protected ClienteManager _clienteManager;
	
	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "tipo_cliente/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Tipo_cliente> tipo_clientiList = _tipo_clienteManager.caricaTipo_clienti();

		modelAndView.addObject("Lista", tipo_clientiList);

		String viewName = "croceitalia/tipo_cliente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}
	
	@RequestMapping(value = "tipo_cliente/list", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String cerca = request.getParameter("cerca");

		try {
			List<Tipo_cliente> lista = _tipo_clienteManager.search(cerca);
			modelAndView.addObject("Lista", lista);

			String viewName = "croceitalia/tipo_cliente/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "tipo_cliente/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Tipo_cliente tipo_cliente) {

		ModelAndView modelAndView = getModelAndView(request);
		
		try {
			_tipo_clienteManager.save(tipo_cliente);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Tipo_cliente> tipo_clientiList = _tipo_clienteManager.caricaTipo_clienti();
		modelAndView.addObject("Lista", tipo_clientiList);
		
		String viewName = "croceitalia/tipo_cliente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
	
	@RequestMapping(value = "tipo_cliente/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			// List<SoggettoUtente> soggetti = userDao.caricaSoggettiUtente();
			// modelAndView.addObject("soggetti", soggetti);

			modelAndView.setViewName("croceitalia/tipo_cliente/create");

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
	@RequestMapping(value = "tipo_cliente/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Tipo_cliente tipo_cliente) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_tipo_clienteManager.update(tipo_cliente);
			request.setAttribute("esito", "ok");
//			modelAndView.addObject("esito", "ok");
			String viewName = "forward:/edit/tipo_cliente/update/" + tipo_cliente.getId_tipo_cliente();
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
	@RequestMapping(value = "tipo_cliente/update/{user_id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Tipo_cliente tipo_cliente = (Tipo_cliente) _tipo_clienteManager.findById(user_id);
			modelAndView.addObject("tipo_cliente", tipo_cliente);
			modelAndView.setViewName("croceitalia/tipo_cliente/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "tipo_cliente/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			List<Cliente> clienti = _clienteManager.listaPerTipo_clienti(id);
//			String a="";
//			for(int i=0;i<patologie.size();i++){
//				a=a+"  "+ patologie.get(i).getNum_documento().toString();
//			}
			if (clienti.size() > 0) {
				modelAndView.addObject("messaggio", "Tipo cliente utilizzato, cancellazione impossibile.");
				
			} else {
				_tipo_clienteManager.deleteById(id);
				modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
			}
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Tipo_cliente> tipo_clientiList = _tipo_clienteManager.caricaTipo_clienti();
		modelAndView.addObject("Lista", tipo_clientiList);

		String viewName = "croceitalia/tipo_cliente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

}
