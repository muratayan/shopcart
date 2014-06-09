<%-- 
    Document   : index
    Created on : Apr 15, 2014, 10:46:47 AM
    Author     : murti_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Welcome to our website</h1>
        <% if(request.getUserPrincipal() == null) { %>
        Please log in <a href="login.jsp"> here </a>
        <% } %>
        <% if(request.getUserPrincipal() != null) { %>
        Please come in <a href="welcomeServ"> here </a> <%=session.getAttribute("username")%>
        <br>Or, do you want to <a href="logoutServ"> logout </a>?
        <% } %>
        
        <br>

        <c:if test="${sessionScope.username eq 'admin'}">
            You can order items <a href="welcomeServ?action=itemMenu"> here </a>
        </c:if>

    </body>
</html>
