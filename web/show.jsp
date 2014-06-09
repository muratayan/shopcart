<%-- 
    Document   : show
    Created on : Jun 8, 2014, 8:31:32 PM
    Author     : murti_000
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ayan Pizza Shop</title>
    </head>
    <body>
        <jsp:useBean id="pizzaList" scope="application" class="com.mypack.beans.PizzaListBean" />
        <jsp:useBean id="shoppingCart" scope="application" class="com.mypack.beans.ShoppingBean" />
        <h1>Hello! Here are our most special pizzas!</h1>
        <br><br>

        <table border="0">
            <tr bgcolor="silver" cellspacing="0">
                <td>
                    <strong>Pizza Id</strong>
                </td>
                <td>
                    <strong>Pizza Name</strong>
                </td>
                <td>
                    <strong>Description</strong>
                </td>
                <td>
                    <strong>Quantity</strong>
                </td>
            </tr>
            <c:forEach items="${pizzaList.getProduktLista()}" var="element">
                <form action=welcomeServ method=post>
                    <tr>
                        <td><c:out value="${element.id}" /></td>
                        <td><c:out value="${element.name}" /></td>
                        <td align="right"><c:out value="${element.desc}" /></td>
                        <td><input type="text" name="quantity" value="1" size="2"> </td>
                    <input type="hidden" name="pizzaId" value=${element.id} />
                    <input type="hidden" name="action" value="add"/>
                    <td><input type="submit" value="Buy"/></td>
                    </tr>
                </form>
            </c:forEach>
        </table>

        <br><br><br>

        <strong>Shoppingcart</strong>
        <table border="0" cellspacing="0">
            <tr bgcolor="silver">
                <td>Pizza</td>
                <td>Quantity</td>
                <td colspan="2">&nbsp;&nbsp;Remove</td>
            </tr>

            <c:forEach items="${shoppingCart.getCart()}" var="pitem">
                <form action=welcomeServ method=post>
                    <tr>
                        <td><c:out value="${pitem[0].name}" /></td>
                        <td align="right"><c:out value="${pitem[1]}" /></td>
                        <td><input type="text" name="quantity" value="1" size="2"> </td>
                    <input type="hidden" name="pizzaId" value=${pitem[0].id} />
                    <input type="hidden" name="action" value="remove"/>
                    <td><input type="submit" value="Remove"/></td>
                    </tr>
                </form>
            </c:forEach>
        </table>


        <a href="welcomeServ?action=checkout">Checkout</a>


    </form>
    <form action=welcomeServ?action=profile method=post>
        <input type="submit" value="Update Profile">
    </form>

    <form action=logoutServ method=post>
        <input type="submit" value="Logout">
    </form>
</body>
</html>
