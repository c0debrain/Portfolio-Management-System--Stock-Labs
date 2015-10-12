<%@page import="com.impetus.portfolio.domain.Company"%>
<%@ page language="java"  errorPage="exception.jsp" %>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset-UTF-8"
	pageEncoding="UTF-8"%>
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
	href="resources/css/portfolio.css" />

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
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
                               $("#logout").attr('style', 'visibility:visible');
                               $("#createportfolio").attr('style', 'visibility:visible');
                               $("#changepassword").attr('style', 'visibility:visible');
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
			<h1>
				Stock-Lab&nbsp;<span id="site_punchline">Track the
					market....</span>
			</h1>
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
				<div id="reg_fp"
					style="position: absolute; width: 120px; top: 25px;">
					<a href="forgotpass" onMouseOver="this.style.color='white'" onMouseOut="this.style.color='yellow'">Forgot Password?</a> <a href="register"
						style="position: relative; left: 9px;" onMouseOver="this.style.color='white'" onMouseOut="this.style.color='yellow'">Register</a>
				</div>
			</div>
			<div style="position:absolute;left:880px;top:60px;font-size:12px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
                 id="logout" href="logout"
                style="visibility: hidden;"onMouseOver="this.style.color='red'" onMouseOut="this.style.color='yellow'">logout</a>
              </div>
		</header>
		<form action="stocks" name="portfolioForm" method="post">
			<input type="hidden" name="userId"
				value=<%=(Integer) session.getAttribute("userId")%>>
		</form>
		<nav>

			<a class="normalMenu" href="index">Home</a>
			<a class="normalMenu" id="portfolio" href="portfolio"
				style="visibility: hidden; display: none;"  onclick="document.portfolioForm.submit();return false">Portfolio</a> <a
				class="normalMenu" href="graph">View Graph</a>
            
			<a class="normalMenu" href="about" style="background-color:red;">About Us</a>
			
			<a class="normalMenu" id="logout" href="logout"
				style="visibility: hidden;">logout</a>
		</nav>
		
			<div id="text_aboutus_div" >
				<h2 id="text_aboutus_h2"
					>About
					US</h2>
				<p style="position: relative; text-align: center;">
					<b>"The historical origin of coin flipping is the
						interpretation of a chance outcome as the expression of divine
						will."</b> <br />
					<br />Choosing something is like flipping a coin, a head or a
					tail..it might be difficult to choose what would be right.We here
					at stock lab look over both the sides of the coin and help you to
					choose what is better.Stock lab was developed with the idea in mind
					to provide you with best application.
				</p>
			</div>
			<div id="image_coin">
				<footer style="position: absolute; top: 369px; left: -300px;">
					<span id="footer_text">Copyright
						&copy; Impetus India Pvt. Ltd. All rights reserved. Designed by
						Prerit.</span>
				</footer>
			</div>
		</div>	
		</body>
</html>
