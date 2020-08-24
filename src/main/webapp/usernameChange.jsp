<%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/21/20
  Time: 16:30
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
    <link rel="stylesheet" href="style/usernameChange.css">
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <title>Change your username</title>
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
        <h2>Insert your new username</h2>
    </div>
    <div class="card-body">
        <form action="ChangeUserNameServlet" method="post">
            <input type="text" name="newUsername" placeholder="Insert your new username" style="width:200px;">
            <button type="submit" class="btn btn-outline-primary btn-sm">Submit</button>
        </form>
    </div>
</div>
</body>
</html>
