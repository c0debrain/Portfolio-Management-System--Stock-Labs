<%@ page language="java"  errorPage="exception.jsp" %>
<%@page import="com.impetus.portfolio.domain.Company"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
    <html>
        <title>Stock-Lab</title>
        <head>
            <link rel="stylesheet" type="text/css" href="resources/css/portfolio.css" />
               
               <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
               
               <link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
                <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
        </head>
        <body>
        <div id="main">
        		 <header>
            <h1>Stock-Lab&nbsp;<span id="site_punchline">Track the market....</span></h1>
            
        </header>
        	
			<h3 id="exception_message">Oops! An Internal Error Occured! Please Try again after some time.</h3>
			<a class="button" href="index" id="exception_back_button">Back</a>
		            
        
        
        <footer>
            <span id="footer_text">Copyright &copy; Impetus India Pvt. Ltd. All rights reserved. Designed by Prerit.</span>
        </footer>
        </div>	
        </body>
</html>
            
    
