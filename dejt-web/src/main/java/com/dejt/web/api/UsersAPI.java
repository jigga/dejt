/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.api;

import com.dejt.common.CRUDFacade;
import com.dejt.common.model.User;
import java.io.FileNotFoundException;
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
@Path("/users/{uid}")
public class UsersAPI {
    
    @Inject
    CRUDFacade facade;
    
    @GET
    @Produces("application/xml")
    public Response getUser(@PathParam("uid") String uid) throws FileNotFoundException {
        
        User user = facade.read(User.class, uid);
        if (user==null) {
            return Response.status(Response.Status.NOT_FOUND)
                .entity(new Error(404, "Brak użytkownika o identyfikatorze " + uid))
                .build();
        }
        return Response.ok(user).build();
        
    }
    
}
