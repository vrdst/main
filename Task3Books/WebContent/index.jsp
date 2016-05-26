<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Books handling for 3rd task</title>
</head>
<body>
<%@ page import="java.util.*" 
import="java.io.*"%>

<%
String title = request.getParameter("title");
String author = request.getParameter("author");
String isbn = request.getParameter("isbn");
Map<String, String> formErrors = (Map<String,String>) request.getAttribute("formErrors");
String bookAdded = (String) request.getAttribute("bookAdded");
%>
<h1>
<form action="/Task3Books/books" method="post">
	<label for="title">Title:</label>
	<input type="text" name="title" value="${param.title}"><br>
	
  	<label for="author">Author:</label>
  	<input type="text" name="author" value="${param.author}"><br>
  	
  	<label for="isbn">ISBN number:</label>
  	<input type="text" name="isbn" value="${param.isbn}"><br>
  	<span class="error">${formErrors.isbnNumeric}</span><br>
  	<br>
	<input type="submit" value="Save book">
    
</form>
<input type="button" value="Show book index" onclick="document.location.href='fileContent.jsp'">
	<br>
	<span class="error">${bookAdded}</span>
	<span class="error">${formErrors.fields}</span><br>
</h1>
<h2>
</h2>




</body>
</html>