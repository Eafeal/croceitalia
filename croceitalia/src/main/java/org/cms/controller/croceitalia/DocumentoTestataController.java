package org.cms.controller.croceitalia;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.controller.edit.EditCmsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class DocumentoTestataController extends EditCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected DocumentoTestataManager	_documentoTestata;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping(value = "documento_testata/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Documento_Testata> documento = _documentoTestata.caricaDocumento_Testata();

		List<Mezzo> mezzo = _documentoTestata.caricaMezzi();
		
		List<Banca> banca = _documentoTestata.caricaBanche();
		
		List<Cliente> cliente = _documentoTestata.caricaClienti();
		modelAndView.addObject("Lista", documento);

		String viewName = "croceitalia/documento_testata/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}
	
	@RequestMapping(value = "documento_testata/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {

			List<Mezzo> mezzo = _documentoTestata.caricaMezzi();
			modelAndView.addObject("mezzo", mezzo);

			modelAndView.setViewName("croceitalia/documento_testata/create");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	

}
