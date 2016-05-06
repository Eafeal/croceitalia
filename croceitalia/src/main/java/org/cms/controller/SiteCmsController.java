package org.cms.controller;

import it.asso.util.AssoLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.tipi.impl.html.Pagina;
import org.springframework.web.servlet.ModelAndView;

public abstract class SiteCmsController extends CmsController {

    /**
     * 
     */
    private static final String   HomePage = "Home";

    protected static final String UserMsg  = "UserMsg";

    /**
     * @param request
     * @param response
     * @param modelAndView
     * @return
     */
    protected ModelAndView goToHome(HttpServletRequest request, HttpServletResponse response, ModelAndView modelAndView) {

        ModelAndView mv = goToPage(request, response, HomePage);
        mv.addAllObjects(modelAndView.getModelMap());

        return mv;
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    protected ModelAndView goToPage(HttpServletRequest request, HttpServletResponse response, String pageUid) {

        AssoLogger.GetInstance().logInfo(this.getClass(), "Richiesta pagina. pageUid=#" + pageUid + "#");
        ModelAndView modelAndView = getModelAndView(request);
        String dominioUid = null;
        try {
            // Recupero il dominio associato alla sessione
            dominioUid = (String) getSession(request).getAttribute(DominioUid);
            // Recupero la pagina associata a pageUid
            Pagina pagina = (Pagina) paginaDao.findByUid(pageUid, dominioUid);

            modelAndView.addObject(PAGINA, pagina);
            String renderPage = pagina.getPathRenderPage();
            modelAndView.setViewName(renderPage);
            return modelAndView;

        } catch (javax.persistence.NoResultException errore) {
            AssoLogger.GetInstance().logInfo(this.getClass(),
                    "Richiesta pagina inesistente! dominioUid=#" + dominioUid + "# pageUid=#" + pageUid + "#");
            // La pagina cercata non esiste
            if (!pageUid.equals(HomePage)) {
                // redirect to home page
                request.setAttribute(ErrorMsg, "La pagina richiesta non esiste o non &egrave; raggiungibile");
                return goToPage(request, response, HomePage);
            } else {
                // Non esiste la Home Page
                return error(modelAndView, errore);
            }
        } catch (Exception errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * Ritorna la pagina dove dobbiamo andare dopo una cancellazione. Se la cancellazione è avvenuta da una pagina del
     * sito (/site/page/***) allora torniamo ad essa senza più l'oggetto cancellato.
     * 
     * Se invece ho cancellato un oggetto dalla gestione allora ritorno alla lista degli oggetti del tipo che ho
     * cancellato
     * 
     * @param request
     * @param response
     * @param pageId
     * @return
     */
    protected final ModelAndView pageAfterDelete(HttpServletRequest request, HttpServletResponse response, String pageId) {

        ModelAndView modelAndView = getModelAndView(request);

        String viewName = "redirect:/site/page/" + pageId;
        modelAndView.setViewName(viewName);

        return modelAndView;
    }

}
