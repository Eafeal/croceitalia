package org.cms.jpa.object.impl;

import java.util.List;

import javax.persistence.EntityManager;

/**
 * @author ConsDonzelliPaolo
 * 
 */
public interface RelazioneDao_itf extends AssoDao_itf {

	List<Relazione> loadChildren(DominioModel_itf padre, TipoOggetto_itf tipo);

	// List select(String sql, Map<String, Object> parameters);

	List<Relazione> loadParents(DominioModel_itf model,
			TipoOggetto_itf tipoOggetto);

	List<Relazione> loadChildren(EntityManager em, DominioModel_itf model,
			TipoOggetto_itf tipoOggetto);

	List<Relazione> loadParents(EntityManager em, DominioModel_itf model,
			TipoOggetto_itf tipoOggetto);

}
