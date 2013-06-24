/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.api;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.Picture;
import com.dejt.common.model.Preferences;
import com.dejt.common.model.Profile;
import com.dejt.common.model.User;
import java.io.File;
import java.text.MessageFormat;
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
    
    /**
     * {0} shall be replaced by {@link User#getUid() user's identifier}.
     * {1} shall be replaced by {@link Picture#getId() photo's identifier}.
     */
    protected static final String BASE_URI_USER_PHOTO = "/upload/dejt/{0}/{1}";
    
    @Inject
    protected CRUDFacade facade;
    
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
    
    /**
     * Gets user's photo.
     * 
     * @param uid User's identifier.
     * @param pid Photo's identifier.
     * 
     * @return User's photo.
     */
    @GET
    @Path("/{uid}/photos/{pid}")
    public Response getUserPhoto(@PathParam("uid") String uid, @PathParam("pid") String pid) {
        
        try {
            File photo = 
                new File(MessageFormat.format(BASE_URI_USER_PHOTO, uid, pid));
            if (!photo.exists()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }
            return Response.ok(photo, "image/*")
                .header("Content-Disposition", "inline;filename=" + photo.getName())
                .build();
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        
    }
    
}
