
package org.cms.controller.soggetti;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.component.soggetti.Soggetto;
import org.cms.component.soggetti.SoggettoDao;
import org.cms.controller.edit.EditCmsController;
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
public class EditSoggetto extends EditCmsController {

    /**
     * 
     */
    @Autowired(required = true)
    protected SoggettoDao soggettoDao;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "soggetti/soggetto/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            modelAndView.setViewName("edit/soggetti/soggetto/create");

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
    @RequestMapping(value = "soggetti/soggetto/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            soggettoDao.deleteById(id);

            modelAndView.setViewName("redirect:/edit/soggetti/soggetto/list");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param soggetto
     * @return
     */
    @RequestMapping(value = "soggetti/soggetto/doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Soggetto soggetto) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            soggettoDao.update(soggetto);

            String viewName = "redirect:/edit/soggetti/soggetto/update/" + soggetto.getId();
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
    @RequestMapping(value = "soggetti/soggetto/list", method = RequestMethod.GET)
    public ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            modelAndView.addObject("title", "soggetti/soggetto/list");
            modelAndView.addObject(Lista, soggettoDao.loadAll());
            modelAndView.setViewName("edit/soggetti/soggetto/list");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
     * @param request
     * @param response
     * @param soggetto
     * @return
     * @throws ClassNotFoundException
     */
    @RequestMapping(value = "soggetti/soggetto/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Soggetto soggetto) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            soggettoDao.save(soggetto);

            modelAndView.setViewName("redirect:/edit/soggetti/soggetto/update/" + soggetto.getId());

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
     */
    @RequestMapping(value = "soggetti/soggetto/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Soggetto soggetto = (Soggetto) soggettoDao.findById(id);
            modelAndView.addObject("soggetto", soggetto);

            modelAndView.setViewName("edit/soggetti/soggetto/update");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }

    }
}
