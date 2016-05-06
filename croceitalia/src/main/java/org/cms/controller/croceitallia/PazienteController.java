package org.cms.controller.croceitallia;

import it.asso.util.AssoException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.controller.SiteCmsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author paolo
 * 
 */
@Controller
public class PazienteController extends SiteCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected PazienteManager _pazienteManager;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "paziente/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Paziente> pazientiList = _pazienteManager.caricaPazienti();

		modelAndView.addObject("ListaPazienti", pazientiList);

		String viewName = "croceitalia/paziente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

	@RequestMapping(value = "paziente/save", method = RequestMethod.GET)
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		Paziente paziente = new Paziente();
		paziente.setNome("Axel");
		paziente.setCognome("Abrea");
		paziente.setFk_id_patologia(1);
		paziente.setCap("20100");
		paziente.setSesso("M");
		Date data_nascita = Calendar.getInstance().getTime();
		paziente.setData_nascita(data_nascita);
		paziente.setComune("Milano");

		try {
			_pazienteManager.save(paziente);
			modelAndView.addObject("messaggio", "Inserimento riuscito");
		} catch (AssoException e) {
			// Inserimento fallito
			modelAndView.addObject("messaggio", "Inserimento fallito");
		}

		List<Paziente> pazientiList = _pazienteManager.caricaPazienti();

		modelAndView.addObject("ListaPazienti", pazientiList);

		String viewName = "croceitalia/paziente/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}

}
