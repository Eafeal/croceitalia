
package org.cms.jpa.object.impl;


/**
 * @author ConsDonzelliPaolo
 * 
 */
public class ModelLoader {

    /**
	 * 
	 */
    private static ThreadLocal<RelazioneDao_itf> tl;

    /**
     * @param dao
     */
    public static void set(RelazioneDao_itf dao) {

        if (tl == null) {
            // AssoLogger.GetInstance().logInfo("ModelLoader create ThreadLocal");
            tl = new ThreadLocal<RelazioneDao_itf>();
        }
        tl.set(dao);
    }

    /**
     * @return
     */
    public static RelazioneDao_itf get() {

        return tl.get();
    }

    /**
     * 
     */
    public static void destroy() {

        // tl = null;
    }
}
