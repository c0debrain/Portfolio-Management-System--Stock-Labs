package com.impetus.portfolio.serviceImpl;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;

import org.springframework.stereotype.Component;

import com.impetus.portfolio.Repository.HistoryRepository;
import com.impetus.portfolio.domain.History;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;

import com.impetus.portfolio.service.HistoryServiceInf;

/**
 * HistoryService is a Service class which serves the functions related to the
 * Xml updates.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
public class HistoryService implements HistoryServiceInf {
    
    private static Logger logger = Logger.getLogger(HistoryService.class);
    public static final int NINETEENHUNDRED = 1900;
    private String message = "Runtime Exception at service layer[Data inaccessable]";
    private String logmessage ="Cannot Access Data..";

    @Autowired
    private HistoryRepository hrep;
   

    /**
     * 
     * @param comName
     *            String
     * @param froD
     *            Date
     * @param toD
     *            Date
     * 
     * 
    
    
     * @throws SystemException
     * @see com.impetus.portfolio.service.HistoryServiceInf#selectdate(String, Date, Date)
     * @return List<History> * @see
     *         com.impetus.portfolio.service.HistoryServiceInf
     *         #selectdate(String, Date, Date) * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     * @see com.impetus.portfolio.service.HistoryServiceInf#selectdate(String, Date, Date)
     */
    public List<History> selectdate(String comName, Date froD, Date toD) throws BusinessException,SystemException{
        logger.info("selectdate():UserServices called");
        try{
        BusinessException bse = null;
        List<History> list = null;
        list = hrep.getByDate(comName, froD, toD);
        if (list==null || list.size()==0) {
            bse = new BusinessException("No matching record found in the database. Please refine your business criteria..");
            throw bse;
        }
        return list;
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while generating graph by date");
            throw new SystemException(message,nre);
        }
    }

    /**
     * 
     * @param comName
     *            String
     * @param stime
     *            String
     * @param etime
     *            String
     * 
     * 
     * @param date
     *            Date
     * 
     * 
    
    
     * @throws SystemException
     * @see com.impetus.portfolio.service.HistoryServiceInf#selectTime(String, String, String, Date)
     * @return List<History> * @see
     *         com.impetus.portfolio.service.HistoryServiceInf
     *         #selectTime(String, String, String, String, String, Date) * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     * @see com.impetus.portfolio.service.HistoryServiceInf#selectTime(String, String, String, Date)
     */
    public List<History> selectTime(String comName, String stime, String etime,
            Date date)throws BusinessException,SystemException {
        logger.info("selectTime():UserServices called");
        List<History> list = null;
        try {
            list = hrep.getByTime(comName, stime, etime, date);
          
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while generating graph by time");
            throw new SystemException(message,nre);
        }
        return list;
    }

    /**
     * 
     * @param comName
     *            String
     * @param year
     *            int
     * 
     * 
    
    
     * @throws SystemException
     * @see com.impetus.portfolio.service.HistoryServiceInf#selectyear(String, int)
     * @return List<History> * @see
     *         com.impetus.portfolio.service.HistoryServiceInf
     *         #selectyear(String, int) * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     * @see com.impetus.portfolio.service.HistoryServiceInf#selectyear(String, int)
     */
    public List<History> selectyear(String comName, int year)throws BusinessException,SystemException {
        logger.info("selectyear():UserServices called");
        List<History> list = null;
        try {
            list = hrep.getByYear(comName, year - NINETEENHUNDRED);
          
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while generating graph by year");
            throw new SystemException(message,nre);
        }
        return list;
    }

    /**
     * 
     * 
     * 
    
    
     * @see com.impetus.portfolio.service.HistoryServiceInf#getHistory()
     * @return List<History> * @see
     *         com.impetus.portfolio.service.HistoryServiceInf#getHistory() * @throws SystemException * @throws SystemException
     * @see com.impetus.portfolio.service.HistoryServiceInf#getHistory()
     */
    public List<History> getHistory()throws SystemException {

        logger.info("getHistory():UserServices called");
        List<History> list = null;
        try {
            list = hrep.getHistoryTable();
        }catch(NestedRuntimeException nre){
                logger.warn(logmessage+nre.getCause());
                logger.error("failed while retrieving history details.");
                throw new SystemException(message,nre);
            }
       
        return list;
    }

}
