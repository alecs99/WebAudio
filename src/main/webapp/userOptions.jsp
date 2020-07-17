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
    <title>Select your option</title>
</head>
<body>
<h2>Welcome, <%=request.getAttribute("username")%>!</h2>
<h3>Choose your option</h3>
<ul>
    <li><a href="ListServlet">Play an audio file</a></li>
    <li><a href="AddFileServlet">Add an audio file</a></li>
    <li><a href="RemoveFileServlet">Remove an audio file</a></li>
    <li><a href="AccountServlet">Manage your account</a></li>
    <%
    Boolean isAdmin = (Boolean) request.getAttribute("isAdmin");
    if(isAdmin){%>
        <li><a href="AdminServlet">Administrative options</a></li>
    <%}%>
</ul>
</body>
</html>
