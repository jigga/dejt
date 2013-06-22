/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import java.io.IOException;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
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
    private Credentials credentials;
    
    // Holds username of the currently logged in user.
    private String user;
    
    private String target;
    
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
            request.login(credentials.getUsername(), credentials.getPassword());
            user = credentials.getUsername();
            if (target != null) {
                System.out.println("Redirecting user to " + target + " after successful login.");
                context.redirect(target);
                return null;
            }
            return "/home/index?faces-redirect=true";
        } catch (ServletException e) {
            context.getFlash().put(
                "login-message",
                "Błędna nazwa użytkownika lub hasło. Spróbuj ponownie."
            );
            return "/login?faces-redirect=true";
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

    public boolean isLoggedIn() {
        return user != null;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
    
}
