<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login</title>

</head>

<body>
<h5>USER: ${userName}</h5>
<div class="header">
<h2 align="center">Hey ${userName}! What do you want to do today?</h2>
</div>
<div class="logout"><a href="Login">LOG OUT</a></div>

<form action="/olympicGamesSpring/userLogin" method="POST">
<table cellspacing="10" cellpadding="10">
<br>
<br><br>
<br>
<tr>
<td>
<input type="radio" name="radio" value="addRecord" checked/>
</td>
<td> Add a record.
</td>
</tr>
<tr>
<td>
<input type="radio" name="radio" value="updateRecord"/>
</td>
<td> Update or delete a record.
</td>
</tr>
<tr>
<td>
<input type="radio" name="radio" value="searchFilter" />
</td>
<td> Search and filter.
</td>
</tr>
<tr><td><input type="submit" value="submit" /></td></tr>
</table>
</form>

</body>
</html>