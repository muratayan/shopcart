<%-- 
    Document   : thanks
    Created on : Jun 9, 2014, 6:06:56 AM
    Author     : murti_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thank you</title>
    </head>
    <body>
        <jsp:useBean id="profile" scope="application" class="com.mypack.beans.ProfileBean" />
        <h1>Thank you for choosing us, ${profile.name}</h1>
        <br>
        <p>Your order is sent to ${profile.address}</p>
        <br>You can continue <a href="welcomeServ"> shopping </a> or simply <a href="logoutServ"> logout </a>
    </body>
</html>
