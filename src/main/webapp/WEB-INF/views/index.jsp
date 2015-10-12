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

<%
    String search;
    	if(session.getAttribute("userId")==null){ 
    		search="searchonly";}
    		else{
    				search="search";}
%>
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
	function noBack() {
		window.history.forward();
	}
</script>
<script>

<%if(session.getAttribute("username")==null){%>
	$(document).ready(function() {
		$("#login").attr('style', 'visibility:visible');
		$("#logout").remove();
		$("#regtext").attr('style', 'visibility:visible');
		
		$(".spaces").remove();
		$("#id").remove();
	});
<%}
                       if(session.getAttribute("username")!=null){%>
	$(document).ready(function() {

		$("#login").remove();
		$("#logout").attr('style', 'visibility:visible');
		$("#updateprofile").attr('style', 'visibility:visible');
		$("#updateprofile").attr('style', 'color:yellow');
		$("#logout").attr('style', 'color:yellow');
		$("#createportfolio").attr('style', 'visibility:visible');
		$("#portfolio").attr('style', 'visibility:visible');
		

	});
<%}%>
	
</script>

</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">

	<div id="main">

		<header>
			<h1>
				Stock-Lab&nbsp;<span id="site_punchline">Track the market....</span>
			</h1>
            
			<div id="searchbox">
				<form id="search_form" action=<%=search%> method="post">
					<span><b>Search:</b></span> <input type="search" name="query"
						list="company" placeholder="Ticker Symbol/Name" required>
					<input type="hidden" name="id"
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
					<input type="submit" class="button" id="searchbutton" name="submit"
						value="OK" />
				</form>
			</div>
			<div id="login" style="visibility: hidden;">
				<form id="login_form" action="login" method="post">
					<span><b>Username:</b></span><input type="text" name="username"
						id="username_box" required /> <span><b>Password:</b></span> <input
						type="password" name="password" id="password_box" required /> <input
						type="submit" class="button" name="submit" value="Login"
						id="submit_login" />
				</form>
				<div id="reg_fp">
					<a href="forgotpass" style="position:relative;top:3px;" onMouseOver="this.style.color='white'" onMouseOut="this.style.color='yellow'">Forgot Password?</a>
				</div>
			</div>
			<div style="position:absolute;left:800px;top:60px;font-size:12px;">
			<a 
                href="updateprofile" id="updateprofile"
                style="visibility: hidden; display: none;color:yellow;"onMouseOver="this.style.color='red'" onMouseOut="this.style.color='yellow'" onclick="document.updateprofile.submit();return false">Update Profile</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
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
			
		<span id="session"
			style="color: brown; font-size: 20px; position: absolute; left: 20px;"><b>${welcomemessage}</b></span>
		<h4 id="exception_message">${exception.message}</h4>
		<form action="updateprofile" name="updateprofile" method="post"><input type="hidden" name="userId"
                value=<%=(Integer) session.getAttribute("userId")%>>
        </form>
		<nav>

			<a class="normalMenu" href="index" style="background-color:red;">Home</a> <a class="normalMenu"
				id="portfolio" href="portfolio"
				style="visibility: hidden; display: none;"
				onclick="document.portfolioForm.submit();return false">Portfolio</a>
			<a class="normalMenu" href="graph">View Graph</a>&nbsp;<a
                class="normalMenu" id="createportfolio" href="viewportfolio"
                style="visibility: hidden; display: none;">Create Portfolio</a><span class="spaces">&nbsp;</span><a
                class="normalMenu" href="about">About Us</a> 
		</nav>
		<div style="position: absolute;">
			<span id="index_text_1"><b>We make Investing in Stock
					Market simple and easy for you....</b></span>
			<div id="index_image"></div>

			<div id="regtext" style="visibility: hidden">
				<a href="register" id="register"><b> <span
						style="position: absolute; bottom: 6px;">Register Here!</span>
				</b></a>
			</div>
			<div id="feeds"
				>
				<script language="javascript"
					src="http://www.generateit.net/rss-javascript/feed2js.php?src=http%3A%2F%2Frss.cnn.com%2Frss%2Fedition_business.rss&amp;chan=y&amp;num=5&amp;desc=1&amp;tz=+5:30&amp;targ=y"
					type="text/javascript"></script>

				<noscript>
					<a
						href="http://www.generateit.net/rss-javascript/feed2js.php?src=http%3A%2F%2Frss.cnn.com%2Frss%2Fedition_business.rss&amp;chan=y&amp;num=5&amp;desc=1&amp;tz=+5:30&amp;targ=y&amp;html=y">View
						RSS feed</a>
				</noscript>


			</div>
			<div id="index_text_2"
				>
				<b>User Review</b>
			</div>
			<div id="index_text_3"
				>
				<span style="height: 20px;"><b>"Your online portfolio is
						just amazing. It's very much useful in tracking one's investments
						at a single glance."</b></span>
			</div>
			<div id="index_text_4"
				>
				<b>What will you find here..</b>
			</div>
			<div
				id="index_text_5">
				<span>Tracks all your investments,assets through LIVE price
					updates. Get a consolidated view of your multiple stock. Know your
					best and worst investments at a glance and much more...</span>
			</div>
			<marquee behavior="scroll" direction="left"
				>
				<%
			    List<Company> listMarq = (List) session.getAttribute("companies");
			    String companynameMarq;
			    Double price;
			    for (int i = 0; i < list.size(); i++) {
			        companynameMarq = list.get(i).getCompanyName();
			        price = list.get(i).getStockPrice();
			%>
				<span style="color: green"><b><%=companynameMarq%></b></span>:<span><b><%=price%></b>&nbsp;&nbsp;&nbsp;</span>
				<%
			    }
			%>
			</marquee>

			<footer style="position: absolute; top: 530px">
				<span id="footer_text">Copyright &copy; Impetus India Pvt.
					Ltd. All rights reserved. Designed by Prerit.</span>
			</footer>
		</div>
	</div>
</body>
</html>
