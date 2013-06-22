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
@Table(name = "D_EDUCATION")
@XmlRootElement
public class DEducation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum EducationType {
        G("Gimnazjalne"),
        L("Licencjat"),
        N("Wyższe niepełne"),
        P("Podstawowe"),
        S("Średnie"),
        W("Wyższe"),
        Z("Zawodowe");

        private String desc;

        private EducationType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "EDUCATION_TYPE")
    @Enumerated(EnumType.STRING)
    private EducationType educationType;

    public DEducation() {
    }

    public DEducation(EducationType educationType) {
        this.educationType = educationType;
    }

    public EducationType getEducationType() {
        return educationType;
    }

    public void setEducationType(EducationType educationType) {
        this.educationType = educationType;
    }

    @Override
    public String toString() {
        return "com.dejt.model.DEducation[educationType=" + educationType + "]";
    }
    
}
