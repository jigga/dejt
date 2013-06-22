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
@Table(name = "D_BODY")
@XmlRootElement
public class DBody implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public enum BodyType {
        
        L("Lekko Puszysta"),
        M("Muskularna"),
        N("Normalna"),
        P("Puszysta"),
        S("Szczupła"),
        W("Wysportowana");
        
        private String desc;

        private BodyType(String desc) {
            this.desc = desc;
        }

        public String getDesc() {
            return desc;
        }
        
    }
    
    @Id
    @Basic(optional = false)
    @Column(name = "BODY_TYPE")
    @Enumerated(EnumType.STRING)
    private BodyType bodyType;

    public DBody() {
    }

    public DBody(BodyType bodyType) {
        this.bodyType=bodyType;
    }

    public BodyType getBodyType() {
        return bodyType;
    }

    public void setBodyType(BodyType bodyType) {
        this.bodyType = bodyType;
    }

    @Override
    public String toString() {
        return "com.dejt.model.DBody[bodyType=" + bodyType + "]";
    }
    
}
