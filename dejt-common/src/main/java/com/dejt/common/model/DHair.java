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
@Table(name = "D_HAIR")
@XmlRootElement
public class DHair implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum HairColor {
        
        C("Czarne"),
        D("Ciemny Blond"),
        J("Jasny Blond"),
        K("Kasztanowe"),
        L("Łysy"),
        R("Rude"),
        S("Siwe"),
        W("Białe");
        
        private String desc;

        private HairColor(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "HAIR_COLOR")
    @Enumerated(EnumType.STRING)
    private HairColor hairColor;

    public DHair() {
    }

    public DHair(HairColor hairColor) {
        this.hairColor = hairColor;
    }

    public HairColor getHairColor() {
        return hairColor;
    }

    public void setHairColor(HairColor hairColor) {
        this.hairColor = hairColor;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.DHair[hairType=" + hairColor + "]";
    }
    
}
