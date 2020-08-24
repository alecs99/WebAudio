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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <link rel="stylesheet" href="style/playerStyle.css">
    <link rel="icon" type="image/png" href="assets/favicon.ico"/>
    <title>Listen</title>

</head>
<body>
<script src="https://kit.fontawesome.com/04cd9dfd70.js" crossorigin="anonymous"></script>
<nav class="navbar navbar-light" style="background-color: rgba(207, 235, 251, 1);" >
    <a class="navbar-brand" href="OptionsServlet">
        <img src="assets/logo.png" width="35" height="35" alt="">WebPlayer
    </a>
    <button type="button" class="btn btn-danger"><a href="LogoutServlet">Logout</a></button>
</nav>
<div id="controls">
    <button id="rewind"><i class="fas fa-backward"></i></button>
    <button id="start"><i class="fas fa-play"></i></button>
    Jump to:<input type="text" id="jump" name="jump" placeholder="0">
    <button data-playing="false" id="resume" role="switch" aria-checked="false"><i class="fas fa-pause"></i></button>
    <button id="stop"><i class="fas fa-stop"></i></button>
    <button id="fForward"><i class="fas fa-forward"></i></button>
    <button id="mute"><i class="fas fa-volume-mute"></i></button>
</div>
<div id="leftChannel" class="card mb-2">
    <div class="card-body">
        <h5 class="card-title">Left channel</h5>
        <button id="monoLeft" class="btn btn-sm btn-outline-dark col-5 mb-2 d-block">Solo</button>
        <label for="volumeLeft"><i class="fas fa-volume-off"></i></label>
        <input type="range" id="volumeLeft" min="0" max="1" value="0.5" step="0.01">
        <i class="fas fa-volume-up"></i>
    </div>
</div>
<div id="rightChannel" class="card">
    <div class="card-body">
        <h5 class="card-title">Right channel</h5>
        <button id="monoRight" class="btn btn-sm btn-outline-dark col-5 mb-2 d-block">Solo</button>
        <i class="fas fa-volume-off"></i>
        <input type="range" id="volumeRight" min="0" max="1" value="0.5" step="0.01">
        <i class="fas fa-volume-up"></i>
    </div>
</div>
<div id="soundWave"></div>
<div id="currentTimeContainer">
    <span id="currentTime">00:00:00</div>
</div>
<div id="optionsCard" class="card">
    <div class="card-body">
        <label for="playback" class=" mb-1 d-block">Playback rate:</label>
        <input type="range" id="playback" min="0.5" max="4" value="1" step="0.5">
        <span id="playback-value">1.0</span>
        <label for="jumping">Rewind/Fast forward rate:</label>
        <input type="text" id="jumping" name="jumping" placeholder="0">
        <button id="regionDelete" class="btn btn-outline-dark mt-2">Delete Regions</button>
    </div>
</div>
<div id = "form" class="card">
    <div class="card-body">
        <form action="AddNoteServlet" method="post">
            <input class="mb-1" id="startTime" type="text" name="startTime" placeholder="Start time">
            <input class="mb-1" id="endTime" type="text" name="endTime" placeholder="End time">
            <input class="mb-1" id="note" type="text" name="note" placeholder="Enter info about region">
            <input id="filePath" type="text" name="filePath" placeholder="filePath">
            <button type="submit" class="btn btn-outline-dark mr-3 col-5" onclick="keepData()">Confirm Note</button>
        </form>
    </div>
</div>
<div id="drop">
    <p class="mt-4" style="text-align: center">
        Drag and Drop your file here
    </p>
</div>
<script src="https://unpkg.com/wavesurfer.js/dist/wavesurfer.js"></script>
<script src="https://unpkg.com/wavesurfer.js/dist/plugin/wavesurfer.regions.js"></script>
<script src="wave.js"></script>
</body>
</html>