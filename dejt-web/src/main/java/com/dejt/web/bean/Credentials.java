/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import java.text.MessageFormat;
import javax.enterprise.inject.Model;

/**
 * 
 * @author jigga
 */
@Model
public class Credentials {
    
    private String username;
    private String password;
    private boolean remember;

    public Credentials() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isRemember() {
        return remember;
    }

    public void setRemember(boolean remember) {
        this.remember = remember;
    }

    @Override
    public String toString() {
        return MessageFormat.format("{0}[uid={1}, pwd={2}]", getClass().getSimpleName(), username, password);
    }
    
}
