package org.cms.controller.edit;

import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.dao.impl.TipoOggettoDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.DominioModel_itf;
import org.cms.jpa.object.impl.Model_itf;
import org.cms.jpa.object.impl.Relazione;
import org.cms.jpa.object.impl.TipoOggetto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Paolo
 * 
 */
@Controller
public class EditRel extends EditCmsController {

	/**
	 * @param request
	 * @param response
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "rel/delete/{idPadre}/{tipoPadreId}/{idFiglio}/{tipoFiglioId}", method = RequestMethod.GET)
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idPadre") String idPadre, @PathVariable("tipoPadreId") String tipoPadreId,
			@PathVariable("idFiglio") String idFiglio, @PathVariable("tipoFiglioId") String tipoFiglioId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			relazioneDao.deleteById(idPadre, idFiglio);

			// http://localhost:8090/cms/edit/anchor/update/3502
			TipoOggetto tipoOggettoPadre = (TipoOggetto) relazioneDao.findById(TipoOggetto.class, tipoPadreId);
			String viewName = "redirect:/edit/" + tipoOggettoPadre.getEditPath() + "/update/" + idPadre;
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
	@RequestMapping(value = "rel/giu/{idPadre}/{tipoPadreId}/{idFiglio}/{tipoFiglioId}", method = RequestMethod.GET)
	public ModelAndView giu(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idPadre") String idPadre, @PathVariable("tipoPadreId") String tipoPadreId,
			@PathVariable("idFiglio") String idFiglio, @PathVariable("tipoFiglioId") String tipoFiglioId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			relazioneDao.giu(tipoPadreId, idPadre, tipoFiglioId, idFiglio);

			TipoOggetto tipoOggettoPadre = TipoOggettoDao.GetTipoOggetto(tipoPadreId);
			String viewName = "redirect:/edit/" + tipoOggettoPadre.getEditPath() + "/update/" + idPadre;
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param idSezione
	 * @return
	 */
	@Override
	@RequestMapping(value = "rel/list", method = RequestMethod.GET)
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		modelAndView.addObject("title", "rel/list");
		try {
			Dominio dominio = getDominio(request);
			String dominioUid = dominio.getUid();
			List<Relazione> relazioni = relazioneDao.loadAll(dominioUid);
			modelAndView.addObject("Relazioni", relazioni);

			modelAndView.setViewName("edit/relazioni/list");

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
	@RequestMapping(value = "rel/su/{idPadre}/{tipoPadreId}/{idFiglio}/{tipoFiglioId}", method = RequestMethod.GET)
	public ModelAndView su(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("idPadre") String idPadre, @PathVariable("tipoPadreId") String tipoPadreId,
			@PathVariable("idFiglio") String idFiglio, @PathVariable("tipoFiglioId") String tipoFiglioId) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			relazioneDao.su(tipoPadreId, idPadre, tipoFiglioId, idFiglio);

			TipoOggetto tipoOggettoPadre = TipoOggettoDao.GetTipoOggetto(tipoPadreId);
			String viewName = "redirect:/edit/" + tipoOggettoPadre.getEditPath() + "/update/" + idPadre;
			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @param uid
	 * @return
	 */
	@RequestMapping(value = "newRel/step1/{tipoOggetto_uid}/{uid}", method = RequestMethod.GET)
	public ModelAndView newRelStep1(HttpServletRequest request, HttpServletResponse response,
			@PathVariable("tipoOggetto_uid") String tipoOggetto_uid, @PathVariable("uid") String uid) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			TipoOggetto tipoPadre = TipoOggettoDao.GetTipoOggetto(tipoOggetto_uid);
			Model_itf padre = (Model_itf) relazioneDao.findById(tipoPadre.getTypeClass(), uid);

			modelAndView.addObject("padre", padre);
			modelAndView.addObject("tipoPadre", tipoPadre);
			modelAndView.addObject(ListaTipiOggetti, TipoOggettoDao.GetListaTipiOggetti());

			modelAndView.setViewName("edit/relazioni/step1_chooseType");

			return modelAndView;

		} catch (Throwable errore) {
			return error(modelAndView, errore);
		}

	}

	/**
	 * 
	 * Aggiunge un nuovo figlio alla pagina. L'elemento NON esiste
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "newRel/newSon", method = RequestMethod.POST)
	public ModelAndView newRelNewSon(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			String tipoOggettoPadre_uid = request.getParameter("tipoOggettoPadre.uid");
			TipoOggetto tipoOggettoPadre = TipoOggettoDao.GetTipoOggetto(tipoOggettoPadre_uid);

			String padre_id = request.getParameter("id");
			DominioModel_itf padre = (DominioModel_itf) relazioneDao.findById(tipoOggettoPadre, padre_id);

			String tipoOggetto_uid = request.getParameter("tipoOggetto.uid");
			TipoOggetto tipoOggettoFiglio = TipoOggettoDao.GetTipoOggetto(tipoOggetto_uid);
			String className = tipoOggettoFiglio.getClassName();

			Class<?> classe = Class.forName(className);
			DominioModel_itf newInstance = (DominioModel_itf) classe.newInstance();
			Dominio dominio = getDominio(request);
			newInstance.setDominio(dominio);

			TipoOggetto tipoOggetto = new TipoOggetto();
			tipoOggetto.setUid(tipoOggetto_uid);
			newInstance.setTipoOggetto(tipoOggetto);

			relazioneDao.save(newInstance);

			Relazione relazione = new Relazione(dominio);
			relazioneDao.setPadre(relazione, padre.getTipoOggetto(), padre);
			relazioneDao.setFiglio(relazione, tipoOggettoFiglio, newInstance);

			relazioneDao.save(relazione);

			String viewName = "redirect:/edit/" + tipoOggettoPadre.getEditPath() + "/update/" + padre.getId();
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
	@RequestMapping(value = "newRel/choose", method = RequestMethod.POST)
	public ModelAndView newRelChoose(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			String id = request.getParameter("id");
			String tipoOggettoPadre_uid = request.getParameter("tipoOggettoPadre.uid");
			String tipoOggetto_uid = request.getParameter("tipoOggetto.uid");

			TipoOggetto tipoOggettoPadre = TipoOggettoDao.GetTipoOggetto(tipoOggettoPadre_uid);
			TipoOggetto tipoOggettoFiglio = TipoOggettoDao.GetTipoOggetto(tipoOggetto_uid);

			DominioModel_itf padre = (DominioModel_itf) relazioneDao.findById(tipoOggettoPadre.getTypeClass(), id);

			modelAndView.addObject("padre", padre);
			modelAndView.addObject("tipoPadre", padre.getTipoOggetto());
			modelAndView.addObject("tipoFiglio", tipoOggettoFiglio);

			List<DominioModel_itf> list = relazioneDao.chooseNewChildren(padre, tipoOggettoFiglio, getDominio(request)
					.getUid());
			TreeSet<DominioModel_itf> treeSet = new TreeSet<DominioModel_itf>(list);
			modelAndView.addObject("Lista", treeSet);

			modelAndView.setViewName("edit/relazioni/step2_chooseType");

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
	@RequestMapping(value = "newRel/save", method = RequestMethod.POST)
	public ModelAndView newRelSave(HttpServletRequest request, HttpServletResponse response) {

		ModelAndView modelAndView = getModelAndView(request);
		try {
			String padre_id = request.getParameter("padre.id");
			String tipoPadre_uid = request.getParameter("tipoPadre");

			TipoOggetto tipoOggettoPadre = (TipoOggetto) relazioneDao.findById(TipoOggetto.class, tipoPadre_uid);
			Class<?> classePadre = tipoOggettoPadre.getTypeClass();
			DominioModel_itf padre = (DominioModel_itf) relazioneDao.findById(classePadre, padre_id);

			String figlio_uid = request.getParameter("oggetto.uid");
			String tipoFiglio_uid = request.getParameter("tipoFiglio");

			TipoOggetto tipoOggettoFiglio = (TipoOggetto) relazioneDao.findById(TipoOggetto.class, tipoFiglio_uid);
			Class<?> classeFiglio = tipoOggettoFiglio.getTypeClass();
			DominioModel_itf figlio = (DominioModel_itf) relazioneDao.findById(classeFiglio, figlio_uid);

			Dominio dominio = getDominio(request);
			Relazione relazione = new Relazione(dominio);
			relazione.setPadre(tipoOggettoPadre.getId(), padre.getId());
			relazione.setFiglio(tipoOggettoFiglio.getId(), figlio.getId());

			int ordine = relazioneDao.maxOrdine(padre, tipoOggettoPadre);
			relazione.setOrdine(ordine + 1);

			// lo metto in testa
			ordine = 0;

			// SALVO
			relazioneDao.save(relazione);

			// REDIRIGO verso il figlio
			String viewName = "redirect:/edit/" + tipoOggettoFiglio.getEditPath() + "/update/" + figlio_uid;
			// REDIRIGO verso il padre
			viewName = "redirect:/edit/" + tipoOggettoPadre.getEditPath() + "/update/" + padre_id;

			modelAndView.setViewName(viewName);

			return modelAndView;

		} catch (Exception errore) {
			return error(modelAndView, errore);
		}

	}

}
