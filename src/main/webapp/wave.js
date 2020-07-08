const playButton = document.getElementById('start');
const resumeButton = document.getElementById('resume');
const rewindButton = document.getElementById('rewind');
const forwardButton = document.getElementById('fForward');
const stopButton = document.getElementById('stop');
const playbackControl = document.getElementById('playback');
const playbackValue = document.getElementById('playback-value');
const pannerControl = document.querySelector('#panner');
const volumeControl = document.querySelector('#volume');

//Crearea obiectului de tip wavesurfer
var wavesurfer = WaveSurfer.create({
    container: '#soundWave',
    progressColor: "#03a9f4"
});

//Crearea unui filtru de tip panner
wavesurfer.panner = wavesurfer.backend.ac.createPanner();
wavesurfer.backend.setFilter(wavesurfer.panner);

//Buton de play cu functionalitatea de jump to pentru a incepe de la secunda dorita
playButton.onclick = function(){
    if(resumeButton.dataset.playing  === 'false'){
        let secs = document.getElementById("jump").value;
        document.getElementById("jump"). disabled = true;
        wavesurfer.seekTo(secs/wavesurfer.getDuration());
        wavesurfer.play();
        this.setAttribute('disabled', 'disabled');
        resumeButton.dataset.playing = 'true';
    }
}
//Buton de stop, revine la secunda 0
stopButton.onclick = function () {
    if(resumeButton.dataset.playing  === 'true'){
        wavesurfer.stop();
        playButton.removeAttribute('disabled')
        playButton.setAttribute('enabled', 'enabled');
        document.getElementById("jump"). disabled = false;
        resumeButton.dataset.playing = 'false';
    }
}

//Buton de resume
resumeButton.addEventListener('click', function() {

    if (this.dataset.playing === 'false') {
        wavesurfer.play();
        this.dataset.playing = 'true';
    } else if (this.dataset.playing === 'true') {
        wavesurfer.pause();
        this.dataset.playing = 'false';
    }

}, false);

//In momentul in care melodia se incheie revine la secunda 0
wavesurfer.on('finish', () => {
    wavesurfer.seekTo(0);
    resumeButton.dataset.playing = 'false';
    playButton.removeAttribute('disabled')
    playButton.setAttribute('enabled', 'enabled');
    document.getElementById("jump"). disabled = false;
}, false);

//Ajustare volum
volumeControl.addEventListener('input', function() {
    wavesurfer.setVolume(this.value);
}, false);

//Setez canalul preferat pentru fisierele audio de tip stereo
pannerControl.addEventListener('input', function () {
    var xDeg = parseInt(this.value);
    var x = Math.sin(xDeg * (Math.PI / 180));
    wavesurfer.panner.setPosition(x, 0, 0);
})

//Seteaza viteza de playback
playbackControl.oninput = function(){
    wavesurfer.setPlaybackRate(playbackControl.value);
    playbackValue.innerHTML = playbackControl.value;
}

//Event listener pentru rewind/forward (sageata stanga rewind, sageata dreapta forward)
document.addEventListener("keydown", function (event) {
    let secs = document.getElementById("jumping").value;
    if(event.which === 39)
        wavesurfer.skip(parseFloat(secs));
    else if (event.which === 37)
        wavesurfer.skip(-parseFloat(secs));
})

//Buton pentru rewind
rewindButton.onclick = function(){
    var secs = document.getElementById("jumping").value;
    wavesurfer.skip(-parseFloat(secs));
}

//Buton pentru forward
forwardButton.onclick = function(){
    var secs = document.getElementById("jumping").value;
    wavesurfer.skip(parseFloat(secs));
}

//Incarcare fisier audio
wavesurfer.load('test.mp3');
