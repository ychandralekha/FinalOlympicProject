<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login page</title>

</head>
<script type="text/javascript">
function S_click(){
	
	val1 = document.getElementById("username").value;
	val2 = document.getElementById("password").value;
	 if(val1=="" && val2==""){
	  alert("Fields cannot be empty");
	 } 
	}
</script>
<body>
<br><br>
<h2 align="center">Olympics Games Summer</h2>
<h3 style="color:red">${error}</h3>
<form:form method="post" action="OlympicController"  modelAttribute="user">
<table align="center" cellspacing="10">

<tr><td><a href="Register">New User?</a></td></tr>
<tr><td>Username: </td>
<td><form:input path="username"/>
<form:errors path="username"  cssClass="error"/></td></tr>
<tr><td>Password: </td>
<td><form:input path="password" />
<form:errors path="password"  cssClass="error"/></td></tr>
<tr><td><input type="submit" value="Submit" onclick="return S_click();"></td></tr>
</table>
</form:form>
</body>
</html>