package com.impetus.portfolio.xmlfeed;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.impetus.portfolio.Repository.CompanyRepository;
import com.impetus.portfolio.domain.Company;

/**
 * Wrapper class wraps the data from the xmlfeeds into the an list object
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@XmlRootElement(name = "Companies")
@XmlSeeAlso({ Company.class })
public class Wrapper {
    
    private List<Company> company = null;

    /**
     * 
     * 
    
     * @return List<Company> */
    @XmlElementWrapper(name = "companies")
    @XmlElementRef()
    public List<Company> getCompany() {
        return company;
    }
    /**
     * wraps the company data from the database.
     */
    public Wrapper() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                CompanyRepository.class);
        
        CompanyRepository crep = ctx.getBean(CompanyRepository.class);
        company = new ArrayList<Company>();
        company = crep.getList();
        

    }
}
