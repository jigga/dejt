/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.faces;

import java.io.IOException;
import javax.faces.FacesException;
import javax.faces.application.ViewHandler;
import javax.faces.application.ViewHandlerWrapper;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;

/**
 * {@link ViewHandlerWrapper} extension that handles view expiration
 * for login pages (user submits the login form after the view has expired).
 * 
 * @author jigga
 */
public class LoginViewWrapper extends ViewHandlerWrapper {

    private ViewHandler wrapped;
    
    /**
     * 
     * @param wrapped 
     */
    public LoginViewWrapper(ViewHandler wrapped) {
        this.wrapped = wrapped;
    }

    /**
     * <p>
     * The pseudo-algorithm for this method is as follows:
     * </p>
     * 
     * <ol>
     * <li>
     * Delegates to {@link ViewHandler} wrapped by this instance to restore 
     * the view. If non-null {@link UIViewRoot} is returned, it is used as
     * the return value for this method. If null value is returned, see 
     * subsequent points.
     * </li>
     * <li>
     * </li>
     * </ol>
     * 
     * @param ctx {@link FacesContext} for the current request.
     * @param viewId the view identifier for the current request.
     * 
     * @return 
     */
    @Override
    public UIViewRoot restoreView(FacesContext ctx, String viewId) {
        
        UIViewRoot viewRoot = 
            wrapped.restoreView(ctx, viewId);
        
        if (viewRoot == null) {
            if ("/login.xhtml".equals(viewId) || "/index.xhtml".equals(viewId)) {
                viewRoot = 
                    wrapped.createView(ctx, viewId);
                ctx.setViewRoot(viewRoot);
                
                try {
                    wrapped.getViewDeclarationLanguage(ctx, viewId)
                        .buildView(ctx, viewRoot);
                } catch (IOException e) {
                    throw new FacesException(e);
                }
            }
        }
        
        return viewRoot;
        
    }

    /**
     * <p>
     * This method returns {@link ViewHandler} instance wrapped by 
     * this instance.
     * </p>
     * 
     * @return {@link ViewHandler} instance wrapped by this instance.
     */
    @Override
    public ViewHandler getWrapped() {
        return wrapped;
    }
    
}
