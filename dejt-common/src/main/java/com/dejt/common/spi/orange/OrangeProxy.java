/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.spi.orange;

import com.dejt.common.Msisdn;
import com.dejt.common.spi.ProviderException;

/**
 * 
 * @author jigga
 * @author sajara
 */
public interface OrangeProxy {
    
    /**
     * Base URI helping to build final request URI in URLCreator.
     */
    public static final String BASE_URI = "https://openmiddleware.pl/";
    
    /**
     * Base URI of terminal location service.
     */
    public static final String BASE_URI_TERMINAL_LOCATION = "https://openmiddleware.pl/rest/terminal_location/location";
    
    
    /**
     * Base URI of terminal location service.
     */
    public static final String BASE_URI_SEND_SMS = "https://openmiddleware.pl/orange/oracle/sendSms";
    
    
    /**
     * Base URI of terminal location service.
     */
    public static final String BASE_URI_MAKE_CALL = "https://openmiddleware.pl/orange/oracle/makeCall";
    
    
    /**
     * SERVICE_LOCATION_ACCEPTABLE_ACCURACY_PARAM 
     * SERVICE_LOCATION - service name
     * ACCEPTABLE_ACCURACY_PARAM - service parameter
     * Parameter set when invoking getLocation API method
     * 
     * acceptableAccuracy: Integer. Required. The range that the application
     * considers useful (in meters). If the location cannot be determined within this
     * range, the application would prefer not to receive this information.
     */
    public static final String SERVICE_LOCATION_ACCEPTABLE_ACCURACY_PARAM = "acceptableAccuracy";
    
    
    /**
     * SERVICE_LOCATION_ADDRESS_PARAM
     * SERVICE_LOCATION - service name
     * ACCEPTABLE_ADDRESS_PARAM - service parameter
     * Parameter set when invoking getLocation API method
     * 
     * Address: String. Required. The address of the terminal whose location is
     * required, as a URI (tel:48510123456).
     */
    public static final String SERVICE_LOCATION_ADDRESS_PARAM = "address";
    
    
    /**
     * SERVICE_LOCATION_REQUESTED_ACCURACY_PARAM 
     * SERVICE_LOCATION - service name
     * REQUESTED_ACCURACY_PARAM - service parameter
     * Parameter set when invoking getLocation API method
     * 
     * requestedAccuracy: Integer. Required. The range over which the application
     * wishes to receive location information. This may influence the choice of location
     * technology to use (for instance, cell sector location may be suitable for requests
     * specifying 1000 meters, but GPS technology may be required for requests below
     * 100 meters.
     */
    public static final String SERVICE_LOCATION_REQUESTED_ACCURACY_PARAM = "requestedAccuracy";
    
    
    /**
     * SERVICE_LOCATION_TOLERANCE_PARAM
     * SERVICE_LOCATION - service name
     * TOLERANCE_PARAM - service parameter
     * Parameter set when invoking getLocation API method
     * 
     *  NODELAY The server should immediately return any location estimate that it
     *	currently has. If no estimate is available, the server return a failure
     *	indication. It may optionally initiate procedures to obtain a location
     *	estimate (for example, to be available for a later request.
     *
     *	LOWDELAY The response time is more important than requested accuracy. The
     *	server attempts to fulfill any accuracy requirement, but not if it adds
     *	delay. A quick response with lower accuracy is more desirable than
     *	waiting for a more accurate response.
     *
     *	DELAYTOLERANT The network is expected to return a location with the requested
     *	accuracy even if this means not complying with the requested
     *	response time.
     */
    public static final String SERVICE_LOCATION_TOLERANCE_PARAM = "tolerance";
    
    
    /**
     * Warning massage for no implementation of service.
     */
    public static final String SERVICE_NOT_IMPLEMENTED = "NOT_IMPLEMENTED";
    
    
    /**
     * UTF-8 encoding constant.
     */
    public static final String UTF_8_ENCODING = "UTF-8";
    
    
    /**
     * Additional parameter needed in Location Service.
     */
    public static final String SERVICE_LOCATION_TEL_PREFIX = "tel:";
    
    
    /**
     * Additional parameter needed in Location Service.
     */
    public static final String SERVICE_LOCATION_LOCATION_NO_DELAY= "NoDelay";
    
    
    /**
     * Additional parameter needed in Location Service.
     */
    public static final String SERVICE_LOCATION_LOCATION_LOW_DELAY= "LowDelay";
    
    
    /**
     * Additional parameter needed in Location Service.
     */
    public static final String SERVICE_LOCATION_LOCATION_DELAY_TOLERANT= "DelayTolerant";

    
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
    public SmsResult sendSMS(Msisdn from, Msisdn to, String message)
	    throws ProviderException;

    
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
    public String getSMSDeliveryStatus(String messageId)
	    throws ProviderException;

    
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
    public LocationOutput getLocation(Msisdn msisdn) throws ProviderException;
    
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
    public CallResult makeCall(Msisdn callingParty, Msisdn calledParty) throws ProviderException;

}
