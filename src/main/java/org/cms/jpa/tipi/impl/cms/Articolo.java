/**
 * 
 */

package org.cms.jpa.tipi.impl.cms;

import it.asso.freemarker.LangFactory;
import it.asso.upload.DBFile;
import it.asso.util.AssoException;

import javax.persistence.CascadeType;
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
@Table(name = "Articolo")
public class Articolo extends DominioModel {

    /**
	 * 
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String        id;

    private String        uid   = "Unique Id";

    /**
	 * 
	 */
    private String        lang  = LangFactory.IT;
    private String        state = WORKINPROGRESS;

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
    @OneToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "testo_id", nullable = false)
    protected Testo       testo;

    /**
	 * 
	 */
    @OneToOne
    @JoinColumn(name = "image_id", nullable = false)
    protected DBFile      image;

    /**
	 * 
	 */
    public Articolo() {

        super();
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
     * @return the image
     */
    public DBFile getImage() {

        return this.image;
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
     * @return the testo
     */
    public Testo getTesto() {

        return this.testo;
    }

    /**
     * @return the tipoOggetto
     */
    public TipoOggetto getTipoOggetto() {

        return this.tipoOggetto;
    }

    /**
     * @return the uid
     */
    public String getUid() {

        return this.uid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.object.impl.Model#onPrePersist()
     */
    @Override
    public void onPrePersist() throws AssoException {

        super.onPrePersist();

        if (this.testo == null) {
            this.testo = new Testo();
        }

        this.testo.setDominio(this.dominio);
        this.testo.setLang(this.lang);
        this.testo.setState(this.state);

        checkImage();

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.object.impl.Model#onPreUpdate()
     */
    @Override
    protected void onPreUpdate() throws AssoException {

        super.onPreUpdate();

        checkImage();
    }

    /**
	 * 
	 */
    private void checkImage() {

        if (this.image != null && this.image.getId().equals("")) {
            this.image = null;
        }
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
     * @param image the image to set
     */
    public void setImage(DBFile image) {

        this.image = image;
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
     * @param testo the testo to set
     */
    public void setTesto(Testo testo) {

        this.testo = testo;
    }

    /**
     * @param tipoOggetto the tipoOggetto to set
     */
    public void setTipoOggetto(TipoOggetto tipoOggetto) {

        this.tipoOggetto = tipoOggetto;
    }

    /**
     * @param uid the uid to set
     */
    public void setUid(String uid) {

        this.uid = uid;
    }

}
