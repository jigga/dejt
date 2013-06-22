/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.User;
import com.dejt.common.spi.orange.OrangeProxy;
import java.io.Serializable;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.bean.RequestScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link RequestScoped} bean responsible for handling the registration
 * requests.
 * 
 * @author jigga
 */
@Named
@ConversationScoped
public class Registrar implements Serializable {
    
    @Inject
    private Conversation conversation;
    
    @Inject
    private CRUDFacade facade;
    
    @Inject
    private OrangeProxy proxy;
    
    private String cid;
    private User user;
    private String gender;
    private boolean codeSent;
    private boolean registered;
    private String confirmationCode;
    private String userConfirmationCode;

    @PostConstruct
    protected void init() {
        System.out.println("Registrar;init");
        this.user = new User();
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public boolean isRegistered() {
        return registered;
    }

    public boolean isCodeSent() {
        return codeSent;
    }

    public String getCid() {
        return cid;
    }

    public String getUserConfirmationCode() {
        return userConfirmationCode;
    }

    public void setUserConfirmationCode(String userConfirmationCode) {
        this.userConfirmationCode = userConfirmationCode;
    }
    
    public void registerBtnClicked(AjaxBehaviorEvent event) {
        
        System.out.println("Registrar;registerBtnClicked;gender=" + gender);
        System.out.println("Registrar;registerBtnClicked;gender=" + gender);
        
        if (conversation.isTransient()) {
            conversation.begin();
            cid = conversation.getId();
            System.out.println("Registrar;registerBtnClicked;cid=" + cid);
        }
        
        confirmationCode = String.valueOf(System.currentTimeMillis());
        System.out.println("Registrar;registerBtnClicked;confirmationCode=" + confirmationCode);
        try {
            proxy.sendSMS(user.getMsisdn(), user.getMsisdn(), confirmationCode);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            codeSent = true;
            System.out.println("Registrar;registerBtnClicked;confirmationCode=" + confirmationCode);
        }
        
    }
    
    /**
     * Handles AJAX registration request.
     * 
     * @param event {@link AjaxBehaviorEvent} instance.
     */
    public void registerUser(AjaxBehaviorEvent event) {
        
        System.out.println("Registrar;registerUser;gender=" + gender);
//        if (!confirmationCode.equals(userConfirmationCode)) {
//            return;
//        }
        
        try {
            user.setCreationTime(new Date());
            facade.create(user);
            registered = true;
        } catch (Exception e) {
            
        }
        
    }
    
}
