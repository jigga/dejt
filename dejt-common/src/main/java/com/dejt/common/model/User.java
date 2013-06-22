/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import com.dejt.common.ISOCountry;
import com.dejt.common.Msisdn;
import java.io.Serializable;
import java.security.MessageDigest;
import java.util.ArrayList;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.ConversionValue;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.ObjectTypeConverter;
import org.gavaghan.geodesy.GlobalPosition;

/**
 *
 * @author jigga
 */
@Entity
@Table(name = "USERS")
@XmlRootElement
@ObjectTypeConverter(
    name="BooleanConverter",
    objectType=Boolean.class,
    dataType=String.class,
    conversionValues={
        @ConversionValue(objectValue="TRUE", dataValue="T"),
        @ConversionValue(objectValue="FALSE", dataValue="F")
    },
    defaultObjectValue="FALSE"
)
@NamedQueries({
    @NamedQuery(name = "User.findByActiveFlag", query = "SELECT u FROM User u WHERE u.active=:active"),
    @NamedQuery(name = "User.checkUID", query = "SELECT COUNT(u.uid) FROM User u WHERE u.uid=:uid")
})
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "UID", length = 30)
    private String uid;
    
    @Basic(optional = false)
    @Column(name = "Password", length = 30)
    private String password;
    
    @Basic(optional = false)
    @Column(name = "Name")
    private String name;
    
    @Basic(optional = false)
    @Column(name = "Surname")
    private String surname;
    
    // Holds ISO country codes.
    @Column(name = "Country")
    @Enumerated(EnumType.STRING)
    private ISOCountry country;
    
    @Column(name = "Region")
    private String region;
    
    @Column(name = "City")
    private String city;
    
    @Basic(optional = false)
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    
    @Basic(optional = false)
    @Column(name = "EMail")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "Comment")
    private String comment;
    
    @Basic(optional = false)
    @Column(name = "ActiveFlag")
    @Convert("BooleanConverter")
    private boolean active;
    
    @Basic(optional = false)
    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @Transient
    private Msisdn msisdn;
    
    @Transient
    private GlobalPosition currentLocation;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Profile profile;
    
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Preferences preferences;
    
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="USER_GROUPS",
        joinColumns={
            @JoinColumn(name="UID", referencedColumnName="UID")
        },
        inverseJoinColumns={
            @JoinColumn(name="GROUP_NAME", referencedColumnName="GROUP_NAME")
        }
    )
    private List<DGroup> groups;
    
    @OneToMany(mappedBy = "user")
    private List<Picture> pictures;
    
    @OneToMany(mappedBy = "user")
    private List<Photoalbum> albums;
    
    @OneToMany(mappedBy = "user")
    private List<Friendship> friendships;

    public User() {
    }

    public User(String uid) {
        this.uid = uid;
    }

    public User(String uid, String name, String surname, String email, ISOCountry country, String phoneNumber) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    public Msisdn getMsisdn() {
        if (msisdn==null) {
            this.msisdn = new Msisdn(country, phoneNumber);
        }
        return msisdn;
    }
    
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public ISOCountry getCountry() {
        return country;
    }

    public void setCountry(ISOCountry country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
    
    public List<DGroup> getGroups() {
        return groups;
    }

    public void setGroups(List<DGroup> groups) {
        this.groups = groups;
    }

    public List<Picture> getPictures() {
        if (pictures == null) {
            pictures = new ArrayList<>();
        }
        return pictures;
    }

    public void setPictures(List<Picture> pictures) {
        this.pictures = pictures;
    }

    public List<Friendship> getFriendships() {
        return friendships;
    }

    public void setFriendships(List<Friendship> friendships) {
        this.friendships = friendships;
    }

    public List<Photoalbum> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Photoalbum> albums) {
        this.albums = albums;
    }

    public GlobalPosition getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(GlobalPosition currentLocation) {
        this.currentLocation = currentLocation;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.User[ uid=" + uid + " ]";
    }
    
    public static void main(String[] args) {
        System.out.println(sha256("mojehaslo"));
    }
    
    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
           throw new RuntimeException(ex);
        }
    }
    
}
