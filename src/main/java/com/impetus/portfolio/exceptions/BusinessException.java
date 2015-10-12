package com.impetus.portfolio.exceptions;

import org.springframework.stereotype.Component;
/**
 * Business Exception handles the exceptions affects a single user.
 * @author prerit
 *
 * @version $Revision: 1.0 $
 */
 
@Component
public class BusinessException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String message;
    
    public BusinessException() {
    }

    /**
     * Constructor for BusinessException.
     * @param message String
     */
    public BusinessException(String message) {
        this.message = message;
        
    }

    /**
     * Method getMessage.
    
     * @return String */
    public String getMessage() {
        return message;
    }

    /**
     * Method setMessage.
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
    }

}
