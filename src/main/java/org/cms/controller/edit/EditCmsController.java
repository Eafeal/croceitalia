/**
 * 
 */

package org.cms.controller.edit;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.cms.controller.CmsController;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author consdonzellipaolo
 * 
 */
public abstract class EditCmsController extends CmsController {

    /**
     * @param request
     * @param response
     * @return
     * @throws AssoException
     */
    protected abstract ModelAndView list(HttpServletRequest request, HttpServletResponse response) throws AssoException;

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
     * @throws AssoException
     */
    protected final ModelAndView pageAfterDelete(HttpServletRequest request, HttpServletResponse response, String pageId)
            throws AssoException {

        if (LIST.equals(pageId)) {
            return list(request, response);
        }

        ModelAndView modelAndView = getModelAndView(request);

        String viewName = "redirect:/site/page/" + pageId;
        modelAndView.setViewName(viewName);

        return modelAndView;
    }

    /**
     * @param modelAndView
     * @param errore
     * @return
     */
    @Override
    protected ModelAndView error(ModelAndView modelAndView, Throwable errore) {

        if (errore instanceof AssoException) {
            // DO NOTHING
            // HO GIA' LOGGATO PRIMA
        } else {
            AssoLogger.GetInstance().logError(errore);
        }

        String message = errore.getMessage();
        if (message == null) {
            message = "Messaggio non disponibile: " + errore.getClass().getSimpleName();
        }

        modelAndView.addObject(ErrorMsg, message);
        modelAndView.setViewName(EditErrorView);

        return modelAndView;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.controller.CmsController#getModelAndView(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected ModelAndView getModelAndView(HttpServletRequest request) {

        ModelAndView modelAndView = super.getModelAndView(request);

        HttpSession session = getSession(request);
        ModelMap sessionMap = (ModelMap) session.getAttribute("LastParameterUsed");
        if (sessionMap == null) {
            sessionMap = new ModelMap();
            session.setAttribute("LastParameterUsed", sessionMap);
        }

        modelAndView.addAllObjects(sessionMap);

        return modelAndView;
    }
}
