const playButton = document.getElementById('start');
const resumeButton = document.getElementById('resume');
const rewindButton = document.getElementById('rewind');
const forwardButton = document.getElementById('fForward');
const stopButton = document.getElementById('stop');
const muteButton = document.getElementById('mute');
const playbackControl = document.getElementById('playback');
const playbackValue = document.getElementById('playback-value');
const pannerControl = document.querySelector('#panner');
const volumeControl = document.querySelector('#volume');
const regionDeleteButton = document.getElementById('regionDelete');
const divForm = document.getElementById('form');

//Crearea obiectului de tip wavesurfer
var wavesurfer = WaveSurfer.create({
    container: '#soundWave',
    progressColor: "#03a9f4",
    plugins: [
        WaveSurfer.regions.create()  //Plugin necesar pentru cue points
    ]
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

//Buton de mute, respectiv unmute
muteButton.onclick = function (){
    if(wavesurfer.getMute() === false)
         wavesurfer.setMute(true);
    else if(wavesurfer.getMute() === true)
         wavesurfer.setMute(false);
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

//Permite selectarea cu mouse a unei regiuni
wavesurfer.on('ready', function () {
    wavesurfer.enableDragSelection({
        color: "rgba(255, 0, 0, 0.5)"
    });
})

//Cand facem click pe regiune va porni sectiunea respectiva
wavesurfer.on('region-click', function (region) {
    region.play();

    //In momentul apasarii va aparea un formular pentru a completa o nota a regiunii selectate
    divForm.style.visibility = "visible";

    //In inputul de start si end vom pune datele de start si sfarsit al regiunii respective
    document.getElementById('startTime').value = parseInt(region.start);
    document.getElementById('endTime').value = parseInt(region.end);

    //Odata ce a iesit din regiune player-ul isi va continua rularea initiala
    region.once('out', function () {
        wavesurfer.play();
    })
})

//In momentul in care facem double click pe o regiune sectiunea va intra in loop
wavesurfer.on('region-dblclick', function (region) {
    region.playLoop();
})

//Permite stergerea tuturor regiunilor
regionDeleteButton.onclick = function(){
    wavesurfer.clearRegions();
}

//In momentul incarcarii paginii vom incarca fisierul audio ales
window.onload = function () {
    var myFile = localStorage['audioFile'];
    localStorage.removeItem( 'audioFile' );

    wavesurfer.load(myFile);
}

