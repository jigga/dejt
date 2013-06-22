/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "PHOTOALBUMS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Photoalbum.findAll", query = "SELECT p FROM Photoalbum p"),
    @NamedQuery(name = "Photoalbum.findByUid", query = "SELECT p FROM Photoalbum p WHERE p.user.uid = :uid"),
    @NamedQuery(name = "Photoalbum.findByAlbumTitle", query = "SELECT p FROM Photoalbum p WHERE p.albumTitle = :albumTitle"),
    @NamedQuery(name = "Photoalbum.findByAlbumComment", query = "SELECT p FROM Photoalbum p WHERE p.albumComment = :albumComment"),
    @NamedQuery(name = "Photoalbum.findByCreationTime", query = "SELECT p FROM Photoalbum p WHERE p.creationTime = :creationTime")
})
public class Photoalbum implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;
    
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "AlbumTitle")
    private String albumTitle;
    
    @Size(max = 255)
    @Column(name = "AlbumComment")
    private String albumComment;
    
    @Column(name = "CreationTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
    
    @ManyToOne
    @JoinColumn(name = "UID")
    private User user;
    
    @OneToMany(mappedBy = "album")
    List<Picture> pictures;

    public Photoalbum() {
    }

    public Photoalbum(Long id) {
        this.id = id;
    }

    public Photoalbum(Long id, String albumTitle) {
        this.id = id;
        this.albumTitle = albumTitle;
    }

    public Photoalbum(Long id, String albumTitle, User user) {
        this(id, albumTitle);
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getAlbumTitle() {
        return albumTitle;
    }

    public void setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
    }

    public String getAlbumComment() {
        return albumComment;
    }

    public void setAlbumComment(String albumComment) {
        this.albumComment = albumComment;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.Photoalbum[id=" + id + "]";
    }
    
}
