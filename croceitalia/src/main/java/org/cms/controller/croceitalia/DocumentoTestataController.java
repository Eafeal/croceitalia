package org.cms.controller.croceitalia;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.controller.edit.EditCmsController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import it.asso.util.ApplConfig;
import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.ModelUser;
import it.asso.util.Utente_itf;

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

		/*
		 * Documento_Righe r = new Documento_Righe(); Documento_Righe riga =
		 * (Documento_Righe) _documentoRigheManager
		 * .findById(r.getFk_id_documento_testata().toString());
		 * 
		 * modelAndView.addObject("riga", riga);
		 */
		List<Documento_Righe> righe = _documentoRigheManager.caricaDocumento_Row();
		modelAndView.addObject("righe", righe);
		
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

	@RequestMapping(value = "documento_testata/save2")
	protected ModelAndView save2(HttpServletRequest request, HttpServletResponse response,
			Documento_Testata documento) {

		ModelAndView modelAndView = getModelAndView(request);

		Utente_itf utente = ModelUser.get();// restituisce l'utente loggato

		String messaggio = "";
		try {

			Integer num_doc = _documentoTestataManager.nextNumDocumento(documento.getAnno_documento());
			Date data_doc = _documentoTestataManager.nextDataDocumento(documento.getAnno_documento());
			documento.setNum_documento(num_doc);
			String data = request.getParameter("data_documento");

			if (data == "") {
				documento.setData_documento(data_doc);
			}

			documento.setUsercrea(utente.getUserId());
			documento.setUserultv(utente.getUserId());
			documento.setDatacrea(new Date());
			documento.setDataultv(new Date());

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
	public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response,
			Documento_Testata documento) {

		ModelAndView modelAndView = getModelAndView(request);
		Utente_itf utente = ModelUser.get();// restituisce l'utente loggato

		try {
			Documento_Testata documento1 = (Documento_Testata) _documentoTestataManager
					.findById(documento.getId_documento_testata().toString());

			// documento1.setStato(documento.getStato());

			documento1.setData_documento(documento.getData_documento());
			documento1.setMese_documento(documento.getMese_documento());
			documento1.setMezzo(documento.getMezzo());
			documento1.setCliente(documento.getCliente());
			documento1.setBanca(documento.getBanca());

			documento1.setCIG(documento.getCIG());

			documento1.setUserultv(utente.getUserId());
			documento1.setDataultv(new Date());

			_documentoTestataManager.update(documento1);
			// _documentoTestataManager.update(documento);
			// _strutturaManager.update(str);

			modelAndView.addObject("esito", "ok");

			// messaggio = "OK,"+documento.getNum_documento()+"
			// "+documento.getAnno_documento()+","+documento.getId_documento_testata();
			String viewName = "forward:/edit/documento_testata/update/" + documento.getId_documento_testata();
			modelAndView.setViewName(viewName);
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
		/*
		 * List<Documento_Testata> documenti =
		 * _documentoTestataManager.descrescente();
		 * modelAndView.addObject("listaDocumenti", documenti);
		 * 
		 * List<Cliente> clientiList = _clienteManager.caricaClienti();
		 * modelAndView.addObject("listaClienti", clientiList);
		 * 
		 * List<Banca> bancheList = _bancaManager.caricaBanche();
		 * modelAndView.addObject("listaBanca", bancheList);
		 * 
		 * List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
		 * modelAndView.addObject("listaMezzo", mezziList);
		 * 
		 * String viewName = "croceitalia/documento_testata/list";
		 */
		String viewName = "redirect:/edit/documento_testata/update/" + id;
		modelAndView.setViewName(viewName);

		return modelAndView;

	}
	// STAMPA PDF DA DETTAGLIO

	@RequestMapping(value = "documento_testata/pdfprint/{id}", method = RequestMethod.GET)
	public ModelAndView pdf(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

		ModelAndView modelAndView = getModelAndView(request);

		Documento_Testata documentoTestato = (Documento_Testata) _documentoTestataManager.findById(id);

		if (!documentoTestato.isPdfGenerato()) {

			List<Documento_Righe> righeDocumento = null;
			try {
				righeDocumento = _documentoRigheManager
						.caricaRigheDocumento(documentoTestato.getId_documento_testata());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			byte[] pdf = _documentoTestataManager.getPdf(documentoTestato, righeDocumento);// non
																							// CAPISCO
		}

		// String viewName = "forward:/edit/documento_testata/update/" + id;
		// modelAndView.setViewName(viewName);

		return visual(request, response, documentoTestato.getNome_file());

	}

	// STAMPA PDF DA LISTA
	@RequestMapping(value = "documento_testata/pdfprint2/{id}", method = RequestMethod.GET)
	public ModelAndView pdf2(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

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

	@RequestMapping(value = "documento_testata/view/{nomefile}", method = RequestMethod.GET)
	protected ModelAndView visual(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("nomefile") String nomefile) {

		ModelAndView modelAndView = getModelAndView(request);

		try {
			response.setContentType("application/pdf");
			ServletOutputStream out = response.getOutputStream();

			// fileDao.writeOn(nomefile, out);
			/////////
			FileInputStream inputStream = null;
			try {

				String path = ApplConfig.GetParameter("RepositoryDocumentiGenerati") + nomefile;
				File resource = new File(path);

				if (!resource.exists()) {
					throw new AssoException("Richiesto file inesistente: " + path);
				}

				AssoLogger.GetInstance().logInfo("Scaricata risorsa: " + path);

				inputStream = new FileInputStream(resource);

				byte[] b = new byte[1];
				while ((inputStream.read(b)) > 0) {
					out.write(b);
				}

			} catch (Throwable t) {
				throw new AssoException(t);
			} finally {
				if (inputStream != null) {
					try {
						inputStream.close();
					} catch (IOException e) {
						AssoLogger.GetInstance().logError(e);
					}
				}
			}

			////////

			out.flush();
			out.close();

			// VALORI DI OUTPUT
			return null;

		} catch (Throwable errore) {
			try {
				response.reset();
			} catch (Throwable t) {
				AssoLogger.GetInstance().logInfo("Non sono riuscito a fare la reset del response");
			}
			return error(modelAndView, errore);
		}
	}

	@RequestMapping(value = "documento_testata/pdfsend/{id}", method = RequestMethod.GET)
	public ModelAndView pdfsend(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id)
			throws Exception {

		Cliente cliente;

		ModelAndView modelAndView = getModelAndView(request);

		// String url = request.getRequestURL().toString();

		Documento_Testata documentoTestato = (Documento_Testata) _documentoTestataManager.findById(id);

		// cliente=(Cliente) _clienteManager.findById(id_cliente);
		cliente = documentoTestato.getCliente();

		File doc = new File(ApplConfig.GetParameter("RepositoryDocumentiGenerati") + documentoTestato.getNome_file());

		pdfSend(doc, cliente);

		modelAndView.setViewName("redirect:/edit/documento_testata/update/" + id);

		return modelAndView;

	}

	public void pdfSend(File myFile, Cliente cliente) throws MessagingException, UnsupportedEncodingException {
		// Recipient's email ID needs to be mentioned.
		String to = cliente.getEmail();

		// Sender's email ID needs to be mentioned
		String from = ApplConfig.GetParameter("from");

		// Assuming you are sending email from localhost
		String host = ApplConfig.GetParameter("smtpServer");

		// Get system properties
		java.util.Properties properties = new java.util.Properties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.user", ApplConfig.GetParameter("mailUser"));
		properties.setProperty("mail.password", ApplConfig.GetParameter("mailPassword"));
		Authenticator auth = new MyAuthenticator();
		// Get the default Session object.
		Session session = Session.getInstance(properties, auth);

		// Set response content type
		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from, "Croce Bianca Bernate"));
			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set Subject: header field
			message.setSubject("In allegato la fattura");

			// MimeMultipart multipart = new MimeMultipart("related");
			MimeMultipart multipart = new MimeMultipart("related");
			// Now set the actual message
			BodyPart messageBodyPart = new MimeBodyPart();
			String htmlText = "<H1>In allegato la fattura</H1>";
			messageBodyPart.setContent(htmlText, "text/html");
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			DataSource fds = new FileDataSource(myFile);
			messageBodyPart.setDataHandler(new DataHandler(fds));
			messageBodyPart.setFileName(myFile.getName());

			// add image to the multipart
			multipart.addBodyPart(messageBodyPart);

			// put everything together
			message.setContent(multipart);
			// Send message
			Transport.send(message);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			throw mex;
		}

		return;
	}

	class MyAuthenticator extends Authenticator {

		String user = ApplConfig.GetParameter("mailUser");
		String password = ApplConfig.GetParameter("mailPassword");

		javax.mail.PasswordAuthentication pwdAuth = new javax.mail.PasswordAuthentication(user, password);

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return pwdAuth;
		}

	}
	
	@RequestMapping(value = "documento_testata/cerca", method = RequestMethod.POST)
	public ModelAndView list1(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		String mezzo = request.getParameter("mezzo");
		String cliente = request.getParameter("cliente");
		String banca = request.getParameter("banca");
		String anno = request.getParameter("anno");
		String num_doc = request.getParameter("num_doc");
		String mese_doc = request.getParameter("mese");
		String cig = request.getParameter("cig");
		String data = request.getParameter("data");
		

		try {
			List<Documento_Testata> lista = _documentoTestataManager.search(anno,mezzo,cliente,banca,num_doc,mese_doc,cig,data);
			modelAndView.addObject("listaDocumenti", lista);
//			modelAndView.addObject("listaClienti", lista);
//			modelAndView.addObject("listaBanca", lista);
			
			List<Cliente> clientiList = _clienteManager.caricaClienti();
			modelAndView.addObject("listaClienti", clientiList);

			List<Banca> bancheList = _bancaManager.caricaBanche();
			modelAndView.addObject("listaBanca", bancheList);

			List<Mezzo> mezziList = _mezzoManager.caricaMezzi();
			modelAndView.addObject("listaMezzo", mezziList);

			String viewName = "croceitalia/documento_testata/list";
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}
	
	@RequestMapping(value = "documento_testata/duplica/{id}", method = RequestMethod.POST)
	public ModelAndView duplica(HttpServletRequest request, HttpServletResponse response,@PathVariable("id") String id) {
		
		ModelAndView modelAndView = getModelAndView(request);
		Utente_itf utente = ModelUser.get();
		
		try {
			Documento_Testata documento=(Documento_Testata) _documentoTestataManager.findById(id);
			Documento_Testata documentoNew = new Documento_Testata(); 
			List<Documento_Righe> righe=(List<Documento_Righe>) _documentoRigheManager.leggi(id);			
			
			Integer num_doc = _documentoTestataManager.nextNumDocumento(documento.getAnno_documento());
			Date data_doc = _documentoTestataManager.nextDataDocumento(documento.getAnno_documento());
			
			documentoNew.setNum_documento(num_doc);
			documentoNew.setData_documento(data_doc);
			documentoNew.setAnno_documento(documento.getAnno_documento());
			documentoNew.setCIG("");
			documentoNew.setEsente_bollo(documento.getEsente_bollo());
			documentoNew.setEsente_iva(documento.getEsente_iva());
			documentoNew.setFk_id_banca(documento.getFk_id_banca());
			documentoNew.setFk_id_cliente(documento.getFk_id_cliente());
			documentoNew.setFk_id_mezzo(documento.getFk_id_mezzo());
			documentoNew.setImponibile(documento.getImponibile());
			documentoNew.setImporto_esente(documento.getImporto_esente());
			documentoNew.setIva(documento.getIva());
			documentoNew.setMese_documento(documento.getMese_documento());
			documentoNew.setNome_file(documento.getNome_file());
			documentoNew.setPdf_generato(documento.getPdf_generato());
			documentoNew.setStato(documento.getStato());
			documentoNew.setTotale(documento.getTotale());
			documentoNew.setUsercrea(utente.getUserId());
			documentoNew.setUserultv(utente.getUserId());
			documentoNew.setDatacrea(new Date());
			documentoNew.setDataultv(new Date());

			_documentoTestataManager.save(documentoNew);

			for(int i=0;i<righe.size();i++){
				
				Documento_Righe righeNew = new Documento_Righe();
				Documento_Righe righeOld = righe.get(i);				
				
				righeNew.setDatacrea(data_doc);
				righeNew.setUsercrea(utente.getUserId());
				righeNew.setUserultv(utente.getUserId());
				righeNew.setDatacrea(new Date());
				righeNew.setDataultv(new Date());
				righeNew.setFk_id_documento_testata(documentoNew.getId_documento_testata());
				righeNew.setFk_id_paziente(righeOld.getFk_id_paziente());
				righeNew.setFk_id_struttura(righeOld.getFk_id_struttura());
				righeNew.setNum_sedute(righeOld.getNum_sedute());
				righeNew.setMese(righeOld.getMese());
				righeNew.setKm_totali(righeOld.getKm_totali());
				righeNew.setKm_percorso(righeOld.getKm_percorso());
				righeNew.setPercorso(righeOld.getPercorso());
				righeNew.setP_partenza(righeOld.getP_partenza());
				righeNew.setP_arrivo(righeOld.getP_arrivo());
				righeNew.setOra_sosta(righeOld.getOra_sosta());
				righeNew.setQuota_fissa(righeOld.getQuota_fissa());
				righeNew.setDiritto_uscita(righeOld.getDiritto_uscita());
				righeNew.setImporto(righeOld.getImporto());
				
				_documentoRigheManager.save(righeNew);
			}
						
			modelAndView.setViewName("redirect:/edit/documento_testata/list");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return modelAndView;
	}
}