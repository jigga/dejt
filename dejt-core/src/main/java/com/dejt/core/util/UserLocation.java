/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core.util;

import com.dejt.common.model.User;
import org.gavaghan.geodesy.GlobalPosition;
import com.dejt.common.OrangeApiRequests;

/**
 * Class to calculate user location and to store this data.
 * 
 * @author quayu
 */
public class UserLocation {
    
    private final User user;
    private final GlobalPosition userLocation;
    private OrangeApiRequests oar;

      
    public UserLocation(User u){      
        this.user = u;
        this.userLocation = oar.getLocation(u);
    }
    
    public User getUser(){
        return this.user;
    }
    
    public GlobalPosition getPosition(){
        return this.userLocation;
    }
    
}
