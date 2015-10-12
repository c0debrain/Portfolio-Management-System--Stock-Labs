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

<link rel="stylesheet" type="text/css"
	href="resources/css/portfolio.css" />
<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<script>
jQuery(function(){
    $("#submit").click(function(){
    $(".error").hide();
    var hasError = false;
    var passwordVal = $("#password").val();
    var checkVal = $("#repass").val();
     if (passwordVal != checkVal ) {
        $("#repass").after('<span class="error" style="color:red;">Passwords do not match.</span>');
        hasError = true;
    }
    if(hasError == true) {return false;}
});
});
</script>
<script>
                    
                    <%
                       if(session.getAttribute("username")!=null){
                           %>
                           $(document).ready(function(){
                              
                               $("#logout").attr('style', 'visibility:visible');
                               
                               $("#changepassword").attr('style', 'visibility:visible');
                               
                               $("#changepassword").attr('style', 'color:yellow');
                               $("#logout").attr('style', 'color:yellow');
                           
                         });
                           <%
                       }
                    %>
                   </script>
<%
    if (session.getAttribute("username") == null) {
%><jsp:forward page="index.jsp" />
<%
    }
%>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
		onunload="">
<div id="main">
	<header>
		<h1>Stock-Lab&nbsp;<span id="site_punchline">Track the market....</span></h1>
		<div id="searchbox">
			<form id="search_form" action="search" method="post">
				<span><b>Search:</b></span>&nbsp;<input type="search"
					placeholder="Ticker Symbol/Name" name="query" list="company"
					required> <input type="hidden" name="id"
					value=<%=session.getAttribute("userId")%>>
				<datalist id="company">
					<%
					    List<Company> list = (List) session.getAttribute("companies");
					    String companyname;
					    for (int i = 0; i < list.size(); i++) {
					        companyname = list.get(i).getCompanyName();
					%>
					<option><%=companyname%></option>
						<%
						    }
						%>
					
				</datalist>
				<input type="submit" name="submit" class="button" value="OK" />
			</form>
		</div>
		<div style="position:absolute;left:888px;top:60px;font-size:12px;">
            <span></span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><a
                 id="logout" href="logout"
                style="visibility: hidden;"onMouseOver="this.style.color='red'" onMouseOut="this.style.color='yellow'">logout</a>
              </div>
	</header>
	<form action="stocks" name="portfolioForm" method="post">
		<input type="hidden" name="userId"
			value=<%=(Integer) session.getAttribute("userId")%>>
	</form>
	<span
            style="color: brown; font-size: 13px; position: absolute; left: 100px;"><b>${message}</b></span>
	<nav>
		<a class="normalMenu" href="index">Home</a>
		<a class="normalMenu" href="portfolio"
			onclick="document.portfolioForm.submit();return false">Portfolio</a>
		<a class="normalMenu" href="graph">View Graph</a>
		
		
		<a class="normalMenu" href="updatepassword" style="background-color:red;">Change Password</a>
		<a class="normalMenu" href="about">About Us</a>
	</nav>
	
		<div id="changepass_div">
			<form action="update" method="post">
				<span style="font-size: 20px;"><strong>&nbsp;&nbsp;Update
						Your Password</strong></span> </br> </br> <span><b>&nbsp;&nbsp;Enter Current
						Password:</b></span><input type="password" name="current_password" required>
				<br /><br/>
				<div style="position: relative; left: 30px; top: 5px;">
					<span><b>Enter New Password:</b></span><input type="password"
						name="password" id="password" class="password" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Atleast one UpperCase, LowerCase, Number/SpecialChar and min 8 Chars" required><br /><br/>
					&nbsp;&nbsp;<span><b>Confirm Password:</b></span><input type="password"
						id="repass" name="repass" class="confpass" pattern="(?=^.{8,}$)((?=.*\d)|(?=.*\W+))(?![.\n])(?=.*[A-Z])(?=.*[a-z]).*$" title="Atleast one UpperCase, LowerCase, Number/SpecialChar and min 8 Chars" required> <input
						type="hidden" name="userid"
						value="<%=(Integer) session.getAttribute("userId")%>">
				</div>
				<input style="position: relative; top: 10px; left: 253px;"
					type="submit" class="button" id="submit" value="Reset">

			</form>
		</div>
	
	<footer>
		<span id="footer_text">Copyright
			&copy; Impetus India Pvt. Ltd. All rights reserved. Designed by
			Prerit.</span>
	</footer>
	</body>
</div>
</html>
