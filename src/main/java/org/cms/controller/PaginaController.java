package org.cms.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.dao.impl.ArticoloDao;
import org.cms.jpa.dao.impl.PhotoGalleryDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.tipi.impl.cms.Articolo;
import org.cms.jpa.tipi.impl.cms.PhotoGallery;
import org.cms.jpa.tipi.impl.html.Pagina;
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
public class PaginaController extends SiteCmsController {

    /**
	 * 
	 */
    private static final String PHOTOGALLERY = "photoGallery";
    private static final String ARTICOLO     = "articolo";

    /**
	 * 
	 */
    @Autowired(required = true)
    private PhotoGalleryDao     photoGalleryDao;

    @Autowired(required = true)
    private ArticoloDao         articoloDao;

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "page/articolo/{articoloUid}", method = RequestMethod.GET)
    public ModelAndView articolo(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("articoloUid") String articoloUid) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Dominio dominio = getDominio(request);
            String dominioUid = dominio.getUid();

            Pagina pagina = (Pagina) paginaDao.findByUid("Articolo", dominioUid);
            modelAndView.addObject(PAGINA, pagina);

            Articolo articolo = (Articolo) articoloDao.findByUid(articoloUid, dominioUid);
            modelAndView.addObject(ARTICOLO, articolo);

            modelAndView.setViewName(pagina.getPathRenderPage());
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
    @RequestMapping(value = "photogallery/{photogalleryId}", method = RequestMethod.GET)
    public ModelAndView photogallery(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("photogalleryId") String photogalleryId) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Dominio dominio = (Dominio) modelAndView.getModelMap().get(DOMINIO);
            String dominioId = dominio.getUid();

            PhotoGallery photogallery = (PhotoGallery) photoGalleryDao.findById(photogalleryId);
            modelAndView.addObject(PHOTOGALLERY, photogallery);

            String tipo = photogallery.getTipoOggetto().getTipo();
            String viewName = dominioId + "/" + tipo + "/" + tipo.toLowerCase();
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
    @RequestMapping(value = "page/{pageUid}")
    public ModelAndView page(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pageUid") String pageUid) {

        return goToPage(request, response, pageUid);
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "avcp/{pageUid}")
    public ModelAndView avcpPage(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("pageUid") String pageUid) {

        return goToPage(request, response, pageUid);
    }

    /**
     * @param request
     * @param response
     * @param errorCode
     * @return
     */
    @RequestMapping(value = "error/{errorCode}")
    public ModelAndView error(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("errorCode") String errorCode) {

        ModelAndView modelAndView = getModelAndView(request);
        modelAndView.setViewName("error" + errorCode);
        return modelAndView;

        // if (errorCode.equals("404")) {
        // modelAndView.setViewName("error404");
        // return modelAndView;
        // }
        //
        //        
        // try {
        // // Recupero il dominio associato alla sessione
        // String dominioUid = getDominio(request).getUid();
        // Pagina pagina = (Pagina) paginaDao.findByUid(ErrorPage, dominioUid);
        // modelAndView.addObject(PAGINA, pagina);
        // modelAndView.addObject("errorCode", errorCode);
        // String renderPage = pagina.getPathRenderPage();
        // modelAndView.setViewName(renderPage);
        //
        // return modelAndView;
        //
        // } catch (Exception errore) {
        // return error(modelAndView, errore);
        // }
    }

}
