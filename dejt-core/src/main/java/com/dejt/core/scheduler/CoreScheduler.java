/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core.scheduler;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.User;
import com.dejt.common.spi.ProviderException;
import com.dejt.common.spi.orange.LocationOutput;
import com.dejt.common.spi.orange.OrangeProxy;
import com.dejt.core.Matcher;
import com.dejt.core.util.UserPair;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalPosition;

/**
 *
 * @author jigga
 */
@Singleton
@ApplicationScoped
public class CoreScheduler implements Serializable {

    private static final String API_KEY = "AIzaSyAeuXFV1Hav1dgzteLLKbvFpsqc-LK2tV0";
    
    @EJB(beanName = "SessionFacade")
    protected CRUDFacade facade;
    
    @Inject
    @ApplicationScoped
    protected Matcher matcher;
    
    @Inject
    @ApplicationScoped
    protected OrangeProxy proxy;
    
    /**
     * 
     * @param timer 
     */
    @Schedule(hour = "*", minute = "*", info = "Dejt Core Scheduler")
    protected void schedule(Timer timer) {
        
        //retrieve list of active users from db  
        List<User> activeUsers = facade.getEntityManager()
            .createNamedQuery("User.findByActiveFlag", User.class)
            .setParameter("active", Boolean.TRUE)
            .getResultList();

        //set users locations        
        for (User u: activeUsers){
            
            try {
                LocationOutput location = 
                    proxy.getLocation(u.getMsisdn());
                u.setCurrentLocation(
                    new GlobalPosition(location.getLatitude(), location.getLongitude(), 0.0)
                );
            } catch (ProviderException p){
                activeUsers.remove(u);
            }             
        }

        //declare list containg matching users 
        int userCount = activeUsers.size();
        List<UserPair> closePairsList;
        closePairsList = new ArrayList<>();
                 
        //find users in close range, match them and fill list with those data
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;  

        for (int i=0; i<userCount-1; i++){
            for (int j=i+1; j<userCount; j++){
                double distance = geoCalc.calculateGeodeticCurve(reference, 
                        activeUsers.get(i).getCurrentLocation(), 
                        activeUsers.get(j).getCurrentLocation())
                        .getEllipsoidalDistance();
                
                if (distance < 500 && matcher.matchUsers(activeUsers.get(i), activeUsers.get(j))) {
                    closePairsList.add(new UserPair(activeUsers.get(i), activeUsers.get(j)));
                }
            }    
        }

        //send push notifications to matching users
        Sender sender = new Sender(API_KEY);
        userCount = closePairsList.size();
        for (int k=0; k<userCount; k++){
            
            Message.Builder messageBuilder = new Message.Builder().delayWhileIdle(false);
            messageBuilder.addData("profile", closePairsList.get(k).getUser2().getUid());
            
            try {
                 sender.send(messageBuilder.build(), closePairsList.get(k).getUser1().getUid(), 5);
            } catch (IOException e) {
            }

        }
        
    }

}
