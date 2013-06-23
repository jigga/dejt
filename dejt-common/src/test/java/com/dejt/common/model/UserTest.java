/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import com.dejt.common.ISOCountry;
import java.util.Calendar;
import java.util.Date;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * This class contains JUnit test cases for {@link Flow} entity class.
 * 
 * @author jigga
 */
public class UserTest extends DejtEntityTest {

    /**
     * TODO: DOCUMENT ME!!!
     */
    @Test
    public void testCreateUserWithProfile() {
        
        Calendar birthDay = Calendar.getInstance();
        birthDay.set(Calendar.YEAR, 1983);
        birthDay.set(Calendar.MONTH, Calendar.AUGUST);
        birthDay.set(Calendar.DATE, 31);
        User user = new User(
            "jigga",
            "Arkadiusz",
            "Gasiński",
            "user@domain.com",
            ISOCountry.PL,
            "501500989"
        );
        
        Profile profile = new Profile(user, Gender.M, birthDay.getTime());
        profile.setCreationTime(new Date());
        profile.setAlkohol(true);
        profile.setCigarets(false);
        profile.setBody(em.find(DBody.class, DBody.BodyType.W));
        profile.setEducation(em.find(DEducation.class, DEducation.EducationType.W));
        profile.setEyeColor(em.find(DEyeColor.class, DEyeColor.EyeColor.B));
        profile.setOrientation(em.find(DOrientation.class, DOrientation.Orientation.H));
        profile.setReligion(em.find(DReligion.class, DReligion.Religion.K));
        
        user.setProfile(profile);
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        
    }
    
    @Test
    public void createUserWithPreferences() {
        
        Calendar birthDay = Calendar.getInstance();
        birthDay.set(Calendar.YEAR, 1983);
        birthDay.set(Calendar.MONTH, Calendar.AUGUST);
        birthDay.set(Calendar.DATE, 31);
        User user = new User(
            "hova",
            "Arkadiusz",
            "Gasiński",
            "user@domain.com",
            ISOCountry.PL,
            "501500989"
        );
        
        Preferences preferences = 
            new Preferences(user, 17, 40, Gender.F, new Date());
        preferences.getBodyPreferences().add(new DBody(DBody.BodyType.S));
        preferences.getBodyPreferences().add(new DBody(DBody.BodyType.W));
        preferences.getBodyPreferences().add(new DBody(DBody.BodyType.L));
        
        user.setPreferences(preferences);
        
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        
    }
    
}
