package com.impetus.portfolio.Repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 * This is a singleton class which will create sesssionFactory object.
 * 
 * @author prerit
 * 
 * @version $Revision: 1.0 $
 */
@SuppressWarnings("deprecation")
public final class HibernateUtils {
    /**
     *
     */
    private HibernateUtils() {
    }

    private static final SessionFactory SESSIONFACTORY;
    static {

        SESSIONFACTORY = new AnnotationConfiguration().configure()
                .buildSessionFactory();

    }

    /**
     * Method returns a SessionFactory object.
     * 
    
     * @return sessionFactory */
    public static SessionFactory getSessionFactory() {
        return SESSIONFACTORY;
    }
}
