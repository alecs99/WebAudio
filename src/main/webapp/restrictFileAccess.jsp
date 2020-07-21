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
    <title>Restrict File access</title>
</head>
<body>
<h2>Select audio file you want to change permission</h2>
<form action="RestrictFileAccessServlet" method="post">
    <%
        List<File> files = (List<File>) request.getAttribute("selectionFiles");
        for(File file:files){%>
    <input type="checkbox" name="fileAccess" value="<%=file.getIdFile()%>"><%=file.toString()%><br>
    <%}%>
    <button type="submit">Submit</button>
</form>
</body>
</html>

</html>
