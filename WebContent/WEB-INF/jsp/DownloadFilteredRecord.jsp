<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Download page</title>
</head>

<body>
<h5>USER:${userName}</h5>
<h2 align="center">Hey ${userName}, We applied your choice now..!</h2>
<div class="logout"><a href="Login">LOG OUT</a></div>
<br>
<br>

<form action="/olympicGamesSpring/download" method="post">
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
               <c:forEach items="${fullQuery}" var="out">
		<tr>
                <td>${out.year}</td>
                <td>${out.city}</td>
                <td>${out.sport}</td>
                <td>${out.discipline}</td>
                <td>${out.athlete}</td>
				<td>${out.country}</td>
				<td>${out.gender}</td>
				<td>${out.event}</td>
				<td>${out.medal}</td>
        </tr>
        </c:forEach> 
            </TR>
           
        </TABLE>
          <input type="submit" value="Download" name="Download" id="Download"/>
          <br>
     <br>
     </form>
</body>
</html>