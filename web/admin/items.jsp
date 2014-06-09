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
        <title>Item Ordering</title>
    </head>
    <body>
        <table border="0">
            <tr bgcolor="silver">
                <td>
                    <strong>Item Id</strong>
                </td>
                <td>
                    <strong>Item Name</strong>
                </td>
                <td>
                    <strong>In Stock</strong>
                </td>
                <td>
                    <strong>Order this much more:</strong>
                </td>
            </tr>
        
        <form action=welcomeServ method=post>
            <tr>
                <td>1</td>
                <td>Cheese</td>
                <td align="right">${sessionScope.stuffs.getItem1()}</td>
                <td align="right"><input type="text" name="q1" value="10" size="2"></td>
            </tr>
            
            <tr>
                <td>2</td>
                <td>Tomato</td>
                <td align="right">${sessionScope.stuffs.getItem2()}</td>
                <td align="right"><input type="text" name="q2" value="10" size="2"></td>
            </tr>
            
            <tr>
                <td>3</td>
                <td>Peperoni</td>
                <td align="right">${sessionScope.stuffs.getItem3()}</td>
                <td align="right"><input type="text" name="q3" value="10" size="2"></td>
            </tr>
            
            <tr >
                <td>4</td>
                <td>Mushrooms</td>
                <td align="right">${sessionScope.stuffs.getItem4()}</td>
                <td align="right"><input type="text" name="q4" value="10" size="2"></td>
            </tr>
            
            <input type="hidden" name="action" value="addItem"/>
            
           </table>
                <tr> <input type="submit" value="Order"/> </tr>
        </form>
                Go <a href="./index.jsp"> back </a>
    </body>
</html>
