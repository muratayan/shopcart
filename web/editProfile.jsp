<%-- 
    Document   : items
    Created on : Jun 9, 2014, 8:52:34 AM
    Author     : murti_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Profile</title>
    </head>
    <body>
        ${sessionScope.profile.getName()}
        <form action=welcomeServ method=post>
            <table border="0">
                <tr>
                    <td bgcolor="silver">
                        <strong>User Name: </strong>
                    </td>
                    <td>
                        ${sessionScope.username}
                    </td>
                </tr>

                <tr>
                    <td bgcolor="silver">
                        <strong>Name: </strong>
                    </td>
                    <td>
                        <strong><input type="text" name="name_" value="${sessionScope.profile.getName()}" size="15"></strong>
                    </td>
                </tr>

                <tr>
                    <td bgcolor="silver">
                        <strong>Address: </strong>
                    </td>
                    <td>
                        <strong><input type="text" name="address_" value="${sessionScope.profile.address}" size="15"></strong>
                    </td>
                </tr>
                <input type="hidden" name="action" value="editProfile"/>
            </table>
            <tr> <input type="submit" value="Save"/> </tr>
    </form>

    <form action=welcomeServ method=post>
        <input type="hidden" name="action" value="show"/>
        <input type="submit" value="Back">
    </form>


</body>
</html>
