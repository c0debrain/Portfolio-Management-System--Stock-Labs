package com.impetus.portfolio.mailing;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.impetus.portfolio.exceptions.BusinessException;

/**
 * Mail class takes up the responsiblity of sending updates to user using
 * e-mails.
 * 
 * @author Prerit
 * @version $Revision: 1.0 $
 */
@Component
public class Mail {

    private static Logger logger = Logger.getLogger(Mail.class);
    @SuppressWarnings("unused")
    private static String email;

    private String password;

    private String username;

    public Mail() {
    }

    /**
     * Constructor for populating the user information to be sent.
     * @param email
     * @param password
     * @param username
     */
    public Mail(String email, String password, String username) {

        this.email = email;
        this.password = password;
        this.username = username;
    }

    /**
     * This method sends email to user.
     * 
     * @param email
     * 
    
     * @throws BusinessException */
    public void mailTo(String email) throws BusinessException {
        logger.info("mailTo():Mail called");
        String to = email;
        String from = "prerit912@gmail.com";
        String host = "localhost";
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        Session session = Session.getDefaultInstance(properties);
        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    to));
            message.setSubject("Stock-Labs-notification");
          
            if ((username != null) && (password != null)) {
                message.setText("Your Password:" + password);
                
                Transport.send(message);
                
            } else {
                message.setText("Your stock prices have"
                        + " increased please visit our site"
                        + " and check your portfolio");
                
                Transport.send(message);
            }
            
        } catch (MessagingException mex) {
            logger.trace(mex);
        }
    }
}
