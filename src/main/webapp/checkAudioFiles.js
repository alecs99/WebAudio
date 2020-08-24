function checkAudio(){
    audioFiles = document.getElementsByName("audios");
    checkbox = document.getElementById("adminCheck");
    for(var i = 0; i < audioFiles.length; i++){
        audioFiles[i].checked = checkbox.checked;
    }
}