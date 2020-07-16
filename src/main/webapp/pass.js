function playSong(filePath){
    localStorage.setItem( 'audioFile', filePath);
    location.replace("listen.jsp");
}