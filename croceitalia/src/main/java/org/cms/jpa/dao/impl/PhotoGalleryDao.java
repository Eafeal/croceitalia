package org.cms.jpa.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cms.jpa.tipi.impl.cms.PhotoGallery;
import org.springframework.stereotype.Repository;

@Repository("photoGalleryDao")
public class PhotoGalleryDao extends DominioDao {

	//@Override
	public Class<?> getEntityClass() {
		return PhotoGallery.class;
	}

	/**
	 * @param dominioUid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PhotoGallery> loadAll(String dominioUid) {

		EntityManager em = getEntityManager();
		Query query = em
				.createQuery("SELECT OBJECT(obj) FROM PhotoGallery obj where obj.dominio.uid=:dominioUid");
		query.setParameter("dominioUid", dominioUid);
		List<PhotoGallery> resultList = query.getResultList();

		close(em);

		return resultList;
	}

}
