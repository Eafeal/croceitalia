
package org.cms.controller.edit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.dao.impl.PhotoGalleryDao;
import org.cms.jpa.dao.impl.TipoOggettoDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.tipi.impl.cms.PhotoGallery;
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
public class EditPhotoGallery extends EditCmsController {

    @Autowired(required = true)
    protected PhotoGalleryDao photoGalleryDao;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "photoGallery/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            modelAndView.addObject("idPadre", 0);

            modelAndView.addObject(ListaTipiOggetti, TipoOggettoDao.GetListaTipiOggetti());

            String viewName = "edit/photoGallery/create";
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
    @RequestMapping(value = "photoGallery/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        return delete(request, response, id, LIST);
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "photoGallery/delete/{id}/{pageId}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
            @PathVariable("pageId") String pageId) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            photoGalleryDao.deleteById(id);

            return pageAfterDelete(request, response, pageId);

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }

    }

    /**
     * @param request
     * @param response
     * @param photoGallery
     * @return
     */
    @RequestMapping(value = "photoGallery/doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, PhotoGallery photoGallery) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            photoGallery.setDominio(getDominio(request));
            photoGalleryDao.update(photoGallery);

            String viewName = "redirect:/edit/photoGallery/update/" + photoGallery.getId();
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
    @RequestMapping(value = "photoGallery/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        modelAndView.addObject("title", "photoGallery/list");
        try {
            Dominio dominio = getDominio(request);
            String dominioUid = dominio.getUid();
            List<PhotoGallery> lista = photoGalleryDao.loadAll(dominioUid);
            modelAndView.addObject("Lista", lista);

            modelAndView.setViewName("edit/photoGallery/list");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param photoGallery
     * @return
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "photoGallery/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, PhotoGallery photoGallery) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            photoGallery.setDominio(getDominio(request));
            photoGalleryDao.save(photoGallery);

            String viewName = "redirect:/edit/photoGallery/update/" + photoGallery.getId();
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
     * @throws ClassNotFoundException
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "photoGallery/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            PhotoGallery photoGallery = (PhotoGallery) photoGalleryDao.findById(id);
            modelAndView.addObject("photoGallery", photoGallery);

            modelAndView.addObject(ListaTipiOggetti, TipoOggettoDao.GetListaTipiOggetti());

            modelAndView.setViewName("edit/photoGallery/update");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }
}
