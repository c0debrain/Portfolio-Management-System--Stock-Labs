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

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="resources/css/portfolio.css" />
<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>

<%
    if (session.getAttribute("username") == null) {
%><jsp:forward page="index.jsp" />
<%
    }
%>
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
				<form id="search_form" action="search" method="post">
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
		<span
			style="color: brown; font-size: 13px; position: relative; left: 100px;">${message}</span>
		<nav>
			<a class="normalMenu" href="index">Home</a> <a class="normalMenu"
				href="portfolio"
				onclick="document.portfolioForm.submit();return false">Portfolio</a>
			<a class="normalMenu" href="graph">View Graph</a>
			 <a class="normalMenu" href="viewportfolio" style="background-color:red;" >Create Portfolio</a>
			 <a class="normalMenu" href="about">About Us</a> 
		</nav>
        <h2 style="position:absolute;top:180px;left:20px;color:maroon;" >Create Portfolio</h2>
		<table style="position: absolute; top: 200px; ">
			<tr>
				<td
					style="position: relative; left: 400px; top: 150px; float: right;">
					<span
					style="font-size: 19px; color: brown; position: relative; top: -30px; right: 170px;">Would
						you like to create a new portfolio??</span>
					<form action="addportfolio" method="post" id="portform"
						commandName="userportfolio">
						<input type="hidden" name="userId"
							value=<%=(Integer) session.getAttribute("userId")%>>
						<div style="position: relative; right: 165px;">
							<b>Portfolio Name:</b><input type="text"
								name="portName" maxlength="45" required>
								<b>Details:</b><textarea style="position:relative;top:25px;" rows="4" cols="20" id="details"
                                name="details" maxlength="255" required></textarea>
								 <input type="submit" 
								class="button" id="createportfoliosubmit"
								value="Create Portfolio">
						</div>
					</form>
					
				</td>
			</tr>

		</table>
		<div id="text_rules">
			<span><b>Important Points in Managing a Stock Portfolio</b></span>
			<ul style="color: purple;">
				<li>
					<p>
						<i>Learn How to Read Financial Statements</i> <br /> <span
							class="info_rules"> A very important part of
							researching companies to invest in is to read their financial
							statements. This seems like a long and boring task when all you
							want to do is get in the action and make some trades, but is
							necessary to become a successful investor. The U.S. Securities
							and Exchange Commission (SEC) provides a lot of information on
							it's website to beginning investors. </span>
					</p>
				</li>
				<li>
					<p>
						<i>Track Company News &amp; Earnings Reports</i> <br /> <span
							class="info_rules"> As a parent is concerned with
							his/her children's report card grades, an investor should be as
							concerned and interested in company news and earnings reports of
							stocks they hold. A piece of good news or bad news can change
							your entire investment within minutes.Much like reading financial
							statements, learning what to make of earnings reports is not easy
							but very important. </span>
					</p>
				</li>
				<li>
					<p>
						<i>Set Goals</i> <br /> <span class="info_rules">Most
							investors don't enter the market for pure fun. Whether you're
							investing for your children's college fund or a new set of golf
							clubs write out your goals. If you are constantly reminded of why
							you are investing it will help keep your thoughts focused on your
							goals. It is good to set monthly and yearly goals. </span>
					</p>
				</li>
			</ul>
		</div>
		<footer>
			<span id="footer_text">Copyright
				&copy; Impetus India Pvt. Ltd. All rights reserved. Designed by
				Prerit.</span>
		</footer>
	</div>
</body>
</html>
