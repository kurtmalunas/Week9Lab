<%-- 
    Document   : users
    Created on : 29-Oct-2022, 8:27:53 PM
    Author     : kurtm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manager Users</title>
    </head>
    <body>
        <h1>Manage Users Hello</h1>
        ${UserOne}
        <ul>
            <c:forEach items="${users}" var="user">
                <li><input type="radio" name="user" value="${user}">${user}</li>
            </c:forEach>
        </ul>
        <h1>Add User</h1>
        <form action="user" method="post">
        <p>Email: <input type="text" name="email"><br>
            First name: <input type="text" name="firstName"><br>
            Last name:  <input type="text" name="lastName"><br>
            Password:   <input type="text" name="password"><br>
            Role:  <select name="regularUser">
                <option name="regularUser" value="">regular user</option><br>
                <option name="systemAdmin" value="">system admin</option></select><br>
        </p>
        </form>
        <form action="user" method="post">
                <input type="hidden" name="action" value="delete">
                <input type="hidden" name="userEmail" value="">
                <input type="submit" value="Delete">
        </form>
    </body>
</html>
