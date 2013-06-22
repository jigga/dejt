/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common;
import com.dejt.common.model.User;
import com.dejt.common.spi.ProviderException;
import com.dejt.common.spi.orange.LocationOutput;
import com.dejt.common.spi.orange.OrangeProxy;
import org.gavaghan.geodesy.GlobalPosition;
/**
 *
 * @author Quayu
 */
public final class OrangeApiRequests {
    
    protected OrangeProxy orangeProxy;
    
    public void Click2Call(User u1, User u2){
        Msisdn m1 = new Msisdn(ISOCountry.PL, u1.getPhoneNumber());
        Msisdn m2 = new Msisdn(ISOCountry.PL, u2.getPhoneNumber());
        
        try {
             orangeProxy.makeCall(m1, m2);
        } catch (ProviderException p) {} 
    }
    
    public void SMSsend(User u1, User u2, String message){
        Msisdn m1 = new Msisdn(ISOCountry.PL, u1.getPhoneNumber());
        Msisdn m2 = new Msisdn(ISOCountry.PL, u2.getPhoneNumber());
        
        try {
             orangeProxy.sendSMS(m1, m2, message);
        } catch (ProviderException p) {} 
    }
    
    public GlobalPosition getLocation(User u){
        
        Msisdn m = new Msisdn(ISOCountry.PL, u.getPhoneNumber());
        LocationOutput lo = new LocationOutput();
        
        try {
            lo = orangeProxy.getLocation(m);
        } catch (ProviderException p) {
        }
        
        return new GlobalPosition(lo.getLongitude(), lo.getLatitude(), 0.0);
    }
    
}
