
package org.cms.jpa.tipi.impl.cms;

/**
 * @author Paolo
 * 
 */

import it.asso.freemarker.LangFactory;

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
@Table(name = "PhotoGallery")
public class PhotoGallery extends DominioModel {

    /**
	 * 
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String        id;

    private String        title       = "";
    private String        description = "";
    private String        autore      = "";

    /**
	 * 
	 */
    private String        lang        = LangFactory.IT;
    private String        state       = WORKINPROGRESS;

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
    public PhotoGallery() {

        super();
    }

    /**
     * @return the autore
     */
    public String getAutore() {

        return this.autore;
    }

    /**
     * @return the description
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * @return the dominio
     */
    public Dominio getDominio() {

        return this.dominio;
    }

    /**
     * @return the id
     */
    public String getId() {

        return this.id;
    }

    /**
     * @return the lang
     */
    public String getLang() {

        return this.lang;
    }

    /**
     * @return the state
     */
    public String getState() {

        return this.state;
    }

    /**
     * @return the tipoOggetto
     */
    public TipoOggetto getTipoOggetto() {

        return this.tipoOggetto;
    }

    /**
     * @return the title
     */
    public String getTitle() {

        return this.title;
    }

    public String getUid() {

        return title;
    }

    /**
     * @param autore the autore to set
     */
    public void setAutore(String autore) {

        this.autore = autore;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * @param dominio the dominio to set
     */
    public void setDominio(Dominio dominio) {

        this.dominio = dominio;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * @param lang the lang to set
     */
    public void setLang(String lang) {

        this.lang = lang;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {

        this.state = state;
    }

    /**
     * @param tipoOggetto the tipoOggetto to set
     */
    public void setTipoOggetto(TipoOggetto tipoOggetto) {

        this.tipoOggetto = tipoOggetto;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {

        this.title = title;
    }
}
