<%-- 
    Document   : welcome
    Created on : Apr 15, 2014, 3:17:26 PM
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
        <h1>Hello <%=session.getAttribute("username")%></h1>
        Do you want to <a href="logoutServ"> logout </a> baby?
    </body>
</html>
