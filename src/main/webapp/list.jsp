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
    <title>Choose</title>
</head>
<body>
<h2>Choose your audio</h2>
<ul id="list">
    <%
        User user = (User) request.getAttribute("user");
        List<File> audioFiles = user.getAudioFiles();
        for(File audioFile:audioFiles){
            String filePath = audioFile.getFilePath();%>
            <li onclick="playSong('<%=filePath%>')"><%=audioFile.toString()%></li>
       <%}%>
</ul>
<script src="pass.js"></script>
</body>
</html>
