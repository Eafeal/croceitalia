/**
 * 
 */
package org.cms.jpa.object.impl;

import it.asso.util.AssoException;

import java.util.List;


/**
 * @author ConsDonzelliPaolo
 * 
 */
public interface DominioDao_itf extends AssoDao_itf {

	List<DominioModel_itf> findAll(TipoOggetto_itf tipoOggetto,
			String dominioUid) throws AssoException;

	Object findById(TipoOggetto_itf tipoOggetto, String id)
			throws AssoException;

	Object findByUid(String uid, String dominioUid) throws AssoException;

	String getTableName(TipoOggetto_itf tipoOggetto) throws AssoException;

	List<DominioModel_itf> chooseNewChildren(DominioModel_itf padre,
			TipoOggetto_itf tipoOggetto, String dominioUid)
			throws AssoException;
}
