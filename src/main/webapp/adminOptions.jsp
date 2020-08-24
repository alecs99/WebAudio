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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="style/mainStyle.css">
    <link rel="stylesheet" href="style/mainMenu.css">
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <title>Admin options</title>
</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>
<div class="nav-side-menu">
    <h3>Choose your option</h3>
    <div class="menu-list">

        <ul id="menu-content">
            <li>
                <a href="ListUsersServlet">
                    <i class="fas fa-users"></i> List all users
                </a>
            </li>

            <li>
                <a href="AddUserServlet">
                    <i class="fas fa-user-plus"></i> Add a new user
                </a>
            </li>

            <li>
                <a href="DeleteUserServlet">
                    <i class="fas fa-user-minus"></i> Delete an user
                </a>
            </li>

            <li>
                <a href="MakeAdminServlet">
                    <i class="fas fa-users-cog"></i> Make a user admin
                </a>
            </li>
            <li>
                <a href="RevokeAdminServlet">
                    <i class="fas fa-user-times"></i> Remove admin privileges
                </a>
            </li>
            <li>
                <a href="UserFileAccessServlet">
                    <i class="fas fa-file-audio"></i> Permit a user access a certain audio file
                </a>
            </li>
            <li>
                <a href="UserRestrictFileAccessServlet">
                    <i class="fas fa-minus-square"></i> Restrict file access to a user
                </a>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
