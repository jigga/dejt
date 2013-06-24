/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.Gender;
import com.dejt.common.model.Preferences;
import com.dejt.common.model.Profile;
import com.dejt.common.model.User;
import com.dejt.common.spi.orange.OrangeProxy;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
    
    private static final long serialVersionUID = -5757450411257036421L;
    
    @Inject
    private Conversation conversation;
    
    @Inject
    private CRUDFacade facade;
    
    @Inject
    private OrangeProxy proxy;
    
    private String cid;
    private User user;
    private Gender gender;
    private boolean codeSent;
    private boolean registered;
    private String confirmationCode;
    private String userConfirmationCode;

    @PostConstruct
    protected void init() {
        System.out.println("Registrar;init");
        this.user = new User();
        this.user.setProfile(new Profile(user));
        
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
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
        
        // Setting user's gender - this could probably be done much better :)
        if (gender==null) {
            return;
        } else {
            user.getProfile().setGender(gender);
        }
        
        // Setting user's password
        try {
            user.setPassword(hashPassword(user.getPassword()));
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            return;
        }
        
        // starting the conversation...
        if (conversation.isTransient()) {
            conversation.begin();
            cid = conversation.getId();
        }
        
        // and lastly - generating and sending the confirmation code...
        confirmationCode = String.valueOf(System.currentTimeMillis());
        try {
            proxy.sendSMS(user.getMsisdn(), user.getMsisdn(), confirmationCode);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            // TODO: move below statement to the try block.
            codeSent = true;
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
            Date creationDate = new Date();
            user.setCreationTime(creationDate);
            user.getProfile().setCreationTime(creationDate);
            Preferences preferences = new Preferences(user);
            preferences.setCreationTime(creationDate);
            user.setPreferences(preferences);
            facade.create(user);
            registered = true;
        } catch (Exception e) {
            // TODO: add faces message...
        }
        
    }
    
    /**
     * Creates password hash using SHA-256 algorithm and converts this hash
     * to hex string.
     * 
     * @param cleartext
     * 
     * @return
     * 
     * @throws NoSuchAlgorithmException If SHA-256 is not supported by VM.
     * @throws UnsupportedEncodingException If UTF-8 encoding is not supported
     *         on the deployment platform.
     */
    protected String hashPassword(String cleartext) 
        throws NoSuchAlgorithmException,
               UnsupportedEncodingException {
        
        MessageDigest digest = 
            MessageDigest.getInstance("SHA-256");
        byte[] hash = 
            digest.digest(cleartext.getBytes("UTF-8"));
        
        StringBuilder hexString = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        return hexString.toString();
        
    }
    
}
