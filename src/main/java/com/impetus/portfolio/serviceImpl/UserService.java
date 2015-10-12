package com.impetus.portfolio.serviceImpl;

import java.sql.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;
import org.springframework.stereotype.Component;

import com.impetus.portfolio.Repository.UserRepository;
import com.impetus.portfolio.domain.User;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;
import com.impetus.portfolio.service.UserServiceInf;

/**
 * UserService is a Service class which serves the functions related to the User
 * data.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
public class UserService implements UserServiceInf {

    private static Logger logger = Logger.getLogger(UserService.class);
    private String message = "Runtime Exception at service layer[Data inaccessable]";
    private String logmessage = "Cannot Access Data..";
    @Autowired
    private UserRepository urep;

    /**
     * 
     * 
     * @param user
     *            User
     * 
    
    
    
    
     * @return String * @throws BusinessException * @throws SystemException * @see com.impetus.portfolio.service.UserServiceInf#insertUser() */
    public String insertUser(User user) throws BusinessException,
            SystemException {
        logger.info("InsertUser():UserServices called");
        try {
            return urep.addUser(user);
        } catch (NestedRuntimeException nre) {
            logger.warn(logmessage + nre.getCause());
            logger.error("failed while inserting user details");
            throw new SystemException(message, nre);
        }

    }

    /**
     * 
     * @param username
     *            String
     * 
     * 
     * 
    tus.portfolio.service.UserServiceInf#retrieveUser(String)
     * @return User * @see
     *         com.impetus.portfolio.service.UserServiceInf#retrieveUser(String)
     *         * @throws BusinessException * @throws BusinessException * @throws SystemException * @see com.impetus.portfolio.service.UserServiceInf#retrieveUser(String)
     */
    public User retrieveUser(String username) throws BusinessException,
            SystemException {
        logger.info("RetrieveUser():UserServices called");
        try {
            User user = null;
            user = urep.getUser(username);
            return user;
        } catch (NestedRuntimeException nre) {
            logger.warn(logmessage + nre.getCause());
            logger.error("failed while retrieving user details.");
            throw new SystemException(message, nre);
        }
    }

    /**
     * 
     * @param email
     *            String
     * 
     * 
     * 
    
     * @throws SystemException
     * @see com.impetus.portfolio.service.UserServiceInf#getUserByEmail(String)
     * @return User * @see
     *         com.impetus.portfolio.service.UserServiceInf#getUserByEmail
     *         (String) * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     * @see com.impetus.portfolio.service.UserServiceInf#getUserByEmail(String)
     */
    public User getUserByEmail(String email) throws BusinessException,
            SystemException {
        logger.info("getUserByEmail():UserServices called");
        try {
            User user = null;

            user = urep.getByEmail(email);

            return user;
        } catch (NestedRuntimeException nre) {
            logger.warn(logmessage + nre.getCause());
            logger.error("failed while retrieving user by email.");
            throw new SystemException(message, nre);
        }
    }

    /**
     * Method updatePassword.
     * 
     * @param cpassword
     *            String
     * @param password
     *            String
     * @param id
     *            int
     * 
     * 
    
    
    
     * @return String * @throws SystemException * @see com.impetus.portfolio.service.UserServiceInf#updatePassword(String,
     *      String, int) */
    public String updatePassword(String cpassword, String password, int id)
            throws SystemException {
        try {
            return urep.update(cpassword, password, id);
        } catch (NestedRuntimeException nre) {
            logger.warn(logmessage + nre.getCause());
            logger.error("failed while updating the user details.");
            throw new SystemException(message, nre);
        }
    }
    /**
     * This method updates the user profile.
     * @param name String
     * @param dob Date
     * @param address String
     * @param country String
     * @param mobile long
     * @param city String
     * @param userid int
     * @return String
     * @throws SystemException
     * @see com.impetus.portfolio.service.UserServiceInf#updateProfile(String, Date, String, String, long, String, int)
     */
    public String updateProfile(String name,Date dob,String address,String country,long mobile,String city,int userid)throws SystemException{
        try {
            return urep.updateProfile(name,dob,address,country,mobile,city,userid);
        } catch (NestedRuntimeException nre) {
            logger.warn(logmessage + nre.getCause());
            logger.error("failed while updating the user details.");
            throw new SystemException(message, nre);
        }
    }
    /**
     * This method retrieves user info using user id.
     * @param userid
    
     * @return User * @throws SystemException
     * @see com.impetus.portfolio.service.UserServiceInf#retrieveUserById(int)
     */
    public User retrieveUserById(int userid)throws SystemException{
        logger.info("getUserByIdl():UserServices called");
        try {
            User user = null;

            user = urep.getByUserId(userid);

            return user;
        } catch (NestedRuntimeException nre) {
            logger.warn(logmessage + nre.getCause());
            logger.error("failed while retrieving user by email.");
            throw new SystemException(message, nre);
        }
    }
}
