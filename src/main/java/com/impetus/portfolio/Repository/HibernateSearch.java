package com.impetus.portfolio.Repository;

import java.util.List;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.SearchException;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.core.NestedRuntimeException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.exceptions.SystemException;

/**
 * This class has the methods which creates Indexes and searching using lucene
 * API.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
@Repository
public class HibernateSearch {
   
    private Logger logger = Logger.getLogger(HibernateSearch.class);

    /**
     * This method will do the index which will lead to faster search.
     */
    private static void doIndex() {
        Logger log = Logger.getLogger("doIndex");
        log.setLevel(Level.DEBUG);
        try {

            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            FullTextSession fullTextSession = Search
                    .getFullTextSession(session);
            fullTextSession.createIndexer().startAndWait();
            fullTextSession.close();
        } catch (InterruptedException e) {
            log.debug(e);
        }
    }

    /**
     * This method searches words and full text from the database.....
     * 
     * @param queryString
     * 
     * 
     * 
    
    
     * @return CompanyList * @throws IndexOutOfBoundsException * @throws
     *         SearchException * @throws IndexOutOfBoundsException * @throws SearchException */
    private static List<Company> search(final String queryString) {
        try{
             
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        FullTextSession fullTextSession = Search.getFullTextSession(session);
        QueryBuilder queryBuilder = fullTextSession.getSearchFactory()
                .buildQueryBuilder().forEntity(Company.class).get();
        org.apache.lucene.search.Query luceneQuery = queryBuilder.keyword()
                .onFields("companyName", "tickerSymbol").ignoreFieldBridge()
                .matching(queryString).createQuery();
        org.hibernate.Query fullTextQuery = fullTextSession
                .createFullTextQuery(luceneQuery, Company.class);

        @SuppressWarnings("unchecked")
        List<Company> companyList = fullTextQuery.list();

        fullTextSession.close();
        return companyList;
        }catch(DataAccessException he){
            Logger log = Logger.getLogger("search");
            log.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * hSearch contains the calls to doIndex() and search()
     * 
     * @param query
     * 
     * 
    
    
     * @return List<Company> * @throws SystemException * @throws SystemException
     */
    public final List<Company> hSearch(final String query)throws SystemException {
        logger.setLevel(Level.INFO);
        logger.info("Index creation started.....");
        doIndex();
        logger.info("index Created....");
        try {
            logger.info("Searching started......");

            return search(query);
        } catch (SearchException se) {
            logger.fatal(se.getCause()+""+se);
            return null;
        }catch(NestedRuntimeException nre){
            logger.fatal(nre.getCause()+""+nre);
            throw new SystemException("Data could not be Accessed..",nre);
        }
    }
}
