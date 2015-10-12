package com.impetus.portfolio.service;

import java.util.List;

import com.impetus.portfolio.domain.Portfolio;
import com.impetus.portfolio.domain.UserPortfolio;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;

/**
 * PortfolioServiceInf is an interface for PortfolioService
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
public interface PortfolioServiceInf {
    /**
     * Adds stocks to the portfolio.
     * @param port
     * 
    
     * @throws SystemException */
    void addStock2Port(Portfolio port)throws SystemException;

    /**
     * retrieve user Portfolio from the database.
     * @param userid
     * 
     * 
    
    
     * @return List<Portfolio> * @throws SystemException * @throws SystemException
     */
    List<UserPortfolio> getPortfolio(int userid)throws SystemException;

    /**
     * 
     * Deletes stock from the user database.
     * @param id
     *            int
    
     * @throws SystemException */
    void deleteStock(int id)throws SystemException;

    /**
     * Retrieves user portfolio from the database.
     * 
     * @param portname
     *            String
     * 
    
    
     * @param id int
     * @return List<Portfolio> * @throws SystemException * @throws SystemException
     */
    List<Portfolio> getPortName(String portname,int id)throws SystemException;

    /**
     * Adds a Porfolio to the database.
     * 
     * @param up
     *            UserPortfolio
    
    
    
    
     * @return String * @throws BusinessException * @throws SystemException * @throws Exception */
    String addPortfolio(UserPortfolio up)throws BusinessException,SystemException;
    /**
     * This method deletes the portfolio from the database.
     * @param portName
     * @param id int
    
     * @throws SystemException */
    void deletePortfolio(String portName,int id)throws SystemException;
    /**
     * This method updates the user stock variance in the database.
     * @param variance
     * @param id
    
    
    
     * @return String * @throws SystemException */
    String updateVariance( double variance, int id)throws SystemException;

}
