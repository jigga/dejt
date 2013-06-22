/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core.util;

import com.dejt.common.model.User;

/**
 * Class to store pair of users.
 * 
 * @author quayu
 */
public class UserPair {
    
    private final User user1, user2;

    public UserPair(User u1, User u2){
        this.user1 = u1;
        this.user2 = u2;
    }
    
    public User getUser1(){
        return this.user1;
    }
    
    public User getUser2(){
        return this.user2;
    }

}
