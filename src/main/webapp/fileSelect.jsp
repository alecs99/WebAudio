<%@ page import="model.User" %>
<%@ page import="model.File" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/8/20
  Time: 17:33
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
    <link rel="stylesheet" href="style/audioList.css">
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <title>Choose</title>
</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>

<div class="card col-sm-3.8  d-inline-block float-right">
    <div class="card-body">
        <label for="filterInput"><i class="fas fa-search" style="color: white"></i></label>
        <input type="text" id="filterInput" onkeyup="filterFiles()" placeholder="Search an audio file...">
    </div>
</div>

<div class="nav-side-menu">
    <h3>Choose your audio</h3>
    <div class="menu-list">
        <ul id="menu-content">
            <%
                User user = (User) request.getAttribute("user");
                List<File> audioFiles = user.getAudioFiles();
                for(File audioFile:audioFiles){
                    String filePath = audioFile.getFilePath();%>
            <li onclick="playSong('<%=filePath%>')"><%=audioFile.toString()%></li>
            <%}%>
        </ul>
    </div>
</div>
<script src="pass.js"></script>
</body>
</html>
