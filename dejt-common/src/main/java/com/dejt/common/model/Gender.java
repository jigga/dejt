/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

/**
 *
 * @author Jigga
 */
public enum Gender {
    
    M("Mężczyzna"), F("Kobieta");
    
    private String desc;

    private Gender(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }
    
}
