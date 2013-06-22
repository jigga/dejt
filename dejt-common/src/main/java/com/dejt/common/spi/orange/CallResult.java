/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.spi.orange;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jigga
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "callResult", namespace = "http://api.openmiddleware.pl/call")
public class CallResult {
    
    @XmlElement(name = "status_code", namespace = "http://api.openmiddleware.pl/call")
    private String statusCode;
    
    @XmlElement(name = "status_msg", namespace = "http://api.openmiddleware.pl/call")
    private String statusMessage;
    
    @XmlElement(namespace = "http://api.openmiddleware.pl/call")
    private String status;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
}
