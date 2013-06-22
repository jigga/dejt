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
@Table(name = "D_TOLERANCE")
@XmlRootElement
public class DTolerance implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Tolerance {
        
        I("Nie Toleruje"),
        L("Lubi"),
        N("Nie lubi"),
        O("Obojętny"),
        T("Toleruje");
        
        private String desc;

        private Tolerance(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "TOLERANCE")
    @Enumerated(EnumType.STRING)
    private Tolerance tolerance;

    public DTolerance() {
    }

    public DTolerance(Tolerance tolerance) {
        this.tolerance = tolerance;
    }

    public Tolerance getTolerance() {
        return tolerance;
    }

    public void setTolerance(Tolerance tolerance) {
        this.tolerance = tolerance;
    }

    @Override
    public String toString() {
        return "com.dejt.model.DTolerance[tolerance=" + tolerance + "]";
    }
    
}
