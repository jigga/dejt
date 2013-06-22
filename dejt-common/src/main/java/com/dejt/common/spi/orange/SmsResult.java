/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.spi.orange;

import java.text.MessageFormat;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jigga
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "smsResult", namespace = "http://api.openmiddleware.pl/sendsms")
public class SmsResult {
    
    @XmlElement(namespace = "http://api.openmiddleware.pl/sendsms")
    private String result;
    
    @XmlElement(namespace = "http://api.openmiddleware.pl/sendsms")
    private String deliveryStatus;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[result={1}, deliveryStatus={2}]", getClass().getSimpleName(), result, deliveryStatus);
    }
    
}
