/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common;

import java.util.Locale;

/**
 *
 * @author jigga
 */
public enum ISOCountry {
    
    PL((short)48);

    private short areaCode;

    private ISOCountry(short areaCode) {
        this.areaCode = areaCode;
    }

    public short getAreaCode() {
        return areaCode;
    }
    
    public static void main(String[] args) {
        
        String[] countryCodes = Locale.getISOCountries();
        for (String s : countryCodes) {
            System.out.println(s);
        }
        
    }
}
