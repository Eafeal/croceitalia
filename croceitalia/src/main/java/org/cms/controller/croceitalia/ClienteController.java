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
public class ClienteController extends EditCmsController{
	
	/**
	 * 
	 */
	@Autowired(required = true)
	protected ClienteManager _clienteManager;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "cliente/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Cliente> clientiList = _clienteManager.caricaClienti();

		modelAndView.addObject("Lista", clientiList);

		String viewName = "croceitalia/cliente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

	@RequestMapping(value = "cliente/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Cliente cliente) {

		ModelAndView modelAndView = getModelAndView(request);		
		try {
			_clienteManager.save(cliente);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (AssoException e) {
			modelAndView.addObject("messaggio", "Inserimento fallito");
		}

		List<Cliente> clientiList = _clienteManager.caricaClienti();
		modelAndView.addObject("Lista", clientiList);
		String viewName = "croceitalia/cliente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
	@RequestMapping(value = "cliente/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {

			List<Tipo_cliente> tipo_cliente = _clienteManager.caricaTipocliente();
			modelAndView.addObject("tipo_cliente", tipo_cliente);
			modelAndView.setViewName("croceitalia/cliente/create");

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
	@RequestMapping(value = "cliente/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Cliente cliente) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_clienteManager.update(cliente);

			String viewName = "redirect:/edit/cliente/update/" + cliente.getId_cliente();
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
	@RequestMapping(value = "cliente/update/{user_id}", method = RequestMethod.GET)
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("user_id") String user_id) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			Cliente cliente = (Cliente) _clienteManager.findById(user_id);
			
			List<Tipo_cliente> tipo_cliente = _clienteManager.caricaTipocliente();
			
			modelAndView.addObject("tipo_cliente", tipo_cliente);
			modelAndView.addObject("cliente", cliente);/*chiave valore*/
			modelAndView.setViewName("croceitalia/cliente/update");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	
	@RequestMapping(value = "cliente/delete/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

		return delete(request, response, id, LIST);
	}

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "cliente/delete/{id}/{pageId}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
			@PathVariable("pageId") String pageId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			_clienteManager.deleteById(id);

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