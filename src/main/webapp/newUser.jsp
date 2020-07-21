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
    <title>Add a new user</title>
</head>
<body>
<h2>Select audio file you want to add</h2>
<form action="AddUserServlet" method="post">
    <input type="text" name="userName" placeholder="Insert username">
    <input type="password" name="userPassword" placeholder="Insert password">
    <input type="text" name="firstName" placeholder="Insert first name">
    <input type="text" name="lastName" placeholder="Insert last name">
    <input type="text" name="email" placeholder="Insert email">
    <input type="checkbox" id="adminCheck" name="isAdmin" value="admin">
    <label for="adminCheck">Make user admin</label><br>
    <h1>Select audio files which user can have access to</h1>
    <%
        List<File> audioFiles = (List<File>) request.getAttribute("audioFiles");
        for(File audioFile:audioFiles){%>
            <input type="checkbox" name="audios" value="<%=audioFile.getIdFile()%>"><%=audioFile.toString()%><br>
    <%  }%>
    <button type="submit">Submit</button>
</form>
</body>
</html>
