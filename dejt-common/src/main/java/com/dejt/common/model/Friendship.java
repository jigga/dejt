/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jigga
 */
@Entity
@Table(name = "FRIENDSHIPS")
@XmlRootElement
public class Friendship implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "UID1")
    private User user;
    
    @Id
    @ManyToOne
    @JoinColumn(name = "UID2")
    private User friend;

    public Friendship() {
    }

    public Friendship(User user, User friend) {
        this.user = user;
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "com.dejt.model.Friendslist[ user=" + user + ", friend=" + friend + "]";
    }
    
}
