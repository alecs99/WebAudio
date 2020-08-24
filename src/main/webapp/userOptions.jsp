<%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/16/20
  Time: 14:17
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
    <title>Select your option</title>
</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <h4 class="text-center text-capitalize">Welcome, <%=request.getAttribute("username")%>!</h4>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>
<div class="nav-side-menu">
    <h3>Choose your option</h3>
    <div class="menu-list">

        <ul id="menu-content">
            <li>
                <a href="ListServlet">
                    <i class="fas fa-play"></i> Play an audio file
                </a>
            </li>

            <li>
                <a href="AddFileServlet">
                    <i class="fas fa-file-upload"></i> Add an audio file
                </a>
            </li>

            <li>
                <a href="RemoveFileServlet">
                    <i class="fas fa-minus-square"></i> Remove an audio file
                </a>
            </li>

            <li>
                <a href="ViewNotesServlet">
                    <i class="fas fa-sticky-note"></i> See your notes
                </a>
            </li>
            <li>
                <a href="DeleteNoteServlet">
                    <i class="fas fa-minus-square"></i> Delete a note
                </a>
            </li>
            <li>
                <a href="AccountServlet">
                    <i class="fas fa-user-edit"></i>Manage your account
                </a>
            </li>
            <%
               Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
                if(isAdmin){%>
                    <li><a href="AdminServlet"><i class="fas fa-users-cog"></i>Administrative options</a></li>
            <%}%>
        </ul>
    </div>
</div>
</body>
</html>
