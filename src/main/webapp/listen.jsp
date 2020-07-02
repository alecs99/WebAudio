<%--
  Created by IntelliJ IDEA.
  User: alc
  Date: 6/30/20
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listen</title>
</head>
<body>
<h5>Asculta aici:</h5>
<audio src="got.mp3" crossOrigin = "anonymous"></audio>
<button data-playing="false" role="switch" aria-checked="false">
    <span>Play/Pause</span>
</button>
<input type="range" id="volume" min="-0.5" max="1" value="0" step="0.01">
<input type="range" id="panner" min="-7" max="7" value="0" step="0.01">
<script src="audio.js"></script>
</body>
</html>
