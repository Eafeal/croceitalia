package org.cms.controller.croceitalia;

import java.util.Date;
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
public class DocumentoTestataController extends EditCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected DocumentoTestataManager _documentoTestataManager;

	@Autowired(required = true)
	protected DocumentoRigheManager _documentoRigheManager;

	@Autowired(required = true)
	protected ClienteManager _clienteManager;

	@Autowired(required = true)
	protected BancaManager _bancaManager;

	@Autowired(required = true)
	protected MezzoManager _mezzoManager;

	///////////////////////////
	@Autowired(required = true)
	protected PazienteManager _pazienteManager;

	@Autowired(required = true)
	protected StrutturaManager _strutturaManager;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@Override

	@RequestMapping(value = "documento_testata/list")
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		// List<Documento_Testata> documenti =
		// _documentoTestataManager.listaDocumento_Testata();
		// modelAndView.addObject("listaDocumenti", documenti);

		List<Documento_Testata> documenti = _documentoTestataManager.descrescente();
		modelAndView.addObject("listaDocumenti", documenti);

		List<Cliente> clientiList = _clienteManager.caricaClienti();
		modelAndView.addObject("listaClienti", clientiList);

		List<Banca> bancheList = _bancaManager.caricaBanche();
		modelAndView.addObject("listaBanca", bancheList);

		List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
		modelAndView.addObject("listaMezzo", mezziList);

		String viewName = "croceitalia/documento_testata/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

	// NON MI SERVE
	@RequestMapping(value = "documento_testata/create", method = RequestMethod.GET)
	public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			List<Documento_Testata> documenti = _documentoTestataManager.descrescente();
			modelAndView.addObject("listaDocumenti", documenti);

			List<Cliente> clientiList = _clienteManager.caricaClienti();
			modelAndView.addObject("listaClienti", clientiList);

			List<Banca> bancheList = _bancaManager.caricaBanche();
			modelAndView.addObject("listaBanca", bancheList);

			List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
			modelAndView.addObject("listaMezzo", mezziList);

			String viewName = "croceitalia/documento_testata/list";
			modelAndView.setViewName(viewName);

			modelAndView.setViewName("croceitalia/documento_testata/create");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	@RequestMapping(value = "documento_testata/save2")
	protected ModelAndView save2(HttpServletRequest request, HttpServletResponse response,
			Documento_Testata documento) {

		ModelAndView modelAndView = getModelAndView(request);
		String messaggio = "";
		try {

			Integer num_doc = _documentoTestataManager.nextNumDocumento(documento.getAnno_documento());
			Date data_doc = _documentoTestataManager.nextDataDocumento(documento.getAnno_documento());
			documento.setNum_documento(num_doc);
			documento.setData_documento(data_doc);

			_documentoTestataManager.save(documento);

			/*
			 * messaggio = "OK," + documento.getNum_documento() + " " +
			 * documento.getAnno_documento() + "," +
			 * documento.getId_documento_testata();
			 */

			modelAndView.setViewName("redirect:/edit/documento_testata/update/" + documento.getId_documento_testata());

		} catch (AssoException e) {
			// Inserimento fallito
			messaggio = "KO";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

	// DEVE PRIMA FUNZIONARE
	@RequestMapping(value = "documento_testata/doUpdate", method = RequestMethod.POST)
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Documento_Righe righe) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			_documentoRigheManager.update(righe);
			// _documentoTestataManager.update(documento);
			// _strutturaManager.update(str);

			modelAndView.addObject("esito", "ok");

			// messaggio = "OK,"+documento.getNum_documento()+"
			// "+documento.getAnno_documento()+","+documento.getId_documento_testata();
			// String viewName = "forward:/edit/documento_testata/list/" +
			// documento.getId_documento_testata();
			// modelAndView.setViewName(viewName);
			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "documento_testata/update/{id}")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			Documento_Testata documento = (Documento_Testata) _documentoTestataManager.findById(id);

			List<Cliente> clientiList = _clienteManager.caricaClienti();
			modelAndView.addObject("listaClienti", clientiList);

			List<Banca> bancheList = _bancaManager.caricaBanche();
			modelAndView.addObject("listaBanca", bancheList);

			List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
			modelAndView.addObject("listaMezzo", mezziList);

			List<Documento_Testata> documenti = _documentoTestataManager.descrescente();
			modelAndView.addObject("listaDocumenti", documenti);

			List<Documento_Righe> righe = _documentoRigheManager.caricaDocumento_Row_byId(id);
			List<Paziente> paziente = _pazienteManager.caricaPazienti();
			List<Struttura> struttura = _strutturaManager.caricaStruttura();

			modelAndView.addObject("documento", documento);/* chiave valore */
			modelAndView.addObject("righe", righe);
			modelAndView.addObject("listaPaziente", paziente);
			modelAndView.addObject("listaStruttura", struttura);

			modelAndView.setViewName("croceitalia/documento_testata/dettaglio");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "documento_testata/chiudi/{id}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		_documentoTestataManager.chiudiDocumento(id);

		List<Documento_Testata> documenti = _documentoTestataManager.descrescente();
		modelAndView.addObject("listaDocumenti", documenti);

		List<Cliente> clientiList = _clienteManager.caricaClienti();
		modelAndView.addObject("listaClienti", clientiList);

		List<Banca> bancheList = _bancaManager.caricaBanche();
		modelAndView.addObject("listaBanca", bancheList);

		List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
		modelAndView.addObject("listaMezzo", mezziList);

		String viewName = "croceitalia/documento_testata/list";

		// String viewName = "redirect:/edit/documento_testata/update/" + id;
		modelAndView.setViewName(viewName);

		return modelAndView;

	}

	@RequestMapping(value = "documento_testata/pdfprint/{id}", method = RequestMethod.GET)
	public ModelAndView pdf(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		Documento_Testata documentoTestato = (Documento_Testata) _documentoTestataManager.findById(id);

		List<Documento_Righe> righeDocumento = null;
		try {
			righeDocumento = _documentoRigheManager.caricaRigheDocumento(documentoTestato.getId_documento_testata());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		byte[] pdf = _documentoTestataManager.getPdf(documentoTestato, righeDocumento);// non
																						// CAPISCO

		List<Documento_Testata> documenti = _documentoTestataManager.descrescente();
		modelAndView.addObject("listaDocumenti", documenti);

		List<Cliente> clientiList = _clienteManager.caricaClienti();
		modelAndView.addObject("listaClienti", clientiList);

		List<Banca> bancheList = _bancaManager.caricaBanche();
		modelAndView.addObject("listaBanca", bancheList);

		List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
		modelAndView.addObject("listaMezzo", mezziList);

		String viewName = "croceitalia/documento_testata/list";
		modelAndView.setViewName(viewName);

		return modelAndView;

	}

}
