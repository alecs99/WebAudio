<%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/21/20
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change your username</title>
</head>
<body>
<h2>Insert your new username</h2>
<form action="ChangeUserNameServlet" method="post">
    <input type="text" name="newUsername" placeholder="Insert your new username">
    <button type="submit">Submit</button>
</form>
</body>
</html>
