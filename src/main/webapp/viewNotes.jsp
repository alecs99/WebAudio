<%@ page import="model.Note" %>
<%@ page import="java.util.List" %>
<%@ page import="model.File" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/30/20
  Time: 14:53
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
    <title>View your notes</title>
</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>
<div class="card justify-content-center table-responsive-sm ">
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Row No.</th>
            <th scope="col">File title</th>
            <th scope="col">File Author</th>
            <th scope="col">Note</th>
            <th scope="col">Start time</th>
            <th scope="col">End time</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Note> notes = (List<Note>) request.getAttribute("notes");
            int i = 0;
            for(Note note:notes){
                i++;
                File file = note.getFile();%>
        <tr>
            <th scope="row"><%=i%></th>
            <td><%=file.getFileName()%></td>
            <td><%=file.getAuthor()%></td>
            <td><%=note.getNote()%></td>
            <td><%=note.getStartTime()%></td>
            <td><%=note.getEndTime()%></td>
        </tr>
        <%}%>
        </tbody>
    </table>
</div>
</body>
</html>
