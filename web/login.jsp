<%-- 
    Document   : login
    Created on : Apr 15, 2014, 2:18:15 PM
    Author     : murti_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Please login:</h1>
        <form method="POST" action="loginServ">
            Username: <input type="text" name="txtUserName" /><br>
            Password: <input type="password" name="txtPassword" /><br>
            <input type="submit" value="Login" />
        </form>
        <% if(request.getAttribute("loginfail") != null) { %>
        Login failed
        <% } %>
        
    </body>
</html>
