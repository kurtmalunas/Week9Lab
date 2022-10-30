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
        <h1>Manage Users</h1>
        ${UserOne}
        ${message}
        <table border="1px, solid">
            <tr>
            <th>Email</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>Role</th>
            <th>Edit User</th>
            <th>Delete User</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.email}</td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.getRoleName()}</td>
                    <td><a href="user?action=edit&amp;emailSel=${user.email}">Edit</td>
                    <td><a href="user?action=delete&amp;emailSel=${user.email}">Delete
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h1>${Manage} User</h1>
        <form action="user" method="post">
        <p>Email: <input type="text" name="email" value="${email}"><br>
            First name: <input type="text" name="firstName" value="${firstName}"><br>
            Last name:  <input type="text" name="lastName" value="${lastName}"><br>
            Password:   <input type="text" name="password" value="${password}"><br>
            Role:  <select name="roleOfUser" default="${roleOfUser}">
                <option name="regularUser" value="">regular user</option><br>
                <option name="systemAdmin" value="">system admin</option></select><br>
        </p>
        </form>
    </body>
</html>
