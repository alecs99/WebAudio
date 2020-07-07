const AudioContext = window.AudioContext || window.webkitAudioContext;

const audioContext = new AudioContext();
const audioElement = document.querySelector('audio');
const track = audioContext.createMediaElementSource(audioElement);



const playButton = document.getElementById('start');
const resumeButton = document.getElementById('resume');
const rewindButton = document.getElementById('rewind');
const forwardButton = document.getElementById('fForward');
const stopButton = document.getElementById('stop');
const playbackControl = document.getElementById('playback');
const playbackValue = document.getElementById('playback-value');

playButton.onclick = function(){
    if(resumeButton.dataset.playing  === 'false'){
        let secs = document.getElementById("jump").value;
        document.getElementById("jump"). disabled = true;
        audioElement.currentTime = secs;
        audioElement.play();
        this.setAttribute('disabled', 'disabled');
        resumeButton.dataset.playing = 'true';
        requestAnimationFrame(drawCanvas);
    }
}

stopButton.onclick = function () {
    if(resumeButton.dataset.playing  === 'true'){
        audioElement.currentTime = 0;
        audioElement.pause();
        playButton.removeAttribute('disabled')
        playButton.setAttribute('enabled', 'enabled');
        document.getElementById("jump"). disabled = false;
        resumeButton.dataset.playing = 'false';
    }
}

resumeButton.addEventListener('click', function() {

    if (audioContext.state === 'suspended') {
        audioContext.resume();
    }

    if (this.dataset.playing === 'false') {
        audioElement.play();
        this.dataset.playing = 'true';
    } else if (this.dataset.playing === 'true') {
        audioElement.pause();
        this.dataset.playing = 'false';
    }

}, false);

audioElement.addEventListener('ended', () => {
    resumeButton.dataset.playing = 'false';
    playButton.removeAttribute('disabled')
    playButton.setAttribute('enabled', 'enabled');
    document.getElementById("jump"). disabled = false;
}, false);

const gainNode = audioContext.createGain();
track.connect(gainNode).connect(audioContext.destination);

const volumeControl = document.querySelector('#volume');

volumeControl.addEventListener('input', function() {
    gainNode.gain.value = this.value;
}, false);

const pannerOptions = { pan: 0 };
const panner = new StereoPannerNode(audioContext, pannerOptions);

const pannerControl = document.querySelector('#panner');

pannerControl.addEventListener('input', function() {
    panner.pan.value = this.value;
}, false);
playbackControl.oninput = function(){
    audioElement.playbackRate = playbackControl.value;
    playbackValue.innerHTML = playbackControl.value;
}
document.addEventListener("keydown", function (event) {
    let secs = document.getElementById("jumping").value;
    if(event.which === 39){
        audioElement.currentTime = audioElement.currentTime + parseFloat(secs);
    } else if (event.which === 37){
        audioElement.currentTime = audioElement.currentTime - parseFloat(secs);
    }

})
rewindButton.onclick = function(){
    var secs = document.getElementById("jumping").value;
    console.log(secs);

    audioElement.currentTime = audioElement.currentTime - parseFloat(secs);
}
forwardButton.onclick = function(){
    var secs = document.getElementById("jumping").value;
    console.log(audioElement.currentTime);
    audioElement.currentTime = audioElement.currentTime + parseFloat(secs);
    console.log(audioElement.currentTime);
}

track.connect(gainNode).connect(panner).connect(audioContext.destination);