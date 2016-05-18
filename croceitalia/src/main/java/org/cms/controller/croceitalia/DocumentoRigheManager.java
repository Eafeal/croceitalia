package org.cms.controller.croceitalia;

import java.util.List;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;

@Repository("documento_righe_manager")
public class DocumentoRigheManager extends AssoDao {

	/**
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<DocumentoRighe> caricaDocumento_Row() {

		List<DocumentoRighe> lista = (List<DocumentoRighe>) this.execNamedQuery("Documento_righe.loadAll");

		return lista;

	}
	
	@SuppressWarnings("unchecked")
	public List<Paziente> caricaPazienti() {

		List<Paziente> list = (List<Paziente>) this.execNamedQuery("Paziente.loadAll");
		return list;
	}


	public Class<?> getEntityClass() {

		// TODO Auto-generated method stub
		return null;
	}

}
