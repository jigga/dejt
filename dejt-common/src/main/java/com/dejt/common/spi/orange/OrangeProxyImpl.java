/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.spi.orange;

import com.dejt.common.ISOCountry;
import com.dejt.common.Msisdn;
import com.dejt.common.spi.ProviderException;
import com.dejt.common.util.JsonUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.net.ssl.SSLContext;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.glassfish.jersey.SslConfigurator;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.filter.HttpBasicAuthFilter;
import org.json.simple.JSONValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 
 * @author sajara
 * @author jigga
 */
@Named
@ApplicationScoped
public class OrangeProxyImpl implements OrangeProxy {
    
    /**
     * Username for authentication to Hackathon APIs.
     */
    protected static final String USERNAME = "hackathon60";
    
    /**
     * Password for authentication to Hackathon APIs.
     */
    protected static final String PASSWORD = "vAA9S2D9";
    
    /**
     * Password for generated CACERT file.
     */
    protected static final String TRUSTSTORE_PASSWORD = "password";
    
    /**
     * CONNECT_TIMEOUT for client connecting to Hackathon APIs.
     */
    protected static final int CONNECT_TIMEOUT = 5000;
    
    /**
     * READ_TIMEOUT for client connecting to Hackathon APIs.
     */
    protected static final int READ_TIMEOUT = 30000;
    
    
    protected static final Logger logger = 
            LoggerFactory.getLogger(OrangeProxyImpl.class);
    
    protected static final Client CLIENT;
    
    static {
        
        byte[] trustStoreBytes;
        InputStream is = null;
        ByteArrayOutputStream baos = null;
        try {
            is = OrangeProxyImpl.class.getResourceAsStream("/cacerts");
            baos = new ByteArrayOutputStream();
            int nRead;
            byte[] buffer = new byte[16384];        
            while ((nRead = is.read(buffer, 0, buffer.length)) != -1) {
              baos.write(buffer, 0, nRead);
            }
            baos.flush();
            
            trustStoreBytes = baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (is!=null) {is.close();}
                if (baos!=null) {baos.close();}
            } catch (Exception e) {}
        }
        
        
        SslConfigurator sslConfig = SslConfigurator.newInstance()
            .trustStoreBytes(trustStoreBytes)
            .trustStorePassword(TRUSTSTORE_PASSWORD);
        SSLContext sslContext = sslConfig.createSSLContext();
        
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, CONNECT_TIMEOUT);
        clientConfig.property(ClientProperties.READ_TIMEOUT, READ_TIMEOUT);
        clientConfig.register(new HttpBasicAuthFilter(USERNAME, PASSWORD));
        
        CLIENT = ClientBuilder.newBuilder()
            .sslContext(sslContext)
            .withConfig(clientConfig)
            .build();
        
    }
    
    /**
     * Sends SMS message.
     * 
     * @param from
     *            Sender's MSISDN number.
     * @param to
     *            Recipient's MSISDN number.
     * @param message
     *            SMS message content.
     * 
     * @return {@link SmsResult} instance which encapsulate message delivery 
     *         status.
     * 
     * @throws ProviderException
     *         If this method could not be completed due to an error on the
     *         API provider side.
     */
    @Override
    public SmsResult sendSMS(Msisdn from, Msisdn to, String message)
	    throws ProviderException {
        
        Response response = CLIENT.target(BASE_URI_SEND_SMS)
            .queryParam("msg", message)
            .queryParam("to", to)
            .queryParam("from", from)
            .request(MediaType.WILDCARD_TYPE)
            .get();
        
        switch (response.getStatus()) {
            case 200:
                return response.readEntity(SmsResult.class);
            default:
                throw new ProviderException(String.valueOf(response));
        }
        
    }

    /**
     * Returns the delivery status of the given SMS message.
     * 
     * @param messageId
     *            SMS message identifier as returned by
     *            {@link #sendSMS(java.lang.String, java.lang.String, java.lang.String)}
     *            method.
     * 
     * @return SMS message delivery status.
     * 
     * @throws ProviderException
     *             If this method could not be completed due to an error on the
     *             API provider side.
     */
    @Override
    public String getSMSDeliveryStatus(String messageId)
	    throws ProviderException {
        return null;
    }

    /**
     * Returns the MSISDN's location.
     * 
     * Our test MSISDNS:
     * 
     * 505576253 797562338 505576276
     * 
     * @param String
     *            msisdn
     * 
     * @return Location object.
     * 
     * @throws ProviderException
     *             If this method could not be completed due to an error on the
     *             API provider side.
     */
    @Override
    public LocationOutput getLocation(Msisdn msisdn) throws ProviderException {

        String logPrefix = String.format("|GetLocation_%s|", msisdn);
        logger.info("{}Invoking getLocation.", logPrefix);

        logger.info("{}Creating Json object.", logPrefix);
        // 	Create Json object with appropriate parameters. Now only msisdn is dynamic but can be more - read docs->
        // 	http://www.openmiddleware.pl/contest/images/OpenAPI/terminal%20location.pdf
        Map<Object, Object> jsonRequest = new HashMap<>();

        //	Setting params
        logger.info("{}Setting SERVICE_LOCATION_ACCEPTABLE_ACCURACY_PARAM to {}.", logPrefix, 50);
        JsonUtils.setJsonField(
            jsonRequest,
            SERVICE_LOCATION_ACCEPTABLE_ACCURACY_PARAM,
            50
        );

        logger.info("{}Setting SERVICE_LOCATION_ADDRESS_PARAM to {}.", logPrefix, SERVICE_LOCATION_TEL_PREFIX + msisdn);
        JsonUtils.setJsonField(jsonRequest, SERVICE_LOCATION_ADDRESS_PARAM, SERVICE_LOCATION_TEL_PREFIX + msisdn);
        
        logger.info("{}Setting SERVICE_LOCATION_REQUESTED_ACCURACY_PARAM to {}.", logPrefix, 1);
        JsonUtils.setJsonField(jsonRequest, SERVICE_LOCATION_REQUESTED_ACCURACY_PARAM,1);
        
        logger.info("{}Setting SERVICE_LOCATION_TOLERANCE_PARAM to {}.", logPrefix, SERVICE_LOCATION_LOCATION_NO_DELAY);
        JsonUtils.setJsonField(jsonRequest, SERVICE_LOCATION_TOLERANCE_PARAM, SERVICE_LOCATION_LOCATION_NO_DELAY);

        //	Creating string request body
        logger.info("{}Building requestBody from Json.", logPrefix);
        String requestBody = JSONValue.toJSONString(jsonRequest);
        String jsonText;

        //	request body UTF-8 encoding - required - read docs
        try {
            logger.info("{}Encoding request body with {}.", logPrefix, UTF_8_ENCODING);
            jsonText = URLEncoder.encode(requestBody, UTF_8_ENCODING);
        } catch (UnsupportedEncodingException e1) {
            logger.info(e1.getMessage(), e1);
            return null;
        }

        String response;
        try {    
            //  Matching URI with request Json object: ...query={0},jsonText
            //  MessageFormat.format(endpointURL, jsonText)
            response = CLIENT.target(BASE_URI_TERMINAL_LOCATION)
                .queryParam("query", jsonText)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(String.class);
        } catch (Exception e) {
            throw new ProviderException(e);
        }

        JsonNode jnode;
        try {
            //  parsing response to JNode
            jnode = new ObjectMapper().readTree(response);
        } catch (JsonProcessingException e) {
            logger.info(logPrefix+e.getMessage());
            return null;
        } catch (IOException e) {
            logger.info(logPrefix+e.getMessage());
            return null;
        }

        //	Creating output
        LocationOutput output = new LocationOutput();
        output = JsonUtils.printAll(jnode,output);
        logger.info("{}Creating LocationiOutput.", logPrefix);
        logger.info("{}LocationiOutput: {}", logPrefix, output);


        //	Output should be like: {"result":{"timestamp":"2013-06-21T09:34:31+02:00","altitude":null,"longitude":"21.021595",
        //	"latitude":"52.22131","accuracy":"0"}}
        //	Only test msisdns can be asked by application
        return output;
        
    }
    
    /**
     * Establish a phone call between two parties.
     * 
     * @param callingParty
     * @param calledParty
     * 
     * @return
     * 
     * @throws ProviderException If this method could not be completed due to 
     *         an error on the API provider side.
     * 
     */
    @Override
    public CallResult makeCall(Msisdn callingParty, Msisdn calledParty) throws ProviderException {
        
        Response response = CLIENT.target(BASE_URI_MAKE_CALL)
            .queryParam("partb", "tel:" + calledParty)
            .queryParam("parta", "tel:" + callingParty)
            .queryParam("method", "ctoc")
            .request(MediaType.WILDCARD_TYPE)
            .get();
        
        switch (response.getStatus()) {
            case 200:
                return response.readEntity(CallResult.class);
            default:
                logger.info(response.readEntity(String.class));
                throw new ProviderException(String.valueOf(response));
        }
        
    }
    
    public static void main(String[] args) throws Exception {
        Msisdn msisdn = new Msisdn(ISOCountry.PL, "505576253");
        OrangeProxy proxy = new OrangeProxyImpl();
        proxy.sendSMS(msisdn, msisdn, "Yet another simple test.");
    }
    
}
