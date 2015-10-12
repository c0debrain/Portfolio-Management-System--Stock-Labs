package com.impetus.portfolio.service;

import java.sql.Date;
import java.util.List;

import com.impetus.portfolio.domain.History;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;

/**
 * HistoryServiceInf is an interface for the HistoryService.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
public interface HistoryServiceInf {
    /**
     * sends information to DAO layer to retrieve data on basis of dates.
     * @param comName
     * @param froD
     * @param toD
     * 
     * 
    
    
     * @throws SystemException
     * @return List<History> * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    List<History> selectdate(String comName, Date froD, Date toD)throws BusinessException,SystemException;

    /**
     * sends information to DAO layer to retrieve data on basis of time.
     * @param comName
     * @param stime
     * @param etime
     * 
     * 
     * @param date
     * 
     * 
    
    
     * @throws SystemException
     * @return List<History> * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    List<History> selectTime(String comName, String stime, String etime,
            Date date)throws BusinessException,SystemException;

    /**
     * sends information to DAO layer to retrieve data on basis of year.
     * @param comName
     * @param year
     * 
     * 
    
    
     * @throws SystemException
     * @return List<History> * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    List<History> selectyear(String comName, int year)throws BusinessException,SystemException;

    /**
     * 
     *  retrieve History table data.
     * 
    
    
     * @return List<History> * @throws SystemException * @throws SystemException
     */
    List<History> getHistory()throws SystemException;

}
