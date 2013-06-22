/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JUnit test {@link Suite} for Aramis entity classes.
 * 
 * @author jigga
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
    com.dejt.common.model.UserTest.class
})
public class DejtEntitySuite {
    
    public static final String PERSISTENCE_UNIT_NAME = "JUnitPU";
    public static final String READ_ALL_QRY_FORMAT = "Select e from %s e";
    
    protected static final EntityManagerFactory emf;
    
    private static Logger logger;
    private static StopWatch sw;
    
    // static initialization block.
    static {
        
        logger = 
            LoggerFactory.getLogger(DejtEntitySuite.class);
        
        // Set up dictionary tables before running tests...
        emf = 
            Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        
    }
    
    /**
     * 
     * @throws Exception 
     */
    @BeforeClass
    public static void setUpClass() throws Exception {
        
        sw = new Slf4JStopWatch(DejtEntitySuite.class.getSimpleName());
        logger.info(
            "{} test suite start.", 
            DejtEntitySuite.class.getSimpleName()
        );
        
        // Initializing the environment.
        preloadDictionaryTables();
        
    }
 
    /**
     * 
     * @throws Exception 
     */
    @AfterClass
    public static void tearDownClass() throws Exception {
        
        sw.stop(DejtEntitySuite.class.getSimpleName());
        logger.info(
            "{} test suite stop.", 
            DejtEntitySuite.class.getSimpleName()
        );
        
    }
    
    /*
     * Fills all dictionaty tables with data before running tests.
     */
    private static void preloadDictionaryTables() {
        
        logger.info("DejtEntitySuite;preloadDictionaryTables");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            
            // Preloading DBody entities.
            for (DBody.BodyType type : DBody.BodyType.values()) {
                logger.info("Preloading {}", type);
                em.persist(new DBody(type));
            }
            
            // Preloading DCharacter entities.
            for (DCharacter.CharacterType character : DCharacter.CharacterType.values()) {
                em.persist(new DCharacter(character));
            }
            
            // Preloading DEducation entities.
            for (DEducation.EducationType type : DEducation.EducationType.values()) {
                em.persist(new DEducation(type));
            }
            
            // Preloading DEyeColor entities.
            for (DEyeColor.EyeColor color : DEyeColor.EyeColor.values()) {
                em.persist(new DEyeColor(color));
            }
            
            // Preloading DHair entities.
            for (DHair.HairColor color : DHair.HairColor.values()) {
                em.persist(new DHair(color));
            }
            
            // Preloading DInterest entities.
            for (DInterest.Interest interest : DInterest.Interest.values()) {
                em.persist(new DInterest(interest));
            }
            
            // Preloading DMaritalstatus entities.
            for (DMaritalstatus.MaritalStatus status : DMaritalstatus.MaritalStatus.values()) {
                em.persist(new DMaritalstatus(status));
            }
            
            // Preloading DMaritalstatus entities.
            for (DOccupation.Occupation occupation : DOccupation.Occupation.values()) {
                em.persist(new DOccupation(occupation));
            }
            
            // Preloading DOrientation entities.
            for (DOrientation.Orientation orientation : DOrientation.Orientation.values()) {
                em.persist(new DOrientation(orientation));
            }
            
            // Preloading DReligion entities.
            for (DReligion.Religion religion : DReligion.Religion.values()) {
                em.persist(new DReligion(religion));
            }
            
            // Preloading DTerm entities.
            for (DTerm.TermType type : DTerm.TermType.values()) {
                em.persist(new DTerm(type));
            }
            
            // Preloading DTolerance entities.
            for (DTolerance.Tolerance tolerance : DTolerance.Tolerance.values()) {
                em.persist(new DTolerance(tolerance));
            }
            
            em.getTransaction().commit();
            em.getEntityManagerFactory().getCache().evictAll();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            closeEntityManager(em);
        }
        
    }
    
    private static void closeEntityManager(EntityManager em) {
        
        if (em != null) {
            em.close();
        }
        
    }
    
}
