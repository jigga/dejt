/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common;

/**
 *
 * @author jigga
 */
public class Msisdn {
    
    private final ISOCountry country;
    private final String subscriberNumber;

    public Msisdn(ISOCountry country, String subscriberNumber) {
        this.country = country;
        this.subscriberNumber = formatSubscriberNumber(subscriberNumber);
    }

    public ISOCountry getCountry() {
        return country;
    }

    public String getSubscriberNumber() {
        return subscriberNumber;
    }
    
    private String formatSubscriberNumber(String subscriberNUmber) {
        
        subscriberNUmber = 
            subscriberNUmber.replaceAll("\\D", "");
        if (subscriberNUmber.startsWith("0")) {
            subscriberNUmber = subscriberNUmber.substring(1);
        }
        return subscriberNUmber;
        
    }

    /**
     * Return Msisdn in international format, i.e. with plus sign at the
     * beginning of the number.
     * 
     * @return String representation of Msisdn in international format.
     */
    @Override
    public String toString() {
        return "+" +  country.getAreaCode() + subscriberNumber;
    }
    
}
