package org.cms.controller.croceitalia;

import it.asso.util.AssoException;

import java.io.PrintWriter;
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
	protected PazienteManager _pazienteManager;

	@Autowired(required = true)
	protected DocumentoTestataManager _documentoManager;

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

		try {
			_pazienteManager.save(paziente);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Paziente> pazientiList = _pazienteManager.caricaPazienti();
		modelAndView.addObject("Lista", pazientiList);

		String viewName = "croceitalia/paziente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}

	@RequestMapping(value = "paziente/save2")
	protected ModelAndView save2(HttpServletRequest request, HttpServletResponse response, Paziente paziente) {

		ModelAndView modelAndView = getModelAndView(request);
		String messaggio = "";
		try {
			_pazienteManager.save(paziente);
			messaggio = "OK," + paziente.getNome() + " " + paziente.getCognome() + "," + paziente.getId_paziente();

		} catch (AssoException e) {
			// Inserimento fallito
			messaggio = "KO";
		}

		try {
			PrintWriter out = response.getWriter();
			response.getWriter().write(messaggio);// <--- Qua viene passato il
													// valore inserito
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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
			modelAndView.addObject("esito", "ok");
			String viewName = "forward:/edit/paziente/update/" + paziente.getId_paziente();
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
	@RequestMapping(value = "paziente/update/{user_id}")
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
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			List<Documento_Testata> pazienti = _documentoManager.listaPerPaziente(id);
			String a="";
			for(int i=0;i<pazienti.size();i++){
				a=a+"  "+ pazienti.get(i).getNum_documento().toString();
			}
			if (pazienti.size() > 0) {
				modelAndView.addObject("messaggio", "Cliente utilizzato, cancellazione impossibile. Il cliente è utilizzato nei documenti numero: "+a);

			} else {
				_pazienteManager.deleteById(id);
				modelAndView.addObject("messaggio", "Cancellazione effettuata correttamente");
			}
		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

		List<Paziente> pazientiList = _pazienteManager.caricaPazienti();
		modelAndView.addObject("Lista", pazientiList);

		String viewName = "croceitalia/paziente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

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
