<%@page import="com.impetus.portfolio.domain.Company"%>
<%@ page language="java"  errorPage="exception.jsp" %>
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
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

<link rel="stylesheet" type="text/css"
	href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
<script
	src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
<%
    String search;
    	if(session.getAttribute("userId")==null){ 
    		search="searchonly";}
    		else{
    				search="search";}
%>
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
	}
</script>
<script>
	
<%if(session.getAttribute("username")==null){%>
	$(document).ready(function() {
		$("#logout").remove();
		$("#values").remove();
		$("#id").remove();
		$(".spaces").remove();
	});
<%}%>
	
<%if(session.getAttribute("username")!=null){%>
	$(document).ready(function() {

		$("#login").remove();
		$("#createportfolio").attr('style', 'visibility:visible');
		$("#portfolio").attr('style', 'visibility:visible');
		$("#logout").attr('style', 'visibility:visible');
		$("#changepassword").attr('style', 'visibility:visible');
		$("#changepassword").attr('style', 'color:yellow');
        $("#logout").attr('style', 'color:yellow');

	});
<%}%>
	
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
			<div id="login">
				<form id="login_form" action="login" method="post">
					<span><b>Username:</b></span><input type="text" name="username"
						id="username_box" required /> <span><b>Password:</b></span> <input
						type="password" name="password" id="password_box" required /> <input
						type="submit" class="button" name="submit" value="Login"
						id="submit_login" />
				</form>
				<div id="reg_fp" style="position:absolute;width:120px;top:25px;">
					<a href="forgotpass">Forgot Password?</a> <a href="register"
						style="position: relative; left: 8px;">Register</a>
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
				onclick="document.portfolioForm.submit();return false"
				style="visibility: hidden; display: none;">Portfolio</a> <a
				class="normalMenu" href="graph">View Graph</a>
			 <a
				class="normalMenu" href="about">About Us</a>
			
		</nav>
		<span id="server_response_message"
			>${message}</span>
		<h2 style="position:relative;bottom:200px;left:20px;color:maroon;" >Add Stock</h2>	
		<%
		    long time = System.currentTimeMillis();
		    java.sql.Date date = new java.sql.Date(time);
		%>
		<div id="portfolioform">


			<div id="portfolioform_border"
				>
				<form id="add" name="add_port" action="addedstock" method="post">

					<table cellspacing="20">
						<tr>
							<td><span style="position: relative; top: -18px;"><b>Company
										Name:</b><span style="font-size: 40px;">
										${companydet.companyname}</span></span></td>

							<br />
							<br />
							<td><span><b>StockPrice</b> <i>(Rs)</i>:<span
									style="font-size: 20px;"> ${companydet.stockprice}</span></span> <input
								type="hidden" name="companyName"
								value="${companydet.companyname}"> <input type="hidden"
								name="stockPrice" value="${companydet.stockprice}" required>
								<input type="hidden" name="tickerSymbol"
								value="${companydet.tickersymbol}"> <input type="hidden"
								id="id" name="userid"
								value=<%=(Integer) session.getAttribute("userId")%> required>
								<input type="hidden" name="date" value=<%=date%>> <br>
								<br></td>
							<td>
								<div id="values">
									<span><b> Stock alert variance:</b></span> <input
										id="variance" type="number" name="variance" min="-100"
										max="100" step="0.1" required />% <br /> <br /> <span><b>
											Portfolio Name:</b></span><br/> <select id="portfolioName"
										name="portfolioName" required>
										<c:forEach var="portfoliolist" items="${companydet.portname}">
											<option>${portfoliolist.getPortName()}</option>
										</c:forEach>
									</select> <br /> <br /> <input type="submit"   class="button" name="Add"
										value="Add"> <br /> <br />

								</div>
							</td>
						</tr>
					</table>
				</form>
				
			</div>
		</div>
		<div id="text_rules">
			<span><b>Rules to Trade By</b></span>
			<ul style="color: purple;">
				<li>
					<p>
						<i>Diversify</i> <br /> <span class="info_rules"> You
							know the saying "don't put all of your eggs in one basket". This
							rule applies heavily to investing. You want to invest in
							different sectors, different capitals (Large caps, small caps,
							etc.), and in different countries. A good plan is to find the top
							performing companies in each sector, country, and capital to
							track in a mock portfolio. Diversifying is important because at
							any given time a sector, or country can take a substantial hit
							that could severely damage your portfolio. An extreme example
							would be if you only owned oil companies and the world ran out of
							oil. If America is in a recession then focus in thriving
							countries around the world. Many companies from other countries
							are traded publicly in U.S. exchanges. You most certainly don't
							need one stock in every sector and/or country, but a decent mix
							will prove to be beneficial to your portfolio's value. </span>
					</p>
				</li>
				<li>
					<p>
						<i>Think Long Term</i> <br /> <span class="info_rules" >
							If you are investing in stocks to become instantly rich, then you
							will soon learn that this is not an easy task. Even the best
							company's stock can suffer during a recession. Investing long
							term will help to balance out the bad times with the good times.
							Don't buy a stock because it will go up today, buy a stock
							because it will succeed over the next 10 years. If you think long
							term when making your selections, you are already ahead of most
							beginning investors. </span>
					</p>
				</li>
				<li>
					<p>
						<i>Start with Large "Blue-Chip" Companies</i> <br /> <span class="info_rules"
							>The smaller the price the more
							volatile. If the price of a stock is low, that doesn't mean it's
							cheap, it means it's risky. Start with larger companies whose
							price is usually around $40-100 a share. There are exceptions to
							every rule and there are many small cap stocks that will
							skyrocket. But as you are beginning to learn the stock market,
							stick with the more established and hopefully stable companies. </span>
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
