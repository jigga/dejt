/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.core;

import com.dejt.common.model.User;

/**
 *
 * @author jigga
 */
public interface Matcher {
    
    /**
     * Matches two users based on their profiles and preferences.
     * 
     * @param user1
     * @param user2
     * 
     * @return 
     */
    public boolean matchUsers(User user1, User user2);
    
}
