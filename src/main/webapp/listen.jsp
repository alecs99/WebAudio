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
    <link rel="icon" type="image/png" href="favicon.png"/>
    <title>Listen</title>

</head>
<body>
<h5>Asculta aici:</h5>
<audio src="got.mp3" crossOrigin = "anonymous"></audio>
<button id="start">Play</button>
<button data-playing="false" id="resume" role="switch" aria-checked="false">Pause/Resume</button>
<button id="stop">Stop</button>
<input type="range" id="volume" min="0" max="1" value="0.5" step="0.01" data-action="volume">
<input type="range" id="panner" min="-7" max="7" value="0" step="0.01" data-action="panner">
<input type="range" id="playback" min="0.5" max="4" value="1" step="0.5">
<span id="playback-value">1.0</span>
<script src="audio.js"></script>
</body>
</html>
