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
	public List<Documento_Righe> caricaDocumento_Row() {

		List<Documento_Righe> lista = (List<Documento_Righe>) this.execNamedQuery("Documento_Righe.loadAll");

		return lista;

	}

	public Class<?> getEntityClass() {

		// TODO Auto-generated method stub
		return null;
	}

}
