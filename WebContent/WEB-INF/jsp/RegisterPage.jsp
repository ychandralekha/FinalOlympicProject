<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/resources/css/LoginStyle.css" rel="stylesheet" type="text/css" /> 
<title>Registration Page</title>
</head>

 <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <style>
.container {
  position: relative;
  width: 45%;
}

.image {
  display: block;
  width: 90%;
  height: auto;
}

</style>
<div class="container">
  <img src="images\olympic.jpg" alt="Avatar" class="image">
  </div>
 <script>
  $( function() {
    $( "#datePicker" ).datepicker();
  } );
  function validateForm() {

	    var email = document.forms["myForm"]["email"].value;

	    var atpos = email.indexOf("@");
	    var dotpos = email.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || dotpos+2>=email.length) {
	        alert("Not a valid e-mail address");
	        return false;
	    }
	}
  </script>
<body background="images\olympic.jpg">
<h2 align="center">Registration Page</h2>
<h3 style="color:red">${error}</h3>
<h6 align="center"><a href="Login">EXISTING USER? LOG IN!</a></h6>

<form:form method="post" action="OlympicControllerRegister"  modelAttribute="register" onsubmit="return validateForm();">

<table align="center">
<tr><td>First Name:</td>
<td><form:input path="firstName" required="required"/>
<form:errors path="firstName"  cssClass="error"/>
</td>
</tr>
<tr><td>Last Name:</td>
<td><form:input path="lastName" required="required"/>
<form:errors path="lastName"  cssClass="error"/>
</td>
</tr>
<tr><td>User Name:</td>
<td><form:input path="username" required="required"/>
<form:errors path="username"  cssClass="error"/></td></tr>
<tr><td>Date of Birth:</td>
<td><form:input path="datePicker" id="datePicker" required="required"/>
<form:errors path="datePicker"  cssClass="error"/>
</td>
</tr>
<tr><td>Email ID:</td>
<td><form:input path="email" required="required"/>
<form:errors path="email"  cssClass="error"/>
</td>
</tr>
<tr><td>Phone Number:</td>
<td><form:input path="phoneNumber" required="required"/>
<form:errors path="phoneNumber"  cssClass="error"/>
</td>
</tr>
<tr><td>Password:</td>
<td><form:input path="password" required="required"/>
<form:errors path="password"  cssClass="error"/>
</td>
</tr>
<tr>
<td>
<input type="submit" value="submit"/>
</td>
</tr>
</table>
</form:form>
</body>
</html>