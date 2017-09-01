<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Updating a page</title>
<style>
input[readonly]{
background-color: #F3F3F3;
}
</style>
<script type="text/javascript">
function getDiscipline()
{	
	var sport=document.getElementById("sport").value;
	window.location.href="http://localhost:8080/OlympicGamesSummer1/OlympicUserOperationsServlet?sport1="+sport+"&updatepage=value";
}
function getEvent()
{
	var year=document.getElementById("year").value;
	var sport=document.getElementById("sport").value;
	var discipline=document.getElementById("discipline").value;
	window.location.href="http://localhost:8080/OlympicGamesSummer1/OlympicUserOperationsServlet?sport="+sport+"&discipline="+discipline+"&actionevent=getdiscipline"+"&updatepage=value";
}
function editFunction(athlete)
{
    document.getElementById(athlete).readOnly  = false;
    document.getElementById(newAthlete).value=athlete;
}
function deleteFunction(athlete)
{
    document.getElementById(athlete).readOnly  = true;
    document.getElementById(newAthlete).value=athlete;
  
}
function newAthlete(a)
{
	alert(a);
}
</script>
</head>
<body>
<h5>USER: ${userName}</h5>
<h2 align="center">Hey ${userName}, Select your choice and update.</h2>
<div class="logout"><a href="Login.jsp">LOG OUT</a></div>
<form action="OlympicUserOperationsServlet" method="post">
<table cellspacing="10" cellpadding="10">

<tr><td>Select Sport</td><td>
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
<td><input type="submit" value="submit" name="updatePage"/>
</td></tr>
</table>
</form>
<form action="OlympicUserOperationsServlet" method="post">
   <TABLE cellspacing="10" cellpadding="10">
            <TR>
                <TH>Year</TH>
                <TH>City</TH>
                <TH>Sport</TH>
                <TH>Discipline</TH>
                <TH>Athlete</TH>
                <TH>Country</TH>
                <TH>Gender</TH>
                <TH>Event</TH>
                <TH>Medal</TH>
            </TR>
      
            <TR>
               <c:forEach items="${displayRecord}" var="out">
		<tr>
                <td><input type="text" name="yearId" id="yearId" value="${out.year}" readonly></td>
                <td><input type="text" name="cityId" id="cityId" value="${out.city}" readonly></td>
                <td><input type="text" name="sportId" id="sportId" value="${out.sport}" readonly></td>
                <td><input type="text" name="disciplineId" id="disciplineId" value="${out.discipline}" readonly></td>
                <td><input type="text" name="${out.athlete}" id="${out.athlete}" value="${out.athlete}" readonly></td>
				<td><input type="text" name="countryId" id="countryId" value="${out.country}" readonly></td>
				<td><input type="text" name="genderId" id="genderId" value="${out.gender}" readonly></td>
				<td><input type="text" name="eventId" id="eventId" value="${out.event}" readonly></td>
				<td><input type="text" name="medalId" id="medalId" value="${out.medal}" readonly></td>
				<td><input type="radio" id="editButton"  value="${out.athlete}" name="edit" onclick="editFunction('${out.athlete}')"/>EDIT</td>
				<td><input type="radio" id="deleteButton" name="delete" value="${out.athlete}" onclick="deleteFunction('${out.athlete}')"/>DELETE</td>
				<td><input type="hidden" name="${out.athlete}"  id="newAthlete" value="newAthlete"></td>
				<td><input type="hidden" name="${out.athlete}"  id="oldAthleteId" value="oldAthleteId"></td>
				<td><input type="submit" value="submit" name="updateDeleteRecordPage" onclick="newAthlete('${out.athlete}')" /></td>
        </tr>
        
        </c:forEach> 
        <br>
           
           <tr><td><a href="UserLogin.jsp">BACK</a></td></tr>
           <br>

            
        </TABLE>
</form>
</body>
</html>