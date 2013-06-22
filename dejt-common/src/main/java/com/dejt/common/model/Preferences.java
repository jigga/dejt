/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "AgeLow")
    private short ageLow;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "AgeHigh")
    private short ageHigh;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 4)
    @Column(name = "Orientation")
    private String orientation;
    
    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
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
    
    @Size(max = 6)
    @Column(name = "Body")
    private String body;
    
    @Column(name = "HeightLow")
    private Short heightLow;
    
    @Column(name = "HeightHigh")
    private Short heightHigh;
    
    @Size(max = 6)
    @Column(name = "EyeColor")
    private String eyeColor;
    
    @Size(max = 8)
    @Column(name = "HairColor")
    private String hairColor;
    
    @Size(max = 10)
    @Column(name = "Interests")
    private String interests;
    
    @Size(max = 7)
    @Column(name = "Education")
    private String education;
    
    @Size(max = 10)
    @Column(name = "Occupation")
    private String occupation;
    
    @Size(max = 5)
    @Column(name = "MaritalStatus")
    private String maritalStatus;
    
    @Size(max = 9)
    @Column(name = "Religion")
    private String religion;
    
    @Size(max = 7)
    @Column(name = "Terms")
    private String terms;
    
    @Basic(optional = false)
    @NotNull
    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    public Preferences() {
    }

    public Preferences(User user) {
        this.uid = user.getUid();
        this.user = user;
    }

    public Preferences(User user, short ageLow, short ageHigh, Gender gender, String orientation, Date creationTime) {
        this(user);
        this.ageLow = ageLow;
        this.ageHigh = ageHigh;
        this.gender = gender;
        this.orientation = orientation;
        this.creationTime = creationTime;
    }

    public User getUser() {
        return user;
    }

    public short getAgeLow() {
        return ageLow;
    }

    public void setAgeLow(short ageLow) {
        this.ageLow = ageLow;
    }

    public short getAgeHigh() {
        return ageHigh;
    }

    public void setAgeHigh(short ageHigh) {
        this.ageHigh = ageHigh;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Short getHeightLow() {
        return heightLow;
    }

    public void setHeightLow(Short heightLow) {
        this.heightLow = heightLow;
    }

    public Short getHeightHigh() {
        return heightHigh;
    }

    public void setHeightHigh(Short heightHigh) {
        this.heightHigh = heightHigh;
    }

    public String getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(String eyeColor) {
        this.eyeColor = eyeColor;
    }

    public String getHairColor() {
        return hairColor;
    }

    public void setHairColor(String hairColor) {
        this.hairColor = hairColor;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getReligion() {
        return religion;
    }

    public void setReligion(String religion) {
        this.religion = religion;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
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
