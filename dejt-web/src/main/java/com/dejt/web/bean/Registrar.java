/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.User;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;

/**
 * {@link RequestScoped} bean responsible for handling the registration
 * requests.
 * 
 * @author jigga
 */
@Model
public class Registrar {
    
    @Inject
    private CRUDFacade facade;
    
    private User user;
    private boolean registered;

    @PostConstruct
    protected void init() {
        this.user = new User();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isRegistered() {
        return registered;
    }

    /**
     * Handles AJAX registration request.
     * 
     * @param event {@link AjaxBehaviorEvent} instance.
     */
    public void registerUser(AjaxBehaviorEvent event) {
        
        try {
            user.setCreationTime(new Date());
            facade.create(user);
            registered = true;
        } catch (Exception e) {
            
        }
        
    }
    
}
