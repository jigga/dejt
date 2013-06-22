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
@Table(name = "D_ORIENTATION")
@XmlRootElement
public class DOrientation implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Orientation {
        
        A("Asexual"),
        B("Bisexual"),
        H("Heterosexual"),
        O("Homosexual");
        
        private String desc;

        private Orientation(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "ORIENTATION")
    @Enumerated(EnumType.STRING)
    private Orientation orientation;

    public DOrientation() {
    }

    public DOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.DOrientation[orientation=" + orientation + "]";
    }
    
}
