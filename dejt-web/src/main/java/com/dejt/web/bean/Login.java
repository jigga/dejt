/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.User;
import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * CDI bean responsible for controlling the login process.
 * 
 * @author jigga
 */
@Named
@SessionScoped
public class Login implements Serializable {
    
    private static final long serialVersionUID = -4951965096369975468L;
    
    @Inject
    private CRUDFacade facade;
    
    @Inject
    private Credentials credentials;
    
    // User entity - initialized after successful login
    private User user;
    
    // redirection target
    private String target;
    
    public boolean isLoggedIn() {
        return user != null;
    }
    
    @Produces
    @LoggedIn
    public User getUser() {
        return user;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
    /**
     * Login action. Delegates authentication to servlet container.
     * 
     * @return 
     */
    public String login() throws IOException {
        
        if (user != null) {
            return "/home/index?faces-redirect=true";
        }
        
        ExternalContext context = 
            FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = 
            (HttpServletRequest)context.getRequest();
        try {
            
            // delegate login logic to the web container
            request.login(credentials.getUsername(), credentials.getPassword());
            
            // fetching User entity after successfull login
            user = facade.read(User.class, credentials.getUsername());
            
            // redirecting user to the home page.
            return "/home/index?faces-redirect=true";
            
        } catch (ServletException e) {
            // TODO: add faces message.
            return null;
        }
    }
    
    /**
     * Logout action.
     * 
     * @return 
     * 
     * @throws ServletException
     * @throws IOException
     */
    public String logout() throws ServletException, IOException {
        
        ExternalContext context = 
            FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = 
            (HttpServletRequest)context.getRequest();
        request.logout();
        user = null;
        context.redirect("/index.xhtml");
        
        return null;
        
    }
    
}
