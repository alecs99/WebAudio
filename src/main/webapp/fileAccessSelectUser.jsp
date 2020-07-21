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
    <title>File access</title>
</head>
<body>
<h2>Pick the user you want to change access</h2>
<form action="UserFileAccessServlet" method="post">
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for(User user:users){%>
            <input type="radio" name="selectedUser" value="<%=user.getIdUser()%>"><%=user.toString()%><br>
    <%}%>
    <button type="submit">Submit</button>
</form>
</body>
</html>
