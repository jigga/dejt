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
@Table(name = "D_TERMS")
@XmlRootElement
public class DTerm implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum TermType {
        
        F("Przyjaźń"),
        L("Miłość"),
        R("Romans"),
        S("Sex");
        
        private String desc;

        private TermType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }

    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "TERM")
    @Enumerated(EnumType.STRING)
    private TermType termType;

    public DTerm() {
    }

    public DTerm(TermType termType) {
        this.termType = termType;
    }

    public TermType getTermType() {
        return termType;
    }
    
    @Override
    public String toString() {
        return "com.dejt.model.DTerm[termType=" + termType + "]";
    }
    
}
