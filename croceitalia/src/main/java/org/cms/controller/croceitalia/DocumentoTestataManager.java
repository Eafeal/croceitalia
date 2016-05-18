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
	
	@SuppressWarnings("unchecked")
	public List<Mezzo> caricaMezzi() {

		List<Mezzo> list = (List<Mezzo>) this.execNamedQuery("Mezzo.loadAll");

		return list;

	}
	
	@SuppressWarnings("unchecked")
	public List<Banca> caricaBanche() {

		List<Banca> list = (List<Banca>) this.execNamedQuery("Banca.loadAll");

		return list;

	}
	@SuppressWarnings("unchecked")
	public List<Cliente> caricaClienti() {

		List<Cliente> list = (List<Cliente>) this.execNamedQuery("Cliente.loadAll");

		return list;

	}
	public Class<?> getEntityClass() {
		// TODO Auto-generated method stub
		return null;
	}


}
