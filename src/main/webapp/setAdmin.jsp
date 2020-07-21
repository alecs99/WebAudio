<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/21/20
  Time: 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Set admin</title>
</head>
<body>
<h2>Select user you want to make admin</h2>
<form action="MakeAdminServlet" method="post">
    <%
        List<User> users = (List<User>) request.getAttribute("users");
        for(User user:users){
           if(!user.getAdmin())%>
                <input type="radio" name="selectedUser" value="<%=user.getIdUser()%>"><%=user.toString()%><br>
    <%}%>
    <button type="submit">Submit</button>
</form>
</body>
</html>
