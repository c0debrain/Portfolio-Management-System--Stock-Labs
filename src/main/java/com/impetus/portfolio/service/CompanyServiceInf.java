package com.impetus.portfolio.service;

import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.exceptions.SystemException;

/**
 * CompanyServiceInf is an interface for the CompanyService.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
public interface CompanyServiceInf {
    /**
     * sends information for retrieving the company details on the basis of company name.
     * 
     * @param companyName
     * 
     * 
    
    
     * @return Company * * @throws SystemException * @throws SystemException
     */
    Company retrieveCompanyDetails(String companyName)throws SystemException;

}
