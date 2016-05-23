<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contact mailing app for 1st task</title>
</head>
<body>
<%@ page import="java.util.*" 
import="java.io.*"%>

<%
String formName = request.getParameter("formName");
String formSurname = request.getParameter("formSurname");
String formPhoneNo = request.getParameter("formPhoneNo");
String formEmailAddress = request.getParameter("formEmailAddress");
Map<String, String> formErrors = (Map<String,String>) request.getAttribute("formErrors");
%>

<form action="/Task1EmailApp/email" method="post">
	<label for="formName">Name:</label>
	<input type="text" name="formName" value="${param.formName}">
	<span class="error">${formErrors.name}</span><br>
	
  	<label for="formSurname">Surname:</label>
  	<input type="text" name="formSurname" value="${param.formSurname}">
  	<span class="error">${formErrors.surname}</span><br>
  	
  	<label for="formPhoneNo">Phone number:</label>
  	<input type="text" name="formPhoneNo" value="${param.formPhoneNo}">
  	<span class="error">${formErrors.phone}</span><br>
  	
  	<label for="formEmailAddress">Email address:</label>
	<input type="text" name="formEmailAddress" value="${param.formEmailAddress}">
	<span class="error">${formErrors.email}</span><br>

	<input type="submit" value="SEND">
</form>

</body>
</html>