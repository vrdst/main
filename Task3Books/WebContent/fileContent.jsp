<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.net.URL"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Book Index</title>
    </head>
    <body>
        <%
        BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
        StringBuilder sb = new StringBuilder();
        String line;

        while((line = reader.readLine())!= null){
            sb.append(line + "<br>");
        }
        out.println(sb.toString());
        reader.close();
        
/*             BufferedReader reader = new BufferedReader(new FileReader("books.txt"));
            StringBuilder sb = new StringBuilder();

            while(reader.readLine()!= null){
                sb.append(reader.readLine() );
            }
            out.println(sb.toString());
            reader.close(); */
        %>

    </body>
</html>