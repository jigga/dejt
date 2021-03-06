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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

/**
 * Dejt service RESTful API.
 * 
 * @author jigga
 */
@RequestScoped
@Path("/users")
public class UsersAPI {
    
    @Context
    protected HttpServletRequest request;
    
    /**
     * {0} shall be replaced by {@link User#getUid() user's identifier}.
     * {1} shall be replaced by {@link Picture#pictureFile file name}.
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
    
    /**
     * Turns dejting mode on.
     * 
     * @param uid
     * @return 
     */
    @POST
    @Path("/{uid}/dejt")
    public Response setDejtingModeOn(@PathParam("uid") String uid) {
        try {
            User user = facade.read(User.class, uid);
            if (user!=null && !user.isActive()) {
                user.setActive(true);
                facade.update(user);
            }
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        return Response.ok().build();
    }
    
    /**
     * Turns dejting mode off.
     * 
     * @param uid
     * @return 
     */
    @POST
    @Path("/{uid}/donedejting")
    public Response setDejtingModeOff(@PathParam("uid") String uid) {
        try {
            User user = facade.read(User.class, uid);
            if (user!=null && user.isActive()) {
                user.setActive(false);
                facade.update(user);
            }
        } catch (Exception e) {
            return Response.status(Response.Status.SERVICE_UNAVAILABLE).build();
        }
        return Response.ok().build();
    }
    
    /**
     * Authenticates Dejt mobile application user.
     * 
     * @param uid User identifier.
     * @param pwd User password.
     * 
     * @return 
     */
    @POST
    @Path("/{uid}/authenticate")
    public Response authenticate(@FormParam("uid") String uid, @FormParam("pwd") String pwd) {
        
        try {
            request.login(uid, pwd);
        } catch (ServletException e) {
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        return Response.ok().build();
        
    }
    
}
