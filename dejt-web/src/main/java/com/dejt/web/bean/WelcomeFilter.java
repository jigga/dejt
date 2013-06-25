/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.bean;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jigga
 */
@WebFilter(
    filterName = "WelcomeFilter",
    urlPatterns = {"/", "/index.xhtml"}
)
public class WelcomeFilter implements Filter {
    
    private static final String PARTIAL_REDIRECT_RESPONSE_FORMAT = 
        "<partial-response><redirect url=\"%s\"/></partial-response>";
    
    /*
     * Regular expression pattern representing views/pages for which 
     * authentication should be ommited.
     */
    private static final String WELCOME_FILES = "/(index.xhtml)?";
    
    // The filter configuration object we are associated with.  If
    // this value is null, this filter instance is not currently
    // configured. 
    private FilterConfig config;
    
    @Inject
    private Login login;
    
    /**
     * Initialization method for this filter.
     */
    @Override
    public void init(FilterConfig config) {        
        this.config = config;
    }
    
    /**
     * Destroy method for this filter
     */
    @Override
    public void destroy() {}
    
    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httprequest = 
                (HttpServletRequest)request;
        HttpServletResponse httpresponse = 
                (HttpServletResponse)response;
        String uri = httprequest.getRequestURI();
        System.out.println("Request URI=" + uri);
        
        if (login.isLoggedIn()) {
            
            System.out.println("User " + login.getUser().getUid() + " is logged in.");
            if (uri.matches(WELCOME_FILES)) {
                System.out.println("Redirecting " + login.getUser().getUid() + " to /home/.");
                if (isAjaxRequest(httprequest)) {
                    PrintWriter pw = response.getWriter();
                    String partialResponse = String.format(
                        PARTIAL_REDIRECT_RESPONSE_FORMAT,
                        "/home/"
                    );
                    pw.println(partialResponse);
                    pw.flush();
                } else {
                    httpresponse.sendRedirect("/home/");
                }    
                return;
            }
            
        }
        
        chain.doFilter(request, response);
        
    }

    /**
     * Returns a String representation of this object.
     */
    @Override
    public String toString() {
        return String.format("WelcomeFilter(%s)", config==null ? "" : config);
    }
    
    /*
     * This method returns true if the given request is a Faces AJAX request.
     */
    protected boolean isAjaxRequest(HttpServletRequest request) {
        return "partial/ajax".equalsIgnoreCase(request.getHeader("Faces-Request"));
    }
    
}
