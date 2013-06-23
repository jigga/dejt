/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.api;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author jigga
 */
@ApplicationPath("/api")
public class DejtApplication extends ResourceConfig {

    public DejtApplication() {
        packages("com.dejt.web.api");
    }

}
