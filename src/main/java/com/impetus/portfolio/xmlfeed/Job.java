package com.impetus.portfolio.xmlfeed;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.impetus.portfolio.Repository.CompanyRepository;
import com.impetus.portfolio.Repository.HistoryRepository;
import com.impetus.portfolio.Repository.UserRepository;
import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.domain.History;
import com.impetus.portfolio.mailing.Mail;

/**
 * This Class gets the XML feeds which are scheduled and returns the Object and
 * stores in the database
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */

public class Job extends SingletonMarshaller {
    private static int count = 0;
    private Logger logger = Logger.getLogger(SingletonMarshaller.class);
    private File folder = new File("D:\\xmlfeeds");
    private File[] listOfFiles = folder.listFiles();
    
    public static final int FIFTEEN = 15;
    public static final int TEN = 10;
    @Autowired
    private HistoryRepository hrep;
    @Autowired
    private CompanyRepository crep;
    @Autowired
    private UserRepository urep;
    @Autowired
    private History history;
    @Autowired
    private Mail mail;

    /**
     * This method will update the table which will keep the current and the
     * historical records
     */
    @Scheduled(cron = "0 0/5 10-16 * * MON-FRI")
    public void updateXMLTable() {
        
        long time = System.currentTimeMillis();
        
        java.sql.Date date = new java.sql.Date(time);
        
        String str = getTime();
        
        try {
            
            String filepath = "" + listOfFiles[count];

            File file = new File(filepath);

            Unmarshaller jaxbUnmarshaller = CONTEXT.createUnmarshaller();
            InputStream inputStream = new FileInputStream(file);
            Wrapper wrap =(Wrapper) jaxbUnmarshaller.unmarshal(inputStream);

            for(Company company: wrap.getCompany())
            {
                history.setTime(str);
                history.setPrice(company.getStockPrice());
                history.setTickersymbol(company.getTickerSymbol());
                history.setDate(date);
                hrep.insertXmlObject(history);
                crep.updateprices(company.getStockPrice(), company.getTickerSymbol());
            }
            

            List<String> alist = urep.priceExceedGetEmail();
            HashSet<String> set = new HashSet<String>(alist);
            alist.clear();
            alist.addAll(set);

            String email;
            for (int j = 0; j < alist.size(); j++) {
                email = "" + alist.get(j);
               
                mail.mailTo(email);
            }

            inputStream.close();
            if (count > FIFTEEN) {
                count = 0;
            } else {
                count++;
            }
        } catch (JAXBException jaxe) {
            logger.trace(jaxe);
        } catch (Exception e) {
            logger.debug("Unexpected error occured.."+e);
        }
        
    }

    /**
     * This method do proper formating of the time which is retrived through the
     * xml feeds. Method getTime.
     * 
    
     * @return String */
    public String getTime() {

        Calendar localTime = Calendar.getInstance();
        int hour = localTime.get(Calendar.HOUR_OF_DAY);
        int minute = localTime.get(Calendar.MINUTE);
        int second = localTime.get(Calendar.SECOND);
        String str;
        if (hour < TEN || minute < TEN || second < TEN) {
            String shour = "";
            String sminutes = "";
            String sseconds = "";
            if (hour < TEN) {
                shour = "0" + hour;
            } else {
                shour = "" + hour;
            }
            if (minute < TEN) {
                sminutes = "0" + minute;
            } else {
                sminutes = "" + minute;
            }
            if (second < TEN) {
                sseconds = "0" + second;
            } else {
                sseconds = "" + second;
            }
            str = shour + ":" + sminutes + ":" + sseconds;
        } else {
            str = hour + ":" + minute + ":" + second;
        }
        return str;
    }
}
