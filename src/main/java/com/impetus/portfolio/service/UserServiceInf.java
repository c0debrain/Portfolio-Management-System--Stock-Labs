package com.impetus.portfolio.service;

import java.sql.Date;

import com.impetus.portfolio.domain.User;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;

/**
 * UserServiceInf is an Interface for UserService.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
public interface UserServiceInf {
    /**
     * Adds a user to the database.
     * 
     * @param user
     *            User
    
    
    
     * @return String * @throws BusinessException * @throws SystemException */
    String insertUser(User user)throws BusinessException,SystemException;

    /**
     * Retrieves user from the database.
     * 
     * @param username
     *            String
    
    
     * @throws SystemException
     * @return User * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    User retrieveUser(String username) throws BusinessException,SystemException;

    /**
     * Retrieves the user information on the basis of the email.
     * 
     * @param email
     * 
     * 
    
    
     * @throws SystemException
     * @return User * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    User getUserByEmail(String email)throws BusinessException,SystemException;

    /**
     * 
     * 
     * Updates the user password.
     * 
     * @param cpassword
     *            String
     * @param password
     *            String
     * @param id
     *            int
    
    
     * @return String * @throws SystemException */
    String updatePassword(String cpassword, String password, int id)throws SystemException;
    /**
     * 
     * Updates user profile.
     * 
     * @param name
     * @param dob
     * @param address
     * @param country
     * @param mobile
     * @param city
    
    
     * @param userid int
     * @return String
     * @throws SystemException */
    String updateProfile(String name,Date dob,String address,String country,long mobile,String city,int userid)throws SystemException;
    /**
     * Retrieves user using User id.
     * @param userid
    
     * @return User
     * @throws SystemException
     */
    User retrieveUserById(int userid)throws SystemException;
}
