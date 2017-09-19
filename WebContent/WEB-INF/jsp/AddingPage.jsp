<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
     <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/userLoginPageStyle.css" rel="stylesheet" type="text/css" /> 
<title>Adding a page</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
$(document).ready(
			function() {
				$('#sport').change(
						function(event) {
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
</head>
<body>
<h5>USER: ${userName}</h5>
<h2 align="center">Hi ${userName}, Let's fill in the details to add.</h2>
<div class="logout"><a href="Login">LOG OUT</a></div>
<form:form action="/olympicGamesSpring/addingPage" method="post" modelAttribute="add">
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
<tr><td>Athlete
</td>
<td>
<form:input path="athlete"/>
</td></tr>
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
</body>
</html>