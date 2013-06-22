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
@Table(name = "D_RELIGION")
@XmlRootElement
public class DReligion implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum Religion {
        
        A("Ateizm"),
        B("Buddyzm"),
        G("Agnostycyzm"),
        H("Hinduizm"),
        I("Islam"),
        J("Judaizm"),
        K("Katolicyzm"),
        O("Inne"),
        P("Protestantyzm");
        
        private String desc;

        private Religion(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "RELIGION")
    @Enumerated(EnumType.STRING)
    private Religion religion;

    public DReligion() {
    }

    public DReligion(Religion religion) {
        this.religion = religion;
    }

    public Religion getReligion() {
        return religion;
    }

    public void setReligion(Religion religion) {
        this.religion = religion;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.DReligion[religion=" + religion + "]";
    }
    
}
