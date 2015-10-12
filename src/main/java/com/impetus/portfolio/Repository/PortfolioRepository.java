package com.impetus.portfolio.Repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindException;

import com.impetus.portfolio.domain.Portfolio;
import com.impetus.portfolio.domain.UserPortfolio;
import com.impetus.portfolio.exceptions.BusinessException;

/**
 * Portfolio Repository contains the DAO layer service for retrieving values
 * about the stocks and portfolio
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("unchecked")
@Component
@Repository
public class PortfolioRepository {
    /**
     *
     */
    private static Logger logger = Logger.getLogger(PortfolioRepository.class);

    /**
     * This method inserts user stocks to the database.
     * @param port
     * 
     * 
    tion
     *             * @throws BindException
     * @throws SystemException * @throws SystemException */
    public void insertUserPortfolio(Portfolio port) throws BindException {
        try{
        logger.info("insertUserPorfolio():DaoServices called");
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(port);
        session.getTransaction().commit();
        session.close();
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * This method retrives the portfoliio using the user id from the database.
     * 
     * @param userid
     * 
     * 
    
     * @return List<Portfolio> * @throws SystemException */
    public List<UserPortfolio> getByIdPortfolio(int userid) {
        logger.info("getByIdPortfolio():DaoServices called");
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(UserPortfolio.class);
        crit.add(Restrictions.eq("userId", userid));

        List<UserPortfolio> results = crit.list();

        session.close();

        return results;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     *  Method retreives the Portfolio data from the database using the Portfolio name. 
     * 
     * @param name
     *            String
     * 
    
     * @param id int
     * @return List<Portfolio> */
    public List<Portfolio> getByNamePortfolio(String name,int id) {
        logger.info("getByIdPortfolio():DaoServices called");
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Portfolio.class);
        crit.add(Restrictions.eq("portfolioName", name));
        crit.add(Restrictions.eq("userid", id));
        List<Portfolio> results = crit.list();
        
        session.close();
        return results;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * Method deletes stock from the database.
     * 
     * @param id
     *            int
     */
    @Transactional
    public void deleteStock(int id) {
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session2 = sf.openSession();
        Portfolio p = (Portfolio) session2.get(Portfolio.class, id);
        Transaction tx = session2.beginTransaction();
        Criteria crit = session2.createCriteria(Portfolio.class);
        crit.add(Restrictions.eq("pkid", id)).uniqueResult();
        List<Portfolio> results = crit.list();
        p.setCompanyName(results.get(0).getCompanyName());
        p.setDate(results.get(0).getDate());
        p.setPkid(results.get(0).getPkid());
        p.setStockPrice(results.get(0).getStockPrice());
        p.setTickerSymbol(results.get(0).getTickerSymbol());
        p.setUserid(results.get(0).getUserid());
        p.setVariance(results.get(0).getVariance());
        session2.delete(p);
        tx.commit();
        session2.close();
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
        

    }

    /**
     * method adds Portfolio to the database. 
     * 
     * @param port
     *            UserPortfolio
    
    
    
     * @return String * @throws BusinessException * @throws Exception */
    public String addPortfolio(UserPortfolio port) throws BusinessException{
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        session.save(port);
        session.getTransaction().commit();
        session.close();
        return "Portfolio created.";
        }catch(ConstraintViolationException cve){
            logger.fatal(cve);
            return "Portfolio Name Already taken...Please Enter another name.";
            
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }
    
    /**
     * Method deletes the entire portfolio from the database.
     * @param portName
     * @param id
     */
    public void deletePortfolio(String portName,int id){
        try{
           
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session2 = sf.openSession();
        UserPortfolio p = (UserPortfolio) session2.get(UserPortfolio.class, id);
        Transaction tx = session2.beginTransaction();
        Criteria crit = session2.createCriteria(UserPortfolio.class);
        crit.add(Restrictions.eq("portId", id)).uniqueResult();
        crit.add(Restrictions.eq("portName", portName)).uniqueResult();
        List<UserPortfolio> results = crit.list();
        p.setPortId(results.get(0).getPortId());
        p.setPortName(results.get(0).getPortName());
        session2.delete(p);
        Criteria crit2 = session2.createCriteria(Portfolio.class);
        crit2.add(Restrictions.eq("portfolioName", portName));
        List<Portfolio> results2 = crit2.list();
        for(int i=0;i<results2.size();i++){
        session2.delete(results2.get(i));
        }
        tx.commit();
        session2.close();
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he; 
        }
    }
    /**
     * method updates the variance of the selected stock.
     * 
     * @param variance
     * @param id
    
    
     * @return String */
    public String updateVariance(double variance,int id){
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        session.beginTransaction();
        Portfolio portfolio = (Portfolio) session.get(Portfolio.class, id);
        portfolio.setVariance(variance);
        session.update(portfolio);
        tx.commit();
        session.close();
        return "true";
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
        }
}
