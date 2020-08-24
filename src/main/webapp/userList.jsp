<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/21/20
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="style/mainStyle.css">
    <link rel="stylesheet" href="style/tableStyle.css">
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <title>User list</title>
</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>
<div class="card justify-content-center table-responsive align-items-center">
    <table class="table ">
        <thead>
        <tr>
            <th scope="col">User Id</th>
            <th scope="col">Username</th>
            <th scope="col">First name</th>
            <th scope="col">Last name</th>
            <th scope="col">Email</th>
            <th scope="col">Admin</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<User> users = (List<User>) request.getAttribute("users");
            for(User user:users){%>
        <tr>
            <th scope="row"><%=user.getIdUser()%></th>
            <td><%=user.getUsername()%></td>
            <td><%=user.getFirstName()%></td>
            <td><%=user.getLastName()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getAdmin()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
