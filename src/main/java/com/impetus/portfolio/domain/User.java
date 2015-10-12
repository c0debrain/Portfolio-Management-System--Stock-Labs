package com.impetus.portfolio.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

/**
 * User class contains the details of the user.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
@Entity
@Table(name = "user")
public class User {
    public static final int FORTYFIVE=45;
    public static final int HUNDRED=100;
    public static final int SIXTY=60;
    public static final int FIFTEEN=15;
    public static final int ONE=1;
    public static final int ELEVEN=11;
    /**
     *
     */
    private String userName;
    /**
	 *
	 */
    private String password;
    private String address;
    /**
     * Method getAddress.
     * @return String
     */
    @Column(name = "address",length=HUNDRED,nullable=false)
    public String getAddress() {
        return address;
    }

    /**
     * Method setAddress.
     * @param address String
     */
    public void setAddress(String address) {
        this.address = address;
    }
    /**
     * Method getCity.
     * @return String
     */
    @Column(name = "city",length=SIXTY,nullable=false)
    public String getCity() {
        return city;
    }

    /**
     * Method setCity.
     * @param city String
     */
    public void setCity(String city) {
        this.city = city;
    }

    private String city;
    /**
	 *
	 */
    private long mobile;
    /**
     * Method getMobile.
     * @return long
     */
    @Column(name = "mobile",length=FIFTEEN,nullable=false,unique=true)
    public long getMobile() {
        return mobile;
    }

    /**
     * Method setMobile.
     * @param mobile long
     */
    public void setMobile(long mobile) {
        this.mobile = mobile;
    }
    /**
     * Method getName.
     * @return String
     */
    @Column(name = "name",length=FORTYFIVE,nullable=false)
    public String getName() {
        return name;
    }

    /**
     * Method setName.
     * @param name String
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Method getDob.
     * @return Date
     */
    @Column(name = "dob")
    public Date getDob() {
        return dob;
    }

    /**
     * Method setDob.
     * @param dob Date
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    private String name;
    private Date dob;
    private char sex;
    /**
     * Method getSex.
     * @return char
     */
    @Column(name = "sex",length=ONE,nullable=false)
    public char getSex() {
        return sex;
    }

    /**
     * Method setSex.
     * @param sex char
     */
    public void setSex(char sex) {
        this.sex = sex;
    }

    public User() {
    }

    /**
     * 
     * @param userName
     * @param password
     */
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    /**
	 *
	 */
    private int id;

    /**
     * 
     * 
    
     * @return userName */
    @Column(name = "username",length=FORTYFIVE,nullable=false,unique=true)
    public String getUserName() {
        return userName;
    }

    /**
     * 
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 
     * 
    
     * @return password */
    @Column(name = "password",length=FORTYFIVE,nullable=false)
    public String getPassword() {
        return password;
    }

    /**
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 
     * 
    
     * @return email */
    @Column(name = "email",length=FORTYFIVE,nullable=false,unique=true)
    public String getEmail() {
        return email;
    }

    /**
     * 
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 
     * 
    
     * @return country */
    @Column(name = "country",length=FORTYFIVE,nullable=false)
    public String getCountry() {
        return country;
    }

    /**
     * 
     * @param country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
	 *
	 */
    private String email;
    /**
	 *
	 */
    private String country;

    /**
     * Method getId.
     * 
    
     * @return int */
    @Id
    @GeneratedValue
    @Column(name = "id",length=ELEVEN,nullable=false)
    /**
     *
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

}
