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

                  <script type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
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
<script>
$("#deleteportfolio").live("click",function(event){
    //event.stopPropagation();
    if(confirm("Do you want to Delete this Stock?")) {
     return true;
    }
    else
    {  
        return false;
    }       
  
 });

</script>
                
		  <%
  if (session.getAttribute("username") == null)
  {
    %><jsp:forward page="index.jsp" />
    <%}%>
		</head>
		<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
		<div id="main">
		<header>
			<h1>Stock-Lab&nbsp;<span id="site_punchline">Track the market....</span></h1>
			<div id="searchbox">
			<form id="search_form" action="search" method="post">
				<span><b>Search:</b></span>
				<input type="search" name="query" placeholder="Ticker Symbol/Name" list="company" required>
				<input type="hidden" name="id" value=<%=session.getAttribute("userId") %>>
				<datalist id="company">
                <%List<Company> list = (List)session.getAttribute("companies");
                   String companyname;
                    for (int i=0;i<list.size();i++){ 
                        companyname = list.get(i).getCompanyName();
                    %>
                        <option><%=companyname%></option>
                <%} %>
                </datalist>
				<input type ="submit" class="button" name="submit" value="OK"/>
			</form>
			</div>
			<div style="position:absolute;left:880px;top:60px;font-size:12px;">
           &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
                 id="logout" href="logout"
                style="visibility: hidden;"onMouseOver="this.style.color='red'" onMouseOut="this.style.color='yellow'">logout</a>
              </div>
		</header>
		<form action="stocks" id="portfolioForm" name="portfolioForm" method="post">
                    <input type="hidden" name="userId" value=<%=(Integer) session.getAttribute("userId")%>>
		</form>
		<nav>
			<a class="normalMenu"  href="index">Home</a>
			<a class="normalMenu" href="portfolio" onclick="document.portfolioForm.submit();return false" style="background-color:red;">Portfolio</a>
			<a class="normalMenu" href="graph">View Graph</a>
			
			<a class="normalMenu" href="about">About Us</a>
			
		</nav>
		<div style="position:absolute;top:200px;left:700px;">
            <form  action="search" method="post">
                 <input type="search" name="query"
                    placeholder="Find Stock" list="company" required> <input
                    type="hidden" name="id" value=<%=session.getAttribute("userId") %>>
                <datalist id="company">
                    <%List<Company> list1 = (List)session.getAttribute("companies");
                   String companyname1;
                    for (int i=0;i<list1.size();i++){ 
                        companyname1 = list.get(i).getCompanyName();
                    %>
                    <option><%=companyname1%></option>
                    <%} %>
                </datalist>
                <input type="submit" class="button"style="background:none;border:none;margin:0;padding:0;color:maroon;" onMouseOver="this.style.color='red'" onMouseOut="this.style.color='maroon'" name="submit" value="Add Stock" />
            </form>
        </div>
        
        <span style="font-size:30px;position:relative;bottom:120px;"><b>YOUR PORTFOLIOS</b></span>
		<table  id="table" >
		  
		      <tr>
		          <th><span style="font-size:16px;"><b>Portfolio Name</b></span></th>
		          <th>Portfolio Details</th>
		          <th colspan="2"><span style="font-size:16px;" ><b>You Want to?</b></span></th>
		      </tr>    
			<c:forEach var="portfoliolist" items="${portfoliodet.list}">
						<tr>
							<td><b>${portfoliolist.getPortName()}</b></td>
							<td style="width:20px;">${portfoliolist.getDetails()}</td>
							<td style="position:relative;top:8px;">
								<form id="viewstock" name="viewstock" action="port" method="post">
								    <input type="hidden" name="userId" id="userId"
                            value=<%=(Integer) session.getAttribute("userId")%>>
									<input type="hidden" name="portfolioname" id="portfolioname" value="${portfoliolist.getPortName()}">
                            		<input type="submit" class="button" style="background:none;border:none;margin:0;padding:0;color:maroon;font-size:13px;" onMouseOver="this.style.color='red'" onMouseOut="this.style.color='maroon'" value="View Stocks">

                            	</form>
            				</td>
            				<td style="position:relative;top:8px;">
            				    <form id="deleteportfolio" action="delport" method="post" >
                                    <input type="hidden" name="portfolioname" id="portfolioname" value="${portfoliolist.getPortName()}">
                                    <input type="submit" class="button" style="background:none;border:none;margin:0;padding:0;color:maroon;font-size:13px;"onMouseOver="this.style.color='red'" onMouseOut="this.style.color='maroon'" value="Delete Portfolio">
                                    <input type="hidden"  name="id" id="id" value="${portfoliolist.getPortId()}">
                                   
                                </form>
                               
            				 </td>   
            			</tr>
            			
            			
         
            </c:forEach>
           						                
		</table>
	
		<a style="color:maroon;position:relative;left:800px;text-decoration:underline;" onMouseOver="this.style.color='red'" onMouseOut="this.style.color='maroon'" href="viewportfolio" ><b>Create New Portfolio</b></a>
		<footer>
			<span id="footer_text">Copyright &copy; Impetus India Pvt. Ltd. All rights reserved.
			Designed by Prerit.</span>
		</footer>
		</body>
	</div>
</html>	