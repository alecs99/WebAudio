<%@ page import="model.File" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/20/20
  Time: 09:42
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
    <link rel="stylesheet" href="style/newUser.css">
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <title>Add a new user</title>
</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>
<div class="card-deck justify-content-center">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Add information about the new user</h4>
            <form action="AddUserServlet" method="post">
            <div class="input-group mb-3">
                <input type="text" name="userName" placeholder="Insert username">
                <input type="password" name="userPassword" placeholder="Insert password">
            </div>
            <div class="input-group mb-3">
                <input type="text" name="firstName" placeholder="Insert first name">
                <input type="text" name="lastName" placeholder="Insert last name">
            </div>
            <div class="input-group mb-3">
                <input type="text" name="email" placeholder="Insert email">
            </div>
            <input type="checkbox" id="adminCheck" name="isAdmin" value="admin" onchange="checkAudio()">
            <label for="adminCheck">Make user admin</label>
        </div>
    </div>
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Select audio files user has access to</h4>
            <%
                List<File> audioFiles = (List<File>) request.getAttribute("audioFiles");
                            for(File audioFile:audioFiles){%>
                        <input type="checkbox" name="audios" value="<%=audioFile.getIdFile()%>"><%=audioFile.toString()%><br>
                        <%}%>
            <button type="submit" class="mt-3 btn btn-outline-primary">Submit</button>
            </form>
        </div>
    </div>
</div>
<script src="checkAudioFiles.js"></script>
</body>
</html>
