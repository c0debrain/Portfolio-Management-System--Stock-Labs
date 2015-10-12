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
				<link rel="stylesheet" type="text/css" href="resources/css/portfolio.css" />
				
				<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
				<script src="resources/js/block.js"></script>
				<link rel="stylesheet" type="text/css" href="http://code.jquery.com/ui/1.9.1/themes/base/jquery-ui.css" />
                <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.9.1/jquery-ui.min.js"></script>
				<!--Javascript-->
					<script type="text/javascript" src="http://www.google.com/jsapi"></script>
					<script type="text/javascript">google.load('visualization', '1', {packages: ['corechart']});</script>
					
					<script src="resources/js/time-picker.js"></script>
				      <script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</script>
					<script>
					
					
					$(document).ready(function(){
     
			               $("#time1").timepicker({timeFormat:"hh:mm:ss",showSecond:true,hourMin:10,hourMax:15});
			               $("#time2").timepicker({timeFormat:"hh:mm:ss",showSecond:true,hourMin:10,hourMax:15});

			           });
					</script>
					<script>
					
						function drawVisualization() {
							// Create and populate the data table.
							var data = google.visualization.arrayToDataTable(${map.string});
							// Create and draw the visualization.
							new google.visualization.LineChart(document.getElementById('visualization')).
							draw(data, {curveType: "line",
								 width: 800, height: 400,
								 vAxis: {maxValue: 20, title:'Stock Price'}, hAxis:{maxValue:20, title:'Intervals'}}
								);
													}
      
						google.setOnLoadCallback(drawVisualization);
					</script>
					<script>
					<%if(session.getAttribute("username")==null){ %>
					      $(document).ready(function(){
					    	    $("#logout").remove();
					    	    $(".spaces").remove();
					    	    
				    	    });
					      <%}%>
					      <%
					      if(session.getAttribute("username")!=null){
	                           %>
	                           $(document).ready(function(){
	                               $("#log").remove();  
	                               $("#reg").remove();
	                               $("#id").remove();
	                               $("#changepassword").attr('style', 'visibility:visible');
	                               $("#portfolio").attr('style', 'visibility:visible');
	                               $("#createportfolio").attr('style', 'visibility:visible');
	                               $("#logout").attr('style', 'visibility:visible');
	                               $("#changepassword").attr('style', 'color:yellow');
	                               $("#logout").attr('style', 'color:yellow');
	                           });
					      <%}%>
					     
					</script>
					
					<script>
					$(document).ready(function(){
						$("#graphtime").hide();
                        $("#graphyear").hide();
                       
						  $("#date").click(function(){
						    $("#graphdate").toggle();
						    $("#graphtime").hide();
						    $("#graphyear").hide();
						  });
						  $("#time").click(function(){
	                            $("#graphtime").toggle();
	                            $("#graphyear").hide();
	                            $("#graphdate").hide();
	                          });
						  $("#year").click(function(){
	                            $("#graphyear").toggle();
	                            $("#graphtime").hide();
	                            $("#graphdate").hide();
	                            });
						});
					
					<%if(session.getAttribute("username")==null){ %>
                    $(document).ready(function(){
                          $("#logout").remove();  
                      
                    });
                    <%}
                 if(session.getAttribute("username")!=null){
                     %>
                     $(document).ready(function(){
                         
                         $("#login").remove();  
                     
                   });
                     <%
                 }
              %>
					</script>
		</head>
	<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
		<div id="main">
		<header>
			<h1>Stock-Lab&nbsp;<span id="site_punchline">Track the market....</span></h1>	
			<div id="searchbox">
			<form id="search_form" action=<%=search%> method="post">
			<span><b>Search:</b></span>
				<input type="search" name="query" list="company" placeholder="Ticker Symbol/Name" required>
				<input type="hidden" name="id" value=<%=session.getAttribute("userId") %>>
				<datalist id="company">
                <%
                List<Company> list = (List)session.getAttribute("companies");
                   String companyname;
                    for (int i=0;i<list.size();i++){ 
                        companyname = list.get(i).getCompanyName();
                    %>
                        <option><%=companyname%></option>
                <%}
                %>
                </datalist>
				<input type ="submit" class="button" id="searchbutton" name="submit" value="OK"/>
			</form>
			</div>
				<div id="login">    
            <form id="login_form" action="login" method="post">
                    <span><b>Username:</b></span><input type="text" name="username"
                        id="username_box" required /> 
                        <span><b>Password:</b></span>
                        <input type="password"name="password" id="password_box" required />
                        <input type="submit" class="button" name="submit" value="Login" id="submit_login" />
                </form>
                <div id="reg_fp" style="position:absolute;width:120px;top:25px;">
                    <a  href="forgotpass" onMouseOver="this.style.color='white'" onMouseOut="this.style.color='yellow'">Forgot Password?</a>
                    <a  href="register" style="position:relative; left:9px;" onMouseOver="this.style.color='white'" onMouseOut="this.style.color='yellow'" >Register</a>
                </div>
         </div>
         <div style="position:absolute;left:880px;top:60px;font-size:12px;">
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
                 id="logout" href="logout"
                style="visibility: hidden;"onMouseOver="this.style.color='red'" onMouseOut="this.style.color='yellow'">logout</a>
              </div>
		</header>
		<form action="stocks" name="portfolioForm" method="post">
                    <input type="hidden" name="userId" value=<%=(Integer) session.getAttribute("userId")%>>
		</form>
		<nav>
			<a class="normalMenu"  href="index">Home</a>
			<a class="normalMenu" id="portfolio"  href="portfolio" onclick="document.portfolioForm.submit();return false" style="visibility:hidden;display:none;">Portfolio</a>
			<a class="normalMenu"  href="graph" style="background-color:red;">View Graph</a>
			
			<a class="normalMenu"  href="about">About Us</a>
			
			
		</nav>
	       <h2 style="position:absolute;top:180px;left:20px;color:maroon;" >Stock Graphs</h2>
		  <div id="graph_forms">
					<div id="divdate">
					   <div id="date">
						<h3 class="button"><b>Date Intervals</b></h3>
					   </div>	
						<form id="graphdate" name="graphdate" action="showgraph" method="post">
							<span><b>Enter Company Name:</b></span><br>
							<input type="text" name="company_name" list="company" required /> <br> 
							<span><b>Date From:</b></span><br>
							<input type="date" name="start_date" id="from" min="2012-11-08"  value="current"  required />
							<br>
					           <script>
					           $("#from").change(function(){
					        	   var date = $("#from").val();
					        	   var parts = date.split("-");
					        	    parts[2]=Number(parts[2])+1;
					        	   if(parts[2]<10){
					        		   parts[2] = "0"+parts[2];
					        	   }
					        	   date = parts[0]+"-"+parts[1]+"-"+parts[2];
					           $("#to").attr('min', date);
					           
					           });
					           </script>
							 <span><b>Date To:</b></span><br>
							<input type="date" name="end_date" id="to"  value="current" min="" required />
							 <br>
							<input type="submit" class="button" name="ok" value="OK" required />
						</form>
					</div>	
					<div id="divtime">	
						<div id="time">
						<h3 class="button"><b>Time Intervals</b></h3>
						</div>
						<form id="graphtime" name="graphtime" action="time" method="post">
							<span><b>Enter Company Name:</b></span><br>
							<input type="text" name="company_name" list="company" required/> 
							<br> 
							<span><b>Date:</b></span>
							<br>
							<input type="date" id="date" name="date" min="2012-11-08"   value="current" required />
							 
							<br> 
							<span><b>Time From:</b></span>
							<br>
							<input type="text" id="time1" class="time" name="start_time" required/>
							<br>
							 
							<span><b>Time To :</b></span><br>
							<input type="text" id="time2" name="end_time" class="time" required/>
							
							<br>
							<input type="submit" class="button" name="ok" value="OK"  />
						</form>
					</div>
                
					<div id="divyear">
						<div id="year">
						  <h3 class="button"><b>Yealy</b></h3>
						</div>
						<form id="graphyear" action="yearly" name="year" method="post">
							<span><b>Enter Company Name:</b></span><br>
							<input type="text" name="company_name" list="company"  required/> <br> 
							<span><b>Enter the Year:</b></span>
							<br>
							<select name="year">
								<%
									for (int i = 1990; i <= 2012; i++) {
								%>
								<option><%=i%></option>
								<%
									}
								%>
							</select> 
							<br>
							<input type="submit" class="button" name="ok" value="OK" />
						</form>
					</div>
				</div>
				    
					<div id="visualization"><b><span id="message">No Data Available!!!</span></b>
					</div>
					<span style="font-size:26px;position:absolute;top:490px;left:100px;color:maroon;">${map.Name}</span>
		
		<footer>
			<span id="footer_text">Copyright &copy; Impetus India Pvt. Ltd. All rights reserved.
			Designed by Prerit.</span>
		</footer>
    </div>
    </body>
</html>