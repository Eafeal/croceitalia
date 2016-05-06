package org.cms.controller;

import it.asso.freemarker.AssoLang;
import it.asso.freemarker.LangFactory;
import it.asso.util.AssoConst_itf;
import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.ErrDetector;
import it.asso.util.IpUtils;
import it.asso.util.ModelUser;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.cms.jpa.dao.impl.PaginaDao;
import org.cms.jpa.dao.impl.RelazioneDao;
import org.cms.jpa.dao.impl.TipoOggettoDao;
import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.ModelLoader;
import org.cms.jpa.tipi.impl.html.Pagina;
import org.cms.login.GuestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Paolo
 */
public abstract class CmsController implements AssoConst_itf {

    /**
	 * 
	 */
    public static final String LongDateFormat = "dd-MM-yyyy";
    public static final String DateHourFormat = "dd-MM-yyyy  HH:mm:ss";

    /**
	 * 
	 */
    @Autowired(required = true)
    protected RelazioneDao     relazioneDao;

    /**
	 * 
	 */
    @Autowired(required = true)
    protected TipoOggettoDao   tipoOggettoDao;

    @Autowired(required = true)
    protected PaginaDao        paginaDao;

    /**
     * Questo metodo aggiunge tutti i parametri ricevuti in ingresso al
     * modelView. Questo mi consente di avere nella vista i parametri che
     * arrivano dalle redirect
     * 
     * @param request
     * @param modelAndView
     */
    @SuppressWarnings("unchecked")
    private void addAttributesAndParameter(HttpServletRequest request, ModelAndView modelAndView) {

        // Aggiungo parametri e attributi
        Enumeration<String> attributeNames = request.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            String name = attributeNames.nextElement();
            Object attribute = request.getAttribute(name);
            if (!name.startsWith("org.springframework")) {
                modelAndView.addObject(name, attribute);
            }
        }

        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String name = parameterNames.nextElement();
            String parameter = request.getParameter(name);
            if (!name.startsWith("org.springframework")) {
                modelAndView.addObject(name, parameter);
            }
        }
    }

    /**
     * @return
     */
    protected Cookie buildCookie(HttpServletRequest request, String domain, String path, String cookieName,
            String cookieValue, String purpose) {

        Cookie cookie = new Cookie(cookieName, cookieValue);

        cookie.setComment(purpose);
        cookie.setPath(path);
        cookie.setDomain(domain);

        return cookie;
    }

    /**
     * @param modelAndView
     * @param errore
     * @return
     */
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

        // Recupero il dominio associato alla sessione
        String dominio_id = (String) modelAndView.getModelMap().get(DominioUid);
        Pagina pagina = (Pagina) paginaDao.findByUid(ErrorPage, dominio_id);
        modelAndView.addObject(PAGINA, pagina);
        String renderPage = pagina.getPathRenderPage();

        modelAndView.setViewName(renderPage);

        return modelAndView;
    }

    /**
     * @param request
     * @param cookieName
     * @return
     * @throws AssoException
     */
    protected final String getCookieValue(HttpServletRequest request, String cookieName) throws AssoException {

        String name = null;
        String value = null;
        try {
            Cookie[] cookies = request.getCookies();
            ErrDetector.invariant(cookies != null, "Non sono presenti cookies!");

            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                name = cookie.getName();
                value = cookie.getValue();

                if (name.equals(cookieName)) {
                    return cookie.getValue();
                }
            }
        } catch (Throwable e) {
            throw new AssoException(e);
        }
        throw new AssoException("Cookie non trovato: " + name + "-" + value);
    }

    /**
     * @param request
     * @return
     * @throws AssoException
     */
    protected final Dominio getDominio(HttpServletRequest request) {

        String dominioUid = (String) getSession(request).getAttribute(DominioUid);
        return getDominio(dominioUid);
    }

    /**
     * @param request
     * @return
     * @throws AssoException
     */
    protected final Dominio getDominio(String dominioUid) {

        Dominio dominio = new Dominio();
        dominio.setUid(dominioUid);

        return dominio;
    }

    /**
     * @param request
     * @return
     */
    protected AssoLang getLang(HttpSession session) {

        AssoLang lang = (AssoLang) session.getAttribute(LINGUA);
        if (lang == null) {
            lang = LangFactory.getInstance(IT);
        }
        return lang;
    }

    /**
     * @param request
     * @return
     */
    protected String getLanguage(HttpSession session) {

        AssoLang lang = getLang(session);
        return lang.getLanguage();
    }

    /**
     * @param request
     * @return
     */
    protected ModelAndView getModelAndView(HttpServletRequest request) {

        HttpSession session = getSession(request);

        ModelAndView modelAndView = new ModelAndView();

        // addAttributesAndParameter(request, modelAndView);

        String ipFromRequest = IpUtils.getIpFromRequest(request);
        modelAndView.addObject(IpFromRequest, ipFromRequest);

        ModelLoader.set(relazioneDao);

        Utente_itf utente = (Utente_itf) session.getAttribute(UTENTE);
        if (utente == null) {
            // L'utente non esiste viene impostato un utente di default di tipo
            // GuestUser
            utente = new GuestUser();
        }
        /**
         * Imposta l'utente della sessione. Bisogna farlo sempre per i Local
         * Thread
         */
        setSessionUser(session, modelAndView, utente);

        /**
         * PARTE RELATIVA AL DOMINIO
         * 
         * ESEMPIO/cms/036456
         */
        String contextPath = request.getContextPath();
        String dominioUid = Util.getDominioUid(contextPath);
        session.setAttribute(DominioUid, dominioUid);

        Dominio dominio = (Dominio) session.getAttribute(DOMINIO);
        if (dominio == null) {
            dominio = loadDominio(dominioUid);
            session.setAttribute(DOMINIO, dominio);
        }

        modelAndView.addObject(DOMINIO, dominio);
        modelAndView.addObject(DominioUid, dominioUid);
        modelAndView.addObject(StylePath, dominio.getPathCSS());

        /**
         * Gestione Url Richiesto
         */
        Object object = modelAndView.getModel().get(RequestedUri);
        if (Util.isNotEmpty(object)) {
            /*
             * La pagina richiesta NON e' stata passata come argomento della
             * request. Non la devo recuperare dall'url di richiesta
             */
            AssoLogger.GetInstance().logInfo("RequestedUri passato come parametro: " + object);
        } else {
            String requestedMvcUri = getRequestedMvcUri(request);
            modelAndView.addObject(RequestedUri, requestedMvcUri);
        }

        /**
         * LINGUA
         */
        AssoLang lang = getLang(session);
        modelAndView.addObject(LINGUA, lang);

        return modelAndView;
    }

    /**
     * @param res
     * @param req
     * @return
     */
    private String getRequestedMvcUri(HttpServletRequest req) {

        String requestURI = req.getRequestURI();
        String contextPath = req.getContextPath();
        String requestedUri = Util.getRequestMvcUri(requestURI, contextPath);

        return requestedUri;
    }

    /**
     * @param request
     * @return
     */
    protected HttpSession getSession(HttpServletRequest request) {

        HttpSession session = request.getSession(false);
        if (session == null) {
            session = request.getSession(true);
        }
        return session;
    }

    /**
     * @param request
     * @param binder
     */
    @InitBinder
    protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(LongDateFormat);
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
    }

    /**
     * @param request
     * @return
     * @throws AssoException
     */
    protected final Dominio loadDominio(String dominioId) {

        Dominio dominio = null;
        try {
            dominio = (Dominio) relazioneDao.findById(Dominio.class, dominioId);
        } catch (AssoException error) {
            AssoLogger.GetInstance().logError("Dominio Inesistente! Id=" + dominioId, error);
        }
        return dominio;
    }

    /**
     * Imposta l'utente della sessione. Bisogna farlo sempre per i Local Thread
     * 
     * @param session
     * @param modelAndView
     * @param utente
     */
    protected final void setSessionUser(HttpSession session, ModelAndView modelAndView, Utente_itf utente) {

        session.setAttribute(UTENTE, utente);
        modelAndView.addObject(UTENTE, utente);
        ModelUser.set(utente);
    }
}
