<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
         <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Updating a page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(
			function() {
				$('#sport').change(
						function(event) {
							alert('entered');
							var sportvalue = $("select#sport").val();
							$.get('getdiscipline', {
								sportName : sportvalue
							}, function(response) {
								var select = $('#discipline');
								select.find('option').remove();
								var select1 = $('#event');
								select1.find('option').remove();
								$('<option>').val("").text(
										"Select").appendTo(
												select);
								$.each(response, function(index, value) {
									$('<option>').val(value).text(
											value).appendTo(
											select);
								});
							});
						});
				$('#discipline').change(
						function(event) {
							alert('entered');
							var disciplinevalue = $("select#discipline").val();
							var sportvalue = $("select#sport").val();
							$.get('getevent', {
								disciplineName : disciplinevalue,
								sportName : sportvalue
							}, function(response) {
								var select = $('#event');
								select.find('option').remove();
								$('<option>').val("").text(
								"select event").appendTo(
										select);
								$.each(response, function(index, value) {
									$('<option>').val(value).text(
											value).appendTo(
											select);
								});
							});
						});

			});
</script>
<script type="text/javascript">
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
</script>
</head>
<body>
<h5>USER: ${userName}</h5>
<h2 align="center">Hey ${userName}, Select your choice and update.</h2>
<div class="logout"><a href="Login">LOG OUT</a></div>

<form:form action="/olympicGamesSpring/updatePage" method="post" modelAttribute="add">
<table  cellspacing="10" cellpadding="10">
<tr>
<td>Select Sport</td><td>
<form:select path="sport" id="sport">
<form:option value="NONE" label="select">Select</form:option>
               <form:options items="${sportList}"/>
</form:select></td>
</tr>
<tr>
<td>Select Discipline</td><td>
<form:select path="discipline" id="discipline">
<form:option value="NONE" label="select">Select</form:option>
               <form:options items="${disciplineList}"/>
</form:select>
</td>
<tr>
<td>Select Event</td><td>
<form:select path="event" id="event">
<form:option value="NONE" label="select">Select</form:option>
               <form:options items="${eventList}" />
               </form:select>
      			</td>

</tr>
<tr>
<td>Select Year</td><td>
<form:select path="year" id="year">
               <form:options items="${host}" itemValue="year" />
               </form:select></td>
</tr>
<tr>
<td>Select Country</td><td>
<form:select path="country" id="country">
<form:option value="NONE" label="select">Select</form:option>
               <form:options items="${countryList}" />
               </form:select></td>
</tr>
<tr>
<td>Select Gender</td><td>
<form:select path="gender">
<form:option value="NONE" label="select"/>
<form:option value="Men"/>
<form:option value="Women"/>
</form:select></td>
</tr>

<tr>
<td>Select Medal</td><td>
<form:select path="medal">
<form:option value="NONE" label="select"/>
<form:option value="Gold"/>
<form:option value="Silver"/>
<form:option value="Bronze"/>
</form:select></td>

</tr>
<tr>
<td><input type="submit" value="submit" name="addPage"/>
</td></tr>   
           <tr><td><br><a href="UserLogin">BACK</a></td></tr>
</table>
</form:form>
<form:form action="/olympicGamesSpring/editDeletePage" method="post">
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
               <c:forEach items="${displayList}" var="out">
		<tr>
                <td><input type="text" name="year" id="yearId" value="${out.year}" readonly></td>
                <td><input type="text" name="city" id="cityId" value="${out.city}" readonly></td>
                <td><input type="text" name="sport" id="sportId" value="${out.sport}" readonly></td>
                <td><input type="text" name="discipline" id="disciplineId" value="${out.discipline}" readonly></td>
                <td><input type="text" name="${out.athlete}" id="${out.athlete}" value="${out.athlete}" readonly></td>
				<td><input type="text" name="country" id="countryId" value="${out.country}" readonly></td>
				<td><input type="text" name="gender" id="genderId" value="${out.gender}" readonly></td>
				<td><input type="text" name="event" id="eventId" value="${out.event}" readonly></td>
				<td><input type="text" name="medal" id="medalId" value="${out.medal}" readonly></td>
				<td><input type="radio" id="editButton"  value="${out.athlete}" name="edit" onclick="editFunction('${out.athlete}')"/>EDIT</td>
				<td><input type="radio" id="deleteButton" name="delete" value="${out.athlete}" onclick="deleteFunction('${out.athlete}')"/>DELETE</td>
				<td><input type="hidden" value="${out.athlete}"  id="newAthlete" name="newAthlete"></td>
				<td><input type="hidden" value="${out.athlete}"  id="oldAthlete" name="oldAthlete"></td>
				<td><input type="submit" value="submit" /></td>
        </tr>
        
        </c:forEach> 
        <br>
           
           <tr><td><a href="UserLogin.jsp">BACK</a></td></tr>
           <br>

            
        </TABLE>
</form:form>
</body>
</html>