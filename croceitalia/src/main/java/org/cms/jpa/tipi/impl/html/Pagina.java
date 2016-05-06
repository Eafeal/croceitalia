package org.cms.jpa.tipi.impl.html;

import it.asso.freemarker.LangFactory;
import it.asso.util.AssoLogger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.cms.jpa.object.impl.Dominio;
import org.cms.jpa.object.impl.DominioModel;
import org.cms.jpa.object.impl.TipoOggetto;

/**
 * @author consdonzellipaolo
 * 
 */
@Entity
@Table(name = "Pagina")
public class Pagina extends DominioModel implements Cloneable {

    /**
	 * 
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String        id;

    private String        uid         = "Home";
    private String        renderPage  = "page";
    private String        title       = "title";
    private String        description = "description";
    private String        keywords    = "key1, key2";
    private String        url         = "/site/page/Home";
    private String        state       = WORKINPROGRESS;
    private String        lang        = LangFactory.IT;

    /**
	 * 
	 */
    @OneToOne
    @JoinColumn(name = "tipooggetto_uid", nullable = false)
    protected TipoOggetto tipoOggetto;

    @OneToOne
    @JoinColumn(name = "dominio_uid", nullable = false)
    protected Dominio     dominio;

    /**
	 * 
	 */
    public Pagina() {

        super();
    }

    public String getDescription() {

        return this.description;
    }

    public Dominio getDominio() {

        return this.dominio;
    }

    public String getId() {

        return this.id;
    }

    public String getKeywords() {

        return this.keywords;
    }

    public String getLang() {

        return this.lang;
    }

    /**
     * @return the renderPage
     */
    public String getRenderPage() {

        return this.renderPage;
    }

    public String getState() {

        return this.state;
    }

    public TipoOggetto getTipoOggetto() {

        return this.tipoOggetto;
    }

    public String getTitle() {

        return this.title;
    }

    public String getUid() {

        return this.uid;
    }

    public String getUrl() {

        return this.url;
    }

    @Override
    public boolean inLavorazione() {

        return state.equals(WORKINPROGRESS);
    }

    public void setDescription(String description) {

        this.description = description;
    }

    public void setDominio(Dominio dominio) {

        this.dominio = dominio;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setKeywords(String keywords) {

        this.keywords = keywords;
    }

    public void setLang(String lang) {

        this.lang = lang;
    }

    /**
     * @param renderPage
     *            the renderPage to set
     */
    public void setRenderPage(String renderPage) {

        this.renderPage = renderPage;
    }

    public void setState(String state) {

        this.state = state;
    }

    public void setTipoOggetto(TipoOggetto tipoOggetto) {

        this.tipoOggetto = tipoOggetto;
    }

    public void setTitle(String title) {

        this.title = title;
    }

    public void setUid(String uid) {

        this.uid = uid;
    }

    public void setUrl(String url) {

        this.url = url;
    }

    @Override
    public String toString() {

        return this.dominio + "/" + this.uid;
    }

    /**
     * @return
     */
    public String getPathRenderPage() {

        String pathRenderPage = dominio.getUid() + "/" + this.renderPage;
        AssoLogger.GetInstance().logInfo(this.getClass(), "Render View=#" + renderPage + "#");

        return pathRenderPage;
    }
}
