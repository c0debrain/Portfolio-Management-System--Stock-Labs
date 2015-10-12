package com.impetus.portfolio.Repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.impetus.portfolio.domain.Company;

/**
 * Company Repository contains all the DAO layer services for retrieving details
 * about companies.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
@Repository
public class CompanyRepository {
   
    private static Logger logger = Logger.getLogger(CompanyRepository.class);

    /**
     * This method retrieves company details from the database.
     * 
     * @param companyName
     * 
     * 
    
     * @return companyDetails */
    public Company getCompanyDetails(String companyName) {
        logger.info("getCompanyDetails():DaoServices called");
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Company.class);
        @SuppressWarnings("unchecked")
        List<Company> results = crit.list();
        session.getTransaction().commit();
        session.close();
        Company cd = null;
        for (int i = 0; i <= results.size(); i++) {
            cd = (Company) results.get(i);
            if (companyName.equals(cd.getCompanyName())) {
                return cd;
            }
        }
        return cd;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * This method updates the prices in the companydetails table.
     * 
     * @param updatedprices
     * @param ticker
     * 
     */
    public void updateprices(Double updatedprices, String ticker) {
        logger.info("updateprices():DaoServices called");
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        Transaction tx = session.beginTransaction();
        int id;
        id = findId(ticker);
        Company comdet = (Company) session.get(Company.class, id);
        comdet.setStockPrice(updatedprices);
        session.update(comdet);
        tx.commit();
        session.close();
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * This method retrieves Company details.
     * 
     * 
     * 
     
    @return List<Company> */
    public List<Company> getList() {

        logger.info("getList():DaoServices called");
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Company.class);
        @SuppressWarnings("unchecked")
        List<Company> results = crit.list();
        session.getTransaction().commit();
        session.close();
        return results;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * This method finds the id of the given company ticker.
     * 
     * @param ticker
     * 
     * 
    
     * @return id */
    public int findId(String ticker) {
        logger.info("findId():DaoServices called");
        try{
        int id;
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(Company.class);
        crit.add(Restrictions.like("tickerSymbol", ticker));
        @SuppressWarnings("unchecked")
        List<Company> results = crit.list();
        id = results.get(0).getId();
        return id;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

}
