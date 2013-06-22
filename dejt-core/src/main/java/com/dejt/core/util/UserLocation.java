/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core.util;

import com.dejt.common.model.User;
import org.gavaghan.geodesy.GlobalPosition;

/**
 * Class to calculate user location and to store this data.
 * 
 * @author quayu
 */
public class UserLocation {
    
    private final User user;
    private final GlobalPosition userLocation;
    
    public UserLocation(User u){
        this.user = u;
        float lon = 1; //call openAPI get location on u.getPhoneNumber, set lon and lat
        float lat = 1; //
        this.userLocation = new GlobalPosition(lon, lat, 0.0);
    }
    
    public User getUser(){
        return this.user;
    }
    
    public GlobalPosition getPosition(){
        return this.userLocation;
    }
    
}
