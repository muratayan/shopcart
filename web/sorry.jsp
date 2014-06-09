<%-- 
    Document   : sorry
    Created on : Jun 9, 2014, 6:12:55 AM
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
        <jsp:useBean id="profile" scope="application" class="com.mypack.beans.ProfileBean" />
        <h1>Sorry :(</h1>
        <p>Hello, ${profile.name}. Unfortunately we don't have enough ingredients to prepare
            your order. We recorded it and will send to you as soon as possible! <br>
        In the mean time You can continue <a href="welcomeServ"> shopping </a> or simply <a href="logoutServ"> logout </a></p>
        
    </body>
</html>
