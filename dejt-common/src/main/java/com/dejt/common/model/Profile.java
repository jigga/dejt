/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.Convert;

/**
 *
 * @author jigga
 */
@Entity
@Table(name = "PROFILES")
@XmlRootElement
public class Profile implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(name = "UID")
    private String uid;
    
    @Basic(optional = false)
    @Column(name = "Gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    
    @Column(name = "BirthDate")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    
    @Column(name = "Height")
    private Integer height;
    
    @Basic(optional = false)
    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Convert("BooleanConverter")
    @Column(name = "Cigarets")
    private Boolean cigarets;
    
    @Convert("BooleanConverter")
    @Column(name = "Alkohol")
    private Boolean alkohol;
    
    @MapsId
    @OneToOne
    @JoinColumn(name = "UID")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "Orientation")
    private DOrientation orientation;
    
    @ManyToOne
    @JoinColumn(name = "Body")
    private DBody body;
    
    @ManyToOne
    @JoinColumn(name = "EyeColor")
    private DEyeColor eyeColor;
    
    @ManyToOne
    @JoinColumn(name = "HairColor")
    private DHair hairColor;
    
    @ManyToOne
    @JoinColumn(name = "Education")
    private DEducation education;
    
    @ManyToOne
    @JoinColumn(name = "Occupation")
    private DOccupation occupation;
    
    @ManyToOne
    @JoinColumn(name = "MaritalStatus")
    private DMaritalstatus maritalStatus;
    
    @ManyToOne
    @JoinColumn(name = "Religion")
    private DReligion religion;

    @Transient
    private int age;
    
    public Profile() {
    }

    public Profile(User user) {
        this.uid = user.getUid();
        this.user = user;
    }

    public Profile(User user, Gender gender, Date birthDate) {
        this(user);
        this.gender = gender;
        this.birthDate = birthDate;
    }

    public User getUser() {
        return user;
    }

    public int getAge() {
        return age;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }
    
    public Boolean getCigarets() {
        return cigarets;
    }
    
    public void setCigarets(Boolean cigarets) {
        this.cigarets = cigarets;
    }

    public Boolean getAlkohol() {
        return alkohol;
    }

    public void setAlkohol(Boolean alkohol) {
        this.alkohol = alkohol;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }
    
    public DBody getBody() {
        return body;
    }

    public void setBody(DBody body) {
        this.body = body;
    }
    
    public DOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(DOrientation orientation) {
        this.orientation = orientation;
    }

    public DEyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(DEyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public DHair getHairColor() {
        return hairColor;
    }

    public void setHairColor(DHair hairColor) {
        this.hairColor = hairColor;
    }

    public DEducation getEducation() {
        return education;
    }

    public void setEducation(DEducation education) {
        this.education = education;
    }

    public DOccupation getOccupation() {
        return occupation;
    }

    public void setOccupation(DOccupation occupation) {
        this.occupation = occupation;
    }

    public DMaritalstatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(DMaritalstatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public DReligion getReligion() {
        return religion;
    }

    public void setReligion(DReligion religion) {
        this.religion = religion;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.Profile[user=" + user + "]";
    }
    
    @PostLoad
    protected void postLoad() {
        if (birthDate != null) {
            Calendar now = Calendar.getInstance(), birthDateCal = Calendar.getInstance();
            birthDateCal.setTime(birthDate);
            age = now.get(Calendar.YEAR) - birthDateCal.get(Calendar.YEAR);
        }
    }
    
}
