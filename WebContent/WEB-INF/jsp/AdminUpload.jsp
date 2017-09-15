<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Login</title>
</head>
<body>
<h5>USER: ${userName}</h5>
<h2 align="center">Hi ${userName}! Let's upload!</h2>
<div class="logout"><a href="Login">LOG OUT</a></div>
<h3 style="color:grey">${error}</h3>
<form action="/olympicGamesSpring/uploadfile" method="post">
<br><br><br>
<h4 >Select a file:<input type="file" name="file"/><br/><br/>  
<input type="submit" value="submit"/> </h4>
<br>
<br>
<a href="AdminWelcomePage.jsp">BACK</a>
</form>
</body>
</html>