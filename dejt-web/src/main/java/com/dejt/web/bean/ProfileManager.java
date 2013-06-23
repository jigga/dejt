/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import com.dejt.common.model.DBody;
import com.dejt.common.model.DEducation;
import com.dejt.common.model.DEyeColor;
import com.dejt.common.model.DHair;
import com.dejt.common.model.DMaritalstatus;
import com.dejt.common.model.DOccupation;
import com.dejt.common.model.DOrientation;
import com.dejt.common.model.DReligion;
import com.dejt.common.model.Profile;
import com.dejt.common.model.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author jigga
 */
@Named("profmgr")
@SessionScoped
public class ProfileManager implements Serializable {
    
    @Inject
    @LoggedIn
    private User user;
    private Profile profile;
    
    private int day;
    private int month;
    private int year;
    private int height;
    
    private DOrientation.Orientation orientation;
    private DBody.BodyType bodyType;
    private DEyeColor.EyeColor eyeColor;
    private DHair.HairColor hairColor;
    private DEducation.EducationType education;
    private DOccupation.Occupation occupation;
    private DMaritalstatus.MaritalStatus maritalStatus;
    private DReligion.Religion religion;
    
    @PostConstruct
    protected void init() {
        profile = user.getProfile();
        if (profile == null) {
            profile = new Profile(user);
            profile.setCreationTime(new Date());
        }
        if (profile.getBirthDate() != null) {
            Calendar birthDay = Calendar.getInstance();
            birthDay.setTime(profile.getBirthDate());
            this.day = birthDay.get(Calendar.DATE);
            this.month = birthDay.get(Calendar.MONTH);
            this.year = birthDay.get(Calendar.YEAR);
        }
    }
    
    public Profile getProfile() {
        return user.getProfile();
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public DOrientation.Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(DOrientation.Orientation orientation) {
        this.orientation = orientation;
    }

    public DBody.BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(DBody.BodyType bodyType) {
        this.bodyType = bodyType;
    }

    public DEyeColor.EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(DEyeColor.EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public DHair.HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(DHair.HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public DEducation.EducationType getEducation() {
        return education;
    }

    public void setEducation(DEducation.EducationType education) {
        this.education = education;
    }

    public DOccupation.Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(DOccupation.Occupation occupation) {
        this.occupation = occupation;
    }

    public DMaritalstatus.MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(DMaritalstatus.MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public DReligion.Religion getReligion() {
        return religion;
    }

    public void setReligion(DReligion.Religion religion) {
        this.religion = religion;
    }
    
    @Produces
    @SessionScoped
    @Named("days")
    public List<Integer> getDays() {
        
        List<Integer> days = new ArrayList<>();
        for (int date = 1; date<=31; date++) {
            days.add(date);
        }
        return days;
        
    }
    
    @Produces
    @SessionScoped
    @Named("months")
    public List<Integer> getMonths() {
        
        List<Integer> months = new ArrayList<>();
        for (int m = 1; m<=12; m++) {
            months.add(m);
        }
        return months;
        
    }
    
    @Produces
    @SessionScoped
    @Named("years")
    public List<Integer> getYears() {
        
        Calendar c = Calendar.getInstance();
        List<Integer> years = new ArrayList<>();
        for (int y = 1900; y<=c.get(Calendar.YEAR); y++) {
            years.add(y);
        }
        return years;
        
    }
    
    @Produces
    @SessionScoped
    @Named("heights")
    public List<Integer> getHeights() {
        
        List<Integer> heights = new ArrayList<>();
        for (int h = 100; h<=250; h++) {
            heights.add(h);
        }
        return heights;
        
    }
    
    @Produces
    @Named
    public List<DOrientation.Orientation> getOrientationValues() {
        return Arrays.asList(DOrientation.Orientation.values());
    }
    
    @Produces
    @Named
    public List<DBody.BodyType> getBodyTypes() {
        return Arrays.asList(DBody.BodyType.values());
    }
    
    @Produces
    @Named
    public List<DEyeColor.EyeColor> getEyeColors() {
        return Arrays.asList(DEyeColor.EyeColor.values());
    }
    
    @Produces
    @Named
    public List<DHair.HairColor> getHairColors() {
        return Arrays.asList(DHair.HairColor.values());
    }
    
    @Produces
    @Named
    public List<DEducation.EducationType> getEducationTypes() {
        return Arrays.asList(DEducation.EducationType.values());
    }
    
    @Produces
    @Named
    public List<DOccupation.Occupation> getOccupationTypes() {
        return Arrays.asList(DOccupation.Occupation.values());
    }
    
    @Produces
    @Named
    public List<DMaritalstatus.MaritalStatus> getMaritalStatuses() {
        return Arrays.asList(DMaritalstatus.MaritalStatus.values());
    }
    
    @Produces
    @Named
    public List<DReligion.Religion> getReligions() {
        return Arrays.asList(DReligion.Religion.values());
    }
    
    public void saveUserProfile(AjaxBehaviorEvent event) {
        
    }
    
}
