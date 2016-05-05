
package org.cms.controller.edit;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.tipi.impl.html.Pagina;
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
public class EditPagina extends EditCmsController {

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "pagina/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            modelAndView.setViewName("edit/pagina/create");

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
    @RequestMapping(value = "pagina/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        return delete(request, response, id, LIST);
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "pagina/delete/{id}/{pageId}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
            @PathVariable("pageId") String pageId) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            paginaDao.deleteById(id);

            return pageAfterDelete(request, response, pageId);

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }

    }

    /**
     * @param request
     * @param response
     * @param pagina
     * @return
     */
    @RequestMapping(value = "pagina/doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Pagina pagina) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Dominio dominio = getDominio(request);
            pagina.setDominio(dominio);

            paginaDao.update(pagina);

            ModelAndView modelAndView2 = super.getModelAndView(request);
            String viewName = "redirect:/edit/pagina/update/" + pagina.getId();
            modelAndView2.setViewName(viewName);

            return modelAndView2;

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
    @RequestMapping(value = "pagina/duplicate/{id}", method = RequestMethod.GET)
    public ModelAndView duplicate(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Pagina pagina = (Pagina) paginaDao.findById(id);
            modelAndView.addObject("pagina", pagina);

            Pagina clone = (Pagina) pagina.duplicate();

            clone.setDominio(getDominio(request));
            String lang = getLanguage(getSession(request));
            clone.setLang(lang);

            paginaDao.clone(pagina, clone);

            String viewName = "redirect:/edit/pagina/update/" + clone.getId();
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
    @RequestMapping(value = "pagina/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Dominio dominio = getDominio(request);
            String dominioUid = dominio.getUid();

            List<Pagina> lista = paginaDao.loadAll(dominioUid);
            modelAndView.addObject("Lista", lista);

            String viewName = "edit/pagina/list";
            modelAndView.setViewName(viewName);

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param pagina
     * @return
     */
    @RequestMapping(value = "pagina/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Pagina pagina) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            pagina.setDominio(getDominio(request));
            String lang = getLanguage(getSession(request));
            pagina.setLang(lang);

            paginaDao.save(pagina);

            String viewName = "redirect:/edit/pagina/update/" + pagina.getId();
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
    @RequestMapping(value = "pagina/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Pagina pagina = (Pagina) paginaDao.findById(id);
            modelAndView.addObject("pagina", pagina);

            String viewName = "edit/pagina/update";
            modelAndView.setViewName(viewName);

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

}
