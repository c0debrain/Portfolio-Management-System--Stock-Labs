package com.impetus.portfolio.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.portfolio.Repository.CompanyRepository;
import com.impetus.portfolio.Repository.HibernateSearch;
import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.domain.Portfolio;
import com.impetus.portfolio.domain.UserPortfolio;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;
import com.impetus.portfolio.serviceImpl.PortfolioService;

/**
 * This Controller controls the Portfolio related methods.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */

@Controller

public class PortfolioController {
    private static final Logger LOGGER = Logger
            .getLogger(PortfolioController.class);
    @Autowired
    private HibernateSearch hSearch;
    @Autowired
    private PortfolioService ps;

    private String addstock = "/addstock";
    private static final String MESSAGE = "message";
    private String liststr = "list";
    private static final String UID = "userId";
    private static final String PORTFOLIONAME = "portfolioname";
    private String displayport = "displayport called.";
    /**
     * This method sends a model to the jsp which contains the company data and
     * a view. Method addstock2Port.
     * 
    
    
     * @throws SystemException
     * @return ModelAndView * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/displayport", method = RequestMethod.GET)
    public ModelAndView addstocktoPort() throws BusinessException,SystemException {
        LOGGER.info("Display Portfolio page....");
        ModelAndView mav = new ModelAndView();
        CompanyRepository com = new CompanyRepository();
        List<Company> companies = com.getList();
        if (companies.isEmpty()) {
            LOGGER.error("failed while retrieving company details");
            throw new BusinessException("Company not found!!");
        }
        int clength = companies.size();
        Map<String, Object> comdet = new HashMap<String, Object>();
        comdet.put(liststr, companies);
        comdet.put("length", clength);
        mav.addObject("comdet", comdet);
        return mav;
    }

    /**
     * Method renders the portfolio view.
     * 
    
     * @return ModelAndView */
    @RequestMapping(value = "/portfolio", method = RequestMethod.GET)
    public ModelAndView getPortfolioView() {
        return new ModelAndView("portfolio");
    }

    /**
     * This method retrieves user portfolio and renders the view.
     * 
     * @param userId
     * 
    
    
     * @throws SystemException
     * @return view * @throws Exception * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/stocks", method = RequestMethod.POST)
    public ModelAndView getportfolio(@ModelAttribute(UID) Integer userId)
            throws BusinessException,SystemException {
        ModelAndView mav = new ModelAndView();

        List<UserPortfolio> list = ps.getPortfolio(userId);
        if (list.isEmpty()) {
            LOGGER.error("failed while retrieving Portfolio");
            throw new BusinessException("Please Create a Portfolio!!");
        }

        int length = list.size();
        Map<String, Object> portfoliodet = new HashMap<String, Object>();

        portfoliodet.put(liststr, list);
        portfoliodet.put("length", length);
        mav.setViewName("viewportfolio");
        mav.addObject("portfoliodet", portfoliodet);
        LOGGER.info(displayport);
        return mav;
    }

    /**
     * method gets the information from the view and renders the view.
     * 
     * @param portname
     *            String
     * 
    
    
     * @throws SystemException
     * @param id int
     * @return ModelAndView * @throws Exception * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/port", method = RequestMethod.POST)
    public ModelAndView getportName(
            @ModelAttribute(PORTFOLIONAME) String portname,@ModelAttribute(UID)int id)
            throws BusinessException,SystemException {
        ModelAndView mav = new ModelAndView();
        List<Portfolio> list = ps.getPortName(portname,id);
        
        if (list.isEmpty()) {
            LOGGER.error("failed while retrieving user stocks.");
            throw new BusinessException(
                    "No stocks Added yet to this portfolio!!");
        }
        int length = list.size();
        Map<String, Object> portfoliodet2 = new HashMap<String, Object>();
        portfoliodet2.put(liststr, list);
        portfoliodet2.put("length", length);
        mav.setViewName("viewstocks");
        mav.addObject("portfoliodet2", portfoliodet2);
        LOGGER.info(displayport);
        return mav;
    }

    /**
     * This method takes a query and returns the company details so that it
     * could be added to the portfolio.
     * 
     * @param query
     * 
     * @param userid
     *            Integer
    
    
     * @throws SystemException
     * @return view * @throws Exception * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView search(@ModelAttribute("query") String query,
            @ModelAttribute("id") Integer userid) throws BusinessException,SystemException {
        LOGGER.info("Search for stocks......");
        List<Company> company = hSearch.hSearch(query);
        List<UserPortfolio> userport = null;
        if (userid != 0) {
            userport = ps.getPortfolio(userid);
        }
        if (company.isEmpty()) {
            LOGGER.error("failed while retrieving company details.");
            throw new BusinessException(
                    "The Company doesnot exists.Please try again!!");
        }
        LOGGER.debug(company);
        ModelAndView mav = new ModelAndView();
        Map<String, Object> companydet = new HashMap<String, Object>();
        companydet.put("companyname", company.get(0).getCompanyName());
        companydet.put("stockprice", company.get(0).getStockPrice());
        companydet.put("tickersymbol", company.get(0).getTickerSymbol());
        if (userid != 0) {
            companydet.put("portname", userport);
        }
        mav.setViewName(addstock);
        mav.addObject("companydet", companydet);
        return mav;

    }

    /**
     * This method works when a guest tries to search stock details. Method
     * searchOnly.
     * 
     * @param query
     *            String
    
    
    
     * @throws SystemException
     * @throws Exception * @return ModelAndView * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/searchonly", method = RequestMethod.POST)
    public ModelAndView searchOnly(@ModelAttribute("query") String query)
            throws BusinessException,SystemException {
        LOGGER.info("Search for stocks......");
        List<Company> company = hSearch.hSearch(query);

        if (company==null || company.size()==0) {
            LOGGER.error("failed while retrieving company details.");
            throw new BusinessException(
                    "The Company doesnot exists.Please try again!!");
        }
        LOGGER.debug(company);
        ModelAndView mav = new ModelAndView();
        Map<String, Object> companydet = new HashMap<String, Object>();
        companydet.put("companyname", company.get(0).getCompanyName());
        companydet.put("stockprice", company.get(0).getStockPrice());
        companydet.put("tickersymbol", company.get(0).getTickerSymbol());

        mav.setViewName(addstock);
        mav.addObject("companydet", companydet);
        return mav;

    }

    /**
     * This method will retrieve portfolio information from database and renders
     * the view.
     * 
     * @param port
     * 
    
    
     * @return view * @throws SystemException * @throws SystemException
     */
    @RequestMapping(value = "/addedstock", method = RequestMethod.POST)
    public ModelAndView add2port(Portfolio port)throws SystemException {
        LOGGER.info("Retrieve user Portfolio.....");
        ps.addStock2Port(port);
        return new ModelAndView("redirect:/addsuccess");
    }
    /**
     * This method returns the success page when a stock is added.
    
     * @return ModelAndView */
    @RequestMapping(value = "/addsuccess", method = RequestMethod.GET)
    public ModelAndView successpage() {
        return new ModelAndView("addsuccess");
    }
    /**
     * This method deletes the stocks from the user portfolio
     * 
     * @param id
     *            String
    
    
     * @param portname String
     * @param userid int
     * @return view * @throws SystemException * @throws SystemException
     */
    @RequestMapping(value = "/deletestock", method = RequestMethod.POST)
    public ModelAndView deleteStock(@RequestParam(value = "id") String id,@RequestParam(value=PORTFOLIONAME)String portname,@RequestParam(value=UID)int userid)throws SystemException {
        LOGGER.info("Stock deletion called...");
        ModelAndView mav = new ModelAndView();
        int i = Integer.parseInt(id);
        ps.deleteStock(i);
        String message ="Stock has been deleted.";
        List<Portfolio> list = ps.getPortName(portname,userid);
       
        Map<String, Object> portfoliodet2 = new HashMap<String, Object>();
        portfoliodet2.put(liststr, list);
        portfoliodet2.put("message", message);
        mav.setViewName("viewstocks");
        mav.addObject("portfoliodet2", portfoliodet2);
        LOGGER.info(displayport);
        return mav;
        
    }
    
    /**
     * method get the information to delete the user Portfolio and renders the view.
     * @param portName
     * @param id
    
    
    
     * @return ModelAndView * @throws SystemException */
    @RequestMapping(value = "/delport", method = RequestMethod.POST)
    public ModelAndView deletePort(@RequestParam(value = PORTFOLIONAME) String portName,@RequestParam(value="id")int id)throws SystemException {
        LOGGER.info("Portfolio deletion called...");
        ps.deletePortfolio(portName,id);
        String message ="Portfolio has been deleted.";
        return new ModelAndView("/portfolio",MESSAGE,message);
    }

    /**
     * This method adds Portfolios related to a particular user. Method
     * addPortfolio.
     * 
     * @param up
     *            UserPortfolio
    
    
    
     * @throws SystemException
     * @throws Exception * @return ModelAndView * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    
    @RequestMapping(value = "/addportfolio", method = RequestMethod.POST)
    public ModelAndView addPortfolio(
            @ModelAttribute("userportfolio") UserPortfolio up)throws BusinessException,SystemException {
        String message;
        LOGGER.info("Portfolio addition called..."); 
        message = ps.addPortfolio(up);
        return new ModelAndView("portfolio",MESSAGE,message);
    }
    /**
     * method renders the view for the portfolio.
    
    
     * @return ModelAndView */
    @RequestMapping(value = "/viewportfolio", method = RequestMethod.GET)
    public ModelAndView viewPortfolio() {
        return new ModelAndView("portfolio");
    }
    /**
     * method gets the information to update the password and renders the view.
     * @param variance
     * @param id
    
    
    
     * @param portname String
     * @param userid int
     * @return ModelAndView * @throws SystemException */
    @RequestMapping(value = "/updatevariance", method = RequestMethod.POST)
    public ModelAndView changeVariance(
            @RequestParam(value = "variance") double variance,
             @RequestParam int id,@RequestParam(value=PORTFOLIONAME)String portname,@RequestParam(value=UID)int userid)throws SystemException {
        String isUpdated = ps.updateVariance(variance,id);
        
        ModelAndView mav = new ModelAndView();
        String message = "Your Variance was not changed! Please try again";
        List<Portfolio> list = ps.getPortName(portname,userid);
        if(isUpdated.equals("true")){
            message = "Your Variance has been changed.";
        }
        Map<String, Object> portfoliodet2 = new HashMap<String, Object>();
        portfoliodet2.put(liststr, list);
        portfoliodet2.put("message", message);
        mav.setViewName("viewstocks");
        mav.addObject("portfoliodet2", portfoliodet2);
        
        LOGGER.info(displayport);
        return mav;


    }

}
