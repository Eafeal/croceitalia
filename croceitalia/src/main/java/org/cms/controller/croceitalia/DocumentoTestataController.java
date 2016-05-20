package org.cms.controller.croceitalia;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.controller.edit.EditCmsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.asso.util.AssoException;

@Controller
public class DocumentoTestataController extends EditCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected DocumentoTestataManager	_documentoTestataManager;

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
//		List<Documento_Testata> documenti = _documentoTestataManager.listaDocumento_Testata();
//		modelAndView.addObject("listaDocumenti", documenti);
		
		List<Documento_Testata> documenti = _documentoTestataManager.descrescente();
		modelAndView.addObject("listaDocumenti", documenti);

		String viewName = "croceitalia/documento_testata/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}
	
	@RequestMapping(value = "documento_testata/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			
			modelAndView.setViewName("croceitalia/documento_testata/create");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	@RequestMapping(value = "documento_testata/save2")
	protected ModelAndView save2(HttpServletRequest request, HttpServletResponse response, Documento_Testata documento) {

		ModelAndView modelAndView = getModelAndView(request);
		String messaggio = "";
		try {
			_documentoTestataManager.save(documento);
			messaggio = "OK,"+documento.getNum_documento()+" "+documento.getAnno_documento()+","+documento.getId_documento_testata();
			
		} catch (AssoException e) {
			// Inserimento fallito
			messaggio = "KO";
		}

		try {
			PrintWriter out = response.getWriter();
			response.getWriter().write(messaggio);//<--- Qua viene passato il valore inserito 
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	
		return null;

	}	
	

}
