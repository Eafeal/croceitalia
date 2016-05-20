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
public class DocumentoRigheController extends EditCmsController{
	
	/**
	 * 
	 */
	@Autowired(required = true)
	protected DocumentoRigheManager _documentoRiga;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "documento_righe/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Documento_Righe> documento = _documentoRiga.caricaDocumento_Row();

//		List<Paziente> paziente = _documentoRiga.caricaPazienti();
		
		modelAndView.addObject("Lista", documento);

		String viewName = "croceitalia/documento_righe/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

}
