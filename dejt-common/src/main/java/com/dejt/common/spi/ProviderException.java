/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dejt.common.spi;

/**
 *
 * @author Jigga
 */
public class ProviderException extends Exception {
    
    private static final long serialVersionUID = -8027491429970626089L;

    public ProviderException() {
    }
    
    public ProviderException(Throwable cause) {
        super(cause);
    }

    public ProviderException(String message) {
        super(message);
    }

    public ProviderException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
