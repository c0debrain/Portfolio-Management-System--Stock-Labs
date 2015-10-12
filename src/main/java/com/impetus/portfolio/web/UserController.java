package com.impetus.portfolio.web;

import java.sql.Date;

import org.apache.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.impetus.portfolio.domain.User;
import com.impetus.portfolio.exceptions.BusinessException;
import com.impetus.portfolio.exceptions.SystemException;

import com.impetus.portfolio.mailing.Mail;
import com.impetus.portfolio.serviceImpl.UserService;

/**
 * This Controller renders the view for login,adding stocks, forgot password and
 * logout.
 * 
 * @author Prerit
 * 
 * @version $Revision: 1.0 $
 */
@Controller
public class UserController {

    private static final Logger LOGGER = Logger.getLogger(UserController.class);
    private String index = "/index";
    private static final String MESSAGE = "message";

    @Autowired
    private UserService us;

    /**
     * This method renders the view for adding the stocks.
     * 
     * 
     * 
    
     * @return view */
    @RequestMapping(value = "/addstock", method = RequestMethod.GET)
    public ModelAndView showpage() {
        LOGGER.info("Now stock can be added...");
        return new ModelAndView("addstock");
    }

    /**
     * This method renders sites main page and authenticates the user.
     * 
     * @param username
     * @param password
     * @param session
     * 
     * 
    
     * @throws SystemException
     * @return view * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView handleLogin(String username, String password,
            HttpSession session) throws BusinessException, SystemException {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(
                User.class);

        User user = ctx.getBean(User.class);
        LOGGER.info("User has to authenticate.....");

        user = us.retrieveUser(username);

        LOGGER.debug(user);
        String dbuserName = user.getUserName();
        String dbPassword = user.getPassword();
        if (username.equals(dbuserName) && password.equals(dbPassword)) {
            session.setAttribute("username", username);
            session.setAttribute("userId", user.getId());
            LOGGER.info("User Authenticated.");
            String message = "Welcome, " + user.getName();
            return new ModelAndView(index, "welcomemessage", message);
        } else {
            LOGGER.error("failed while retrieving user details.");
            throw new BusinessException("User Does not Exists!");
        }

    }

    /**
     * This Method will renders the view for forgot password.
     * 
     * 
     * 
    
     * @return view */
    @RequestMapping("/forgotpass")
    public ModelAndView getForgotPass() {
        LOGGER.info("Getting the details from the user....");
        return new ModelAndView("/forgotpass");
    }

    /**
     * This method sends email to the user.
     * 
     * @param email
     * 
     * 
    
     * @throws SystemException
     * @return view * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "/sendUserInfo", method = RequestMethod.POST)
    public ModelAndView sendEmail(String email) throws BusinessException,
            SystemException {
        LOGGER.info("Going back to home page....");
        String message;
        User user2 = us.getUserByEmail(email);
        
        if (user2 == null) {
            LOGGER.error("failed while retrieving user by email.");
            throw new BusinessException("Email not found!!");
        } else {
            message = "Mail has been sent to your inbox.";
        }
        LOGGER.debug(user2);
        Mail mail = new Mail(user2.getEmail(), user2.getPassword(),
                user2.getUserName());
        
        mail.mailTo(user2.getEmail());

        return new ModelAndView(index, MESSAGE, message);

    }

    /**
     * This method renders the login page if user wants to go back from forgot
     * password page.
     * 
     * 
     * 
    
     * @return view */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView loginpage() {
        LOGGER.info("login page called.");
        return new ModelAndView(index);
    }

    /**
     * This method terminates the session
     * 
     * @param session
     * @param request
     * 
     * 
    
     * @return view */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session, HttpServletRequest request) {

        request.getSession();
        session.setAttribute("username", null);
        session.setAttribute("userId", null);
        LOGGER.info("logout() called");
        return new ModelAndView(index);
    }

    /**
     * This method renders the view for registration form Method getRegister.
     * 
     * 
    
     * @return ModelAndView */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView getRegister() {
        return new ModelAndView("register");
    }

    /**
     * This method calls the method for user registration. Method register.
     * 
     * @param user
     *            User
     * 
     * 
    
     * @throws SystemException
     * @return ModelAndView * @throws BusinessException * @throws BusinessException
     * @throws SystemException
     */
    @RequestMapping(value = "index", method = RequestMethod.POST)
    public ModelAndView register(@ModelAttribute("register") User user)
            throws BusinessException, SystemException {
        String message;
        String response;
        LOGGER.info("User wants to register......");
        LOGGER.debug(us);
        response = us.insertUser(user);
        if (response.equals("false")) {
            message = "User already Exists.Please provide different username. ";
            return new ModelAndView("register", MESSAGE, message);
        }
        LOGGER.info("User has registered.....");
        return new ModelAndView(index, MESSAGE, response);

    }

    /**
     * This method renders the View for update Password form. Method
     * getupdateform.
     * 
     * 
    
     * @return ModelAndView */
    @RequestMapping(value = "/updatepassword", method = RequestMethod.GET)
    public ModelAndView getupdateform() {
        return new ModelAndView("/updatepassword");
    }

    /**
     * Calls the function for password updation. Method changePassword.
     * 
     * @param cpassword
     *            String
     * @param password
     *            String
     * @param userid
     *            int
     * 
     * 
    
     * @return ModelAndView * @throws SystemException * @throws SystemException
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ModelAndView changePassword(
            @RequestParam(value = "current_password") String cpassword,
            @RequestParam String password, @RequestParam int userid)
            throws SystemException {
        String isUpdated = us.updatePassword(cpassword, password, userid);
        String message = "Your Password was not changed! Please try again";
        if (isUpdated.equals("true")) {
            message = "Your Password has been changed.";
        } else {
            message = "Your Password was not changed.";
            return new ModelAndView("updatepassword", MESSAGE, message);
        }
        return new ModelAndView(index, MESSAGE, message);
    }
    /**
     * This method returns update profile view.
    
     * @param userId int
     * @return ModelAndView * @throws SystemException
     */
    @RequestMapping(value = "/updateprofile", method = RequestMethod.POST)
    public ModelAndView getprofileupdateform(@RequestParam int userId)throws SystemException {
        User user = us.retrieveUserById(userId);
        return new ModelAndView("/updateprofile","user",user);
    }
    /**
     * This method takes user information to update and render view.
     * @param name
     * @param dob
     * @param address
     * @param country
     * @param mobile
     * @param city
    
    
     * @param userid int
     * @return ModelAndView * @throws SystemException */
   @RequestMapping(value="/profile",method = RequestMethod.POST)
   public ModelAndView updateProfile(@RequestParam(value="name")String name,@RequestParam(value="dob")Date dob,
           @RequestParam(value="address")String address,
           @RequestParam(value="country")String country,
           @RequestParam(value="mobile")long mobile,@RequestParam(value="city")String city,@RequestParam int userid)throws SystemException{
       
       String isUpdated = us.updateProfile(name,dob,address,country,mobile,city,userid);
       String message = "Your Profile was not changed! Please try again";
       if (isUpdated.equals("true")) {
           message = "Your Profile info has been changed.";
       } else {
           message = "Your Profile info was not changed.";
           return new ModelAndView("updateprofile", MESSAGE, message);
       }
       return new ModelAndView(index, MESSAGE, message);
   }

}
