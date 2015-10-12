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
<% String search;
            	if(session.getAttribute("userId")==null){ 
            		search="searchonly";}
            		else{
            				search="search";}%>
<!-- css -->
<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<link rel="stylesheet" type="text/css"
	href="resources/css/portfolio.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
<script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
<script>
           
           
                    <%if(session.getAttribute("username")==null){ %>
                          $(document).ready(function(){
                                $("#logout").remove();
                                $("#id").remove();
                                $(".spaces").remove();
                            
                          });
                          <%}
                       if(session.getAttribute("username")!=null){
                           %>
                           $(document).ready(function(){
                               
                               $("#login").remove();
                               $("#createportfolio").attr('style', 'visibility:visible');
                               $("#portfolio").attr('style', 'visibility:visible');
                               $("#changepassword").attr('style', 'color:yellow');
                               $("#logout").attr('style', 'color:yellow');
                           
                         });
                           <%
                       }
                    %>
                   </script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<div id="main">

		<header>
			<h1>Stock-Lab&nbsp;<span id="site_punchline">Track the market....</span></h1>
			<div id="searchbox">
				<form id="search_form" action=<%=search%> method="post">
					<span><b>Search:</b></span> <input type="search" name="query"
						list="company" placeholder="Ticker Symbol/Name" required>
					<input type="hidden" name="id"
						value=<%=session.getAttribute("userId") %>>
					<datalist id="company">
						<%List<Company> list = (List)session.getAttribute("companies");
                   String companyname;
                    for (int i=0;i<list.size();i++){ 
                        companyname = list.get(i).getCompanyName();
                    %>
						<option><%=companyname%></option>
							<%}%>
						
					</datalist>
					<input type="submit" class="button" id="searchbutton" name="submit"
						value="OK" />
				</form>
			</div>
			<div id="login">
				<form id="login_form" action="login" method="post">
					<span><b>Username:</b></span><input type="text" name="username"
						id="username_box" required /> <span><b>Password:</b></span> <input
						type="password" name="password" id="password_box" required /> <input
						type="submit" class="button" name="submit" value="Login"
						id="submit_login" />
				</form>
			</div>
		</header>
		<form action="stocks" name="portfolioForm" method="post">
			<input type="hidden" name="userId"
				value=<%=(Integer) session.getAttribute("userId")%>>
		</form>
		<nav>
			<a class="normalMenu" href="index">Home</a>
			<a
				class="normalMenu" href="graph">View Graph</a>
			<a class="normalMenu" href="about">About Us</a>
			

		</nav>

        <h2 style="position:absolute;top:180px;left:20px;color:maroon;" >Forgot Password</h2>
		<div id="forgotpassword_form_div">
			<span style="font-size: 20px;color:brown;">Don't
				worry we are here to help ! Please Enter your email..</span><br/><br/><br/>
			<form action="sendUserInfo" method="post" name="forgotpass_form">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span><b>Email:</b></span> <input type="email" name="email" required />
				<input type="submit" class="button" value="OK">
			</form>
			

		</div>

		<footer>
			<span id="footer_text">Copyright
				&copy; Impetus India Pvt. Ltd. All rights reserved. Designed by
				Prerit.</span>
		</footer>
	</div>
</body>
</html>
