package com.impetus.portfolio.serviceImpl;

import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import com.impetus.portfolio.Repository.PortfolioRepository;
import com.impetus.portfolio.domain.Portfolio;
import com.impetus.portfolio.domain.UserPortfolio;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;
import com.impetus.portfolio.service.PortfolioServiceInf;

/**
 * PortfolioService is a Service class which serves the functions related to the
 * User Portfolios.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
public class PortfolioService implements PortfolioServiceInf {
    
    private static Logger logger = Logger.getLogger(PortfolioService.class);
    private String message = "Runtime Exception at service layer[Data inaccessable]";
    private String logmessage ="Cannot Access Data..";
    @Autowired
    private PortfolioRepository prep;

    /**
     * 
     * @param port
     *            Portfolio
     * 
    
    
     * @throws SystemException * @see com.impetus.portfolio.service.PortfolioServiceInf#addStock2Port(Portfolio) */
    public void addStock2Port(Portfolio port)throws SystemException {
        logger.info("addStock2Port():UserServices called");
        try {
            try {
                prep.insertUserPortfolio(port);
            } catch (BindException be) {
                logger.warn("Bind Exception");
            }
        } catch (ConstraintViolationException e) {
            logger.warn("Constraint voilation Exception");
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while inserting user stock");
            throw new SystemException(message,nre);
        }
    }

    /**
     * 
     * @param name
     *            String
     * 
     * 
    
    
     * @see com.impetus.portfolio.service.PortfolioServiceInf#getPortName(String)
     * @param id int
     * @return List<Portfolio> * @see
     *         com.impetus.portfolio.service.PortfolioServiceInf
     *         #getPortName(String) * @throws SystemException * @throws SystemException
     * @see com.impetus.portfolio.service.PortfolioServiceInf#getPortName(String, int)
     */
    public List<Portfolio> getPortName(String name,int id)throws SystemException {
        logger.info("getPortfolio():UserServices called");
        List<Portfolio> list = null;
        try{
            list = prep.getByNamePortfolio(name,id);
           
        return list;
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while retrieving user stocks.");
            throw new SystemException(message,nre);
        }
    }

    /**
     * Method getPortfolio.
     * 
     * @param userid
     *            int
     * 
     * 
    
    
     * @see com.impetus.portfolio.service.PortfolioServiceInf#getPortfolio(int)
     * @return List<Portfolio> * @see
     *         com.impetus.portfolio.service.PortfolioServiceInf
     *         #getPortfolio(int) * @throws SystemException * @throws SystemException
     * @see com.impetus.portfolio.service.PortfolioServiceInf#getPortfolio(int)
     */
    public List<UserPortfolio> getPortfolio(int userid)throws SystemException {
        logger.info("getPortfolio():UserServices called");
        List<UserPortfolio> list = null;
        try{
            list = prep.getByIdPortfolio(userid);
            return list;
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while retrieving portfolio.");
            throw new SystemException(message,nre);
        }
    }

    /**
     * 
     * @param id
     *            int
     * 
    
    
     * @throws SystemException * @see com.impetus.portfolio.service.PortfolioServiceInf#deleteStock(int) */
    public void deleteStock(int id)throws SystemException {
        try{
        logger.info("delete stock called..");
        prep.deleteStock(id);
        logger.info("stock deleted..");
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while deleting stocks");
            throw new SystemException(message,nre);
        }
        }

    /**
     * Method addPortfolio.
     * 
     * @param up
     *            UserPortfolio
    
    
    
    
    
     * @return String * @throws BusinessException * @throws SystemException * @throws Exception * @see com.impetus.portfolio.service.PortfolioServiceInf#addPortfolio(UserPortfolio) * @see com.impetus.portfolio.service.PortfolioServiceInf#addPortfolio(UserPortfolio)
     */
    public String addPortfolio(UserPortfolio up)throws BusinessException,SystemException {
        try{
        return prep.addPortfolio(up);
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            logger.error("failed while adding portfolio.");
            throw new SystemException(message,nre);
        }
        }
    /**
     * @param portName
     * @param id
    
    
     * @throws SystemException * @see com.impetus.portfolio.service.PortfolioServiceInf#deletePortfolio(String, int) */
    public void deletePortfolio(String portName,int id)throws SystemException{
        try{
        prep.deletePortfolio(portName,id);
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            throw new SystemException(message,nre);
        }
    }
    /**
     * @param variance
     * @param id
    
    
    
     * @return String * @throws SystemException * @see com.impetus.portfolio.service.PortfolioServiceInf#updateVariance(double, int) */
    public String updateVariance( double variance, int id)throws SystemException{
        try{
        return prep.updateVariance(variance,id);
        }catch(NestedRuntimeException nre){
            logger.warn(logmessage+nre.getCause());
            throw new SystemException(message,nre);
        }
        }
}
