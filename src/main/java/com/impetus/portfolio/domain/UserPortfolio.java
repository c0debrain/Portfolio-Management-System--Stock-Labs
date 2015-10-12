package com.impetus.portfolio.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User Portfolio contains the details about the Portfolios a particular user
 * creates
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Entity
@Table(name = "portfolio")
public class UserPortfolio {
    private int userId;
    private int portId;
    private String portName;
    private String details;
    public static final int TWOTWOFIVE=225;
    public static final int ELEVEN=11;
    public static final int FORTYFIVE=45;
    
    /**
     * Method getDetails.
     * @return String
     */
    @Column(name="details",length=TWOTWOFIVE)
    public String getDetails() {
        return details;
    }

    /**
     * Method setDetails.
     * @param details String
     */
    public void setDetails(String details) {
        this.details = details;
    }

    /**
     * Method getUserId.
     * 
    
     * @return int */
    @Column(name = "userid",length=ELEVEN)
    public int getUserId() {
        return userId;
    }

    /**
     * Method setUserId.
     * 
     * @param userId
     *            int
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Method getPortId.
     * 
    
     * @return int */
    @Id
    @Column(name = "id",length=ELEVEN)
    public int getPortId() {
        return portId;
    }

    /**
     * Method setPortId.
     * 
     * @param portId
     *            int
     */
    public void setPortId(int portId) {
        this.portId = portId;
    }

    /**
     * Method getPortName.
     * 
    
     * @return String */
    @Column(name = "portfolioname",length=FORTYFIVE,nullable=false)
    public String getPortName() {
        return portName;
    }
    

    /**
     * Method setPortName.
     * 
     * @param portName
     *            String
     */
    public void setPortName(String portName) {
        this.portName = portName;
    }

}
