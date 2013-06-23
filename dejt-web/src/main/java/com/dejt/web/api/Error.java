/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jigga
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error", namespace = "http://dejt.arkadiuszg.pl/api/")
public class Error {
    
    @XmlElement(namespace = "http://dejt.arkadiuszg.pl/api/")
    private int code;
    
    @XmlElement(namespace = "http://dejt.arkadiuszg.pl/api/")
    private String message;

    public Error() {
    }

    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
