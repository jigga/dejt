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
@Table(name = "D_OCCUPATION")
@XmlRootElement
public class DOccupation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Occupation {
        
        B("Pracownik biurowy"),
        E("Emeryt/Rencista"),
        F("Pracownik fizyczny"),
        H("Handlowiec"),
        N("Pracownik naukowy"),
        S("Student/Uczeń"),
        U("Niepracujący"),
        W("Wolny zawód"),
        Z("Dyrektor/Kadra zarządzająca");
        
        private String desc;

        private Occupation(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "OCCUPATION_TYPE")
    @Enumerated(EnumType.STRING)
    private Occupation occupation;

    public DOccupation() {
    }

    public DOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    @Override
    public String toString() {
        return "com.dejt.model.DOccupation[occupation=" + occupation + "]";
    }
    
}
