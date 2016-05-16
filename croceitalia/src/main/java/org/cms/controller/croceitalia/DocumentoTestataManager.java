package org.cms.controller.croceitalia;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.cms.jpa.dao.impl.AssoDao;
import org.cms.jpa.object.impl.Model;
import org.springframework.stereotype.Repository;
@Repository("documento_testata_manager")
public class DocumentoTestataManager extends AssoDao{
	
	/**
	 * @return
	 */

	@SuppressWarnings("unchecked")
	public List<DocumentoTestata> caricaDocumento_Testata() {

		List<DocumentoTestata> lista = (List<DocumentoTestata>) this.execNamedQuery("Documento_Testata.loadAll");

		return lista;

	}
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}


}
