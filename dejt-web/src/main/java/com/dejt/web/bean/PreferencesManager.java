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
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
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
    private Integer heightLow;
    private Integer heightHigh;
    private Gender gender;
    
    private List<DBody.BodyType> bodyPreferences = 
            new ArrayList<>();
    private List<DEducation.EducationType> educationPreferences = 
            new ArrayList<>();
    private List<DEyeColor.EyeColor> eyePreferences = 
            new ArrayList<>();
    private List<DHair.HairColor> hairPreferences = 
            new ArrayList<>();
    private List<DOccupation.Occupation> occupationPreferences = 
            new ArrayList<>();
    private List<DOrientation.Orientation> orientationPreferences = 
            new ArrayList<>();
    private List<DMaritalstatus.MaritalStatus> maritalPreferences = 
            new ArrayList<>();
    private List<DReligion.Religion> religionPreferences = 
            new ArrayList<>();
    
    @PostConstruct
    protected void init() {
        this.preferences = 
            facade.read(Preferences.class, user.getUid());
        if (preferences==null) {
            preferences = new Preferences(user);
            preferences.setCreationTime(new Date());
            user.setPreferences(preferences);
        } else {
            ageLow = preferences.getAgeLow();
            ageHigh = preferences.getAgeHigh();
            heightLow = preferences.getHeightLow();
            heightHigh = preferences.getHeightHigh();
            gender = preferences.getGender();
            
            if (!isNullOrEmpty(preferences.getBodyPreferences())) {
                for (DBody body : preferences.getBodyPreferences()) {
                    bodyPreferences.add(body.getBodyType());
                }
            }
            if (!isNullOrEmpty(preferences.getEducationPreferences())) {
                for (DEducation edu : preferences.getEducationPreferences()) {
                    educationPreferences.add(edu.getEducationType());
                }
            }
            if (!isNullOrEmpty(preferences.getEyePreferences())) {
                for (DEyeColor ec : preferences.getEyePreferences()) {
                    eyePreferences.add(ec.getEyeColor());
                }
            }
            if (!isNullOrEmpty(preferences.getHairPreferences())) {
                for (DHair hair : preferences.getHairPreferences()) {
                    hairPreferences.add(hair.getHairColor());
                }
            }
            if (!isNullOrEmpty(preferences.getOccupationPreferences())) {
                for (DOccupation occ : preferences.getOccupationPreferences()) {
                    occupationPreferences.add(occ.getOccupation());
                }
            }
            if (!isNullOrEmpty(preferences.getOrientationPreferences())) {
                for (DOrientation orient : preferences.getOrientationPreferences()) {
                    orientationPreferences.add(orient.getOrientation());
                }
            }
            if (!isNullOrEmpty(preferences.getMaritalPreferences())) {
                for (DMaritalstatus ms : preferences.getMaritalPreferences()) {
                    maritalPreferences.add(ms.getMaritalStatus());
                }
            }
            if (!isNullOrEmpty(preferences.getReligionPreferences())) {
                for (DReligion religion : preferences.getReligionPreferences()) {
                    religionPreferences.add(religion.getReligion());
                }
            }
        }
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

    public Integer getHeightLow() {
        return heightLow;
    }

    public void setHeightLow(Integer heightLow) {
        this.heightLow = heightLow;
    }

    public Integer getHeightHigh() {
        return heightHigh;
    }

    public void setHeightHigh(Integer heightHigh) {
        this.heightHigh = heightHigh;
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
        
        if (ageLow!=null) {
            user.getPreferences().setAgeLow(ageLow);
        }
        if (ageHigh!=null) {
            user.getPreferences().setAgeHigh(ageHigh);
        }
        if (heightLow!=null) {
            user.getPreferences().setHeightLow(heightLow);
        }
        if (heightHigh!=null) {
            user.getPreferences().setHeightHigh(heightHigh);
        }
        if (gender!=null) {
            user.getPreferences().setGender(gender);
        }
    
        // updating body preferences
        if (!isNullOrEmpty(bodyPreferences)) {
            List<DBody> list = new ArrayList<>();
            for (Object obj : bodyPreferences) {
                try {
                    list.add(new DBody(DBody.BodyType.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setBodyPreferences(list);
        }
        
        // updating education preferences
        if (!isNullOrEmpty(educationPreferences)) {
            List<DEducation> list = new ArrayList<>();
            for (Object obj : educationPreferences) {
                try {
                    list.add(new DEducation(DEducation.EducationType.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setEducationPreferences(list);
        }

        // updating eye preferences
        if (!isNullOrEmpty(eyePreferences)) {
            List<DEyeColor> list = new ArrayList<>();
            for (Object obj : eyePreferences) {
                try {
                    list.add(new DEyeColor(DEyeColor.EyeColor.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setEyePreferences(list);
        }
        
        // updating hair preferences
        if (!isNullOrEmpty(hairPreferences)) {
            List<DHair> list = new ArrayList<>();
            for (Object obj : hairPreferences) {
                try {
                    list.add(new DHair(DHair.HairColor.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setHairPreferences(list);
        }
        
        // updating hair preferences
        if (!isNullOrEmpty(occupationPreferences)) {
            List<DOccupation> list = new ArrayList<>();
            for (Object obj : occupationPreferences) {
                try {
                    list.add(new DOccupation(DOccupation.Occupation.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setOccupationPreferences(list);
        }
        
        // updating hair preferences
        if (!isNullOrEmpty(orientationPreferences)) {
            List<DOrientation> list = new ArrayList<>();
            for (Object obj : orientationPreferences) {
                try {
                    list.add(new DOrientation(DOrientation.Orientation.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setOrientationPreferences(list);
        }
        
        // updating hair preferences
        if (!isNullOrEmpty(maritalPreferences)) {
            List<DMaritalstatus> list = new ArrayList<>();
            for (Object obj : maritalPreferences) {
                try {
                    list.add(new DMaritalstatus(DMaritalstatus.MaritalStatus.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setMaritalPreferences(list);
        }
        
        // updating hair preferences
        if (!isNullOrEmpty(religionPreferences)) {
            List<DReligion> list = new ArrayList<>();
            for (Object obj : religionPreferences) {
                try {
                    list.add(new DReligion(DReligion.Religion.valueOf(String.valueOf(obj))));
                } catch (Exception e) {}
            }
            user.getPreferences().setReligionPreferences(list);
        }
        
        try {
            facade.update(user.getPreferences());
            FacesContext.getCurrentInstance().addMessage(
                event.getComponent().getClientId(),
                new FacesMessage("Zmiany w preferencjach zostły zapisane.")
            );
        } catch (Exception e) {
            System.out.println(e);
            FacesContext.getCurrentInstance().addMessage(
                event.getComponent().getClientId(),
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Wystąpił błąd przy aktualizacji preferencji. Spróbuj ponownie.", null)
            );
        }
        
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
