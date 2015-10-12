package com.impetus.portfolio.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.impetus.portfolio.domain.Company;
import com.impetus.portfolio.domain.Portfolio;
import com.impetus.portfolio.domain.User;
import com.impetus.portfolio.exceptions.BusinessException;

/**
 * User Repository contains the DAO layer service for retrieving values about
 * the user.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Component
@Repository
public class UserRepository {
    /**
     *
     */
    private static Logger logger = Logger.getLogger(UserRepository.class);
    public static final int HUNDRED = 100;

    /**
     * This method retrieves user object.
     * 
     * @param username
     * 
     * 
    
    
     * @return User * @throws SystemException * @throws
     *         IndexOutOfBoundsException * @throws BusinessException * @throws BusinessException
     */
    public User getUser(String username) throws BusinessException {
        logger.info("getUser():DaoServices called");
        try{
        User user;
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();

        Criteria crit = session.createCriteria(User.class);

        @SuppressWarnings("unchecked")
        List<User> results = crit.list();
        user = null;
        for (int i = 0; i < results.size(); i++) {
            user = (User) results.get(i);

            if (username.equals(user.getUserName())) {
                return user;
            }
        }

        return user;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
        }

    /**
     * This method adds user to database.
     * 
     * @param user
     * 
    
    
     * @return String * @throws BusinessException */
    public String addUser(User user) throws BusinessException {

        logger.info("addUser():DaoServices called");
        try {
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        
            session.save(user);

            session.getTransaction().commit();
            session.close();
            return "You Have been registered";
        } catch (ConstraintViolationException cve) {
            logger.fatal(cve);
            return "false";
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * This method retrieves user from the database.
     * 
     * @param email
     * 
     * 
    
    
     * @return User * @throws IndexOutOfBoundsException * @throws BusinessException * @throws BusinessException
     */
    public User getByEmail(String email) throws BusinessException {
        logger.info("getEmail():DaoServices called");
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        Criteria crit = session.createCriteria(User.class);
        @SuppressWarnings("unchecked")
        List<User> results = crit.list();
        session.getTransaction().commit();
        session.close();

        User user = null;

        for (int i = 0; i < results.size(); i++) {

            user = (User) results.get(i);

            if (email.equals(user.getEmail())) {
                return user;
            }

        }

        return null;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * This method retrieves email from the database.
     * 
     * 
     * 
    
     * @return ArrayList */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<String> priceExceedGetEmail() {
        logger.info("priceExceedGetEmail():DaoServices called");
        try{
        Double newprice;
        Double oldprice;
        Double variance;
        Double uservariance;
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();

        session.beginTransaction();
        Criteria crit1 = session.createCriteria(Company.class);
        Criteria crit2 = session.createCriteria(Portfolio.class);
        Criteria crit3 = session.createCriteria(User.class);
        List<Company> list1 = crit1.list();
        List<Portfolio> list2 = crit2.list();
        List<User> list3 = crit3.list();

        List<String> alist = new ArrayList<String>();
        for (int k = 0; k < list3.size(); k++) {
            for (int i = 0; i < list2.size(); i++) {
                for (int j = 0; j < list1.size(); j++) {
                    if (list1.get(j).getCompanyName()
                            .equals(list2.get(i).getCompanyName())&&list3.get(k).getId()==(list2.get(i).getUserid())) {
                        newprice = list1.get(j).getStockPrice();
                        oldprice = list2.get(i).getStockPrice();
                        uservariance = list2.get(i).getVariance();
                        variance = ((newprice - oldprice) / oldprice) * HUNDRED;
                       
                        if (Math.abs(variance) > uservariance) {
                            alist.add(list3.get(k).getEmail());
                           
                        } 
                    }
                }
            }
        }
        session.close();
        return alist;
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }

    /**
     * method updates the user password.
     * 
     * @param cpassword
     *            String
     * @param password
     *            String
     * @param id
     *            int
    
     * @return String */
    public String update(String cpassword, String password, int id) {
        try{
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        String isUpdated = "false";
        Transaction tx = session.beginTransaction();
        session.beginTransaction();
        User user = (User) session.get(User.class, id);
        if (cpassword.equals(user.getPassword())) {
            user.setPassword(password);
            session.update(user);
            isUpdated = "true";
        }
        tx.commit();
        session.close();
        return isUpdated;
    
    }catch(DataAccessException he){
        logger.fatal(he.getCause()+""+he);
        throw he;
    }
}
    /**
     * This method updates the user profile.
     * @param name
     * @param dob
     * @param address
     * @param country
     * @param mobile
     * @param city
     * @param id
    
     * @return String */
    public String updateProfile(String name,Date dob,String address,String country,long mobile,String city,int id){
        try{
            SessionFactory sf = HibernateUtils.getSessionFactory();
            Session session = sf.openSession();
            String isUpdated = "false";
            Transaction tx = session.beginTransaction();
            session.beginTransaction();
            User user = (User) session.get(User.class, id);
            
                user.setName(name);
                user.setDob(dob);
                user.setAddress(address);
                user.setCountry(country);
                user.setMobile(mobile);
                user.setCity(city);
                session.update(user);
                isUpdated = "true";
           
            tx.commit();
            session.close();
            return isUpdated;
        
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }
    /**
     * This method retrieves user information using user id.
     * @param userid
    
     * @return User */
    public User getByUserId(int userid){
        logger.info("getUser():DaoServices called");
        try{
            
        SessionFactory sf = HibernateUtils.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        return (User) session.get(User.class, userid);
        
        }catch(DataAccessException he){
            logger.fatal(he.getCause()+""+he);
            throw he;
        }
    }
    }
