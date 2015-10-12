package com.impetus.portfolio.web;

import java.sql.Date;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.impetus.portfolio.domain.History;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;
import com.impetus.portfolio.serviceImpl.HistoryService;

/**
 * This Controller controls and renders the view for the user as user iteracts.
 * 
 * @author Prerit
 * 
 * 
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("deprecation")
@Controller
public class HistoryController {

    private List<History> list = null;

    private static Logger logger = Logger.getLogger(HistoryController.class);
    private String displaygraph = "/displaygraph";
    @Autowired
    private HistoryService hs;

    /**
     * This Method will render view for graph page
     * 
     * 
    
     * @return view */
    @RequestMapping(value = "/showgraph/time", method = RequestMethod.GET)
    public String changeurl() {
        logger.setLevel(Level.INFO);
        logger.info("changeurl():called.");
        return "redirect:/displaygraph";
    }

    /**
     * This method will render view to display the graph after user has entered
     * the details.
     * 
     * 
    
     * @return view */
    @RequestMapping(value = "/graph", method = RequestMethod.GET)
    public ModelAndView displaygraph() {
        logger.info("displaygraph():called.");
        ModelAndView mav = new ModelAndView();
        mav.setViewName(displaygraph);
        return mav;
    }

    /**
     * This method takes the parameters and selects the date intervals for which
     * the graph is to be shown
     * 
     * @param comName
     * @param froD
     * @param toD
     * 
    
    
     * @throws SystemException
     * @return view * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/showgraph", method = RequestMethod.POST)
    public ModelAndView selectdates(
            @ModelAttribute("company_name") String comName,
            @ModelAttribute("start_date") Date froD,
            @ModelAttribute("end_date") Date toD) throws BusinessException,SystemException {
   
        logger.info("selectdates():called.");
        ModelAndView mav = null;

        list = hs.selectdate(comName, froD, toD);
        if (list.isEmpty()) {
            logger.error("failed while generating graph");
            throw new BusinessException(
                    "No matching record found in the database. Please refine your business criteria..");
        }
        logger.debug(list);
        String str = "[['x','Stock Price'],[";
        for (int i = 0; i < list.size(); i++) {
            str = str + "'";
            str = str + list.get(i).getDate();
            str = str + "'";
            str = str + ",";
            str = str + list.get(i).getPrice();
            str = str + "]";
            if (i != list.size() - 1) {
                str = str + ",[";
            }
        }
        str = str + "]";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("string", str);
        map.put("Name", comName);
        mav = new ModelAndView();
        mav.addObject("map", map);
        mav.setViewName(displaygraph);
        return mav;
        }
    

    /**
     * This Method will take parameters and wil render the graph for the given
     * time interval.
     * 
     * @param comName
     * @param date
     * @param stime
     * @param etime
     * 
     * 
     * 
    
    
     * @throws SystemException
     * @return view * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/time", method = RequestMethod.POST)
    public ModelAndView selectTime(
            @ModelAttribute("company_name") String comName, Date date,
            @ModelAttribute("start_time") String stime,
            @ModelAttribute("end_time") String etime) throws BusinessException,SystemException {
        logger.info("selectTime():called.");

        list = hs.selectTime(comName, stime, etime, date);
        if (list.isEmpty()) {
            logger.error("failed while generating graph");
            throw new BusinessException(
                    "No matching record found in the database. Please refine your business criteria..");
        }
        ModelAndView mav = new ModelAndView();
        String str = "[['x','Stock Price'],[";
        for (int i = 0; i < list.size(); i++) {

            str = str + "'";
            str = str + list.get(i).getTime();
            str = str + "'";
            str = str + ",";
            str = str + list.get(i).getPrice();
            str = str + "]";
            if (i != list.size() - 1) {
                str = str + ",[";
            }
        }
        str = str + "]";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("string", str);
        map.put("Name", comName);
        mav.addObject("map", map);
        mav.setViewName(displaygraph);
        return mav;
    }

    /**
     * This method will render the graph according to the choosen year
     * 
     * @param year
     * @param comName
     * 
    
    
     * @throws SystemException
     * @return view * @throws Exception * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "yearly", method = RequestMethod.POST)
    public ModelAndView selectyear(@ModelAttribute("year") int year,
            @ModelAttribute("company_name") String comName)
            throws BusinessException,SystemException {
        logger.info("selectyear():called.");
        List<History> newlist = new ArrayList<History>();
        History h = new History();

        list = hs.selectyear(comName, year);
        if (list.isEmpty()) {
            logger.error("failed while generating graph");
            throw new BusinessException(
                    "No matching record found in the database. Please refine your business criteria..");
        }
        for (int i = 0; i < list.size() - 1; i++) {

            if (list.get(i).getDate().getMonth() != (list.get(i + 1).getDate()
                    .getMonth())) {
                h = list.get(i);
                if (h != null) {
                    newlist.add(h);
                }
                if (i == list.size() - 2) {
                    newlist.add(list.get(i + 1));
                }
            }
        }
        logger.debug(list);

        ModelAndView mav = new ModelAndView();
        String str = "[['x','Stock Price'],[";
        for (int i = 0; i < newlist.size(); i++) {
            str = str + "'";
            int mon = newlist.get(i).getDate().getMonth();
            DateFormatSymbols symbols;
            symbols = new DateFormatSymbols();
            String month = symbols.getMonths()[mon - 1];
            str = str + month;
            str = str + "'";
            str = str + ",";
            str = str + newlist.get(i).getPrice();
            str = str + "]";
            if (i != newlist.size() - 1) {
                str = str + ",[";
            }
        }
        str = str + "]";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("Name", comName);
        map.put("string", str);
        mav.addObject("map", map);
        mav.setViewName(displaygraph);
        return mav;

    }

}
