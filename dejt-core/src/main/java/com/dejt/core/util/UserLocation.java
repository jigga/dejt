/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core.util;

import com.dejt.common.model.User;
import org.gavaghan.geodesy.GlobalPosition;
import com.dejt.common.spi.orange.OrangeProxy;
import com.dejt.common.Msisdn;
import com.dejt.common.ISOCountry;
import com.dejt.common.spi.ProviderException;
import com.dejt.common.spi.orange.LocationOutput;

/**
 * Class to calculate user location and to store this data.
 * 
 * @author quayu
 */
public class UserLocation {
    
    private final User user;
    private final GlobalPosition userLocation;
    
    protected OrangeProxy orangeProxy;
      
    public UserLocation(User u){
        
        Msisdn m = new Msisdn(ISOCountry.PL, u.getPhoneNumber());
        LocationOutput lo = new LocationOutput();
        
        try {
            lo = orangeProxy.getLocation(m);
        } catch (ProviderException p) {
        }
        
        this.user = u;
        this.userLocation = new GlobalPosition(lo.getLongitude(), lo.getLatitude(), 0.0);
    }
    
    public User getUser(){
        return this.user;
    }
    
    public GlobalPosition getPosition(){
        return this.userLocation;
    }
    
}
