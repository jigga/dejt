/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common;

import java.util.List;
import javax.persistence.EntityManager;

/**
 * Simple CRUD facade definition.
 * 
 * @author jigga
 */
public interface CRUDFacade {
    
    /**
     * 
     * @return 
     */
    EntityManager getEntityManager();
    
    /**
     * Saves the given {@code entity} to the persistence store.
     * 
     * @param entity
     * 
     * @return
     */
    <T> T create(T entity);
    
    /**
     * TODO: add javadocs.
     * 
     * @param type 
     * @param primaryKey
     * 
     * @return 
     */
    <T> T read(Class<T> type, Object primaryKey);
    
    /**
     * TODO: add javadocs.
     * 
     * @param type 
     * 
     * @return
     */
    <T> List<T> readAll(Class<T> type);
    
    /**
     * TODO: add javadocs.
     * 
     * @param type
     * @param primaryKey 
     */
    <T> T refresh(Class<T> type, Object primaryKey);
    
    /**
     * TODO: add javadocs.
     * 
     * @param entity
     * 
     * @return
     */
    <T> T update(T entity);
    
    /**
     * TODO: add javadocs.
     * 
     * @param entity
     */
    <T> void delete(T entity);
    
}
