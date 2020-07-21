<%@ page import="model.File" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 7/20/20
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>File access</title>
</head>
<body>
<form action="FileAccessServlet" method="post">
    <%
        List<File> files = (List<File>) request.getAttribute("selectionFiles");
        for(File file:files){%>
            <input type="checkbox" name="fileAccess" value="<%=file.getIdFile()%>"><%=file.toString()%><br>
       <%}%>
    <button type="submit">Submit</button>
</form>
</body>
</html>
