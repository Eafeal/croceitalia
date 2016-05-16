package org.cms.controller.croceitalia;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.dao.impl.AssoDao;
import org.springframework.stereotype.Repository;
import org.springframework.ui.ModelMap;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;
import it.asso.util.Utente_itf;
import it.asso.util.Util;

@Repository("documento_row_manager")
public class Documento_Row_Manager extends AssoDao{
	
	/**
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<Documento_Row> caricaDocumento_Row() {

		List<Documento_Row> lista = (List<Documento_Row>) this.execNamedQuery("Documento_Row.loadAll");

		return lista;

	}
	
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}



}
