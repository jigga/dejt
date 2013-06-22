/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core.scheduler;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.User;
import com.dejt.common.spi.orange.OrangeProxy;
import com.dejt.core.Matcher;
import com.dejt.core.util.UserLocation;
import com.dejt.core.util.UserPair;
import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Sender;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.ejb.Timer;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;

/**
 *
 * @author jigga
 */
@Singleton
@ApplicationScoped
public class CoreScheduler implements Serializable {

    @Inject
    protected CRUDFacade facade;
    
    @Inject
    protected Matcher matcher;
    
    @Inject
    protected OrangeProxy proxy;
    
    private static final String API_KEY = "AIzaSyAeuXFV1Hav1dgzteLLKbvFpsqc-LK2tV0";
    
    @Schedule(hour = "*", minute = "*", info = "Dejt Core Scheduler")
    protected void schedule(Timer timer) {
        
        //retrieve list of active users from db  
        List<User> activeUsers = facade.getEntityManager()
            .createNamedQuery("User.findByActiveFlag", User.class)
            .setParameter("active", Boolean.TRUE)
            .getResultList();

        //declare array containing users with their locations        
        int userCount = activeUsers.size();
        UserLocation[] locTable;
        locTable = new UserLocation[userCount];
        
        //fill array with data        
        for (int k=0; k<userCount; k++){             
             locTable[k] = new UserLocation(activeUsers.get(k)); 
        }

        //declare list containg matching users        
        List<UserPair> closePairsList;
        closePairsList = new ArrayList<>();
                 
        //find users in close range, match them and fill list with those data
        GeodeticCalculator geoCalc = new GeodeticCalculator();
        Ellipsoid reference = Ellipsoid.WGS84;  

        for (int i=0; i<userCount-1; i++){
            for (int j=i+1; j<userCount; j++){
                double distance = 
                    geoCalc.calculateGeodeticCurve(reference, locTable[i].getPosition(), locTable[j].getPosition()).getEllipsoidalDistance();
                
                if (distance < 500 && matcher.matchUsers(locTable[i].getUser(), locTable[j].getUser())) {
                    closePairsList.add(new UserPair(locTable[i].getUser(), locTable[j].getUser()));
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
