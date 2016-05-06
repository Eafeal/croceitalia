
package org.cms.jpa.tipi.impl.cms;

/**
 * @author Paolo
 * 
 */

import it.asso.freemarker.LangFactory;
import it.asso.util.AssoException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
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
@Table(name = "Testo")
@NamedQuery(name = "Testo.loadAll", query = "SELECT OBJECT(testo) FROM Testo testo")
public class Testo extends DominioModel {

    /**
	 * 
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String        id;

    private String        title  = "Title";
    private String        autore = "Author";

    @Lob
    private byte[]        txt;

    /**
	 * 
	 */
    private String        lang   = LangFactory.IT;
    private String        state  = WORKINPROGRESS;

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
    public Testo() {

        super();
    }

    /**
     * @return the autore
     */
    public String getAutore() {

        return this.autore;
    }

    public Dominio getDominio() {

        return dominio;
    }

    public String getId() {

        return id;
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

    public TipoOggetto getTipoOggetto() {

        return tipoOggetto;
    }

    /**
     * @return the title
     */
    public String getTitle() {

        return this.title;
    }

    /**
     * @param autore the autore to set
     */
    public void setAutore(String autore) {

        this.autore = autore;
    }

    public void setDominio(Dominio dominio) {

        this.dominio = dominio;
    }

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

    public void setTipoOggetto(TipoOggetto tipoOggetto) {

        this.tipoOggetto = tipoOggetto;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {

        this.title = title;
    }

    //@Override
    public String getUid() {

        return title;
    }

    public byte[] getTxt() {

        return txt;
    }

    public void setTxt(byte[] txt) {

        this.txt = txt;
    }

    /**
     * @return
     */
    public String getTesto() {

        if (this.txt == null) {
            return "";
        }
        return new String(this.txt);
    }

    /**
     * @return
     */
    public String getPreviewTesto() {

        String testo = getTesto();
        int length = testo.length();
        int endIndex = Math.min(length, 240);
        String preview = testo.substring(0, endIndex);
        return preview;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.object.impl.DominioModel#onPrePersist()
     */
    //@Override
    protected void onPrePersist() throws AssoException {

        super.onPrePersist();

        if (this.txt == null) {
            String t = "Sao ko kelle terre, per kelle fini que ki contene, trenta anni le possette parte Sancti Benedicti.";
            this.setTxt(t.getBytes());
        }

    }
}
