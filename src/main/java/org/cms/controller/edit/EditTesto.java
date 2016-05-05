
package org.cms.controller.edit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.dao.impl.TestoDao;
import org.cms.jpa.dao.impl.TipoOggettoDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.tipi.impl.cms.Testo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Paolo
 */
@Controller
public class EditTesto extends EditCmsController {

    @Autowired(required = true)
    protected TestoDao testoDao;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "testo/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            modelAndView.addObject("idPadre", 0);

            modelAndView.addObject(ListaTipiOggetti, TipoOggettoDao.GetListaTipiOggetti());

            modelAndView.setViewName("edit/testo/create");

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
    @RequestMapping(value = "testo/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        return delete(request, response, id, LIST);
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "testo/delete/{id}/{pageId}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
            @PathVariable("pageId") String pageId) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            testoDao.deleteById(id);

            return pageAfterDelete(request, response, pageId);

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param testo
     * @return
     */
    @RequestMapping(value = "testo/doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Testo testo) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Dominio dominio = getDominio(request);
            testo.setDominio(dominio);

            String text = request.getParameter("testo");
            testo.setTxt(text.getBytes());

            testoDao.update(testo);

            String viewName = "redirect:/edit/testo/update/" + testo.getId();
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
    @RequestMapping(value = "testo/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        modelAndView.addObject("title", "testo/list");
        try {
            Dominio dominio = getDominio(request);
            String dominioUid = dominio.getUid();
            List<Testo> lista = testoDao.loadAll(dominioUid);
            modelAndView.addObject("Lista", lista);

            modelAndView.setViewName("edit/testo/list");

            return modelAndView;
        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param testo
     * @return
     */
    @RequestMapping(value = "testo/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Testo testo) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Dominio dominio = getDominio(request);
            testo.setDominio(dominio);

            String text = request.getParameter("testo");
            testo.setTxt(text.getBytes());

            testoDao.save(testo);

            String viewName = "redirect:/edit/testo/update/" + testo.getId();
            modelAndView.setViewName(viewName);

            return modelAndView;

        } catch (Exception errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "testo/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Testo testo = (Testo) testoDao.findById(id);
            modelAndView.addObject("oggetto", testo);

            modelAndView.setViewName("edit/testo/update");

            return modelAndView;

        } catch (Exception errore) {
            return error(modelAndView, errore);
        }
    }
}
