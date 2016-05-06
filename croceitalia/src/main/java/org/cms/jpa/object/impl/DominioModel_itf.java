package org.cms.jpa.object.impl;

import it.asso.util.AssoException;
import it.asso.util.Dominio_itf;

import java.util.List;

/**
 * @author ConsDonzelliPaolo
 * 
 *         Questa interfaccia raccoglie in se' tutti gli oggetti che concorrono
 *         a comporre il dominio, ossia che hanno relazioni padre/figlio tra
 *         loro
 * 
 */
public interface DominioModel_itf extends Model_itf {

	/**
	 * 
	 */
	static final String PUBLIC = "PUBL";
	static final String WORKINPROGRESS = "WIP";
	static final String PRIVATE = "PRIV";

	/**
	 * @param uid
	 * @return
	 * @throws AssoException
	 */
	DominioModel_itf getChild(String uid) throws AssoException;

	List<DominioModel_itf> getChildren() throws AssoException;

	String getId();

	String getTemplate();

	String getUid();

	TipoOggetto_itf getTipoOggetto();

	Dominio_itf getDominio();

	void setDominio(Dominio dominio);

	void setTipoOggetto(TipoOggetto tipoOggettoFiglio);

	String getState();

}
