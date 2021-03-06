var httpRequest;
$(document).ready(function() {
    makeRequest('headerIndex.html');

    function makeRequest(url) {
        httpRequest = new XMLHttpRequest();

        if (!httpRequest) {
            alert('Giving up :( Cannot create an XMLHTTP instance');
            return false;
        }
        httpRequest.onreadystatechange = setContents;
        httpRequest.open('GET', url);
        httpRequest.send();
    }

    function setContents() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                $(".header").html(httpRequest.responseText);
            } else {
                alert('There was a problem with the request.');
            }
        }
    }
});