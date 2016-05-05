/**
 * 
 */

package org.cms.controller.edit;

import it.asso.upload.FileManager_itf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.dao.impl.ArticoloDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.tipi.impl.cms.Articolo;
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
public class EditArticolo extends EditCmsController {

    private static final String ALL = "ALL";

    /**
	 * 
	 */
    @Autowired(required = true)
    protected ArticoloDao       articoloDao;

    @Autowired(required = true)
    protected FileManager_itf   fileDao;

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "articolo/create", method = RequestMethod.GET)
    public ModelAndView create(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            modelAndView.setViewName("edit/articolo/create");

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
    @RequestMapping(value = "articolo/delete/{id}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        return delete(request, response, id, LIST);
    }

    /**
     * @param request
     * @param response
     * @param id
     * @return
     */
    @RequestMapping(value = "articolo/delete/{id}/{pageId}", method = RequestMethod.GET)
    public ModelAndView delete(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id,
            @PathVariable("pageId") String pageId) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            articoloDao.deleteById(id);

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
    @RequestMapping(value = "articolo/doUpdate", method = RequestMethod.POST)
    public ModelAndView doUpdate(HttpServletRequest request, HttpServletResponse response, Articolo articolo) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Testo testo = articolo.getTesto();
            String text = request.getParameter("txt");
            testo.setTxt(text.getBytes());

            String id = articolo.getImage().getId();
            if (id.equals("")) {
                articolo.setImage(null);
            }
            articoloDao.update(articolo);

            String viewName = "redirect:/edit/articolo/update/" + articolo.getId();
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
    @RequestMapping(value = "articolo/image/list/{nomeFile}", method = RequestMethod.GET)
    public ModelAndView getTypeMap(HttpServletRequest request, HttpServletResponse response,
            @PathVariable("nomeFile") String nomeFile) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Map<String, String> map = fileDao.getTypeMap("Image", nomeFile, getDominio(request));
            // Output Data
            modelAndView.addObject(ESITO, OK);
            modelAndView.addObject("map", map);
            modelAndView.addObject("selectedKey", "NonLoSoAncora");

            response.setContentType(XMLType);

            modelAndView.setViewName(PAGE_DDLB);

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

    /**
	 * 
	 */
    @Override
    @RequestMapping(value = "articolo/list", method = RequestMethod.GET)
    protected ModelAndView list(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView modelAndView = getModelAndView(request);
        modelAndView.addObject("title", "articolo/list");
        try {
            Dominio dominio = getDominio(request);
            String dominioUid = dominio.getUid();
            List<Articolo> lista = articoloDao.loadAll(dominioUid);
            modelAndView.addObject("Lista", lista);

            modelAndView.setViewName("edit/articolo/list");

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
    @RequestMapping(value = "articolo/save", method = RequestMethod.POST)
    public ModelAndView save(HttpServletRequest request, HttpServletResponse response, Articolo articolo) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Dominio dominio = getDominio(request);
            articolo.setDominio(dominio);

            Testo testo = articolo.getTesto();
            String text = request.getParameter("txt");
            testo.setTxt(text.getBytes());

            articoloDao.save(articolo);

            String viewName = "redirect:/edit/articolo/update/" + articolo.getId();
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
    @RequestMapping(value = "articolo/update/{id}", method = RequestMethod.GET)
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response, @PathVariable("id") String id) {

        ModelAndView modelAndView = getModelAndView(request);
        try {
            Articolo articolo = (Articolo) articoloDao.findById(id);
            modelAndView.addObject("oggetto", articolo);

            String nomeFile = ALL;
            Map<String, String> map = fileDao.getTypeMap("Image", nomeFile, getDominio(request));
            // Output Data
            modelAndView.addObject(ESITO, OK);
            modelAndView.addObject("map", map);

            String image_id = "";
            if (articolo.getImage() != null) {
                image_id = articolo.getImage().getId();
            }
            modelAndView.addObject("selectedKey", image_id);

            modelAndView.setViewName("edit/articolo/update");

            return modelAndView;

        } catch (Throwable errore) {
            return error(modelAndView, errore);
        }
    }

}
