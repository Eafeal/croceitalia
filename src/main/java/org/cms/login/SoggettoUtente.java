
package org.cms.login;

import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.cms.jpa.object.impl.DominioModel_itf;
import org.cms.jpa.object.impl.Model;

/**
 * @author ConsDonzelliPaolo
 * 
 */
@Entity
@Table(name = "SoggettoUtente")
@NamedQuery(name = "SoggettoUtente.loadAll", query = "SELECT OBJECT(soggetto) FROM SoggettoUtente soggetto ORDER BY soggetto.ruolo ")
public class SoggettoUtente extends Model {

    /**
	 * 
	 */
    public static final String GUEST               = "Guest";
    public static final String USER                = "User";
    public static final String DOMAINADMINISTRATOR = "DomainAdministrator";
    public static final String GLOBALADMINISTRATOR = "GlobalAdministrator";

    /**
	 * 
	 */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, insertable = true, updatable = true)
    private String             id;

    private String             ruolo;
    private String             description;
    private String             state;

    /**
	 * 
	 */
    private String             parent_id;

    /**
	 * 
	 */
    @OneToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "metaprofilo_id", nullable = true)
    private Metaprofilo        metaprofilo;

    /**
	 * 
	 */
    @Temporal(TemporalType.DATE)
    private Timestamp          insertTime;

    @Temporal(TemporalType.DATE)
    private Timestamp          lastUpdateTime;

    private String             user_id;

    /**
	 * 
	 */
    public SoggettoUtente() {

        super();
    }

    /**
     * @return the description
     */
    public String getDescription() {

        return this.description;
    }

    /**
     * @return the id
     */
    public String getId() {

        return this.id;
    }

    /**
     * @return the insertTime
     */
    public Timestamp getInsertTime() {

        return this.insertTime;
    }

    /**
     * @return the lastUpdateTime
     */
    public Timestamp getLastUpdateTime() {

        return this.lastUpdateTime;
    }

    /**
     * @return the metaprofilo
     */
    public Metaprofilo getMetaprofilo() {

        return this.metaprofilo;
    }

    public List<String> getObjectStates() {

        List<String> answer = new Vector<String>();

        answer.add(DominioModel_itf.PUBLIC);

        if (this.isUser()) {
            answer.add(DominioModel_itf.PRIVATE);
        } else if (this.isAdmin()) {
            answer.add(DominioModel_itf.PRIVATE);
            answer.add(DominioModel_itf.WORKINPROGRESS);
        }

        return answer;
    }

    /**
     * @return the parent
     */
    public String getParent() {

        return this.parent_id;
    }

    /**
     * @return the ruolo
     */
    public String getRuolo() {

        return this.ruolo;
    }

    /**
     * @return the state
     */
    public String getState() {

        return this.state;
    }

    /**
     * @return the user_id
     */
    public String getUser_id() {

        return this.user_id;
    }

    /**
     * @return
     */
    public boolean isAdmin() {

        if (DOMAINADMINISTRATOR.equals(this.ruolo)) {
            return true;
        }
        if (GLOBALADMINISTRATOR.equals(this.ruolo)) {
            return true;
        }
        return false;
    }

    /**
     * @return
     */
    public boolean isUser() {

        if (USER.equals(this.ruolo)) {
            return true;
        }
        return false;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {

        this.id = id;
    }

    /**
     * @param insertTime the insertTime to set
     */
    public void setInsertTime(Timestamp insertTime) {

        this.insertTime = insertTime;
    }

    /**
     * @param lastUpdateTime the lastUpdateTime to set
     */
    public void setLastUpdateTime(Timestamp lastUpdateTime) {

        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * @param metaprofilo the metaprofilo to set
     */
    public void setMetaprofilo(Metaprofilo metaprofilo) {

        this.metaprofilo = metaprofilo;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(String parent) {

        this.parent_id = parent;
    }

    /**
     * @param ruolo the ruolo to set
     */
    public void setRuolo(String ruolo) {

        this.ruolo = ruolo;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {

        this.state = state;
    }

    /**
     * @param userId the user_id to set
     */
    public void setUser_id(String userId) {

        this.user_id = userId;
    }

}
