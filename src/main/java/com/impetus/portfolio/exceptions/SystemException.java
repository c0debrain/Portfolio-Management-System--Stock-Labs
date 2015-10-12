package com.impetus.portfolio.exceptions;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
/**
 * System exception handles the exceptions which affects multiple user.
 * @author prerit
 * 
 *
 * @version $Revision: 1.0 $
 */
@Component
public class SystemException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private String message;
    private static Logger logger = Logger.getLogger(SystemException.class);
    public SystemException() {
    }

    /**
     * Constructor for SystemException.
     * @param message String
     * @param e Exception
     */
    public SystemException(String message,Exception e) {
        this.message = message;
        logger.error(e+"");
        
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
