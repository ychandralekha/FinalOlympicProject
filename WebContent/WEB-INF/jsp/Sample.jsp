<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>

<body>
	<h5 align="right">
		<iframe
			src="http://free.timeanddate.com/clock/i5twqof3/n553/fn4/fs16/tct/pct/ahr/avt/ftb/tt0/tw0/th2/ta1/tb4"
			frameborder="0" width="142" height="40" allowTransparency="true"></iframe>
	</h5>
	<center>
		<h2>CENSUS REPORT - GOVERNMENT OF INDIA</h2>
	</center>
	<marquee>
		<h3>${error}</h3>
	</marquee>
	<h3>Login with your Account</h3>
	<table>
		<form action="/HibernateWebProject/login" method="post">
			<tr>
				<td><h4>Enter the UserName:</h4></td>
				<td><input type="text" placeholder="UserName" name="UserName"
					value="" /></td>
			</tr>
			<tr>
				<td><h4>Enter the Password:</h4></td>
				<td><input type="password" placeholder="Password"
					name="Password" value="" /></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="submit" /></td>
			</tr>
		</form>
	</table>

	<table>
		<tr>
			<td><h3>Don't have an account? Register Now</h3></td>
			<td>
				<button
					onclick="document.getElementById('Register').style.display='block'"">Sign
					Up</button>
			</td>
		</tr>
	</table>

	<div id="Register" class="RegisterForm">
		<span
			onclick="document.getElementById('Register').style.display='none'"
			class="close">×</span>
		<form:form modelAttribute="user" class="Sign-Form animate"
			name="clientvalidate" action="/HibernateWebProject/register"
			method="POST" onsubmit="return validateform()">
			<div class="container">
				<label><b><form:label path="fname">Enter the First Name</form:label></b></label>
				<form:input path="fname" name="fname" placeholder="First Name"
					value="${uif.fname}" required="required" />
					<form:errors path="fname" />
				<label><b><form:label path="lname">Enter the LastName</form:label></b></label>
				<form:input path="lname" name="lname" placeholder="Last Name"
					value="${uif.lname}" required="required" />
					<form:errors path="lname" />
				<label><b><form:label path="uname">Enter the User Name</form:label></b></label>
				<form:input path="uname" name="uname" placeholder="Last Name"
					value="${uif.uname}" required="required" />
					<form:errors path="uname" />
				<label><b><form:label path="pword">Enter the Password</form:label></b></label>
				<form:input path="pword" name="pword" placeholder="Enter Password"
					required="required" />
					<form:errors path="pword" />
				<label><b><form:label path="rpword">Re-enter the Passoword</form:label></b></label>
				<form:input path="rpword" name="rpword"
					placeholder="Re-enter Password" required="required" />
				<label><b><form:label path="email">Enter the Email Id</form:label></b></label>
				<form:input path="email" name="email"
					placeholder="enter the valid email" value="${uif.email}"
					required="required" />
					<form:errors path="email" />
				<label><b><form:label path="monbile">Enter the Mobile Number</form:label></b></label>
				<form:input path="monbile" onkeypress="return isNumberKey(event)"
					name="monbile" placeholder="enter the 10 digit mobile"
					value="${uif.monbile}" required="required" />
					<form:errors path="monbile" />
				<label><b><form:label path="role">Enter the role(User/Admin)</form:label></b></label>
				<form:input path="role" name="role"
					placeholder="(User role/Admin role)" value="${uif.role}"
					required="required" />
					<form:errors path="role" />
				<p>By creating an account you agree to our ..Terms & Privacy</p>
				<input type="submit" value="submit" />
			</div>
		</form:form>
	</div>
</body>
</html>
