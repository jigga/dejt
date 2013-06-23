/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jigga
 */
@Entity
@Table(name = "PREFERENCES")
@XmlRootElement
public class Preferences implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "UID")
    private String uid;
    
    @MapsId
    @OneToOne
    @JoinColumn(name = "UID")
    private User user;
    
    @Basic(optional = false)
    @Column(name = "AgeLow")
    private Integer ageLow;
    
    @Basic(optional = false)
    @Column(name = "AgeHigh")
    private Integer ageHigh;
    
    @Basic(optional = false)
    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Basic(optional = false)
    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "HeightLow")
    private Integer heightLow;
    
    @Column(name = "HeightHigh")
    private Integer heightHigh;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_BODY",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="BODY_TYPE", referencedColumnName="BODY_TYPE")
        }
    )
    private List<DBody> bodyPreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_EDUCATION",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="EDUCATION_TYPE", referencedColumnName="EDUCATION_TYPE")
        }
    )
    private List<DEducation> educationPreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_EYE",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="EYE_COLOR", referencedColumnName="EYE_COLOR")
        }
    )
    private List<DEyeColor> eyePreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_HAIR",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="HAIR_COLOR", referencedColumnName="HAIR_COLOR")
        }
    )
    private List<DHair> hairPreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_INTEREST",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="INTEREST_TYPE", referencedColumnName="INTEREST_TYPE")
        }
    )
    private List<DInterest> interests;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_MARITALSTATUS",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="MARITAL_STATUS", referencedColumnName="MARITAL_STATUS")
        }
    )
    private List<DMaritalstatus> maritalPreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_OCCUPATION",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="OCCUPATION_TYPE", referencedColumnName="OCCUPATION_TYPE")
        }
    )
    private List<DOccupation> occupationPreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_ORIENTATION",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="ORIENTATION", referencedColumnName="ORIENTATION")
        }
    )
    private List<DOrientation> orientationPreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_RELIGION",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="RELIGION", referencedColumnName="RELIGION")
        }
    )
    private List<DReligion> religionPreferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="PREFERENCES_TERMS",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="TERM", referencedColumnName="TERM")
        }
    )
    private List<DTerm> terms;
    
    public Preferences() {
    }

    public Preferences(User user) {
        this.uid = user.getUid();
        this.user = user;
    }

    public Preferences(User user, Integer ageLow, Integer ageHigh, Gender gender, Date creationTime) {
        this(user);
        this.ageLow = ageLow;
        this.ageHigh = ageHigh;
        this.gender = gender;
        this.creationTime = creationTime;
    }

    public User getUser() {
        return user;
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

    public List<DInterest> getInterests() {
        if (interests == null) {
            interests = new ArrayList<>();
        }
        return interests;
    }

    public void setInterests(List<DInterest> interests) {
        this.interests = interests;
    }

    public List<DBody> getBodyPreferences() {
        if (bodyPreferences==null) {
            bodyPreferences = new ArrayList<>();
        }
        return bodyPreferences;
    }

    public void setBodyPreferences(List<DBody> bodyPreferences) {
        this.bodyPreferences = bodyPreferences;
    }

    public List<DEducation> getEducationPreferences() {
        if (educationPreferences==null) {
            educationPreferences = new ArrayList<>();
        }
        return educationPreferences;
    }

    public void setEducationPreferences(List<DEducation> educationPreferences) {
        this.educationPreferences = educationPreferences;
    }

    public List<DEyeColor> getEyePreferences() {
        if (eyePreferences==null) {
            eyePreferences = new ArrayList<>();
        }
        return eyePreferences;
    }

    public void setEyePreferences(List<DEyeColor> eyePreferences) {
        this.eyePreferences = eyePreferences;
    }

    public List<DHair> getHairPreferences() {
        if (hairPreferences==null) {
            hairPreferences = new ArrayList<>();
        }
        return hairPreferences;
    }

    public void setHairPreferences(List<DHair> hairPreferences) {
        this.hairPreferences = hairPreferences;
    }

    public List<DMaritalstatus> getMaritalPreferences() {
        if (maritalPreferences==null) {
            maritalPreferences = new ArrayList<>();
        }
        return maritalPreferences;
    }

    public void setMaritalPreferences(List<DMaritalstatus> maritalPreferences) {
        this.maritalPreferences = maritalPreferences;
    }

    public List<DOccupation> getOccupationPreferences() {
        if (occupationPreferences==null) {
            occupationPreferences = new ArrayList<>();
        }
        return occupationPreferences;
    }

    public void setOccupationPreferences(List<DOccupation> occupationPreferences) {
        this.occupationPreferences = occupationPreferences;
    }

    public List<DOrientation> getOrientationPreferences() {
        if (orientationPreferences==null) {
            orientationPreferences = new ArrayList<>();
        }
        return orientationPreferences;
    }

    public void setOrientationPreferences(List<DOrientation> orientationPreferences) {
        this.orientationPreferences = orientationPreferences;
    }

    public List<DReligion> getReligionPreferences() {
        if (religionPreferences==null) {
            religionPreferences = new ArrayList<>();
        }
        return religionPreferences;
    }

    public void setReligionPreferences(List<DReligion> religionPreferences) {
        this.religionPreferences = religionPreferences;
    }

    public List<DTerm> getTerms() {
        if (terms==null) {
            terms = new ArrayList<>();
        }
        return terms;
    }

    public void setTerms(List<DTerm> terms) {
        this.terms = terms;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.Preference[ uid=" + uid + " ]";
    }
    
}
