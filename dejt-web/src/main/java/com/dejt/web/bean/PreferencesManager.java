/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.DBody;
import com.dejt.common.model.DEducation;
import com.dejt.common.model.DEyeColor;
import com.dejt.common.model.DHair;
import com.dejt.common.model.DMaritalstatus;
import com.dejt.common.model.DOccupation;
import com.dejt.common.model.DOrientation;
import com.dejt.common.model.DReligion;
import com.dejt.common.model.Gender;
import com.dejt.common.model.Preferences;
import com.dejt.common.model.User;
import java.io.Serializable;
import java.util.ArrayList;
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
@Named("prefmgr")
@SessionScoped
public class PreferencesManager  implements Serializable {
    
    @Inject
    private CRUDFacade facade;
    
    @Inject
    @LoggedIn
    private User user;
    
    private Preferences preferences;
    
    private Integer ageLow;
    private Integer ageHigh;
    private Gender gender;
    
    
    private List<DBody.BodyType> bodyPreferences;
    private List<DEducation.EducationType> educationPreferences;
    private List<DEyeColor.EyeColor> eyePreferences;
    private List<DHair.HairColor> hairPreferences;
    private List<DOccupation.Occupation> occupationPreferences;
    private List<DOrientation.Orientation> orientationPreferences;
    private List<DMaritalstatus.MaritalStatus> maritalPreferences;
    private List<DReligion.Religion> religionPreferences;
    
    @PostConstruct
    protected void init() {
        this.preferences = 
            facade.read(Preferences.class, user.getUid());
    }

    public Integer getAgeLow() {
        return ageLow;
    }

    public void setAgeLow(Integer ageLow) {
        this.ageLow = ageLow;
    }

    public Integer getAgeHigh() {
        return ageHigh;
    }

    public void setAgeHigh(Integer ageHigh) {
        this.ageHigh = ageHigh;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public List<DBody.BodyType> getBodyPreferences() {
        return bodyPreferences;
    }

    public void setBodyPreferences(List<DBody.BodyType> bodyPreferences) {
        this.bodyPreferences = bodyPreferences;
    }

    public List<DEducation.EducationType> getEducationPreferences() {
        return educationPreferences;
    }

    public void setEducationPreferences(List<DEducation.EducationType> educationPreferences) {
        this.educationPreferences = educationPreferences;
    }

    public List<DEyeColor.EyeColor> getEyePreferences() {
        return eyePreferences;
    }

    public void setEyePreferences(List<DEyeColor.EyeColor> eyePreferences) {
        this.eyePreferences = eyePreferences;
    }

    public List<DHair.HairColor> getHairPreferences() {
        return hairPreferences;
    }

    public void setHairPreferences(List<DHair.HairColor> hairPreferences) {
        this.hairPreferences = hairPreferences;
    }

    public List<DOccupation.Occupation> getOccupationPreferences() {
        return occupationPreferences;
    }

    public void setOccupationPreferences(List<DOccupation.Occupation> occupationPreferences) {
        this.occupationPreferences = occupationPreferences;
    }

    public List<DOrientation.Orientation> getOrientationPreferences() {
        return orientationPreferences;
    }

    public void setOrientationPreferences(List<DOrientation.Orientation> orientationPreferences) {
        this.orientationPreferences = orientationPreferences;
    }

    public List<DMaritalstatus.MaritalStatus> getMaritalPreferences() {
        return maritalPreferences;
    }

    public void setMaritalPreferences(List<DMaritalstatus.MaritalStatus> maritalPreferences) {
        this.maritalPreferences = maritalPreferences;
    }

    public List<DReligion.Religion> getReligionPreferences() {
        return religionPreferences;
    }

    public void setReligionPreferences(List<DReligion.Religion> religionPreferences) {
        this.religionPreferences = religionPreferences;
    }
    
    @Produces
    @Named
    public List<Integer> getAges() {
        
        List<Integer> ages = new ArrayList<>();
        for (int age = 18; age<=100; age++) {
            ages.add(age);
        }
        return ages;
        
    }
    
    public void saveUserPreferences(AjaxBehaviorEvent event) {
        
    }
    
}
