function playSong(filePath){
    localStorage.setItem( 'audioFile', filePath);
    location.replace("listen.jsp");
}

function filterFiles() {
    inputField = document.getElementById("filterInput");
    filterKeyword = inputField.value.toUpperCase();
    ul = document.getElementById("menu-content");
    li = ul.getElementsByTagName('li');
    for(i = 0; i < li.length; i++){
        txtValue = li[i].innerText;
        if(txtValue.toUpperCase().indexOf(filterKeyword) > -1){
            li[i].style.display = "";
        }else {
            li[i].style.display = "none";
        }
    }

}