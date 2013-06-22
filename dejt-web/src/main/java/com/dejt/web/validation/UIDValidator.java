/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.web.validation;

import com.dejt.common.CRUDFacade;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * {@link FacesValidator} for validating new Aramis/Disco action's name.
 * 
 * @author jigga
 */
@RequestScoped
@Named("UIDValidator")
public class UIDValidator implements Validator {
    
    @Inject
    private CRUDFacade facade;
    
    /**
     * This method verifies if the action with the given name does not already
     * exist in Aramis database.
     * 
     * @param context
     * @param component
     * @param value
     * 
     * @throws ValidatorException 
     */
    @Override
    public void validate(FacesContext context, UIComponent component, Object value)
        throws ValidatorException {
        
        boolean uidTaken = ((Number)facade.getEntityManager()
            .createNamedQuery("User.checkUID")
            .setParameter("uid", String.valueOf(value))
            .getSingleResult())
            .intValue()>0;
        if (uidTaken) {
            throw new ValidatorException(new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Identifikator \"" + value + "\" jest już zajęty.", null
            ));
        }
        
    }
    
}
