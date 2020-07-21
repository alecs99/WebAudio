<%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/20/20
  Time: 09:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin options</title>
</head>
<body>
<h2>Choose your option:</h2>
<ul>
    <li><a href="ListUsersServlet">List all users</a></li>
    <li><a href="AddUserServlet">Add a new user</a></li>
    <li><a href="DeleteUserServlet">Delete an user</a></li>
    <li><a href="MakeAdminServlet">Make an user admin</a></li>
    <li><a href="UserFileAccessServlet">Permit an user access a certain audio file</a></li>
    <li><a href="UserRestrictFileAccessServlet">Restrict file access to an user</a></li>
</ul>
</body>
</html>
