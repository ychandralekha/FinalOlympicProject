<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
       <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin Approval page</title>
</head>
<h5>USER: ${userName}</h5>
<h2 align="center">Hey ${userName}, Let's check out today's approval requests !</h2>
<div class="logout"><a href="Login">LOG OUT</a></div>
<br>
<h3 style="color:grey">${error}</h3>
<body>
<form:form method="post" action="/olympicGamesSpring/adminApproval">
<table align="center" cellpadding="25">
<tr><th>
</th><th></th>
<th></th></tr>
  <c:forEach items="${list}" var="out">
  
<tr><td>${out.username}</td><td>${out.email}</td><td>${out.phoneNumber}</td><td><input type="checkbox" name="approve" value="${out.username}">Approve</td>
</tr>
 </c:forEach>
 <tr><td align="center" colspan="3"><input type="submit" value="submit" name="approvalValidation"></td></tr>

</table>
<br>
<br>
<br>
<a style="color:white" href="AdminWelcomePage.jsp">BACK</a>
</form:form>

</body>
</html>