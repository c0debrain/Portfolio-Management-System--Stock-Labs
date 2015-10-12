package com.impetus.portfolio.serviceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NestedRuntimeException;

import org.springframework.stereotype.Component;

import com.impetus.portfolio.Repository.CompanyRepository;
import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.exceptions.SystemException;

import com.impetus.portfolio.service.CompanyServiceInf;

/**
 * CompanyService is a Service class which serves the functions related to the
 * Company data
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
public class CompanyService implements CompanyServiceInf {

    @Autowired
    private CompanyRepository crep;
   
    private static Logger logger = Logger.getLogger(CompanyService.class);

    /**
     * 
     * @param companyName
     *            String
     * 
     * 
    
    
     * @see com.impetus.portfolio.service.CompanyServiceInf#retrieveCompanyDetails(String)
     * @return Company * @see
     *         com.impetus.portfolio.service.CompanyServiceInf#retrieveCompanyDetails
     *         (String) * @throws SystemException * @throws SystemException
     * @see com.impetus.portfolio.service.CompanyServiceInf#retrieveCompanyDetails(String)
     */
    public Company retrieveCompanyDetails(String companyName)throws SystemException {
        logger.info("UserServices called");
        try{
        return crep.getCompanyDetails(companyName);
        }catch(NestedRuntimeException nre){
            logger.warn("Cannot Access Data.."+nre.getCause());
            logger.error("failed while retrieving company details");
            throw new SystemException("Data Could not be Accessed..",nre);
        }
    }

}
