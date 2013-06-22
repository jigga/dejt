/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core;

import com.dejt.common.model.Preferences;
import com.dejt.common.model.Profile;
import com.dejt.common.model.User;
import java.io.Serializable;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author jigga
 */
@Named
@ApplicationScoped
public class BasicMatcher implements Matcher, Serializable {
    
    /**
     * 
     * @param user1
     * @param user2
     * 
     * @return 
     */
    @Override
    public boolean matchUsers(User user1, User user2) {
        return 
            matchProfPref(user1.getProfile(), user2.getPreferences())
                &&
            matchProfPref(user2.getProfile(), user1.getPreferences());
    }
    
    /**
     * Matches user's profile with other user's preferences.
     * 
     * @param p User's {@link Profile}.
     * @param r Other user's {@link Preferences}.
     * 
     * @return True if user's profile matches other user's preferences,
     *         false otherwise.
     */
    protected boolean matchProfPref(Profile p, Preferences r){
        return (r.getBody().contains(p.getBody().toString())                || r.getBody().isEmpty())          &&
            (r.getEyeColor().contains(p.getEyeColor().toString())           || r.getEyeColor().isEmpty())      &&
            (r.getHairColor().contains(p.getHairColor().toString())         || r.getHairColor().isEmpty())     &&   
            (r.getEducation().contains(p.getEducation().toString())         || r.getEducation().isEmpty())     &&
            (r.getOccupation().contains(p.getOccupation().toString())       || r.getOccupation().isEmpty())    &&
            (r.getMaritalStatus().contains(p.getMaritalStatus().toString()) || r.getMaritalStatus().isEmpty()) &&
            (r.getReligion().contains(p.getReligion().toString())           || r.getReligion().isEmpty())      &&
            (r.getHeightLow() < p.getHeight() && r.getHeightHigh() > p.getHeight()) &&
            (r.getAgeLow() < p.getAge() && r.getAgeHigh() > p.getAge()) &&
            r.getOrientation().contains(p.getOrientation().toString()) &&    
            r.getGender() == p.getGender();
    }
    
}
