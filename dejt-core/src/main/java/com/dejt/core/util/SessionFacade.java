/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core.util;

import com.dejt.common.CRUDFacade;
import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.Entity;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * SLSB implementation of {@link CRUDFacade} interface.
 * 
 * @author jigga
 */
@Named
@Stateless(name = "SessionFacade")
public class SessionFacade implements CRUDFacade {
    
    protected static final String READALL_QUERY_FORMAT = 
            "Select e from %s e";
    
    @PersistenceContext(unitName="DejtPU")
    protected EntityManager em;

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    /**
     * <p>
     * Persists the given {@link Entity} instance to the underlying data store
     * and returns a copy to the client if the entity was successfully
     * persisted.
     * </p>
     * 
     * @param entity {@link Entity} instance to be persisted.
     * 
     * @return A copy of the newly created entity.
     * 
     * @throws EntityExistsException If such entity already exist in the target
     *                               data store.
     */
    @Override
    public <T> T create(T entity) throws EntityExistsException {
        em.persist(entity);
        return entity;
    }
    
    /**
     * TODO: Add javadocs.
     * 
     * @param type
     * @param primaryKey
     * 
     * @return
     */
    @Override
    public <T> T read(Class<T> type, Object primaryKey) {
        return em.find(type, primaryKey);
    }
    
    /**
     * Returns all entities from the target data store.
     * 
     * @param type 
     * 
     * @return  
     */
    @Override
    public <T> List<T> readAll(Class<T> type) {
        
        String query = 
            String.format(READALL_QUERY_FORMAT, type.getSimpleName());
        return em.createQuery(query, type).getResultList();
        
    }

    @Override
    public <T> T refresh(Class<T> type, Object primaryKey) {
        T entity = read(type, primaryKey);
        if (entity != null) {
            em.refresh(entity);
        }
        return entity;
    }
    
    /**
     * Updates entity of the given type in the target data store.
     * 
     * @param entity {@link Entity} instance.
     * 
     * @return Updated entity instance. 
     */
    @Override
    public <T> T update(T entity) {
        entity = em.merge(entity);
        return entity;
    }
    
    /**
     * TODO: add javadocs.
     * 
     * @param entity 
     */
    @Override
    public <T> void delete(T entity) {
        entity = em.merge(entity);
        em.remove(entity);
    }
    
}
