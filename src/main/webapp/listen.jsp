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
    <link rel="stylesheet" href="style.css">
    <title>Listen</title>

</head>
<body>
<h3>Asculta aici:</h3>
<div id="soundWave"></div>

<button id="start">Play</button>
Jump to:<input type="text" id="jump" name="jump" placeholder="0">
<button data-playing="false" id="resume" role="switch" aria-checked="false">Pause/Resume</button>
<button id="stop">Stop</button>
<input type="range" id="volume" min="0" max="1" value="0.5" step="0.01">
<input type="range" id="panner" min="-45" max="45" value="0" step="45">
<input type="range" id="playback" min="0.5" max="4" value="1" step="0.5">
<span id="playback-value">1.0</span>
Rewind/Fast forward: <input type="text" id="jumping" name="jumping" placeholder="0">
<button id="rewind">Rewind</button>
<button id="fForward">Fast Forward</button>

<script src="https://cdnjs.cloudflare.com/ajax/libs/wavesurfer.js/1.3.7/wavesurfer.min.js"></script>
<script src="wave.js"></script>
</body>
</html>