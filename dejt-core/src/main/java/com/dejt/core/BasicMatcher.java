/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core;

import com.dejt.common.model.Preferences;
import com.dejt.common.model.Profile;
import com.dejt.common.model.User;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author jigga
 */
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
        
        return
            isNullOrEmpty(r.getBodyPreferences()) || r.getBodyPreferences().contains(p.getBody())                         &&
            isNullOrEmpty(r.getEyePreferences()) || r.getEyePreferences().contains(p.getEyeColor())                       &&
            isNullOrEmpty(r.getHairPreferences()) || r.getHairPreferences().contains(p.getHairColor())                    &&
            isNullOrEmpty(r.getEducationPreferences()) || r.getEducationPreferences().contains(p.getEducation())          &&
            isNullOrEmpty(r.getOccupationPreferences()) || r.getOccupationPreferences().contains(p.getOccupation())       &&
            isNullOrEmpty(r.getMaritalPreferences()) || r.getMaritalPreferences().contains(p.getMaritalStatus())          &&
            isNullOrEmpty(r.getReligionPreferences()) || r.getReligionPreferences().contains(p.getReligion())             &&
            isNullOrEmpty(r.getOrientationPreferences()) || r.getOrientationPreferences().contains(p.getOrientation())    &&
            (r.getHeightLow() < p.getHeight() && r.getHeightHigh() > p.getHeight())                                       &&
            (r.getAgeLow() < p.getAge() && r.getAgeHigh() > p.getAge())                                                   &&
            r.getGender() == p.getGender();
        
    }
    
    /**
     * Checks if the given list is null or empty.
     * 
     * @param list
     * 
     * @return true if the given list is null or empty, false otherwise.
     */
    protected boolean isNullOrEmpty(List list) {
        return list == null || list.isEmpty();
    }
    
}
