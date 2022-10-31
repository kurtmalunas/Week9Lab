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
                    <td>${user.role.getRoleName()}</td>
                    <td><a href="user?action=edit&amp;emailSel=${user.email}">Edit</td>
                    <td><a href="user?action=delete&amp;emailSel=${user.email}">Delete
                    </td>
                </tr>
            </c:forEach>
        </table>
        <h1>${Manage} User</h1>
        <form action="user" method="post">
            <p> 
                <c:if test="${edit eq true}">
                    Email: <input type="text" name="email" value="${email}" STYLE="background: lightgray;" readonly required><br>
                </c:if>
                <c:if test="${edit eq false}">
                    Email: <input type="text" name="email" value="${email}" required><br>
                </c:if>
               
            First name: <input type="text" name="firstName" value="${firstName}" required><br>
            Last name:  <input type="text" name="lastName" value="${lastName}" required><br>
            Password:   <input type="text" name="password" value="${password}" required><br>
            Role:  <select name="role" >
                <c:forEach items="${roles}" var="role">
                        <option value="${role.roleId}">${role.roleName}</option>
                </c:forEach>
            </select><br/>
            <c:if test="${adding eq false}">
                <form action="user" method="post">
                    <input type="hidden" name="action" value="Update">
                    <input type="submit" value="Update" style="width:300px; display:inline-block;">
                </form>
                <form action="user" method="post">
                    <input type="hidden" name="action" value="Cancel">
                    <input type="submit" value="Cancel" style="width:300px; display:inline;">
                </form>
            </c:if>
            <c:if test="${adding eq true}">
                <form action="user" method="post">
                    <input type="hidden" name="action" value="Add User">
                    <input type="submit" value="Add User">
                </form>
            </c:if>
            </p>
        </form>
            
            
    </body>
</html>
