package com.impetus.portfolio.xmlfeed;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

/**
 * This is a Singleton class which will create a single JAXBContext instance
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
public class SingletonMarshaller {
    protected SingletonMarshaller() {

    }

    public static final JAXBContext CONTEXT = initContext();

    /**
     * Method initContext.
     * 
    
     * @return JAXBContext */
    private static JAXBContext initContext() {
        try {
            return JAXBContext.newInstance(Wrapper.class);
        } catch (JAXBException j) {
            return null;
        }
    }
}
