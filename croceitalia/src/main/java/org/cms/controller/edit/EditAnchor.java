
package org.cms.controller.edit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.html.anchor.Anchor;
import org.cms.html.anchor.AnchorDao;
import org.cms.jpa.dao.impl.TipoOggettoDao;
import org.cms.jpa.object.impl.Dominio;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EditAnchor extends EditCmsController {

    /**
	 * 
	 */
    @Autowired(required = true)
    protected AnchorDao anchorDao;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "anchor/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            modelAndView.addObject("idPadre", 0);
            modelAndView.addObject(ListaTipiOggetti, TipoOggettoDao.GetListaTipiOggetti());
            modelAndView.setViewName("edit/anchor/create");

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
    @RequestMapping(value = "anchor/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        return delete(request, response, id, LIST);
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "anchor/delete/{id}/{pageId}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
            @PathVariable("pageId") String pageId) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            anchorDao.deleteById(id);

            return pageAfterDelete(request, response, pageId);

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param anchor
     * @return
     */
    @RequestMapping(value = "anchor/doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Anchor anchor) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            anchor.setDominio(getDominio(request));
            anchorDao.update(anchor);

            String viewName = "redirect:/edit/anchor/update/" + anchor.getId();
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
    @RequestMapping(value = "anchor/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        modelAndView.addObject("title", "anchor/list");
        try {
            Dominio dominio = getDominio(request);
            String dominioUid = dominio.getUid();
            List<Anchor> lista = anchorDao.loadAll(dominioUid);
            modelAndView.addObject("Lista", lista);

            modelAndView.setViewName("edit/anchor/list");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }

    }

    /**
     * @param request
     * @param response
     * @param anchor
     * @return
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "anchor/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Anchor anchor) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            anchor.setDominio(getDominio(request));
            anchorDao.save(anchor);

            String viewName = "redirect:/edit/anchor/update/" + anchor.getId();
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
    @RequestMapping(value = "anchor/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Anchor anchor = (Anchor) anchorDao.findById(id);

            modelAndView.addObject("anchor", anchor);
            modelAndView.addObject(ListaTipiOggetti, TipoOggettoDao.GetListaTipiOggetti());
            modelAndView.setViewName("edit/anchor/update");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }

    }
}
