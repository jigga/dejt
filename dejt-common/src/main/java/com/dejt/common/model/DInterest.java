/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jigga
 */
@Entity
@Table(name = "D_INTEREST")
@XmlRootElement
public class DInterest implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Interest {
        
        F("Filmy/Kino"),
        K("Kulinaria/Gotowanie"),
        L("Literatura"),
        M("Muzyka"),
        P("Gry planszowe"),
        S("Sport"),
        T("Taniec");

        private String desc;

        private Interest(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "INTEREST_TYPE")
    @Enumerated(EnumType.STRING)
    private Interest interest;

    public DInterest() {
    }

    public DInterest(Interest interest) {
        this.interest = interest;
    }

    public Interest getInterest() {
        return interest;
    }

    public void setInterest(Interest interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "com.dejt.model.DInterest[interest=" + interest + "]";
    }
    
}
