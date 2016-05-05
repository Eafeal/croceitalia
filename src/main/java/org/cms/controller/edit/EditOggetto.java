
package org.cms.controller.edit;

import it.asso.util.Util;

import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.dao.impl.OggettoDao;
import org.cms.jpa.dao.impl.TipoOggettoDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.Oggetto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Paolo
 */
/**
 * @author consdonzellipaolo
 */
@Controller
public class EditOggetto extends EditCmsController {

    /**
	 * 
	 */
    @Autowired(required = true)
    protected OggettoDao oggettoDao;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "oggetto/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {

            int idPadre = 0;
            create(modelAndView, idPadre);

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param modelAndView
     * @param idPadre
     */
    private void create(ModelAndView modelAndView, int idPadre) {

        modelAndView.addObject("idPadre", idPadre);

        String uid = Util.format(Calendar.getInstance().getTime(), "yyyyMMdd-hhss");
        modelAndView.addObject("uid", uid);

        modelAndView.addObject(ListaTipiOggetti, TipoOggettoDao.GetListaTipiOggettiClasse(Oggetto.class));

        modelAndView.setViewName("edit/oggetto/create");
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "oggetto/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        return delete(request, response, id, LIST);
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "oggetto/delete/{id}/{pageId}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
            @PathVariable("pageId") String pageId) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            oggettoDao.deleteById(id);

            return pageAfterDelete(request, response, pageId);

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param oggetto
     * @return
     */
    @RequestMapping(value = "oggetto/doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Oggetto oggetto) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            oggetto.setDominio(getDominio(request));
            oggettoDao.update(oggetto);

            String editPath = oggetto.getTipoOggetto().getEditPath();
            String oggetto_id = oggetto.getId();
            String viewName = "redirect:/edit/" + editPath + "/update/" + oggetto_id;
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
    @RequestMapping(value = "oggetto/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        modelAndView.addObject("title", "oggetto/list");
        try {
            Dominio dominio = getDominio(request);
            String dominioUid = dominio.getUid();
            List<Oggetto> lista = oggettoDao.loadAll(dominioUid);
            modelAndView.addObject("ListaOggetti", lista);

            String viewName = "edit/oggetto/list";
            modelAndView.setViewName(viewName);

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param oggetto
     * @return
     */
    @RequestMapping(value = "oggetto/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Oggetto oggetto) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            oggetto.setDominio(getDominio(request));
            oggettoDao.save(oggetto);

            String viewName = "redirect:/edit/oggetto/update/" + oggetto.getId();
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
    @RequestMapping(value = "oggetto/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Oggetto oggetto = (Oggetto) oggettoDao.findById(id);
            modelAndView.addObject("oggetto", oggetto);

            modelAndView.setViewName("edit/oggetto/update");

            return modelAndView;

        } catch (Exception errore) {
            return error(modelAndView, errore);
        }
    }
}
