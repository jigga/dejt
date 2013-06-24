/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.api;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.Preferences;
import com.dejt.common.model.Profile;
import com.dejt.common.model.User;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Dejt service RESTful API.
 * 
 * @author jigga
 */
@RequestScoped
@Path("/users")
public class UsersAPI {
    
    @Inject
    CRUDFacade facade;
    
    /**
     * Gets user entity.
     * 
     * @param uid User's identifier.
     * 
     * @return User's {@link User entity}.
     */
    @GET
    @Path("/{uid}")
    @Produces("application/xml")
    public Response getUser(@PathParam("uid") String uid) {
        
        User user = facade.read(User.class, uid);
        if (user==null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new Error(404, "Brak użytkownika o identyfikatorze " + uid))
                .build();
        }
        return Response.ok(user).build();
        
    }
    
    /**
     * Gets user's public profile.
     * 
     * @param uid User's identifier.
     * 
     * @return User's {@link Profile profile}.
     */
    @GET
    @Path("/{uid}/profile")
    public Response getUserProfile(@PathParam("uid") String uid) {
        
        Profile profile = facade.read(Profile.class, uid);
        if (profile==null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new Error(404, "Brak użytkownika o identyfikatorze " + uid))
                .build();
        }
        return Response.ok(profile).build();
        
    }
    
    /**
     * Gets user's preferences.
     * 
     * @param uid User's identifier.
     * 
     * @return User's {@link Preferences preferences}.
     */
    @GET
    @Path("/{uid}/preferences")
    public Response getUserPreferences(@PathParam("uid") String uid) {
        
        Preferences preferences = facade.read(Preferences.class, uid);
        if (preferences==null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new Error(404, "Brak użytkownika o identyfikatorze " + uid))
                .build();
        }
        return Response.ok(preferences).build();
        
    }
    
}
