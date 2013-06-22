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
@Table(name = "D_EYES")
@XmlRootElement
public class DEyeColor implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum EyeColor {
        
        B("Brązowe"),
        C("Czarne"),
        N("Niebieskie"),
        P("Piwne"),
        S("Szare"),
        Z("Zielone");
        
        private String desc;

        private EyeColor(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "EYE_COLOR")
    @Enumerated(EnumType.STRING)
    private EyeColor eyeColor;

    public DEyeColor() {
    }

    public DEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.DEyeColor[eyeColor=" + eyeColor + "]";
    }
    
}
