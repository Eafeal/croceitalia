/**
 * 
 */
package org.cms.jpa.object.impl;

import it.asso.util.AssoException;

/**
 * @author ConsDonzelliPaolo
 * 
 */
public interface TipoOggetto_itf {

	String getId();

	String getTemplate();

	Object getUid();

	Class<?> getTypeClass() throws AssoException;

}
