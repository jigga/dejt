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
@Table(name = "D_MARITALSTATUS")
@XmlRootElement
public class DMaritalstatus implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum MaritalStatus {
        
        M("Mężatka/Żonaty"),
        P("Panna/Kawaler"),
        R("Rozwiedziony/a"),
        W("Wdowiec/a");
        
        private String desc;

        private MaritalStatus(String desc) {
            this.desc = desc;
        }
        
        public String getDesc() {
            return desc;
        }

    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "MARITAL_STATUS")
    @Enumerated(EnumType.STRING)
    private MaritalStatus maritalStatus;

    public DMaritalstatus() {
    }

    public DMaritalstatus(MaritalStatus martialStatus) {
        this.maritalStatus = martialStatus;
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    @Override
    public String toString() {
        return "com.dejt.model.DMaritalstatus[maritalstatus=" + maritalStatus + "]";
    }
    
}
