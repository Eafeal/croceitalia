/**
 * 
 */

package org.cms.jpa.object.impl;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;


/**
 * @author ConsDonzelliPaolo
 * 
 *         Questa classe astratta raccoglie in se' tutti gli oggetti di business dei vari domini. Per il momento
 *         l'interfaccia e' vuota. Serve solo a dare ordine alle classi
 * 
 */
public abstract class Model implements Model_itf {

    @PostLoad
    protected void onPostLoad() {

    }

    @PostPersist
    protected void onPostPersist() {

    }

    @PostRemove
    protected void onPostRemove() {

    }

    @PostUpdate
    protected void onPostUpdate() {

    }

    @PrePersist
    protected void onPrePersist() throws AssoException {

    }

    @PreRemove
    protected void onPreRemove() {

    }

    @PreUpdate
    protected void onPreUpdate() {

        AssoLogger.GetInstance().logInfo("Aggiornamento " + this.getClass().getSimpleName());
    }

//    @Override
    public int compareTo(Model_itf obj) {

        return this.toString().compareTo(obj.toString());

    }

}
