package org.cms.controller.croceitalia;

import it.asso.util.AssoException;
import it.asso.util.ModelUser;
import it.asso.util.Utente_itf;

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

@Controller
public class DocumentoRigheController extends EditCmsController {

	/**
	 * 
	 */
	@Autowired(required = true)
	protected DocumentoTestataManager	_documentoTestataManager;

	@Autowired(required = true)
	protected DocumentoRigheManager		_documentoRigaManager;

	@Autowired(required = true)
	protected ClienteManager			_clienteManager;

	@Autowired(required = true)
	protected BancaManager				_bancaManager;

	@Autowired(required = true)
	protected MezzoManager				_mezzoManager;

	@Autowired(required = true)
	protected PazienteManager			_pazienteManager;

	@Autowired(required = true)
	protected StrutturaManager			_strutturaManager;

	/**
	 * @param request
	 * @param response
	 * @return
	 */
	@Override
	@RequestMapping(value = "documento_righe/list", method = RequestMethod.GET)
	protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);

		// MODEL
		List<Documento_Righe> documento = _documentoRigaManager.caricaDocumento_Row();

		// List<Paziente> paziente = _documentoRiga.caricaPazienti();

		modelAndView.addObject("Lista", documento);

		String viewName = "croceitalia/documento_righe/list";
		modelAndView.setViewName(viewName);

		return modelAndView;
	}

	@RequestMapping(value = "documento_righe/save")
	protected ModelAndView save(HttpServletRequest request, HttpServletResponse response, Documento_Righe riga) {

		ModelAndView modelAndView = getModelAndView(request);

		String importo = request.getParameter("importo_s");
		String quota_fissa = request.getParameter("quota_fissa_s");

		String km_percorso = request.getParameter("km_percorso_s");
		String km_totali = request.getParameter("km_totali_s");

		if (importo.equals(""))
			importo = "0";
		riga.setImporto(importo);

		if (quota_fissa.equals(""))
			quota_fissa = "0";
		riga.setQuotaFissa(quota_fissa);

		if (km_percorso.equals(""))
			km_percorso = "0";
		riga.setKm_percorso(km_percorso);

		if (km_totali.equals(""))
			km_totali = "0";
		riga.setKm_totali(km_totali);

		Utente_itf utente = ModelUser.get();// restituisce l'utente loggato
		String messaggio = "";

		try {

			riga.setUsercrea(utente.getUserId());
			riga.setUserultv(utente.getUserId());
			riga.setDatacrea(new Date());
			riga.setDataultv(new Date());

			Paziente paz = (Paziente) _pazienteManager.findById(riga.getPaziente().getId_paziente().toString());
			Struttura str = (Struttura) _strutturaManager.findById(riga.getStruttura().getId_struttura().toString());

			riga.setP_partenza(paz.getComune());
			riga.setP_arrivo(str.getDescrizione_breve());

			_documentoRigaManager.save(riga);
			_documentoTestataManager.aggiornaTotale(riga.getFk_id_documento_testata().toString(), riga.getImporto());

			String viewName = "redirect:/edit/documento_testata/update/" + riga.getFk_id_documento_testata();
			modelAndView.setViewName(viewName);

		} catch (AssoException e) {
			// Inserimento fallito
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return modelAndView;
	}

	@RequestMapping(value = "documento_righe/delete/{id}", method = RequestMethod.POST)
	protected ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		try {

			Documento_Righe riga = (Documento_Righe) _documentoRigaManager.findById(id);
			String idDoc = riga.getFk_id_documento_testata().toString();

			_documentoTestataManager.sottraiTotale(idDoc, riga.getImporto());
			_documentoRigaManager.deleteById(id);

			String viewName = "redirect:/edit/documento_testata/update/" + idDoc;
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "documento_righe/delete", method = RequestMethod.POST)
	protected ModelAndView delete1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String id = request.getParameter("id_riga");

		try {

			Documento_Righe riga = (Documento_Righe) _documentoRigaManager.findById(id);
			String idDoc = riga.getFk_id_documento_testata().toString();

			_documentoTestataManager.sottraiTotale(idDoc, riga.getImporto());
			_documentoRigaManager.deleteById(id);

			String viewName = "redirect:/edit/documento_testata/update/" + idDoc;
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
}
