package com.impetus.portfolio.Repository;

import java.sql.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.domain.History;
import com.impetus.portfolio.exceptions.BusinessException;

/**
 * History Repositry contains DAO layer service for retrieve updated values.
 * 
 * @author prerit
 * 
 * @version $Revision: 1.0 $
 */
@SuppressWarnings({ "unchecked", "deprecation" })
@Component
@Repository
public class HistoryRepository {

    private static Logger logger = Logger.getLogger(HistoryRepository.class);
    private static final String DATE = "date";
    private static final String TICKERS = "tickersymbol";
    public static final int THIRTYONE = 31;
    public static final int ELEVEN = 11;

    /**
     * This method inserts xml file objects into history table.
     * 
     * @param history
     * 
     * */
    public void insertXmlObject(History history) {
        logger.info("insertXmlObject():DaoServices called");
        try {
            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            session.save(history);
            session.getTransaction().commit();
            session.close();
        } catch (DataAccessException he) {
            logger.fatal(he.getCause() + "" + he);
            throw he;
        }
    }

    /**
     * This method retrieves history table from the database.
     * 
     * 
     * 
    
     * @return List<History> */
    public List<History> getHistoryTable() {
        logger.info("getHistoryTable():DaoServices called");
        try {
            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Criteria crit = session.createCriteria(History.class);
            List<History> results = crit.list();
            session.close();
            return results;
        } catch (DataAccessException he) {
            logger.fatal(he.getCause() + "" + he);
            throw he;
        }
    }

    /**
     * This method retrieves History table from the database.
     * 
     * @param comName
     * @param froD
     * @param toD
     * 
     * 
    
    
     * @return List<History> * @throws IndexOutOfBoundsException * @throws BusinessException * @throws BusinessException
     */
    public List<History> getByDate(String comName, Date froD, Date toD)
            throws BusinessException {
        logger.info("getByDate():DaoServices called");
        try {
            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            String ticker = findTicker(comName);
            Criteria crit = session.createCriteria(History.class);
            crit.add(Restrictions.between(DATE, froD, toD));
            crit.add(Restrictions.like(TICKERS, ticker));

            List<History> results = crit
                    .setProjection(
                            Projections
                                    .projectionList()
                                    .add(Projections.groupProperty(DATE), DATE)
                                    .add(Projections.avg("price"), "price")
                                    .add(Projections.property("id"), "id")
                                    .add(Projections.property(TICKERS), TICKERS)
                                    .add(Projections.property("time"), "time"))
                    .setResultTransformer(
                            Transformers.aliasToBean(History.class)).list();
            session.close();

            return results;
        } catch (DataAccessException he) {
            logger.fatal(he.getCause() + "" + he);
            throw he;
        }
    }

    /**
     * This method retrieves history on the basis of time intervals.
     * 
     * @param comName
     * @param stime
     * @param etime
     * 
     * 
     * @param date
     * 
     * 
    
    
     * @return List<History> * @throws IndexOutOfBoundsException * @throws BusinessException * @throws BusinessException
     */
    public List<History> getByTime(String comName, String stime, String etime,
            Date date) throws BusinessException {
        logger.info("getByTime():DaoServices called");
        try {
            final String sDate = "date";
            final String tickerSym = "tickersymbol";

            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            String ticker = findTicker(comName);
            Criteria crit = session.createCriteria(History.class);
            crit.add(Restrictions.eq(sDate, date));
            crit.add(Restrictions.between("time", stime, etime));
            crit.add(Restrictions.like(tickerSym, ticker));
            List<History> results = crit.list();
            session.close();
            return results;
        } catch (DataAccessException he) {
            logger.fatal(he.getCause() + "" + he);
            throw he;
        }
    }

    /**
     * This method retrieves the history table by year
     * 
     * @param comName
     * @param year
     * 
     * 
    
    
     * @return List<History> * @throws IndexOutOfBoundsException * @throws BusinessException * @throws BusinessException
     */
    public List<History> getByYear(String comName, int year)
            throws BusinessException {
        logger.info("getByYear():DaoServices called");
        try {
            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            String ticker = findTicker(comName);
            Criteria crit = session.createCriteria(History.class);
            Date startdate = new Date(year, 0, 1);
            Date enddate = new Date(year, ELEVEN, THIRTYONE);
            crit.add(Restrictions.between(DATE, startdate, enddate));
            crit.add(Restrictions.like(TICKERS, ticker));
            crit.addOrder(Order.asc("date"));
            List<History> results = crit.list();
            session.close();
            return results;
        } catch (DataAccessException he) {
            logger.fatal(he.getCause() + "" + he);
            throw he;
        }
    }

    /**
     * This method finds ticker from the database.
     * 
     * @param comName
     * 
     * 
    
    
     * @return ticker * @throws BusinessException * @throws BusinessException
     */
    public String findTicker(String comName) throws BusinessException {
        logger.info("findTicker():DaoServices called");
        try {
            String ticker;
            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            session.beginTransaction();
            Criteria crit = session.createCriteria(Company.class);
            crit.add(Restrictions.like("companyName", comName));

            List<Company> results = crit.list();
            if (results.isEmpty()) {
                throw new BusinessException("Company does not exists!!");
            }
            ticker = results.get(0).getTickerSymbol();
            session.close();

            return ticker;
        } catch (DataAccessException he) {
            logger.fatal(he.getCause() + "" + he);
            throw he;
        }
    }
}
