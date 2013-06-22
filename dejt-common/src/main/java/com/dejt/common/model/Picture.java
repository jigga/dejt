/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jigga
 */
@Entity
@Table(name = "PICTURES")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Picture.findAll", query = "SELECT p FROM Picture p"),
    @NamedQuery(name = "Picture.findByUid", query = "SELECT p FROM Picture p WHERE p.user.uid = :uid"),
    @NamedQuery(name = "Picture.findByPictureFile", query = "SELECT p FROM Picture p WHERE p.pictureFile = :pictureFile"),
    @NamedQuery(name = "Picture.findByPictureTitle", query = "SELECT p FROM Picture p WHERE p.pictureTitle = :pictureTitle"),
    @NamedQuery(name = "Picture.findByPictureComment", query = "SELECT p FROM Picture p WHERE p.pictureComment = :pictureComment"),
    @NamedQuery(name = "Picture.findByAlbumID", query = "SELECT p FROM Picture p WHERE p.album.id = :albumID"),
    @NamedQuery(name = "Picture.findByCreationTime", query = "SELECT p FROM Picture p WHERE p.creationTime = :creationTime")
})
public class Picture implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @Column(name = "PictureFile")
    private String pictureFile;
    
    @Column(name = "PictureTitle")
    private String pictureTitle;
    
    @Column(name = "PictureComment")
    private String pictureComment;
    
    @Basic(optional = false)
    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @ManyToOne
    @JoinColumn(name = "AlbumID", referencedColumnName = "ID")
    private Photoalbum album;
    
    @ManyToOne
    @JoinColumn(name = "UID")
    private User user;

    public Picture() {
    }

    public Picture(Long id) {
        this.id = id;
    }

    public Picture(Long id, User user) {
        this.id = id;
        this.user = user;
    }
    
    public Picture(Long id, User user, String pictureFile, Date creationTime) {
        this.id = id;
        this.user = user;
        this.pictureFile = pictureFile;
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(String pictureFile) {
        this.pictureFile = pictureFile;
    }

    public String getPictureTitle() {
        return pictureTitle;
    }

    public void setPictureTitle(String pictureTitle) {
        this.pictureTitle = pictureTitle;
    }

    public String getPictureComment() {
        return pictureComment;
    }

    public void setPictureComment(String pictureComment) {
        this.pictureComment = pictureComment;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Photoalbum getAlbum() {
        return album;
    }

    public void setAlbum(Photoalbum album) {
        this.album = album;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.Picture[id=" + id + "]";
    }
    
}
