/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.spi.orange;

import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jigga
 */
public class SmsResultTest {
    
    private static JAXBContext ctx;
    
    @BeforeClass
    public static void setUpClass() {
        try {
            ctx = JAXBContext.newInstance(SmsResult.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getResult method, of class SmsResult.
     */
    @Test
    public void testMarshall() {
        
        try {
            Marshaller m = ctx.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

            SmsResult smsResult = new SmsResult();
            smsResult.setResult(String.valueOf(System.nanoTime()));
            smsResult.setDeliveryStatus("MessageWaiting");

            Writer writer = new StringWriter();
            m.marshal(smsResult, writer);
            System.out.println(writer);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        
    }

    /**
     * Test of setResult method, of class SmsResult.
     */
    @Test
    public void testUnmarshall() {
        
        SmsResult result;
        try {
            result = (SmsResult)ctx.createUnmarshaller().unmarshal(new StringReader("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><smsResult xmlns=\"http://api.openmiddleware.pl/sendsms\"><result>51c4b5d0952a3061679280</result><deliveryStatus>MessageWaiting</deliveryStatus></smsResult>"));
        } catch (Exception e) {
            fail(e.getMessage());
            return;
        }
        
        assertNotNull(result.getResult());
        assertNotNull(result.getDeliveryStatus());
        
    }

}