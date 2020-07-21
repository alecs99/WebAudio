<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/21/20
  Time: 13:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User list</title>
</head>
<body>
<h2>The users are:</h2>
<ul>
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for(User user:users){%>
            <li><%=user.toString()%></li>
    <%}%>
</ul>
<a href="OptionsServlet">Return to main page</a>
</body>
</html>
