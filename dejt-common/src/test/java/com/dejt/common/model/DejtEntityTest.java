/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import javax.persistence.EntityManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.perf4j.StopWatch;
import org.perf4j.slf4j.Slf4JStopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author jigga
 */
public class DejtEntityTest {
    
    public static final String PERSISTENCE_UNIT_NAME = "JUnitPU";
    public static final String READ_ALL_QRY_FORMAT = "Select e from %s e";
    
    protected static Logger logger;
    
    /**
     * Holds information about current test name.
     */
    @Rule
    public TestName testName = new TestName();
    
    protected String testMethod;
    protected EntityManager em;
    protected StopWatch sw;
    protected String testClassName;
    protected String entityClassName;
    
    /*
     * Static initialization block used to initialize slf4j console Logger
     * instance.
     */
    static {
        logger = LoggerFactory.getLogger(DejtEntityTest.class);
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception {
        logger.info("{}: setUpClass", DejtEntityTest.class.getSimpleName());
    }
 
    @AfterClass
    public static void tearDownClass() throws Exception {
        logger.info("{}: tearDownClass", DejtEntityTest.class.getSimpleName());
    }

    /**
     * Constructs new {@link DejtEntityTest} instance.
     */
    public DejtEntityTest() {
        this.testClassName = 
            this.getClass().getSimpleName();
        this.entityClassName = 
            this.getClass().getSimpleName().replaceFirst("Test", "");
    }
    
    /**
     * DOCUMENT ME!
     */
    @Before
    public void setUp() {
        
        this.testMethod = 
                testName.getMethodName();
        
        em = DejtEntitySuite.emf.createEntityManager();
        
        String method = testName.getMethodName();
        logger.info("{}:start", method);
        if (sw == null) {
            sw = new Slf4JStopWatch(method, logger);
        } else {
            sw.start(method);
        }
        
    }
    
    /**
     * DOCUMENT ME!
     */
    @After
    public void tearDown() {
        
        if (em != null) {
            em.clear();
            em.close();
        }
        
        String method = testName.getMethodName();
        sw.stop(method);
        logger.info("{}:stop", method);
        
    }
    
    /**
     * Evicts all entities from the L2 cache maintained by underlying 
     * {@link EntityManagerFactory}.
     */
    protected void clearCache() {
        em.getEntityManagerFactory().getCache().evictAll();
    }
    
}
