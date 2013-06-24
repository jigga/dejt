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
import com.dejt.common.model.Picture;
import com.dejt.common.model.Profile;
import com.dejt.common.model.User;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
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
import javax.servlet.http.Part;

/**
 *
 * @author jigga
 */
@Named("profmgr")
@SessionScoped
public class ProfileManager implements Serializable {
    
    private static final long serialVersionUID = -4389893235046227693L;
    
    /**
     * Base URI for user's photos.
     * {0} shall be replaced by {@link User#getUid() user's identifier}.
     */
    protected static final String BASE_URI_USER_PHOTOS_DIR = "/upload/dejt/{0}";
    
    /**
     * Base URI for user's photo.
     * {0} shall be replaced by {@link User#getUid() user's identifier}.
     * {1} shall be replaced by {@link Picture#pictureFile picture name}.
     */
    protected static final String BASE_URI_USER_PHOTO = "/upload/dejt/{0}/{1}";
    
    @Inject
    private CRUDFacade facade;
    
    @Inject
    @LoggedIn
    private User user;
    private Profile profile;
    
    // profile picture
    private Part profilePhoto;
    
    private Integer day;
    private Integer month;
    private Integer year;
    private Integer height;
    
    private DOrientation.Orientation orientation;
    private DBody.BodyType bodyType;
    private DEyeColor.EyeColor eyeColor;
    private DHair.HairColor hairColor;
    private DEducation.EducationType education;
    private DOccupation.Occupation occupation;
    private DMaritalstatus.MaritalStatus maritalStatus;
    private DReligion.Religion religion;
    
    /**
     * Initialization method. Rewrites {@link Profile profile's} properties
     * into this bean properties.
     */
    @PostConstruct
    protected void init() {
        
        profile = user.getProfile();
        if (profile == null) {
            profile = new Profile(user);
            profile.setCreationTime(new Date());
        } else {
            if (profile.getBirthDate() != null) {
                Calendar birthDay = Calendar.getInstance();
                birthDay.setTime(profile.getBirthDate());
                this.day = birthDay.get(Calendar.DATE);
                this.month = birthDay.get(Calendar.MONTH);
                this.year = birthDay.get(Calendar.YEAR);
            }
            this.height = profile.getHeight();
            this.orientation = profile.getOrientation() != null ? profile.getOrientation().getOrientation() : null;
            this.bodyType = profile.getBody()!= null ? profile.getBody().getBodyType() : null;
            this.eyeColor = profile.getEyeColor() != null ? profile.getEyeColor().getEyeColor() : null;
            this.hairColor = profile.getHairColor() != null ? profile.getHairColor().getHairColor() : null;
            this.education = profile.getEducation() != null ? profile.getEducation().getEducationType() : null;
            this.occupation = profile.getOccupation() != null ? profile.getOccupation().getOccupation() : null;
            this.maritalStatus = profile.getMaritalStatus()!= null ? profile.getMaritalStatus().getMaritalStatus(): null;
            this.religion = profile.getReligion()!= null ? profile.getReligion().getReligion(): null;
        }
        
    }

    public Part getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(Part profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
    
    public Profile getProfile() {
        return user.getProfile();
    }

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
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

    public User getUser() {
        return user;
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
    
    /**
     * TODO: DOCUMENT ME!!!
     * 
     * @param event 
     */
    public void saveUserProfile(AjaxBehaviorEvent event) {
        
        if (day!=null && month!=null && year!=null) {
            Calendar bd = Calendar.getInstance();
            bd.set(year, month, day, 0, 0, 0);
            user.getProfile().setBirthDate(bd.getTime());
        }
        if (height!=null) {
            user.getProfile().setHeight(height);
        }
        if (orientation!=null) {
            user.getProfile().setOrientation(new DOrientation(orientation));
        }
        if (bodyType!=null) {
            user.getProfile().setBody(new DBody(bodyType));
        }
        if (eyeColor!=null) {
            user.getProfile().setEyeColor(new DEyeColor(eyeColor));
        }
        if (hairColor!=null) {
            user.getProfile().setHairColor(new DHair(hairColor));
        }
        if (education!=null) {
            user.getProfile().setEducation(new DEducation(education));
        }
        if (occupation!=null) {
            user.getProfile().setOccupation(new DOccupation(occupation));
        }
        if (maritalStatus!=null) {
            user.getProfile().setMaritalStatus(new DMaritalstatus(maritalStatus));
        }
        if (religion!=null) {
            user.getProfile().setReligion(new DReligion(religion));
        }
        
        try {
            facade.update(user.getProfile());
            FacesContext.getCurrentInstance().addMessage(
                event.getComponent().getClientId(),
                new FacesMessage("Zmiany w Twoim profilu zostły zapisane.")
            );
        } catch (Exception e) {
            System.out.println(e);
            FacesContext.getCurrentInstance().addMessage(
                event.getComponent().getClientId(),
                new FacesMessage(FacesMessage.SEVERITY_WARN, "Wystąpił błąd przy aktualizacji zmian. Spróbuj ponownie.", null)
            );
        }
        
    }
    
    /**
     * Handles profile photo upload requests.
     */
    public void uploadProfilePhoto(AjaxBehaviorEvent event) {
        
        try {
            System.out.println("Profilemanager;uploadProfilePhoto;profilePicture=" + profilePhoto.getName());
            Path picturesDir = 
                Paths.get(MessageFormat.format(BASE_URI_USER_PHOTOS_DIR, user.getUid()));
            System.out.println("Profilemanager;uploadProfilePhoto;picturesDir=" + picturesDir);
            boolean picturesDirExist = 
                Files.exists(picturesDir, LinkOption.NOFOLLOW_LINKS);
            if (!picturesDirExist) {
                Files.createDirectory(picturesDir);
            }
            
            String targetFileName = String.valueOf(System.nanoTime());
            Path pictureFile = 
                Paths.get(MessageFormat.format(BASE_URI_USER_PHOTO, user.getUid(), targetFileName));
            System.out.println("Profilemanager;uploadProfilePhoto;pictureFile=" + pictureFile);
            Files.copy(profilePhoto.getInputStream(), pictureFile);
            
            user.setProfilePicture(targetFileName);
            facade.update(user);
            
            FacesContext.getCurrentInstance().addMessage(
                event.getComponent().getClientId(),
                new FacesMessage("Zdjęcie profilowe zostało zmienione.")
            );
            
        } catch (Exception e) {
        }
        
    }
    
}
