<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search and Filter page</title>
</head>

<body>
<h5>USER: ${userName}</h5>
<h2 align="center">Hey ${userName}, Make your choice!</h2>
<div class="logout"><a href="Login">LOG OUT</a></div>
<br>
<br>
<form action="/olympicGamesSpring/searchFilter" method="post">
    Enter the Start year:
    <input type="text" name="startyear" value="" />
    Enter the end year:
     <input type="text" name="endyear" value="" />
<br><br>
    Enter the Athlete:
    <input type="text" name="athlete" value="" />
<br><br>
    Enter the Sport:
    <input type="text" name="sport" value="" />
<br><br>
    Enter the Country:
    <input type="text" name="country" value="" /> 
<br><br>
    Enter the Gender:
    <input type="text" name="gender" value="" />

<br><br>
<br>
Select The Option to sort:
<select name="sortingSelect" value="">
<option value="year">year</option>
<option Value="medal">medal</option>
<option value="country">Country</option>
<option value="gender">Gender</option>

</select>
<br>
<br>
    <input type="submit" value="submit">
   <br>
   <br>
      <a href="UserLogin.jsp">BACK</a> <br>
      <br>
</form>    
</body>
</html>