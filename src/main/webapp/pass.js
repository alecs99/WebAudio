function getEventTarget(e) {
    e = e || window.event;
    return e.target || e.srcElement;
}

var ul = document.getElementById('list');
ul.onclick = function(event) {
    var target = getEventTarget(event);
    localStorage.setItem( 'audioFile', target.innerHTML);
};