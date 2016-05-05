package org.cms.jpa.dao.impl;

import it.asso.util.AssoException;
import it.asso.util.AssoLogger;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.Table;

import org.cms.jpa.object.impl.DominioDao_itf;
import org.cms.jpa.object.impl.DominioModel_itf;
import org.cms.jpa.object.impl.ModelLoader;
import org.cms.jpa.object.impl.Relazione;
import org.cms.jpa.object.impl.RelazioneDao_itf;
import org.cms.jpa.object.impl.TipoOggetto_itf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ConsDonzelliPaolo
 * 
 *         Sono i DAO che appartengono al Dominio. NON Appartengono al Business
 */
public abstract class DominioDao implements DominioDao_itf {

    /**
	 * 
	 */
    @Autowired
    @PersistenceUnit(unitName = "pu_cms")
    private EntityManagerFactory entityManagerFactory;

    /**
     * @param em
     */
    public void close(EntityManager em) {

        // em.getDelegate();
        if (em.isOpen()) {
            // System.out.println("Close em");
            em.close();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#deleteById(java.lang.Class,
     * java.lang.String)
     */
    @Override
    public void deleteById(Class<?> aClass, String id) throws AssoException {

        EntityManager em = null;
        EntityTransaction transaction = null;
        RelazioneDao_itf dao = ModelLoader.get();
        try {
            em = getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            // Trovo l'oggetto in questione
            DominioModel_itf model = (DominioModel_itf) em.find(aClass, id);

            // Cancello l'oggetto dove è padre
            Iterator<Relazione> figli = dao.loadChildren(em, model, model.getTipoOggetto()).iterator();
            while (figli.hasNext()) {
                Relazione relazione = figli.next();
                em.remove(relazione);
            }

            // Cancello l'oggetto dove è figlio
            Iterator<Relazione> padri = dao.loadParents(em, model, model.getTipoOggetto()).iterator();
            while (padri.hasNext()) {
                Relazione relazione = padri.next();
                em.remove(relazione);
            }

            // Cancello l'oggetto
            em.remove(model);

            // Commit the transaction
            em.getTransaction().commit();

        } catch (Throwable e) {
            // Rollback the transaction
            error(transaction, e);
        } finally {
            // Close this EntityManager
            if (em != null) {
                close(em);
            }
        }
    }

    @Override
    public void deleteById(String id) throws AssoException {

        deleteById(getEntityClass(), id);
    }

    /**
     * @param transaction
     * @param e
     * @throws AssoException
     */
    protected final void error(EntityTransaction transaction, Throwable e) throws AssoException {

        boolean active = transaction.isActive();
        if (active) {
            transaction.rollback();
        }
        AssoException assoException = new AssoException(e);
        throw assoException;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#findAll(java.lang.Class,
     * org.cms.jpa.object.impl.Dominio)
     * 
     * "SELECT o.id, o.quantity, o.item  FROM Order o, Item i  WHERE (o.item = i.id) AND (i.name = 'widget')"
     * "select obj from Pagina obj where obj.uid=:uid and obj.dominio.uid=:dominioUid"
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DominioModel_itf> findAll(TipoOggetto_itf tipoOggetto, String dominioUid) throws AssoException {

        EntityManager em = getEntityManager();
        String ql = "select obj from " + getTableName(tipoOggetto)
                + " obj WHERE obj.dominio.uid=:dominioUid AND obj.tipoOggetto.uid=:tipooggetto_uid ";
        Query query = em.createQuery(ql);
        query.setParameter("dominioUid", dominioUid);
        query.setParameter("tipooggetto_uid", tipoOggetto.getUid());

        List<DominioModel_itf> resultList = query.getResultList();

        return resultList;

    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#findById(java.lang.Class,
     * java.lang.String)
     */
    public Object findById(Class<?> classe, String id) throws AssoException {

        EntityManager em = getEntityManager();
        Object oggetto = em.find(classe, id);

        if (oggetto == null) {
            throw new AssoException("Oggetto Inesistente! Classe=" + classe + ";id=" + id);
        }

        // close(em);

        return oggetto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#findById(java.lang.String)
     */
    @Override
    public Object findById(String id) {

        try {
            return findById(getEntityClass(), id);
        } catch (AssoException error) {
            // DO NOTHING
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.cms.jpa.manager.itf.AssoDao_itf#findById(org.cms.jpa.object.impl.
     * TipoOggetto, java.lang.String)
     */
    public Object findById(TipoOggetto_itf tipoOggetto, String id) throws AssoException {

        return findById(tipoOggetto.getTypeClass(), id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#findByUid(java.lang.String,
     * java.lang.String)
     */
    public Object findByUid(String uid, String dominioUid) {

        EntityManager em = getEntityManager();

        String tableName = getTableName();
        Query query = em.createQuery("select obj from " + tableName
                + " obj where obj.uid=:uid and obj.dominio.uid=:dominioUid");
        query.setParameter("uid", uid);
        query.setParameter("dominioUid", dominioUid);
        try {
            Object singleResult = query.getSingleResult();

            AssoLogger.GetInstance().logInfo(
                    this.getClass().getSimpleName(),
                    "Caricata oggetto di tipo " + tableName + " avente uid=#" + uid + "# per il dominio #" + dominioUid
                            + "#");

            return singleResult;

        } catch (javax.persistence.NoResultException e) {
            AssoLogger.GetInstance().logInfo(
                    this.getClass().getSimpleName(),
                    "OGGETTO INESISTENTE! L'oggetto di tipo " + tableName + " avente uid=#" + uid
                            + "# per il dominio #" + dominioUid + "# NON esiste");
            throw e;
        }

    }

    /**
     * @return
     */
    public EntityManager getEntityManager() {

        // if (_entityManager != null && _entityManager.isOpen()) {
        // return _entityManager;
        // }
        // _entityManager = getEntityManagerFactory().createEntityManager();
        // return _entityManager;

        return getEntityManagerFactory().createEntityManager();
    }

    /**
     * @return
     */
    private EntityManagerFactory getEntityManagerFactory() {

        return entityManagerFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#getTableName()
     */
    public final String getTableName() {

        Table annotation = getEntityClass().getAnnotation(javax.persistence.Table.class);
        String tableName = annotation.name();

        return tableName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.cms.jpa.manager.itf.AssoDao_itf#getTableName(org.cms.jpa.object.impl
     * .TipoOggetto)
     */
    public String getTableName(TipoOggetto_itf tipoOggetto) throws AssoException {

        Class<?> typeClass = tipoOggetto.getTypeClass();
        Table annotation = typeClass.getAnnotation(javax.persistence.Table.class);
        String tableName = annotation.name();

        return tableName;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#save(java.util.List)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(List<Object> objectList) throws AssoException {

        EntityManager em = null;
        EntityTransaction transaction = null;

        try {
            em = getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Iterator<Object> iterator = objectList.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();
                em.persist(obj);
            }
            // Commit the transaction
            transaction.commit();
        } catch (Throwable e) {
            // Rollback the transaction
            error(transaction, e);
        } finally {
            // Close this EntityManager
            if (em != null) {
                close(em);
            }
        }

    }

    /*
     * 
     * Esegue la INSERT dei nuovi oggetti
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#save(java.lang.Object)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public void save(Object obj) throws AssoException {

        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();
            em.persist(obj);
            transaction.commit();
        } catch (Throwable e) {
            // Rollback the transaction
            error(transaction, e);
        } finally {
            // Close this EntityManager
            if (em != null) {
                close(em);
            }
        }
    }

    /**
     * @param sql
     * @param parameters
     * @return
     */
    @SuppressWarnings("rawtypes")
    public List select(String sql, Map<String, Object> parameters) {

        EntityManager em = getEntityManager();

        Query query = em.createQuery(sql);
        Iterator<Entry<String, Object>> iterator = parameters.entrySet().iterator();
        while (iterator.hasNext()) {
            Entry<String, Object> entry = iterator.next();
            query.setParameter(entry.getKey(), entry.getValue());
        }

        List resultList = query.getResultList();

        return resultList;
    }

    /**
     * @param entityManagerFactory
     */
    public void setEntityManagerFactory(EntityManagerFactory entityManagerFactory) {

        this.entityManagerFactory = entityManagerFactory;
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#update(java.util.List)
     */
    public void update(List<Object> objectList) throws AssoException {

        EntityManager em = null;
        EntityTransaction transaction = null;

        try {
            em = getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            Iterator<Object> iterator = objectList.iterator();
            while (iterator.hasNext()) {
                Object obj = iterator.next();

                boolean isManaged = em.contains(obj);
                if (!isManaged) {
                    // System.out.println("isManaged=" + isManaged);
                    // System.out.println("Now isManaged=" + em.contains(obj));
                }
                em.merge(obj);
            }
            // Commit the transaction
            transaction.commit();
        } catch (Throwable e) {
            // Rollback the transaction
            error(transaction, e);
        } finally {
            // Close this EntityManager
            if (em != null) {
                close(em);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#update(java.lang.Object)
     */
    public void update(Object obj) throws AssoException {

        EntityManager em = null;
        EntityTransaction transaction = null;
        try {
            em = getEntityManager();
            transaction = em.getTransaction();
            transaction.begin();

            em.merge(obj);

            transaction.commit();

        } catch (Exception e) {
            error(transaction, e);
        } finally {
            // Close this EntityManager
            if (em != null) {
                close(em);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.cms.jpa.manager.itf.AssoDao_itf#findAll(java.lang.Class,
     * org.cms.jpa.object.impl.Dominio)
     * 
     * "SELECT o.id, o.quantity, o.item  FROM Order o, Item i  WHERE (o.item = i.id) AND (i.name = 'widget')"
     * "select obj from Pagina obj where obj.uid=:uid and obj.dominio.uid=:dominioUid"
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<DominioModel_itf> chooseNewChildren(DominioModel_itf padre, TipoOggetto_itf tipoOggetto,
            String dominioUid) throws AssoException {

        EntityManager em = getEntityManager();
        String ql = "select obj from " + getTableName(tipoOggetto)
                + " obj WHERE obj.dominio.uid=:dominioUid AND obj.tipoOggetto.uid=:tipooggetto_uid "
                + " AND obj.id not in ( select rel.figlio_id from Relazione rel where rel.padre_id=:padre_id)";
        Query query = em.createQuery(ql);
        query.setParameter("dominioUid", dominioUid);
        query.setParameter("tipooggetto_uid", tipoOggetto.getUid());
        query.setParameter("padre_id", padre.getId());

        List<DominioModel_itf> resultList = query.getResultList();

        return resultList;

    }

}
