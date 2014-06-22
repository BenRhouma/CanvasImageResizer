
function upload() {
    //get image element
    var img = document.getElementById('image');
    var canvas = document.createElement('canvas');
    canvas.width = 400;
    canvas.height = 250;
    var ctx = canvas.getContext('2d');
    ctx.drawImage(img, 0, 0,400,250);
    var dataURL = canvas.toDataURL('image/jpeg');
    var data = dataURL.toString().substring(dataURL.indexOf(",") + 1);
    sendForm(data);
}

function sendForm(data) {
    var formData = new FormData();
    var oBlob = new Blob([data], {type: "application/octet-stream"});
    formData.append('img', oBlob);
    var xhr = new XMLHttpRequest();
    xhr.open('POST', '/ReiszeImage/upload/image', true);
    xhr.onload = function(e) {
        if (xhr.readyState === 4 && xhr.status === 200)
        {
            alert('success');
        }
        else {
            alert('upload error');
        }
    };
    xhr.send(formData);
}



