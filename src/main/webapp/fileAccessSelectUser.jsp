<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/20/20
  Time: 17:00
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
    <link rel="stylesheet" href="style/radioCardStyle.css">
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <title>File access</title>
</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>
<div class="card">
    <div class="card-title">
        <h2>Pick the user you want to change access</h2>
    </div>
    <div class="card-body">
        <form action="UserFileAccessServlet" method="post">
            <%
                List<User> users = (List<User>) request.getAttribute("users");
                for(User user:users){%>
            <input type="radio" name="selectedUser" value="<%=user.getIdUser()%>"><%=user.toString()%><br>
            <%}%>
            <button type="submit" class=" mt-3 btn btn-outline-primary">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
