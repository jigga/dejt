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
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.annotations.ConversionValue;
import org.eclipse.persistence.annotations.Convert;
import org.eclipse.persistence.annotations.ObjectTypeConverter;

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
    @NamedQuery(name = "User.findByActiveFlag", query = "SELECT u FROM User u WHERE u.active=:active")
})
public class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @NotNull
    @Basic(optional = false)
    @Size(min = 6, max = 30, message = "Nazwa użytkownika musi zawierać od 6 do 30 znaków")
    @Column(name = "UID", length = 30)
    private String uid;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 8, max = 30, message = "Hasło musi zawierać od 8 do 30 znaków")
    @Column(name = "Password", length = 30)
    private String password;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "Name")
    private String name;
    
    @NotNull
    @Basic(optional = false)
    @Size(min = 1, max = 30)
    @Column(name = "Surname")
    private String surname;
    
    @Size(max = 30)
    @Column(name = "Country")
    private String country;
    
    @Size(max = 30)
    @Column(name = "Region")
    private String region;
    
    @Size(max = 30)
    @Column(name = "City")
    private String city;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "PhoneNumber")
    private String phoneNumber;
    
    @NotNull
    @Pattern(
        regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
        message="Błędny format adresu e-mail"
    )
    @Basic(optional = false)
    @Size(min = 1, max = 100)
    @Column(name = "EMail")
    private String email;
    
    @Basic(optional = false)
    @Size(min = 1, max = 200)
    @Column(name = "Comment")
    private String comment;
    
    @Basic(optional = false)
    @Column(name = "ActiveFlag")
    @Convert("BooleanConverter")
    private boolean active;
    
    @NotNull
    @Basic(optional = false)
    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
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

    public User(String uid, String name, String surname, String email, String country, String phoneNumber) {
        this.uid = uid;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.country = country;
        this.phoneNumber = phoneNumber;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
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
    
    @Override
    public String toString() {
        return "com.dejt.model.User[ uid=" + uid + " ]";
    }
    
}
