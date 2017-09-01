<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Adding a page</title>
<script type="text/javascript">
function getDiscipline()
{	
	var sport=document.getElementById("sport").value;
	window.location.href="http://localhost:8080/OlympicGamesSummer1/OlympicUserOperationsServlet?sport1="+sport+"&addpage=value";
}
function getEvent()
{
	var year=document.getElementById("year").value;
	var sport=document.getElementById("sport").value;
	var discipline=document.getElementById("discipline").value;
	window.location.href="http://localhost:8080/OlympicGamesSummer1/OlympicUserOperationsServlet?sport="+sport+"&discipline="+discipline+"&actionevent=getdiscipline"+"&addpage=value";
}
</script>
</head>
<body>
<h5>USER: ${userName}</h5>
<h2 align="center">Hi ${userName}, Let's fill in the details to add.</h2>
<div class="logout"><a href="Login">LOG OUT</a></div>
<form action="OlympicUserOperationsServlet" method="post">
<table  cellspacing="10" cellpadding="10">

<td>Select Sport</td><td>
<select name="sportselect" id="sport" onchange="getDiscipline()" >
<option  >Select</option>
               <c:forEach items="${sportList}" var="out">
               <c:choose>
               <c:when test="${out.key==sport}">
               <option value="${out.key}" selected="selected" >${out.key}</option>
               </c:when>
               <c:otherwise>
               <option value="${out.key}" >${out.key}</option>
               </c:otherwise>
               </c:choose>
      			 
      		 </c:forEach> 
</select></td>
</tr>
<tr>
<td>Select Discipline</td><td>
<select name="disciplineselect" id="discipline" onchange="getEvent()">
<option >Select</option>
               <c:forEach items="${disciplineList}" var="out">
               <c:choose>
               <c:when test="${out==discipline}">
               <option value="${out}" selected="selected" >${out}</option>
               </c:when>
               <c:otherwise>
               <option value="${out}" >${out}</option>
               </c:otherwise>
               </c:choose>
      		 </c:forEach>
</select></td>
<tr>
<td>Select Event</td><td>
<select name="eventselect" >
<option >Select</option>
               <c:forEach items="${eventList}" var="out">
      			 <option value="${out}" >${out}</option>
      			 </c:forEach>
</select></td>

</tr>
</tr>
<tr>
<td>Select Year</td><td>
<select name="yearselect" id="year" onchange="getCity()" >
<option selected="selected" >Select</option>
               <c:forEach items="${yearList}" var="out">
               <option value="${out}" >${out.key}-${out.value}</option>
      		 </c:forEach> 
</select></td>
</tr>
<tr>
<tr><td>Athlete
</td>
<td><input type="text" name="athlete"/>
</td></tr>
<tr>
<td>Select Country</td><td>
<select name="countryselect" >
<option  selected="selected"  value="" >Select</option>
               <c:forEach items="${countryList}" var="out">
      			 <option value="${out}" >${out}</option>
      		 </c:forEach> 
</select></td>
</tr>
<tr>
<td>Select Gender</td><td>
<select name="genderselect" >
<option value="Men">Men</option>
<option value="Women">Women</option>
</select></td>
</tr>

<tr>
<td>Select Medal</td><td>
<select name="medalselect" >
<option value="gold">Gold</option>
<option value="silver">Silver</option>
<option value="Bronze">Bronze</option>
</select></td>
</tr>
<tr>
<td><input type="submit" value="submit" name="addPage"/>
</td></tr>   
           <tr><td><br><a href="UserLogin">BACK</a></td></tr>
</table>
</form>
</body>
</html>