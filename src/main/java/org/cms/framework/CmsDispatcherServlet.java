/**
 * 
 */

package org.cms.framework;

import it.asso.util.AssoLogger;
import it.asso.util.ModelUser;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.cms.jpa.object.impl.ModelLoader;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Paolo
 * 
 */
@SuppressWarnings("serial")
public class CmsDispatcherServlet extends DispatcherServlet {

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.FrameworkServlet#destroy()
     */
    @Override
    public void destroy() {

        super.destroy();
        ModelUser.destroy();
        ModelLoader.destroy();
    }

    @Override
    protected void doService(HttpServletRequest request, HttpServletResponse response) throws Exception {

        StringBuffer requestURL = request.getRequestURL();
        String message = "RequestURL=" + requestURL;
        AssoLogger.GetInstance().logInfo(message);
        // Thread.currentThread().setName(request.getRemoteAddr() + "_" + Thread.currentThread().getName());

        try {

            super.doService(request, response);

        } catch (Throwable t) {
            AssoLogger.GetInstance().logError(t);
            try {
                response.sendRedirect("/error500.html");
            } catch (Throwable e2) {
                response.getWriter().write("<h1>Si &grave; verificato un errore applicativo.</h1>");
                response.getWriter().write(
                        "<h1>Se l'errore dovesse persistere ti preghiamo di segnarlo al nostro servizio clienti</h1>");
                response.getWriter().write("<h1>Grazie.</h1>");
            }
        } finally {
            ModelUser.destroy();
            ModelLoader.destroy();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        AssoLogger.GetInstance().logInfo("init");

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.FrameworkServlet#initFrameworkServlet()
     */
    @Override
    protected void initFrameworkServlet() throws ServletException {

        super.initFrameworkServlet();
        AssoLogger.GetInstance().logInfo("initFrameworkServlet");
        // CmsStarter.Start();

    }

}
