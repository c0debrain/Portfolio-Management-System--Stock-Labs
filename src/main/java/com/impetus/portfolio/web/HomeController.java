package com.impetus.portfolio.web;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.portfolio.Repository.CompanyRepository;
import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;

/**
 * Home controller returns company details for populating the datalist and the
 * index page the first time the user visits.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Controller
public class HomeController {
    private static Logger logger = Logger.getLogger(HomeController.class);

    @Autowired
    private CompanyRepository com;

    /**
     * Simply selects the home view to render by returning its name.
     * 
     * @param session
     *            HttpSession
    
    
     * @return view * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpSession session) throws BusinessException,SystemException {

        logger.debug("Log4j Logging started....");
        try{
        List<Company> companies = com.getList();
        
        if (companies.isEmpty()) {
            logger.error("failed while retrieving company details");
            throw new BusinessException("Company cannot Be found!!");
        }
        session.setAttribute("companies", companies);
        
        logger.info("login page returned.");
        return "index";
        }catch(Exception e){ 
            logger.error("failed while retrieving company details...garbage collection due to time out!");
            throw new SystemException("error while retrieving company..",e);}
        }

    /**
     * Method about.
     * 
    
     * @return ModelAndView */
    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about() {
        return new ModelAndView("about");
    }

}
